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
		println "Tweet NO guardado"
		Tweet tweet = new Tweet(content:status.getText());
		println "Llega"
		try {
			tweet.save(flush: true)
		} catch (Exception e) {
			print e.getMessage()
		}
		println "Llega2"
		println "Tweet guardado con ID :  " + tweet.id
	}
	
	
	
	
	def streamConection(StatusListener listener){
		String args = "@CFKArgentina"
		
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
