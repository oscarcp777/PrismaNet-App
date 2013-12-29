package com.prismanet

import org.springframework.transaction.annotation.Transactional;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import grails.plugins.springsecurity.Secured
@Secured(['ROLE_USER'])
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
	
	
	def list(Integer max) {
		params.max = Math.min(max ?: 3, 100)
		def tweets = tweetService.getTweets([],params)
		[tweetInstanceList: tweets.resultList, tweetInstanceTotal: tweets.totalCount]
	}

}
