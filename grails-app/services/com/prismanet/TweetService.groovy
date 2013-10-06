package com.prismanet

import org.springframework.transaction.annotation.Transactional;

import twitter4j.FilterQuery
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener
import twitter4j.TwitterStream
import twitter4j.TwitterStreamFactory

import com.prismanet.twitter.job.TweetsTwitterJob

class TweetService{

	
	
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
		Author author = Author.findByAccountName("@"+status.getUser().getScreenName())
		if (!author){
			author = new Author(accountName:"@"+status.getUser().getScreenName(), followers:status.getUser().getFollowersCount(), sex: Sex.M, userSince:status.getUser().getCreatedAt(), profileImage:status.getUser().getProfileImageURL()).save()
		}
		Tweet tweet = new Tweet(content:status.getText(),author:author, created:status.getCreatedAt(), tweetId:status.getId())
		try {
			def List<Concept> concepts = Concept.list()
			for (Concept concept in concepts){
				if (concept.testAddTweet(tweet)){
					print "valido para concepto: " +  concept
					tweet.save()
					concept.addToTweets(tweet)
					println "Llega2"
					println "Tweet guardado con ID :  " + tweet.id
					break
				}
			}
		} catch (Exception e) {
			print e.getStackTrace()
//			tweet.delete()
		}
		
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
	
}
