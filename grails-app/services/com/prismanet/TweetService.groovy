package com.prismanet

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
import com.prismanet.sentiment.Opinion
import com.prismanet.sentiment.OpinionValue

class TweetService extends GenericService{

	def sessionFactory
	def propertyInstanceMap = org.codehaus.groovy.grails.plugins.DomainClassGrailsPlugin.PROPERTY_INSTANCE_MAP
	//boolean transactional = false
	
	TweetService(){
		super(Tweet, new TweetAttributeContext())
	}
	
	def saveTweets(def tweets){
		def index = 0
		def error = false

		for (BasicDBObject tweetObj : tweets){

			JSONObject obj = new JSONObject(tweetObj)
			Status status = new StatusJSONImpl(obj)
			try {
				
				index++
				
				def start = System.currentTimeMillis()
				Author author = Author.findByAccountName("@"+status.getUser().getScreenName())
				if (!author){
					author = new Author(accountName:"@"+status.getUser().getScreenName(), followers:status.getUser().getFollowersCount(), sex: Sex.M, userSince:status.getUser().getCreatedAt(), profileImage:status.getUser().getProfileImageURL()).save(validate:false)
				}else{
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
				
				def List<Concept> concepts = Concept.list()
				concepts.each(){ concept->
					if (concept.testAddTweet(tweet)){
//									print "valido para concepto: " +  concept
						if (!tweet.id){
							tweet.save(validate:false)
//							println "Tweet guardado con ID :  " + tweet.id
						}
						if (!tweet.id)
							throw RuntimeException("Tweet no guardado")
//						print "Tiempo tweet: " + (start - System.currentTimeMillis())/1000 + " segundos"
//						start = System.currentTimeMillis()
						concept.doAddToTweets(tweet)
//						print "Tiempo concept: " + (start - System.currentTimeMillis())/1000 + " segundos"
						
					}
				}
//				if (index % 20 == 0) cleanUpGorm()

			} catch (Exception e) {
				error = true
				print "Tweet Fallido: " + status.getId() +"-"+status.getText()
				print e.getCause()
				print e.getStackTrace()
				
				
			}
		}
		if (!error)
			cleanUpGorm()
	}
	
	
	def cleanUpGorm() {
		try {
			sessionFactory.currentSession.flush()
			sessionFactory.currentSession.clear()
			propertyInstanceMap.get().clear()
		} catch (Exception e) {
			print e.getCause()
		}
		
		
	}

	
	def OpinionValue getOpinion(User user, Concept concept){
		def opinion = Opinion.findByUserandConcept(user,concept)
		if (!opinion)
			return OpinionValue.NEUTRAL
		
		opinion.value
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
	
	
	
	
	
	
	private boolean isNumericalArgument(String argument) {
		def String arguments = argument.split(",");
		boolean isNumericalArgument = true;
		for (String arg : arguments) {
			try {
				Integer.parseInt(arg);
			} catch (NumberFormatException nfe) {
				isNumericalArgument = false;
				break;
			}
		}
		return isNumericalArgument;
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
			
		def auxList =	list(Tweet, new TweetAttributeContext(), filters, parameters, [[attribute:"id",value:OrderType.DESC]])
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
