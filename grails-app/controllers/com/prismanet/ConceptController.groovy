package com.prismanet
import grails.converters.*

import java.text.SimpleDateFormat
import grails.plugins.springsecurity.Secured
import com.prismanet.GenericService.ProjectionType

@Secured(['ROLE_USER'])
class ConceptController extends GenericService {

	def scaffold = true
	def conceptService
	
	
	
	def weightStats = {
		def weightProjection = ["authorFollowers" : ProjectionType.SUM]
		customStats(weightProjection)
	}

	def stats = {
		Concept concept = Concept.findByConceptName(params.id)
		[concept : concept]
	}
	
	
	def customStats(proyection){
		
		Concept concept = Concept.findByConceptName(params.id)
		def filters = ["id" : concept.id]
		// Tweets por fecha
		def groupList = ["tweetCreated"]
		def dateList = conceptService.categoryStore(groupList, filters, proyection);
		println "Tweets por fecha" +dateList
		// Tweets por hora del dia X (va a ser el actual ahora pongo uno con datos)
		SimpleDateFormat dF = new SimpleDateFormat("dd/MM/yyyy")
		filters["tweetPeriod"] = "201309"
		
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
		def dateList = conceptService.categoryStore(["tweetCreated"], ["id" : concept.id], ["tweetsId" : ProjectionType.COUNT]);
		dateList.each{ i -> dateValueList << i.getAt(0)}
		def json =["data":dateList,"cat":dateValueList]
		render json as JSON
	}
	def conceptsHourJson={
		Concept concept = Concept.findByConceptName(params.id)
		def dateList = conceptService.categoryStore(["tweetHour"], ["id" : concept.id,"tweetPeriod":"201309"], ["tweetsId" : ProjectionType.COUNT]);
		render dateList as JSON
	}
	def conceptsMinuteJson={
		Concept concept = Concept.findByConceptName(params.id)
		def dateValueList = []
		def dateList = conceptService.categoryStore(["tweetMinute"], ["id" : concept.id,"tweetPeriod":"201309"], ["tweetsId" : ProjectionType.COUNT]);
		dateList.each{ i -> dateValueList << i.getAt(0)}
		def json =["data":dateList,"cat":dateValueList]
		render json as JSON
	}
	def conceptsDateJson={
		Concept concept = Concept.findByConceptName(params.id)
		def dateList = conceptService.categoryStore(["created"], ["id" : concept.id], ["tweetsId" : ProjectionType.COUNT]);
		def dateValueList = []
		dateList.each{ i -> dateValueList << [i.getAt(0).time,i.getAt(1)]	}
		
		render dateValueList as JSON
	}
	
}
