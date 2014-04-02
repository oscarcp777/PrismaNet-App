package com.prismanet
import grails.converters.*
import grails.plugins.springsecurity.Secured
import groovy.time.TimeCategory

import java.text.SimpleDateFormat

import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.OrderType
import com.prismanet.GenericService.ProjectionType
import com.prismanet.context.Filter
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils
@Secured(['ROLE_USER'])
class ConceptController extends GenericController{

	def scaffold = true
	def conceptService
	def tweetService
	
	
	def weightStats = {
		def weightProjection = ["authorFollowers" : ProjectionType.SUM]
		customStats(weightProjection)
	}

	def stats = {
		Concept concept = Concept.get(params.id)
		def authors=TwitterAuthor.list(max:10,sort:"followers",order:"desc");
		if(!grailsApplication.config.grails.twitter.offline)
		tweetService.loadDataAuthors(authors)
		[concept : concept,authors:authors]
	}
	
	
	def getGroupedTweets(){
		log.info "getGroupedTweets params: " + params
		def container = params.div
		Concept concept = Concept.get(params.id)
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
		}
		render resultMap as JSON
	}
	
	
	
	def getGroupedWeight(){
		log.info "getGroupedWeight params: " + params
		def container = params.div
		Concept concept = Concept.get(params.id)
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
		}
		render resultMap as JSON
	}
	
	
	
	def getRelevantAuthors(){
		log.info "getRelevantAuthors params: " + params
		def container = params.div
		Concept concept = Concept.get(params.id)
		def filters = []
		filters.add(new Filter(attribute:"conceptId",value:concept.id, type:FilterType.EQ))
		
		Date dateFrom = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateFrom)
		Date dateTo = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateTo)
				
		
		// Obtengo autores
//		def authorsList = conceptService.getRelevantAuthors(filters, dateFrom, dateTo, 10)
		def results = []
		for ( i in 0..9 ){
				results.add(["cfk","1200000"])
		}
		render results as JSON
	}
	
	
	
	def conceptsRealTime={
		Concept concept = Concept.get(params.id)
		def container = params.div
		def listRealTime=conceptService.getConceptsRealTime(concept.id,20)
		def series=[[name:concept.conceptName,data:listRealTime]]
		def json =[series:series,"container":container,id:params.id,title:"Tweets por minuto",
			subTitle:"Actualizacion en tiempo Real de la cantidad de Tweets" ,
			titleY:'Cantidad de tweets',titleX:'Minutos',dateProp:"tweetMinute",
			cursorEvent:"../../tweet/list?conceptsId=",ajaxMethodReload:'../conceptsRealTimeForOneMinute']
		
		render json as JSON
	}
	def conceptsRealTimeForOneMinute={
		Concept concept = Concept.get(params.id)
		def listRealTime=conceptService.getConceptsRealTime(concept.id,1)
		render  listRealTime as JSON
	}
}
