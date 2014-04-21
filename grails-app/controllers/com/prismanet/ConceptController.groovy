package com.prismanet
import grails.converters.*
import grails.plugins.springsecurity.Secured

import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.ProjectionType
import com.prismanet.context.ConceptAttributeContext
import com.prismanet.context.Filter
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils
@Secured(['ROLE_USER'])
class ConceptController extends GenericController{

	def scaffold = true
	def conceptService
	def tweetService
	
	def dashboard = {
		Concept concept = getConcept(params.id)
		def filters = [new Filter(attribute:"id",value:concept.id, type:FilterType.EQ)]
		def groups = ["conceptName"]
		def tweetCount = conceptService.groupBy(Concept, new ConceptAttributeContext(),
				groups, filters, ['tweetsId' : ProjectionType.COUNT],null)
		def tweetTotal = tweetCount.size()>0 ? tweetCount.get(0).getAt(1) : 0
		
		def postCount = conceptService.groupBy(Concept, new ConceptAttributeContext(),
				groups, filters, ['postsId' : ProjectionType.COUNT],null)
		def postTotal = postCount.size()>0 ? postCount.get(0).getAt(1) : 0

		[concept :concept,tweetsTotal:tweetTotal, postsTotal:postTotal, total:postTotal+tweetTotal]
	}

	def tweetStats = {
		Concept concept = getConcept(params.id)
		def authors=TwitterAuthor.list(max:10,sort:"followers",order:"desc");
		if(!grailsApplication.config.grails.twitter.offline)
			tweetService.loadDataAuthors(authors)
		[concept : concept,authors:authors]
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
		
		Date dateFrom = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateFrom)
		Date dateTo = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateTo)
		
		DateServiceType type = conceptService.getChartType(dateFrom, dateTo) 
		
		
		// Obtengo tweets 
		def dateList = conceptService.getTweetsBy(filters, dateFrom, dateTo)
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
		
		Date dateFrom = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateFrom)
		Date dateTo = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateTo)
		
		DateServiceType type = conceptService.getChartType(dateFrom, dateTo)
		
		
		// Obtengo posts
		def dateList = conceptService.getPostsBy(filters, dateFrom, dateTo)
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
		render authorsList as JSON
	}
	
	
	
	def conceptsRealTime={
		Concept concept = getConcept(params.id)
		def container = params.div
		def listRealTime=conceptService.getTweetsRealTime(concept.id,20)
		def series=[[name:concept.conceptName,data:listRealTime]]
		def json =[series:series,"container":container,id:params.id,title:"Tweets por minuto",
			subTitle:"Actualizacion en tiempo Real de la cantidad de Tweets" ,
			titleY:'Cantidad de tweets',titleX:'Minutos',dateProp:"tweetMinute",
			cursorEvent:"../../tweet/list?conceptsId=",ajaxMethodReload:'../conceptsRealTimeForOneMinute']
		
		render json as JSON
	}
	def conceptsRealTimeForOneMinute={
		Concept concept = getConcept(params.id)
		def listRealTime=conceptService.getTweetsRealTime(concept.id,1)
		render  listRealTime as JSON
	}
	
	def postRealTime={
		Concept concept = getConcept(params.id)
		
		def container = params.div
		def listRealTime=conceptService.getPostsRealTime(concept.id,20)
		def series=[[name:concept.conceptName,data:listRealTime]]
		def json =[series:series,"container":container,id:params.id,title:"Posts por minuto",
			subTitle:"Actualizacion en tiempo Real de la cantidad de Posts" ,
			titleY:'Cantidad de posts',titleX:'Minutos',dateProp:"postMinute",
			cursorEvent:"../../post/list?conceptsId=",ajaxMethodReload:'../postRealTimeForOneMinute']
		
		render json as JSON
	}
	def postRealTimeForOneMinute={
		Concept concept = getConcept(params.id)
		def listRealTime=conceptService.getPostsRealTime(concept.id,1)
		render  listRealTime as JSON
	}
}
