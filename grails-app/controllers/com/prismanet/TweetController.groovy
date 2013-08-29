package com.prismanet

import org.springframework.transaction.annotation.Transactional;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

class TweetController {
    static scaffold = true
	def tweetService
	
	static allowedMethods = [save: "POST"]
	
	def executeJob = {
		
		StatusListener listener = new StatusListener() {
			@Override
			@Transactional
			public void onStatus(Status status) {
				System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
				println "Tweet NO guardado"
				Tweet tweet = new Tweet();
				tweet.setContent("yyyyy")
				println "Llega"
				tweet.save(flush: true)
				println "Llega2"
				println "Tweet guardado con ID :  " + tweet.id
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
		};

		
		tweetService.executeJob(listener)
	}
}
