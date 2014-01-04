package com.prismanet

import grails.plugins.springsecurity.Secured

import com.prismanet.GenericService.FilterType
import com.prismanet.context.Filter
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
		params.max = Math.min(max ?: 6, 100)
		def tweets = tweetService.getTweets([],params)
		if(!grailsApplication.config.grails.twitter.offline)
		tweetService.loadAvatarUsers(tweets.resultList)
		[tweetInstanceList: tweets.resultList, tweetInstanceTotal: tweets.totalCount]
	}
	
}
