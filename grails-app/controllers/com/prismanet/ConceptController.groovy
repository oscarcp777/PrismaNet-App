package com.prismanet
import grails.converters.*
import grails.plugins.springsecurity.Secured
import groovy.time.TimeCategory

import java.text.SimpleDateFormat

import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.ProjectionType
import com.prismanet.context.Filter
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils
import java.util.Random
@Secured(['ROLE_USER'])
class ConceptController{

	def scaffold = true
	def conceptService
	
	
	
	def weightStats = {
		def weightProjection = ["authorFollowers" : ProjectionType.SUM]
		customStats(weightProjection)
	}

	def stats = {
		Concept concept = Concept.get(params.id)
		println concept
		[concept : concept]
	}
	
	
	def customStats(proyection){
		
		Concept concept = Concept.findByConceptName(params.id)
		def filters = [new Filter(attribute:"id",value: concept.id, type:FilterType.EQ)]
		// Tweets por fecha
		def groupList = ["tweetCreated"]
		def dateList = conceptService.categoryStore(groupList, filters, proyection);
		println "Tweets por fecha" +dateList
		// Tweets por hora del dia X (va a ser el actual ahora pongo uno con datos)
		SimpleDateFormat dF = new SimpleDateFormat("dd/MM/yyyy")
		filters.add(new Filter(attribute:"tweetPeriod",value: "201309", type:FilterType.GE))
		
		groupList = ["tweetHour"]
		def hourList = conceptService.categoryStore(groupList, filters, proyection);
		println "Tweets por hora" +hourList
		groupList = ["tweetMinute"]
		def minuteList = conceptService.categoryStore(groupList, filters, proyection);
		println "Tweets por minuto" + minuteList
		def dateValueList = []
		minuteList.each{ i -> dateValueList << i.getAt(1)	}
		println "Tweets por solo" +dateValueList
		[concept : concept, dateList : dateList, hourList:hourList, minuteList:minuteList, lineGraphList: dateValueList]
	}
	
	def conceptsDayJson={
		Concept concept = Concept.findByConceptName(params.id)
		def dateValueList = []
		def dateList = conceptService.categoryStore(["tweetCreated"], [new Filter(attribute:"id",value: concept.id, type:FilterType.EQ)], ["tweetsId" : ProjectionType.COUNT]);
		dateList.each{ i -> dateValueList << i.getAt(0)}
		def json =["data":dateList,"cat":dateValueList]
		render json as JSON
	}
	def conceptsHourJson={
		Concept concept = Concept.findByConceptName(params.id)
		def listHour=conceptService.getConceptsHourJson(concept,params.day);
		render listHour as JSON
	}
	def conceptsMinuteJson={
		Concept concept = Concept.findByConceptName(params.id)
		def dateValueList = []
		def dateList = conceptService.categoryStore(["tweetMinute"], [new Filter(attribute:"id", value : concept.id, type:FilterType.EQ),
			new Filter(attribute:"tweetPeriod",value:"201309", type:FilterType.GE)], ["tweetsId" : ProjectionType.COUNT]);
		dateList.each{ i -> dateValueList << i.getAt(0)}
		def json =["data":dateList,"cat":dateValueList]
		render json as JSON
	}
	def conceptsDateJson={
		Concept concept = Concept.findByConceptName(params.id)
		def dateList = conceptService.categoryStore(["created"], [new Filter(attribute:"id",value : concept.id, type:FilterType.EQ)], ["tweetsId" : ProjectionType.COUNT]);
		def dateValueList = []
		dateList.each{ i -> dateValueList << [i.getAt(0).time,i.getAt(1)]	}
		
		render dateValueList as JSON
	}
	/**
	 * formato que se necesita la lista [[1381448400000,9],[1381448400000,0]]
	 */
	def conceptsRealTime={
		Concept concept = Concept.get(params.id)
		def listRealTime=conceptService.getConceptsRealTime(concept)
		render listRealTime as JSON
	}
	def conceptsRealTimeForOneMinute={
		Concept concept = Concept.get(params.id)
		def dateValueList = [:]
		Date from
		Date to=new Date()
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(to);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		to=calendar.getTime();
		use ( TimeCategory ) {
			from = to-2.minutes
			def dateList = conceptService.categoryStore(["conceptName","tweetMinute"],
			[new Filter(attribute:"id", value : concept.id, type:FilterType.EQ),
				new Filter(attribute:"created",value:from, type:FilterType.GE)], ["tweetsId" : ProjectionType.COUNT]);

			dateList.each{ i ->
				dateValueList.put(DateUtils.parseDate(DateTypes.MINUTE_PERIOD, i.getAt(1)).time,i.getAt(2))	
			}
		}
		render DateUtils.loadZerosForMinute(dateValueList,from,to) as JSON
	}
}
