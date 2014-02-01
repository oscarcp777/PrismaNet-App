package com.prismanet

import grails.plugins.springsecurity.Secured

import com.prismanet.GenericService.FilterType
import com.prismanet.context.Filter
import com.prismanet.sentiment.Opinion
import com.prismanet.sentiment.OpinionValue
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils
@Secured(['ROLE_USER'])
class TweetController extends GenericController{
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
		
		log.debug "tweetController->list params: " + params
		print params
		
		def filters=[]
//		filters = loadFilters(params, new TweetAttributeContext())
		if (params["conceptsId"])
			filters.add(new Filter(attribute:"conceptsId",value: params.conceptsId.toLong(), type:FilterType.EQ))
		
		if (params["tweetMinute"]){
			def cal = new GregorianCalendar()
			cal.setTimeInMillis(params["tweetMinute"] as Long)
			def minuteFilter=DateUtils.getDateFormat(DateTypes.MINUTE_PERIOD, cal.time)
			filters.add(new Filter(attribute:"tweetMinute",value: minuteFilter, type:FilterType.EQ))
		}
		
		if (params["tweetCreated"]){
			def cal = new GregorianCalendar()
			cal.setTimeInMillis(params["tweetCreated"] as Long)
			def day = DateUtils.getDateFormat(DateTypes.DAY_PERIOD, cal.time)
			filters.add(new Filter(attribute:"tweetCreated",value: day, type:FilterType.EQ))
		}
		
		if (params["tweetHour"]){
			def cal = new GregorianCalendar()
			cal.setTimeInMillis(params["tweetHour"] as Long)
			def hourFilter=DateUtils.getDateFormat(DateTypes.HOUR_PERIOD, cal.time)
			filters.add(new Filter(attribute:"tweetHour",value: hourFilter, type:FilterType.EQ))
		}
		
		params.max = Math.min(max ?: 6, 100)
//		def parameters = cleanParams(params)
		
//		Filter filter = new Filter(attribute:"conceptsId",value: params.id.toLong(), type:FilterType.EQ)
//		filters.add(filter)
		def tweets = tweetService.getTweets(filters,params)
		Concept concept = Concept.get(params.conceptsId.toLong())
		print tweets.totalCount
		if(!grailsApplication.config.grails.twitter.offline)
			tweetService.loadAvatarUsers(tweets.resultList)
		[tweetInstanceList: tweets.resultList, tweetInstanceTotal: tweets.totalCount, concept: concept, tweetMinute:params["tweetMinute"]]
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
