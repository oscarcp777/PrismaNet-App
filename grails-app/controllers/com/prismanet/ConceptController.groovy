package com.prismanet
import grails.converters.*
import grails.plugins.springsecurity.Secured

import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.ProjectionType
import com.prismanet.context.ConceptAttributeContext
import com.prismanet.context.Filter
import com.prismanet.context.OpinionAttributeContext
import com.prismanet.sentiment.Opinion
import com.prismanet.sentiment.OpinionValue
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils
@Secured(['ROLE_USER'])
class ConceptController extends GenericController{

	def scaffold = true
	def conceptService
	def opinionService
	def tweetService
	
	def getTwitterFilter(){
		new Filter(attribute:"sourceType",value:Tweet.class, type:FilterType.EQ)
	}
	
	def getFacebookFilter(){
		new Filter(attribute:"sourceType",value:Post.class, type:FilterType.EQ)
	}
	
	def dashboard = {
		Concept concept = getConcept(params.id)
		
		def conceptFilter = new Filter(attribute:"id",value:concept.id, type:FilterType.EQ)
		
		def filters = [conceptFilter, getTwitterFilter()]
		def groups = ["conceptName"]
		def projection = ['mentionId' : ProjectionType.COUNT]
		
		// Contabilizar menciones de twitter
		def tweetCount = conceptService.groupBy(Concept, new ConceptAttributeContext(),
				groups, filters, projection, null)
		def tweetTotal = tweetCount.size()>0 ? tweetCount.get(0).getAt(1) : 0

		// Contabilizar menciones de facebook
		filters = [conceptFilter, getFacebookFilter()]
		def postCount = conceptService.groupBy(Concept, new ConceptAttributeContext(),
				groups, filters, projection, null)
		def postTotal = postCount.size()>0 ? postCount.get(0).getAt(1) : 0

		
		[concept :concept,tweetsTotal:tweetTotal, postsTotal:postTotal, total:postTotal+tweetTotal]
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
												redirectOnClick+"&tweetMinute=")
			break
			case DateServiceType.BY_HOUR:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.HOUR_PERIOD,
											   'Tweets por hora','Fecha','Tweets',
											   redirectOnClick+"&tweetHour=")
			break
			case DateServiceType.BY_DATE:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.DAY_PERIOD,
												'Tweets por Dia','Fecha','Tweets',
												redirectOnClick+"&tweetCreated=")
			break
			case DateServiceType.BY_MONTH:
			resultMap = getChartLineFormat(dateList, 2, container, DateTypes.MONTH_PERIOD,
											'Tweets por Mes','Mes','Tweets',
											redirectOnClick+"&tweetCreated=")
			break
		}
//		def aux = resultMap as JSON
//		print "resultMap: " + aux
		render resultMap as JSON
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
		def redirectOnClick = "../../tweet/list?conceptsId="+concept.id
		def resultMap = [:]
		//TODO localizar todos los textos
		// Parseo resultado para generar el grafico
		
		switch (type) {
			case DateServiceType.BY_MINUTE:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.MINUTE_PERIOD,
												'Posts por minuto','Fecha','Posts',
												redirectOnClick+"&postMinute=")
			break
			case DateServiceType.BY_HOUR:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.HOUR_PERIOD,
											   'Posts por hora','Fecha','Posts',
											   redirectOnClick+"&postHour=")
			break
			case DateServiceType.BY_DATE:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.DAY_PERIOD,
												'Posts por Dia','Fecha','Posts',
												redirectOnClick+"&postCreated=")
			break
			case DateServiceType.BY_MONTH:
			resultMap = getChartLineFormat(dateList, 2, container, DateTypes.MONTH_PERIOD,
											'Posts por Mes','Mes','Posts',
											redirectOnClick+"&postCreated=")
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
												redirectOnClick+"&tweetMinute=")
			break
			case DateServiceType.BY_HOUR:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.HOUR_PERIOD,
											   'Alcance por hora','Fecha','Alcance',
											   redirectOnClick+"&tweetHour=")
			break
			case DateServiceType.BY_DATE:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.DAY_PERIOD,
												'Alcance por Dia','Fecha','Alcance',
												redirectOnClick+"&tweetCreated=")
			break
			case DateServiceType.BY_MONTH:
			resultMap = getChartLineFormat(dateList, 2, container, DateTypes.MONTH_PERIOD,
											'Alcance por Mes','Mes','Alcance',
											redirectOnClick+"&tweetCreated=")
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
			titleY:'Cantidad de tweets',titleX:'Minutos',dateProp:"tweetMinute",
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
		def json =[series:series,"container":container,id:params.id,title:"Posts por minuto",
			subTitle:"Actualizacion en tiempo Real de la cantidad de Posts" ,
			titleY:'Cantidad de posts',titleX:'Minutos',dateProp:"postMinute",
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
												redirectOnClick+"&tweetMinute=")
			break
			case DateServiceType.BY_HOUR:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.HOUR_PERIOD,
											   'Opiniones por hora','Fecha','Opiniones',
											   redirectOnClick+"&tweetHour=")
			break
			case DateServiceType.BY_DATE:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.DAY_PERIOD,
												'Opiniones por Dia','Fecha','Opiniones',
												redirectOnClick+"&tweetCreated=")
			break
			case DateServiceType.BY_MONTH:
			resultMap = getChartLineFormat(dateList, 2, container, DateTypes.MONTH_PERIOD,
											'Opiniones por Mes','Mes','Opiniones',
											redirectOnClick+"&tweetCreated=")
			break
		}
		resultMap.series.each{
			it.name = it.name.localize()
		}
		render resultMap as JSON
	}
	
	def sentimentChartPie ={
		print "params: " + params
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
}
