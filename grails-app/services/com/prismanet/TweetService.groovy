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

import com.prismanet.GenericService.FilterType
import com.prismanet.context.TweetAttributeContext
import com.prismanet.sentiment.Opinion
import com.prismanet.sentiment.OpinionValue

class TweetService extends GenericService{

	def sessionFactory
	
	TweetService(){
		super(Tweet, new TweetAttributeContext())
	}
	
	/*def executeJob(StatusListener listener){
		TweetsTwitterJob job = new TweetsTwitterJob()
		job.execute(listener)
	}*/
	
	@Transactional
	def saveTweet(Status status){

		println ""
		println ""
//		print "nombre: " + status.getUser().getScreenName()
//		print "seguidores: " + status.getUser().getFollowersCount()  
//		print "fecha creacion: " + status.getUser().getCreatedAt()
		def start = System.currentTimeMillis()
		Author author = Author.findByAccountName("@"+status.getUser().getScreenName())
		if (!author){
			author = new Author(accountName:"@"+status.getUser().getScreenName(), followers:status.getUser().getFollowersCount(), sex: Sex.M, userSince:status.getUser().getCreatedAt(), profileImage:status.getUser().getProfileImageURL()).save(flush:true)
		}
		print "Tiempo autor: " + (start - System.currentTimeMillis())/1000 + " segundos"
		start = System.currentTimeMillis()
		Tweet tweet = new Tweet(content:status.getText(),author:author, created:status.getCreatedAt(), tweetId:status.getId())
		try {
			def List<Concept> concepts = Concept.list()
			concepts.eachWithIndex(){ concept, index ->
				if (concept.testAddTweet(tweet)){
					print "valido para concepto: " +  concept
					tweet.save(flush:true)
					if (!tweet.id)
						throw RuntimeException("Tweet no guardado")
					print "Tiempo tweet: " + (start - System.currentTimeMillis())/1000 + " segundos"
					start = System.currentTimeMillis()
					concept.doAddToTweets(tweet)
					print "Tiempo concept: " + (start - System.currentTimeMillis())/1000 + " segundos"
					println "Tweet guardado con ID :  " + tweet.id
				}
				if (index % 20 == 0) cleanUpGorm()
				
			}
		} catch (Exception e) {
			print e.getCause()
			print "Tweet Fallido: " + status.getId() +"-"+status.getText()
//			tweet.delete()
		}
		
	}
	
	
	def cleanUpGorm() {
		sessionFactory.currentSession.flush()
//		sessionFactory.currentSession.clear()
//		DomainClassGrailsPlugin.PROPERTY_INSTANCE_MAP?.get()?.clear()
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
			
		def auxList =	list(filters, parameters)
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
		println usersName.toListString()
		Twitter twitter = new TwitterFactory().getInstance();
		ResponseList<twitter4j.User> users = twitter.lookupUsers((String[])usersName.toArray());
		for (twitter4j.User user : users) {
			Tweet tweet=tweetsTemp.find {it.author.accountNameSingle == user.screenName}
			tweet.author.profileImage=user.profileImageURL
		}
	}
}
