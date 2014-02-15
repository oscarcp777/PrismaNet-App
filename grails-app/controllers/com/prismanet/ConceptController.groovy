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
	
	
	
	def weightStats = {
		def weightProjection = ["authorFollowers" : ProjectionType.SUM]
		customStats(weightProjection)
	}

	def stats = {
		Concept concept = Concept.get(params.id)
		[concept : concept]
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
		
		
		// Obtengo tweets por hora
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
		
		
		// Obtengo tweets por hora
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
	
	
	/**
	 * formato que se necesita la lista [[1381448400000,9],[1381448400000,0]]
	 */
	def conceptsRealTime={
		
		Concept concept = Concept.get(params.id)
		def container = params.div
		def listRealTime=conceptService.getConceptsRealTime(concept)
		def json =["data":listRealTime,"container":container,"id":params.id, "title":'Tweets por minuto',"subTitle":"Actualización en tiempo Real de la cantidad de Tweets" ,"titleY":'Cantidad de tweets',"titleX":'Minutos']
		json<< [dateProp:"tweetMinute"]
		render json as JSON
	}
	def conceptsRealTimeForOneMinute={
		Concept concept = Concept.get(params.id)
		def dateValueList = [:]
		Date from,to
		Calendar calendar = new GregorianCalendar()
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		to=calendar.getTime();
		use ( TimeCategory ) {
			from = to-1.minutes
			def tweetByMinute=DateUtils.getDateFormat(DateTypes.MINUTE_PERIOD, to)
			//TODO trae siempre cero
//			def dateList = conceptService.categoryStore(["conceptName","tweetByMinute"],[new Filter(attribute:"id", value : concept.id, type:FilterType.EQ),new Filter(attribute:"tweetByMinute",value:tweetByMinute, type:FilterType.GE)], ["tweetsId" : ProjectionType.COUNT], null);
			def dateList =conceptService.categoryStore(["conceptName","tweetByMinute"],[new Filter(attribute:"id", value : concept.id, type:FilterType.EQ),new Filter(attribute:"created",value:from, type:FilterType.GE)], ["tweetsId" : ProjectionType.COUNT],null);
			
			dateList.each{ i ->
				dateValueList.put(DateUtils.parseDate(DateTypes.MINUTE_PERIOD, i.getAt(1)).time,i.getAt(2))	
			}
		}
		render DateUtils.loadZerosForMinute(dateValueList,from,to) as JSON
	}
}
