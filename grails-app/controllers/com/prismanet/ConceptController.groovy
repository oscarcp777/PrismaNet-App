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
		def quantityProjection = ["tweetsId" : ProjectionType.COUNT]
		customStats(quantityProjection)
	}
	
	
	def customStats(proyection){
		
		Concept concept = Concept.findByConceptName(params.id)
		def filters = ["id" : concept.id]
		
		//TODO agregar mensaje de error o 404 si no encuentra el concept
		
		// Tweets por sexo
		def groupList = ["sex"]
		def sexList = conceptService.categoryStore(groupList, filters, proyection);
		
		// Tweets por fecha
		groupList = ["tweetCreated"]
		def dateList = conceptService.categoryStore(groupList, filters, proyection);
		
		// Tweets por hora del dia X (va a ser el actual ahora pongo uno con datos)
		SimpleDateFormat dF = new SimpleDateFormat("dd/MM/yyyy")
		filters["tweetPeriod"] = "201309"
		
		groupList = ["tweetHour"]
		def hourList = conceptService.categoryStore(groupList, filters, proyection);

		groupList = ["tweetMinute"]
		def minuteList = conceptService.categoryStore(groupList, filters, proyection);
		
		def dateValueList = []
		dateList.each{ i -> dateValueList << i.getAt(1)	}
		
		[concept : concept, sexList : sexList, dateList : dateList, hourList:hourList, minuteList:minuteList, lineGraphList: dateValueList, initialDate:dateList.getAt(0).getAt(0)]
	}

	def conceptsJson={
		def listJson=Concept.list();
		render listJson as JSON
	}
	
}
