package com.prismanet

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;



class TweetJob implements StatusListener{

	def tweetService
	def group = "tweetsJobs"

	static triggers = {
		simple repeatInterval: 600000l, repeatCount:-1 , startDelay: 10000
	}

	def execute() {
		print "Starting the Tweets job."
//		tweetService.streamConection(this)
		println "Running Job!" + new Date()
	}
	
	
	
	@Override
	public void onStatus(Status status) {
		System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
		tweetService.saveTweet(status)
	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
		System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
	}

	@Override
	public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
		System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
	}

	@Override
	public void onScrubGeo(long userId, long upToStatusId) {
		System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
	}

	@Override
	public void onStallWarning(StallWarning warning) {
		System.out.println("Got stall warning:" + warning);
	}

	@Override
	public void onException(Exception ex) {
		ex.printStackTrace();
	}
}
