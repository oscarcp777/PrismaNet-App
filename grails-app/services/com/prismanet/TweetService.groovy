package com.prismanet

import twitter4j.StallWarning
import twitter4j.Status
import twitter4j.StatusDeletionNotice
import twitter4j.StatusListener

import com.prismanet.twitter.job.TweetsTwitterJob

class TweetService {

	
	
	def executeJob(StatusListener listener){
		TweetsTwitterJob job = new TweetsTwitterJob()
		job.execute(listener)
	}
	
	
	
}
