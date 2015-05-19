package com.prismanet

import groovy.sql.Sql

import org.springframework.transaction.annotation.Transactional
import org.springframework.util.StopWatch

import twitter4j.FilterQuery
import twitter4j.JSONObject
import twitter4j.ResponseList
import twitter4j.Status
import twitter4j.StatusJSONImpl
import twitter4j.StatusListener
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.TwitterStream
import twitter4j.TwitterStreamFactory

import com.mongodb.BasicDBObject
import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.OrderType
import com.prismanet.context.Filter
import com.prismanet.context.TweetAttributeContext
import com.prismanet.exception.ApplicationException
import com.prismanet.sentiment.Opinion
import com.prismanet.sentiment.OpinionValue

class TweetService extends MentionService{
	
	def opinionService

	TweetService(){
		super(Tweet, new TweetAttributeContext())
	}
	
	@Transactional
	def saveTweets(def tweets){
		def List<Concept> concepts = Concept.list()
		for (BasicDBObject tweetObj : tweets){
			JSONObject obj = new JSONObject(tweetObj)
			Status status = new StatusJSONImpl(obj)
			StopWatch timer
			def retCount = 0
			def favCount = 0
			def retweet = false
			def retweetId = null
			if (status.getRetweetedStatus()){
				retCount = status.getRetweetedStatus().getRetweetCount()
				favCount = status.getRetweetedStatus().getFavoriteCount()
				retweet = true
				retweetId = status.getRetweetedStatus().getId()
			}
			Tweet tweet = new Tweet(content:status.getText(),
			created:status.getCreatedAt(),
			tweetId:status.getId(),
			retweetCount:retCount,
			favoriteCount:favCount,
			retweet:retweet,
			retweetId:retweetId)

			concepts.each(){ concept->
				timer = new StopWatch()
				timer.start()
		
				if (evaluateLanguage(concept, status))
				if (evaluateCountry(concept, status))
				if (concept.testAddTweet(tweet)){
					log.info "valido para concepto: " +  concept
					if (!tweet.id){
						Author author = TwitterAuthor.findByAccountName("@"+status.getUser().getScreenName())
						if (!author){
							author = new TwitterAuthor(name: status.getUser().getName(), twitterAuthorId: status.getUser().getId(), accountName:"@"+status.getUser().getScreenName(), followers:status.getUser().getFollowersCount(), sex: Sex.M, userSince:status.getUser().getCreatedAt(), profileImage:status.getUser().getProfileImageURL()).save(validate:false)
							if (!author.id){
								author.discard()
								throw ApplicationException.create(author)
							}
						}
						else{
							author.followers = status.getUser().getFollowersCount()
							author.profileImage = status.getUser().getProfileImageURL()
						}
						tweet.author = author
						tweet.save(validate:false)
						log.info "Tweet guardado con ID :  " + tweet.id
					}
					if (!tweet.id){
						tweet.discard()
						timer.stop()
						throw ApplicationException.create(tweet)
					}
					def sql = new Sql(sessionFactory.currentSession.connection())
					sql.execute("insert into concept_mentions values(?,?)", [concept.id, tweet.id])
//					concept.doAddToMentions(tweet)
					if (concept.twitterSetup.isPositiveHashtag(tweet)){
						opinionService.addOpinion(concept, tweet, OpinionValue.POSITIVE)
					}
					if (concept.twitterSetup.isNegativeHashtag(tweet)){
						opinionService.addOpinion(concept, tweet, OpinionValue.NEGATIVE)
					}
				}
				timer.stop()
				if (timer.getTotalTimeMillis()>100)
					log.info "tiempo tweet: " + timer.getTotalTimeMillis()
			}
			
		}
		cleanUpGorm()
	}
	
	private boolean evaluateLanguage(Concept concept, Status tweet){
		boolean result = false
		log.info "status.lang " + tweet.getLang()
		log.info "concept.lang " + concept.lang?.code
		if (!concept.lang || tweet.getLang() == concept.lang.code)
			result = true
		log.info "Evaluacion lenguaje: " + result
		result
	}
	
	private boolean evaluateCountry(Concept concept, Status tweet){
		boolean result = false
		log.info "status.timezone " + tweet.getUser().getTimeZone()
		log.info "status.place " + tweet.getPlace()
		log.info "concept.country " + concept.country?.code
		if (!concept.country)
			result = true
		else	
			if (tweet.getPlace()){
				if(tweet.getPlace().getCountryCode() == concept.country?.code)
					result = true
			}else{	
				if (!tweet.getUser().getTimeZone() || 
					(concept.country?.code == 'AR' &&
					(tweet.getUser().getTimeZone().trim() == 'Buenos Aires' ||
					tweet.getUser().getTimeZone().trim() == 'Brasilia' ||
					tweet.getUser().getTimeZone().trim() == 'America/Argentina/Buenos_Aires')))
						result = true
			}		
		log.info "Evaluacion pais: " + result
		result
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
			log.info "Args: " + args
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
		filters.add(new Filter(attribute:"sourceType",value:Tweet.class, type:FilterType.EQ))
		getMentions(filters, parameters, new TweetAttributeContext(), Tweet)
	}
	
	def getSamplingTweets(filters, parameters, SamplingType type){
		getSamplingTweets(filters, parameters, type, 50)
	}
	
	def getSamplingTweets(filters, parameters, SamplingType type, Integer maxSamplingSize){
		
		def tweets = getMentions(filters, parameters, new TweetAttributeContext(), Tweet)
		parameters.max = maxSamplingSize
		
		switch (type){
			case SamplingType.RANDOM:
				def resultList = []
				def samplingSize = tweets.totalCount != 0 ? getSamplingSize(tweets.totalCount) : 0
				Random rand = new Random(tweets.totalCount)
				def randomIntegerList = []
				if (samplingSize > 0)
				(1..samplingSize).each {
					resultList.add(tweets.resultList.get(rand.nextInt(tweets.totalCount)))
				}
			
				//codigo para validar que sea diferente
				//		resultList.sort{it.tweet.id}
				//		resultList.eachWithIndex{ obj , i->
				//			println i +"-"+ obj.tweet.id
				//		}
				return [resultList:resultList,totalSampling:samplingSize,totalDemo:tweets.totalCount]
			break;
			case SamplingType.TOP_RELEVANT_AUTHORS:
				def samplingTweets = getMentions(filters, parameters, new TweetAttributeContext(), Tweet, [[attribute:"authorFollowers",value:OrderType.DESC]])
				return [resultList:samplingTweets.resultList,totalSampling:samplingTweets.resultList.list.size(),totalDemo:tweets.totalCount]
			break;
			case SamplingType.TOP_RETWEETS:
				def samplingTweets = getMentions(filters, parameters, new TweetAttributeContext(), Tweet, [[attribute:"retweetCount",value:OrderType.DESC]])
				return [resultList:samplingTweets.resultList,totalSampling:samplingTweets.resultList.list.size(),totalDemo:tweets.totalCount]
			break;
			case SamplingType.TOP_FAVS:
				def samplingTweets = getMentions(filters, parameters, new TweetAttributeContext(), Tweet, [[attribute:"favoriteCount",value:OrderType.DESC]])
				return [resultList:samplingTweets.resultList,totalSampling:samplingTweets.resultList.list.size(),totalDemo:tweets.totalCount]
			break;
		}
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
			
		}
	}
	def getOpinionsByTweets(tweets){
		def c = Opinion.createCriteria()
		def results = c.list {
			'in'("mention",tweets)
		}
		results
	}
	def loadAvatarUsers(tweets){
		
		def usersName=[]
		def tweetsTemp=[]
		tweets.each {it -> 
			usersName.add(it.tweet.author.accountNameSingle)
			tweetsTemp.add(it.tweet)
		}
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
			
		}
	}
	
	public String getDateGroupProperty(DateServiceType type){
		switch (type) {
			case DateServiceType.BY_MINUTE:
				return "tweetByMinute"
			case DateServiceType.BY_HOUR:
				return "tweetByHour"
			case DateServiceType.BY_DATE:
				return "tweetCreated"
			case DateServiceType.BY_MONTH:
				return "tweetPeriod"
		}
		return null
	}
	
	public enum SamplingType{
		RANDOM,
		TOP_RELEVANT_AUTHORS,
		TOP_RETWEETS,
		TOP_FAVS;
	}
}
