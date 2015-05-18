package com.prismanet
import grails.converters.*
import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.servlet.support.RequestContextUtils as RCU

import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.ProjectionType
import com.prismanet.context.Filter
import com.prismanet.context.OpinionAttributeContext
import com.prismanet.sentiment.Opinion
import com.prismanet.sentiment.OpinionValue
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils
@Secured(['ROLE_USER','ROLE_USER_ADVANCE'])
class ConceptController extends GenericController{

	def scaffold = true
	def conceptService
	def opinionService
	def tweetService
	
	def getTwitterFilter(){
		new Filter(attribute:"sourceType",value:Tweet.class, type:FilterType.EQ)
	}
	
	def getFacebookFilter(){
		new Filter(attribute:"sourceType",value:FacebookComment.class, type:FilterType.EQ)
	}
	
	def dashboard = {
		Concept concept = Concept.get(params.id)
		
		def conceptFilter = new Filter(attribute:"id",value:concept.id, type:FilterType.EQ)
		
		def filters = [conceptFilter, getTwitterFilter()]
		def groups = ["conceptName"]
		def projection = ['mentionId' : ProjectionType.COUNT]
		
		// Contabilizar menciones de twitter
//		def tweetCount = conceptService.groupBy(Concept, new ConceptAttributeContext(),groups, filters, projection, null)
		def tweetTotal = 0;//tweetCount.size()>0 ? tweetCount.get(0).getAt(1) : 0

		// Contabilizar menciones de facebook
//		filters = [conceptFilter, getFacebookFilter()]
//		def postCount = conceptService.groupBy(Concept, new ConceptAttributeContext(),groups, filters, projection, null)
		def postTotal = 0;//postCount.size()>0 ? postCount.get(0).getAt(1) : 0

		
		[concept :concept,twSetup:concept.twitterSetup,fcSetup:concept.facebookSetup,tweetsTotal:tweetTotal, postsTotal:postTotal, total:postTotal+tweetTotal]
	}

	def tweetStats = {
		Concept concept = getConcept(params.id)
		[concept : concept]
	}
	
	def postStats = {
		Concept concept = getConcept(params.id)
		[concept : concept]
	}
	
	
	def getGroupedTweets(){
		log.info "getGroupedTweets params: " + params
		def container = params.div
		Concept concept = getConcept(params.id)
		def filters = []
		filters.add(new Filter(attribute:"id",value:concept.id, type:FilterType.EQ))
		filters.add(getTwitterFilter())
		
		Date dateFrom = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateFrom)
		Date dateTo = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateTo)
		
		DateServiceType type = conceptService.getChartType(dateFrom, dateTo) 
		
		
		// Obtengo tweets 
		def dateList = conceptService.getMentionsBy(filters, dateFrom, dateTo)
		def redirectOnClick = "../../tweet/list?conceptsId="+concept.id
		def resultMap = [:]
		//TODO localizar todos los textos
		// Parseo resultado para generar el grafico
		
		switch (type) {
			case DateServiceType.BY_MINUTE:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.MINUTE_PERIOD,
												'Tweets por minuto','Fecha','Tweets',
												redirectOnClick+"&dateMinute=")
			break
			case DateServiceType.BY_HOUR:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.HOUR_PERIOD,
											   'Tweets por hora','Fecha','Tweets',
											   redirectOnClick+"&dateHour=")
			break
			case DateServiceType.BY_DATE:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.DAY_PERIOD,
												'Tweets por Dia','Fecha','Tweets',
												redirectOnClick+"&dateCreated=")
			break
			case DateServiceType.BY_MONTH:
			resultMap = getChartLineFormat(dateList, 2, container, DateTypes.MONTH_PERIOD,
											'Tweets por Mes','Mes','Tweets',
											redirectOnClick+"&datePeriod=")
			break
		}
		render resultMap as JSON
	}
	def getPostMoreLikes={
		PostService postService = new PostService()
		def filters = []
		if (session.user.id){
			filters.add(new Filter(attribute:"userId",value: session.user.id, type:FilterType.EQ))
		}
		Date dateFrom, dateTo
		if (params.dateFrom)
			dateFrom = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateFrom)
		if (params.dateTo)
			dateTo = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateTo)
		filters.addAll(postService.getFilterList(dateFrom, dateTo))
		filters.add(new Filter(attribute:"conceptsId",value: params.id.toLong(), type:FilterType.EQ))
		def result = postService.getPosts(filters, [max:10])
		def statsList = result.results.collect{
			
			[created:it.created,name:it.name,postId:it.postId,picture:it.picture,link:createLing(it.postId),totalLikes:it.totalLikes, totalComments:it.totalComments, faceName:it.concepts[0]?.facebookSetup?.keywords]
		}
		render(template: "/user/postStats", model: [statsList:statsList])
	}
	
	def getGroupedPosts(){
		log.info "getGroupedPosts params: " + params
		def container = params.div
		Concept concept = getConcept(params.id)
		def filters = []
		filters.add(new Filter(attribute:"id",value:concept.id, type:FilterType.EQ))
		filters.add(getFacebookFilter())
		
		Date dateFrom = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateFrom)
		Date dateTo = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateTo)
		
		DateServiceType type = conceptService.getChartType(dateFrom, dateTo)
		
		
		// Obtengo posts
		def dateList = conceptService.getMentionsBy(filters, dateFrom, dateTo)
		def redirectOnClick = "../../post/list?conceptsId="+concept.id
		def resultMap = [:]
		//TODO localizar todos los textos
		// Parseo resultado para generar el grafico
		
		switch (type) {
			case DateServiceType.BY_MINUTE:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.MINUTE_PERIOD,
												'Comentarios por minuto','Fecha','Comentarios',
												redirectOnClick+"&dateMinute=")
			break
			case DateServiceType.BY_HOUR:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.HOUR_PERIOD,
											   'Comentarios por hora','Fecha','Comentarios',
											   redirectOnClick+"&dateHour=")
			break
			case DateServiceType.BY_DATE:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.DAY_PERIOD,
												'Comentarios por Dia','Fecha','Comentarios',
												redirectOnClick+"&dateCreated=")
			break
			case DateServiceType.BY_MONTH:
			resultMap = getChartLineFormat(dateList, 2, container, DateTypes.MONTH_PERIOD,
											'Comentarios por Mes','Mes','Comentarios',
											redirectOnClick+"&dateCreated=")
			break
		}
		render resultMap as JSON
	}
	
	
	
	def getGroupedWeight(){
		log.info "getGroupedWeight params: " + params
		def container = params.div
		Concept concept = getConcept(params.id)
		def filters = []
		filters.add(new Filter(attribute:"id",value:concept.id, type:FilterType.EQ))
		filters.add(getTwitterFilter())
		
		Date dateFrom = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateFrom)
		Date dateTo = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateTo)
			
		DateServiceType type = conceptService.getChartType(dateFrom, dateTo)
		
		
		// Obtengo alcance
		def dateList = conceptService.getWeightBy(filters, dateFrom, dateTo)

		def redirectOnClick = "../../tweet/list?conceptsId="+concept.id
		def resultMap = [:]
		//TODO localizar todos los textos
		// Parseo resultado para generar el grafico
		
		switch (type) {
			case DateServiceType.BY_MINUTE:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.MINUTE_PERIOD,
												'Alcance por minuto','Fecha','Alcance',
												redirectOnClick+"&dateMinute=")
			break
			case DateServiceType.BY_HOUR:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.HOUR_PERIOD,
											   'Alcance por hora','Fecha','Alcance',
											   redirectOnClick+"&dateHour=")
			break
			case DateServiceType.BY_DATE:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.DAY_PERIOD,
												'Alcance por Dia','Fecha','Alcance',
												redirectOnClick+"&dateCreated=")
			break
			case DateServiceType.BY_MONTH:
			resultMap = getChartLineFormat(dateList, 2, container, DateTypes.MONTH_PERIOD,
											'Alcance por Mes','Mes','Alcance',
											redirectOnClick+"&dateCreated=")
			break
		}
		render resultMap as JSON
	}
	
	
	
	def getRelevantAuthors(){
		log.info "getRelevantAuthors params: " + params
		def container = params.div
		Concept concept = getConcept(params.id)
		def filters = []
		filters.add(new Filter(attribute:"conceptId",value:concept.id, type:FilterType.EQ))
		
		Date dateFrom = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateFrom)
		Date dateTo = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateTo)
		
		// Obtengo autores
		def authorsList = conceptService.getRelevantAuthors(filters, dateFrom, dateTo, 10)
		if(!grailsApplication.config.grails.twitter.offline)
			tweetService.loadDataAuthors(authorsList)
		render(template: "author", model: [authors:authorsList])
	}
	
	
	
	def conceptsRealTime={
		def conceptId = params.id as Long
		def conceptFilter = new Filter(attribute:"id",value:conceptId, type:FilterType.EQ)
		def filters = [conceptFilter, getTwitterFilter()]
		def listRealTime=conceptService.getMentionsRealTime(filters,20)
		
		Concept concept = getConcept(conceptId)
		def container = params.div
		def series=[[name:concept.conceptName,data:listRealTime]]
		def json =[series:series,"container":container,id:conceptId,title:"Tweets por minuto",
			subTitle:"Actualizacion en tiempo Real de la cantidad de Tweets" ,
			titleY:'Cantidad de tweets',titleX:'Minutos',dateProp:"dateMinute",
			cursorEvent:"../../tweet/list?conceptsId=",ajaxMethodReload:'../conceptsRealTimeForOneMinute']
		
		render json as JSON
	}
	def conceptsRealTimeForOneMinute={
		def conceptId = params.id as Long
		Concept concept = getConcept(conceptId)
		def conceptFilter = new Filter(attribute:"id",value:conceptId, type:FilterType.EQ)
		def filters = [conceptFilter, getTwitterFilter()]
		def listRealTime=conceptService.getMentionsRealTime(filters,1)
		def series=[[name:concept.conceptName,data:listRealTime]]
		render  series as JSON
	}
	
	def postRealTime={
		def conceptId = params.id as Long
		def conceptFilter = new Filter(attribute:"id",value:conceptId, type:FilterType.EQ)
		def filters = [conceptFilter, getFacebookFilter()]
		def listRealTime=conceptService.getMentionsRealTime(filters,20)
		
		Concept concept = getConcept(params.id)
		def container = params.div
		def series=[[name:concept.conceptName,data:listRealTime]]
		def json =[series:series,"container":container,id:params.id,title:"Comentarios por minuto",
			subTitle:"Actualizacion en tiempo Real de la cantidad de Comentarios" ,
			titleY:'Cantidad de Comentarios',titleX:'Minutos',dateProp:"dateMinute",
			cursorEvent:"../../post/list?conceptsId=",ajaxMethodReload:'../postRealTimeForOneMinute']
		
		render json as JSON
	}
	
	def postRealTimeForOneMinute={
		def conceptId = params.id as Long
		def conceptFilter = new Filter(attribute:"id",value:conceptId, type:FilterType.EQ)
		def filters = [conceptFilter, getFacebookFilter()]
		def listRealTime=conceptService.getMentionsRealTime(filters,1)
		render  listRealTime as JSON
	}
	
	def sentimental = {
		Concept concept = getConcept(params.id)
		[concept : concept]
	}
	def sampling = {
		Concept concept = getConcept(params.id)
		[concept : concept]
	}
	def sentimentalAnalitycs(){
		log.info "sentimentalAnalitycs params: " + params
		def container = params.div
		Concept concept = getConcept(params.id)
		def filters = []
		filters.add(new Filter(attribute:"conceptId",value:concept.id, type:FilterType.EQ))
		filters.add(getTwitterFilter())
		
		Date dateFrom = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateFrom)
		Date dateTo = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateTo)
		
		DateServiceType type = conceptService.getChartType(dateFrom, dateTo)
		
		
		// Obtengo opiniones por valor
		def dateList = opinionService.getOpinionsBy(filters, dateFrom, dateTo)
		def redirectOnClick = "../../tweet/list?conceptsId="+concept.id
		def resultMap = [:]
		//TODO localizar todos los textos
		// Parseo resultado para generar el grafico
		
		switch (type) {
			case DateServiceType.BY_MINUTE:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.MINUTE_PERIOD,
												'Opiniones por minuto','Fecha','Opiniones',
												redirectOnClick+"&dateMinute=")
			break
			case DateServiceType.BY_HOUR:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.HOUR_PERIOD,
											   'Opiniones por hora','Fecha','Opiniones',
											   redirectOnClick+"&dateHour=")
			break
			case DateServiceType.BY_DATE:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.DAY_PERIOD,
												'Opiniones por Dia','Fecha','Opiniones',
												redirectOnClick+"&dateCreated=")
			break
			case DateServiceType.BY_MONTH:
			resultMap = getChartLineFormat(dateList, 2, container, DateTypes.MONTH_PERIOD,
											'Opiniones por Mes','Mes','Opiniones',
											redirectOnClick+"&dateCreated=")
			break
		}
		resultMap.series.each{
			it.name = it.name.localize()
		}
		render resultMap as JSON
	}
	
	def sentimentChartPie ={
		log.info "params: " + params
		def container=params.div
		def filters = []
		def conceptId = params.id as Long
		filters.add(new Filter(attribute:"conceptId",value:conceptId as Long, type:FilterType.EQ))
		filters.add(new Filter(attribute:"value",value:OpinionValue.NEUTRAL, type:FilterType.NE))
		filters.add(getTwitterFilter())
		def projection = ["mentionId" : ProjectionType.COUNT]
		def dateList = opinionService.groupBy(Opinion, new OpinionAttributeContext(), ["value"], filters, projection, null).sort{a,b -> a[1] <=> b[1] }
		dateList.each{
			it[0] = it[0].localize()
		}
		def resultMap = [container:container,data:dateList,title:'Porcentajes de Opiniones',name : 'Opinion']
		render resultMap as JSON
	}
	
	@Secured(['ROLE_ADMIN'])
	def create() {
		def concept=new Concept(params);
		concept.setTwitterSetup(new TwitterSetup(params))
		concept.setFacebookSetup(new FacebookSetup(params))
		[conceptInstance: new Concept(params)]
	}
	
	@Secured(['ROLE_ADMIN'])
	def save() {
		def conceptInstance = new Concept(params)
		def fcSetup=new FacebookSetup(params)
		def twSetup=new TwitterSetup(params)
		conceptInstance.setTwitterSetup(twSetup)
		conceptInstance.setFacebookSetup(fcSetup)
		if (!conceptInstance.save(flush: true)) {
			conceptInstance.errors.each {
				log.info it
			}
			render(view: "create", model: [conceptInstance: conceptInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'concept.label', default: 'Concept'), conceptInstance.id])
		redirect(action: "show", id: conceptInstance.id)
	}
	
	@Secured(['ROLE_ADMIN'])
	def update(Long id, Long version) {
		def conceptInstance = Concept.get(id)
		if (!conceptInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'concept.label', default: 'Concept'), id])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (conceptInstance.version > version) {
				conceptInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						  [message(code: 'concept.label', default: 'Concept')] as Object[],
						  "Another user has updated this Concept while you were editing")
				render(view: "edit", model: [conceptInstance: conceptInstance])
				return
			}
		}

		conceptInstance.properties = params
		conceptInstance.twitterSetup.properties = params
		conceptInstance.facebookSetup.properties = params
		if (!conceptInstance.save(flush: true)) {
			render(view: "edit", model: [conceptInstance: conceptInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'concept.label', default: 'Concept'), conceptInstance.id])
		redirect(action: "show", id: conceptInstance.id)
	}
	

	
	@Secured(['ROLE_ADMIN'])
	def index() {
		redirect(action: "list", params: params)
	}
	@Secured(['ROLE_ADMIN'])
	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[conceptInstanceList: Concept.list(params), conceptInstanceTotal: Concept.count()]
	}
	@Secured(['ROLE_ADMIN'])
	def show(Long id) {
		def conceptInstance = Concept.get(id)
		if (!conceptInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'concept.label', default: 'Concept'), id])
			redirect(action: "list")
			return
		}

		[conceptInstance: conceptInstance]
	}
	@Secured(['ROLE_ADMIN'])
	def edit(Long id) {
		def conceptInstance = Concept.get(id)
		if (!conceptInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'concept.label', default: 'Concept'), id])
			redirect(action: "list")
			return
		}

		[conceptInstance: conceptInstance]
	}
	@Secured(['ROLE_ADMIN'])
	def delete(Long id) {
		def conceptInstance = Concept.get(id)
		if (!conceptInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'concept.label', default: 'Concept'), id])
			redirect(action: "list")
			return
		}

		try {
			conceptInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'concept.label', default: 'Concept'), id])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'concept.label', default: 'Concept'), id])
			redirect(action: "show", id: id)
		}
	}
	
	@Secured(['ROLE_USER_ADVANCE'])
	def listAdvance(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def listAll=springSecurityService.currentUser.concepts.sort{it.id}
		def results = Concept.findAllByUser(session.user,params)
		[conceptList: results, conceptTotal: listAll.size()]
	}
	@Secured(['ROLE_USER_ADVANCE'])
	def editAdvance(Long id) {
		def conceptInstance = Concept.get(id)
		if (!conceptInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'concept.label', default: 'Concept'), id])
			redirect(action: "listAdvance")
			return
		}
		[conceptInstance: conceptInstance]
	}
	
	@Secured(['ROLE_USER_ADVANCE'])
	def createAdvance() {
		def concept=new Concept(params);
		concept.setTwitterSetup(new TwitterSetup(params))
		concept.setFacebookSetup(new FacebookSetup(params))
		[conceptInstance: new Concept(params)]
	}
	@Secured(['ROLE_USER_ADVANCE'])
	def saveAdvance() {
		log.info "params: " + params
		
		def conceptInstance = new Concept(getParamsConcept(params))
		conceptInstance.user=session.user
		def fcSetup=new FacebookSetup(params)
		fcSetup.keywords=params.keywordsFace
		def twSetup=new TwitterSetup(params)
		twSetup.keywords=params.keywordsTw
		conceptInstance.setTwitterSetup(twSetup)
		conceptInstance.setFacebookSetup(fcSetup)
		if (!conceptInstance.save(flush: true)) {
			conceptInstance.errors.each {
				log.info it
			}
			render(view: "createAdvance", model: [conceptInstance: conceptInstance])
			return
		}

		flash.message = message(code: 'concept.user.advance.created.ok', args: [conceptInstance.conceptName])
		loadConceptsSession()
		redirect(action: "showAdvance", id: conceptInstance.id)
	}
	@Secured(['ROLE_USER_ADVANCE'])
	def showAdvance(Long id) {
		def conceptInstance = Concept.get(id)
		if (!conceptInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'concept.label', default: 'Concept'), id])
			redirect(action: "listAdvance")
			return
		}

		[conceptInstance: conceptInstance]
	}
	private Map getParamsConcept(params){
		def map2 = params.clone()
		map2.lang=params.language
		map2
	}
	@Secured(['ROLE_USER_ADVANCE'])
	def updateAdvance(Long id, Long version) {
		log.info "params: " + params
		
		def conceptInstance = Concept.get(id)
		if (!conceptInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'concept.label', default: 'Concept'), id])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (conceptInstance.version > version) {
				conceptInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						  [message(code: 'concept.label', default: 'Concept')] as Object[],
						  "Another user has updated this Concept while you were editing")
				render(view: "edit", model: [conceptInstance: conceptInstance])
				return
			}
		}

		conceptInstance.properties = getParamsConcept(params)
		if(conceptInstance.twitterSetup==null){
			conceptInstance.twitterSetup=new TwitterSetup()
		}
		conceptInstance.twitterSetup.properties = params
		conceptInstance.twitterSetup.keywords=params.keywordsTw
		if(conceptInstance.facebookSetup==null){
			conceptInstance.facebookSetup=new FacebookSetup()
		}
		conceptInstance.facebookSetup.properties = params
		conceptInstance.facebookSetup.keywords=params.keywordsFace
		if (!conceptInstance.save(flush: true)) {
			render(view: "editAdvance", model: [conceptInstance: conceptInstance])
			return
		}

		flash.message = message(code: 'concept.user.advance.update.ok', args: [conceptInstance.conceptName])
		redirect(action: "showAdvance", id: conceptInstance.id)
	}
	def changeStatus(){
		def concept = Concept.get(params.id)
		concept.active=!concept.active
		if (concept.save(flush: true)) {
			loadConceptsSession()
			render(template: "tableConcepts", model: [conceptList:session.concepts])
		}else{
		    def resultMap = [error:'error-save',id:params.id,status:params.active]
		    render resultMap as JSON
		}
		
	}
}
