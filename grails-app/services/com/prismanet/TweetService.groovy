package com.prismanet

import org.springframework.transaction.annotation.Transactional

import twitter4j.FilterQuery
import twitter4j.ResponseList
import twitter4j.Status
import twitter4j.StatusListener
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.TwitterStream
import twitter4j.TwitterStreamFactory
import twitter4j.internal.json.StatusJSONImpl
import twitter4j.internal.org.json.JSONObject

import com.mongodb.BasicDBObject
import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.OrderType
import com.prismanet.context.TweetAttributeContext
import com.prismanet.exception.ApplicationException
import com.prismanet.sentiment.Opinion
import com.prismanet.sentiment.OpinionValue

class TweetService extends MentionService{

	TweetService(){
		super(Tweet, new TweetAttributeContext())
	}
	
	@Transactional
	def saveTweets(def tweets){
		def List<Concept> concepts = Concept.list()
		for (BasicDBObject tweetObj : tweets){
			JSONObject obj = new JSONObject(tweetObj)
			Status status = new StatusJSONImpl(obj)
			//def start = System.currentTimeMillis()
			Author author = TwitterAuthor.findByAccountName("@"+status.getUser().getScreenName())
			if (!author){
				author = new TwitterAuthor(name: status.getUser().getName(), twitterAuthorId: status.getUser().getId(), accountName:"@"+status.getUser().getScreenName(), followers:status.getUser().getFollowersCount(), sex: Sex.M, userSince:status.getUser().getCreatedAt(), profileImage:status.getUser().getProfileImageURL()).save(validate:false)
			}
			else{
				author.followers = status.getUser().getFollowersCount()
				author.profileImage = status.getUser().getProfileImageURL()
			}
			//				print "Tiempo autor: " + (start - System.currentTimeMillis())/1000 + " segundos"
			//				start = System.currentTimeMillis()
			def retCount = 0
			def favCount = 0
			if (status.getRetweetedStatus()){
				retCount = status.getRetweetedStatus().getRetweetCount()
				favCount = status.getRetweetedStatus().getFavoriteCount()
			}
			Tweet tweet = new Tweet(content:status.getText(),
			author:author,
			created:status.getCreatedAt(),
			tweetId:status.getId(),
			retweetCount:retCount,
			favoriteCount:favCount)

			concepts.each(){ concept->
				if (concept.testAddTweet(tweet)){
					log.info "valido para concepto: " +  concept
					if (!tweet.id){
						tweet.save(validate:false)
						log.info "Tweet guardado con ID :  " + tweet.id
					}
					if (!tweet.id)
						throw ApplicationException.create(tweet)
					//print "Tiempo tweet: " + (start - System.currentTimeMillis())/1000 + " segundos"
					//start = System.currentTimeMillis()
					concept.doAddToTweets(tweet)
					//print "Tiempo concept: " + (start - System.currentTimeMillis())/1000 + " segundos"
				}
			}
		}
		cleanUpGorm()
	}
	
	
	def streamConection(StatusListener listener){
		String args;
		Concept.withTransaction{
			def List<Concept> concepts = Concept.list()
			for (Concept concept in concepts){
				for (String twitterAccount in concept.twitterSetup.includedAccounts.split(',')) {
					if (!args)
						args = twitterAccount
					else
						args += "," + twitterAccount
				}

			}
			print "Args: " + args
		}
		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
		twitterStream.addListener(listener);
		ArrayList<Long> follow = new ArrayList<Long>();
		ArrayList<String> track = new ArrayList<String>();
		//		for (String arg : args) {
		if (isNumericalArgument(args)) {
			for (String id : args.split(",")) {
				follow.add(Long.parseLong(id));
			}
		} else {
			track.addAll(Arrays.asList(args.split(",")));
		}
		//		}
		long[] followArray = new long[follow.size()];
		for (int i = 0; i < follow.size(); i++) {
			followArray[i] = follow.get(i);
		}
		String[] trackArray = track.toArray(new String[track.size()]);

		// filter() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
		twitterStream.filter(new FilterQuery(0, followArray, trackArray));
	}
	
	def getTweets(filters, parameters){
		
		def conceptId
		filters.each {
			if (it.attribute == "conceptsId" && it.type ==  FilterType.EQ)
				conceptId = it.value
		}
		def resultList = []
		Concept concept
		if (conceptId)
			concept = Concept.get(conceptId)
			
		def auxList =	list(Tweet.class, new TweetAttributeContext(), filters, parameters, [[attribute:"id",value:OrderType.DESC]])
		for (tweet in auxList.results){
			def opValue
			if (conceptId){
				opValue = OpinionValue.NEUTRAL
				Opinion op = Opinion.findByTweetAndConcept(tweet,concept)
				if (op)
					opValue = op.value
			}
			resultList << [tweet:tweet, value:opValue]
		}
		
		[resultList:resultList,totalCount:auxList.totalCount]
		
	}
	
	def getSamplingTweets(filters, parameters){
		def tweets = getTweets(filters, [:])
		print "size: "+ tweets.resultList.size()
		def resultList = []
		def samplingSize = getSamplingSize(tweets.totalCount)
		print "tamaño muestra: " + samplingSize
		Random rand = new Random()
		def randomIntegerList = []
		(1..samplingSize).each {
			resultList.add(tweets.resultList.get(rand.nextInt(tweets.totalCount)))
		}
		[resultList:resultList,totalCount:samplingSize]
	}
	
	private def getSamplingSize(poblationNumber){
		def numerator = poblationNumber*1.96**2*0.05*0.95
		numerator.divide(0.03**2*(poblationNumber-1)+1.96**2*0.05*0.95, 0, BigDecimal.ROUND_HALF_UP)
	}
	def loadDataAuthors(authors){
		
		def usersName=[]
		authors.each {it ->
			usersName.add(it.accountNameSingle)
		}
		Twitter twitter = new TwitterFactory().getInstance();
		ResponseList<twitter4j.User> users = null
		if (usersName.size()>0)
			users = twitter.lookupUsers((String[])usersName.toArray());
		if (users != null)
		for (twitter4j.User user : users) {
			TwitterAuthor author=authors.find {it.accountNameSingle == user.screenName}
			if (author){
				author.profileImage=user.profileImageURL
				author.followers=user.followersCount
				author.following=user.friendsCount
				author.tweetsCount=user.statusesCount
				author.name=user.name
			}
			if (author)
				author.save(flush:true)
			
		}
	}
	def loadAvatarUsers(tweets){
		
		def usersName=[]
		def tweetsTemp=[]
		tweets.each {it -> 
			usersName.add(it.tweet.author.accountNameSingle)
			tweetsTemp.add(it.tweet)
		}
//		println usersName.toListString()
		Twitter twitter = new TwitterFactory().getInstance();
		ResponseList<twitter4j.User> users = null
		if (usersName.size()>0)
			users = twitter.lookupUsers((String[])usersName.toArray());
		if (users != null)	
		for (twitter4j.User user : users) {
			Tweet tweet=tweetsTemp.find {it.author.accountNameSingle == user.screenName}
			if (tweet && tweet.author){
				tweet.author.profileImage=user.profileImageURL
				tweet.author.followers=user.followersCount
				tweet.author.following=user.friendsCount
				tweet.author.tweetsCount=user.statusesCount
				tweet.author.name=user.name
			}
			if (tweet)
				tweet.save(flush:true)
			
		}
	}
}
