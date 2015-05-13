package com.prismanet

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService

import com.prismanet.TweetService.SamplingType;
import com.prismanet.sentiment.Opinion
import com.prismanet.sentiment.OpinionValue
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils
@Secured(['ROLE_USER','ROLE_USER_ADVANCE'])
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
		def map = loadMentionFilters()
		params.max = Math.min(max ?: 6, 100)
		def tweets = tweetService.getTweets(map.filters,params)
//		def relevantWords = tweetService.getRelevantWords(loadSolrFilters())
		if(!grailsApplication.config.grails.twitter.offline)
			tweetService.loadAvatarUsers(tweets.resultList)
		
		if(params.offset==null){
		  session.relevantWords=[]
		}
		
		[tweetInstanceList: tweets.resultList, tweetInstanceTotal: tweets.totalCount, concept: concept, 
		 tweetMinute:params["tweetMinute"], dateCreated:params.dateCreated,dateFrom:map.dateFrom,dateTo:map.dateTo]
	}
	
	def randomList(){
		def filters = loadMentionFilters().filters
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
     private void loadTweets(parcialList){
		 parcialList.each {it ->
			 it.tweet.refresh()
		 }
		 if(!grailsApplication.config.grails.twitter.offline)
		 tweetService.loadAvatarUsers(parcialList)
	 }
	def samplingStep1(){
		def container = params.div
		def samplingType = params.samplingType as SamplingType
		Date dateFrom = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateFrom)
		Date dateTo = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateTo)
		log.info "samplingStep1 params: " + params
		def filters = loadMentionFilters().filters
		filters.addAll(getService().getFilterList(dateFrom, dateTo, "created", false))
		def samplings = tweetService.getSamplingTweets(filters, params, samplingType)
		//TODO guardo en session la muestra ver
		session.conceptsId=params.conceptsId
		session.samplingTweets=samplings.resultList
		render(template: "samplingStep1", model: [dateFrom:params.dateFrom,dateTo:params.dateTo,demos:samplings.totalDemo,sampling:samplings.totalSampling])
	}
	def samplingStep2(){
		Concept concept =getConcept(session.conceptsId)
		
		def totalPage=(session.samplingTweets.size()%10 == 0)?(int)session.samplingTweets.size()/10:(session.samplingTweets.size()/10).setScale(0, BigDecimal.ROUND_DOWN)+1
		def end=totalPage >1?9:session.samplingTweets.size()-1
		def parcialList=totalPage==0?session.samplingTweets:session.samplingTweets[0..end]
		loadTweets(parcialList)
		render(template: "samplingTweets", model: [tweetList:parcialList,concept:concept,totalPage:totalPage])
		
	}
	def samplingTweetsPage(){
		Concept concept =getConcept(session.conceptsId)
		def from=0,to=session.samplingTweets.size()-1
		if(params.init!='-1'){
			from=params.init.toInteger()
		}
		if(params.end!='-1'){
			to=params.end.toInteger()
		}
		def parcialList=session.samplingTweets[from..to]
		loadTweets(parcialList)
		render(template: "ulListTweets", model: [tweetList:parcialList,concept:concept])
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
		def filters = loadMentionFilters().filters
		
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
