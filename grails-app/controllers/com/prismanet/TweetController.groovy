package com.prismanet

import grails.plugins.springsecurity.Secured

import com.prismanet.GenericService.FilterType
import com.prismanet.context.Filter
import com.prismanet.sentiment.Opinion
import com.prismanet.sentiment.OpinionValue
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
		def filters=[]
		Filter filter = new Filter(attribute:"conceptsId",value: params.id.toLong(), type:FilterType.EQ)
		filters.add(filter)
		def tweets = tweetService.getTweets(filters,params)
		Concept concept = Concept.get(params.id)
		
		if(!grailsApplication.config.grails.twitter.offline)
			tweetService.loadAvatarUsers(tweets.resultList)
		[tweetInstanceList: tweets.resultList, tweetInstanceTotal: tweets.totalCount, concept: concept]
	}
	
	
	def saveOpinion(){
		def conceptId = params.conceptId
		def tweetId = params.tweetId
		if (!conceptId){
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'concept.label', default: 'Concept'), conceptId])
			redirect(action: "list")
			return
		}
		if (!tweetId){
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tweet.label', default: 'Tweet'), conceptId])
			redirect(action: "list")
			return
		}
		def concept = Concept.get(conceptId)
		def tweet = Tweet.get(tweetId)
		Opinion opinion = Opinion.findByConceptAndTweet(concept, tweet)
		if (!opinion){
			opinion = new Opinion(concept:concept,tweet:tweet,value:getValue(params.value))
		}else{
			opinion.value = getValue(params.value) ?: OpinionValue.NEUTRAL
		}
		if (!opinion.save(flush: true)) {
			print "NO FUE CREADO: " + opinion.errors
			return
		}
		render "ok"
	}
	
	private OpinionValue getValue(value){
		if (value == "POS")
			return OpinionValue.POSITIVE
		if (value == "NEG")
			return OpinionValue.NEGATIVE
		
		return OpinionValue.NEUTRAL
	}

	
}
