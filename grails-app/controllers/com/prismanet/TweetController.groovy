package com.prismanet

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService;

import com.prismanet.GenericService.FilterType
import com.prismanet.context.Filter
import com.prismanet.sentiment.Opinion
import com.prismanet.sentiment.OpinionValue
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils
@Secured(['ROLE_USER'])
class TweetController extends GenericController{
	static scaffold = true
	SpringSecurityService springSecurityService;
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
		Concept concept =chooseConcept(params)
		def filters = loadTweetFilters()
		params.max = Math.min(max ?: 6, 100)
		def tweets = tweetService.getTweets(filters,params)
		def relevantWords = tweetService.getRelevantWords(filters)
		if(!grailsApplication.config.grails.twitter.offline)
			tweetService.loadAvatarUsers(tweets.resultList)
		[tweetInstanceList: tweets.resultList, tweetInstanceTotal: tweets.totalCount, concept: concept, tweetMinute:params["tweetMinute"], 
			relevantWords:relevantWords, dateCreated:params.dateCreated]
	}
	
	def randomList(){
		def filters = loadTweetFilters()
	}
	
	def wordsCloud() {
		log.debug "tweetController->wordsCloud params: " + params
		Concept concept =chooseConcept(params)
		
		def filters = loadTweetFilters()
		def listWords=[]
		int counter=0
		def relevantWords = tweetService.getRelevantWords(filters)
		
		def maxPercent = 40, minPercent = 20
		def max = relevantWords.get(0).size, min = relevantWords.get(relevantWords.size -1).size
		def multiplier
		if (max != min)
			multiplier = (maxPercent-minPercent)/(max-min)
		else	
			multiplier = (maxPercent-minPercent)
		
		for (var in relevantWords) {
			int size = minPercent + ((max-(max-(var.size-min)))*multiplier);
			listWords.add([var.text,size]);
		}
		def mapJson=[div:params['div'],json:listWords]
		render  mapJson as JSON
	}
	
	private def loadTweetFilters(){
		def filters=[]

		if (params["conceptsId"])
			filters.add(new Filter(attribute:"conceptsId",value: params.conceptsId.toLong(), type:FilterType.EQ))

		if (params["dateMinute"]){
			def cal = new GregorianCalendar()
			cal.setTimeInMillis(params["dateMinute"] as Long)
			def minuteFilter=DateUtils.getDateFormat(DateTypes.MINUTE_PERIOD, cal.time)
			filters.add(new Filter(attribute:"dateMinute",value: minuteFilter, type:FilterType.EQ))
		}

		if (params["dateCreated"]){
			def cal = new GregorianCalendar()
			cal.setTimeInMillis(params["dateCreated"] as Long)
			def day = DateUtils.getDateFormat(DateTypes.DAY_PERIOD, cal.time)
			filters.add(new Filter(attribute:"dateCreated",value: day, type:FilterType.EQ))
		}

		if (params["dateHour"]){
			def cal = new GregorianCalendar()
			cal.setTimeInMillis(params["dateHour"] as Long)
			def hourFilter=DateUtils.getDateFormat(DateTypes.HOUR_PERIOD, cal.time)
			filters.add(new Filter(attribute:"dateHour",value: hourFilter, type:FilterType.EQ))
		}
		filters
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
		Opinion opinion = Opinion.findByConceptAndMention(concept, tweet)
		if (!opinion){
			opinion = new Opinion(concept:concept,mention:tweet,value:getValue(params.value))
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
