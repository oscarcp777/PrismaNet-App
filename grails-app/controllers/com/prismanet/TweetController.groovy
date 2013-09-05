package com.prismanet

import org.springframework.transaction.annotation.Transactional;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

class TweetController {
	static scaffold = true

	def quartzScheduler
	def tweetService
	
	def showJobState = {
		def status = ""
		switch(params.operation) {
			case 'pause':
				quartzScheduler.pauseAll()
				status = "Paused Jobs"
				break
			case 'resume':
				quartzScheduler.resumeAll()
				status = "Resumed Jobs"
				break
		}
		return [ status: status ]
	}

}
