package com.prismanet

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService

import com.prismanet.sentiment.Opinion
import com.prismanet.sentiment.OpinionValue
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils
@Secured(['ROLE_USER'])
class TweetController extends MentionController{
	static scaffold = true
	SpringSecurityService springSecurityService;
	def quartzScheduler
	def tweetService
	
	protected MentionService getService(){
		tweetService
	}
	
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
		
		log.info "tweetController->list params: " + params
		Concept concept =chooseConcept(params)
		def filters = loadMentionFilters()
		params.max = Math.min(max ?: 6, 100)
		def tweets = tweetService.getTweets(filters,params)
		def relevantWords = tweetService.getRelevantWords(loadSolrFilters())
		if(!grailsApplication.config.grails.twitter.offline)
			tweetService.loadAvatarUsers(tweets.resultList)
		
		if(params.offset==null){
		session.relevantWords=tweetService.getRelevantWords(loadSolrTweetFilters())
		}
		
		[tweetInstanceList: tweets.resultList, tweetInstanceTotal: tweets.totalCount, concept: concept, tweetMinute:params["tweetMinute"], 
			relevantWords:session.relevantWords, dateCreated:params.dateCreated]
	}
	
	def randomList(){
		def filters = loadMentionFilters()
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
			log.info "NO FUE CREADO: " + opinion.errors
			return
		}
		render "ok"
	}
	def samplingStep1(){
		def container = params.div
		Date dateFrom = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateFrom)
		Date dateTo = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateTo)
		//TODO hardcode para test 
		params.dateCreated = '1409194800000'
		log.info "samplingStep1 params: " + params
		def filters = loadMentionFilters()
		def samplings = tweetService.getSamplingTweets(filters, params)
		//TODO guardo en session la muestra ver
		session.conceptsId=params.conceptsId
		session.samplingTweets=samplings.resultList
		render(template: "samplingStep1", model: [dateFrom:params.dateFrom,dateTo:params.dateTo,demos:samplings.totalDemo,sampling:samplings.totalSampling])
	}
	def samplingStep2(){
		Concept concept =getConcept(session.conceptsId)
		session.samplingTweets.each {it ->
			it.tweet.refresh()
		}
		render(template: "samplingTweets", model: [tweetList:session.samplingTweets,concept:concept])
	}
	def samplingStep3(){
		def tweets=[]
		def dateList=[]
		def container=params.div
		session.samplingTweets.each {it ->
			tweets.add(it.tweet)
		}
		def opinions=tweetService.getOpinionsByTweets(tweets)
		Integer cantPos=0,cantNeg=0,cantNeu=0
		opinions.each {it ->
			if(it.value== OpinionValue.POSITIVE)
			cantPos++
			else if(it.value== OpinionValue.NEGATIVE)
			cantNeg++
			else if(it.value== OpinionValue.NEUTRAL)
			cantNeu++
		}
		
		dateList.add(['Positivo',cantPos]);
		dateList.add(['Negativo',cantNeg]);
		dateList.add(['Neutral',cantNeu]);
		session.samplingTweets=null
		def resultMap = [container:container,data:dateList,title:'Porcentajes de Opiniones',name : 'Opinion']
		render resultMap as JSON
	}
	def samplingTweets(){
		log.debug "tweetController->samplingTweets params: " + params
		Concept concept =chooseConcept(params)
		def filters = loadMentionFilters()
		
		def samplingTweets = tweetService.getSamplingTweets(filters, params)
		
		samplingTweets as JSON
	}
	
	private OpinionValue getValue(value){
		if (value == "POS")
			return OpinionValue.POSITIVE
		if (value == "NEG")
			return OpinionValue.NEGATIVE
		
		return OpinionValue.NEUTRAL
	}

	
}
