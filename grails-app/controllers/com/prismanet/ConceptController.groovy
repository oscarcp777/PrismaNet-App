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
	
	
	def customStats(proyection){
		
		Concept concept = Concept.findByConceptName(params.id)
		def filters = [new Filter(attribute:"id",value: concept.id, type:FilterType.EQ)]
		// Tweets por fecha
		def groupList = ["tweetCreated"]
		def dateList = conceptService.categoryStore(groupList, filters, proyection);
		// Tweets por hora del dia X (va a ser el actual ahora pongo uno con datos)
		SimpleDateFormat dF = new SimpleDateFormat("dd/MM/yyyy")
		filters.add(new Filter(attribute:"tweetPeriod",value: "201309", type:FilterType.GE))
		
		groupList = ["tweetHour"]
		def hourList = conceptService.categoryStore(groupList, filters, proyection);
		groupList = ["tweetMinute"]
		def minuteList = conceptService.categoryStore(groupList, filters, proyection);
		def dateValueList = []
		minuteList.each{ i -> dateValueList << i.getAt(1)	}
		[concept : concept, dateList : dateList, hourList:hourList, minuteList:minuteList, lineGraphList: dateValueList]
	}
	
	def getGroupedTweets(){
		log.info "conceptsHourJson params: " + params
		def container = params.div
		Concept concept = Concept.get(params.id)
		def filters = []
		filters.add(new Filter(attribute:"id",value:concept.id, type:FilterType.EQ))
		
		//TODO reemplazar enum por el que viene por parametro
		def range = DateFilterType.LAST_7_DAYS
		
		DateServiceType type = conceptService.getChartType(range) 
		
		
		// Obtengo tweets por hora
		def dateList = conceptService.getTweetsBy(filters, range, type)

		def redirectOnClick = "../../tweet/list?conceptsId="+concept.id
		def resultMap = [:]
		//TODO localizar todos los textos
		// Parseo resultado para generar el grafico
		switch (type) {
			case DateServiceType.BY_MINUTE:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.MINUTE_PERIOD,
												'Tweets por minuto','Cantidad de tweets','Tweets',
												redirectOnClick+"&tweetMinute=")
			break
			case DateServiceType.BY_HOUR:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.HOUR_PERIOD,
											   'Tweets por hora','Cantidad de tweets','Tweets',
											   redirectOnClick+"&tweetHour=")
			break
			case DateServiceType.BY_DATE:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.DAY_PERIOD,
												'Tweets por Dia','Cantidad de tweets','Tweets',
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
