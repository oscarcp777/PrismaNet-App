package com.prismanet
import grails.converters.*

import java.text.SimpleDateFormat
import grails.plugins.springsecurity.Secured
@Secured(['ROLE_USER'])
class ConceptController extends GenericService {

	def scaffold = true
	def conceptService

	def stats = {
		Concept concept = Concept.findByConceptName(params.id)
		def filters = ["id" : concept.id]
		
		//TODO agregar mensaje de error o 404 si no encuentra el concept
		
		// Tweets por sexo
		def groupList = ["sex"]
		def sexList = conceptService.categoryStore(concept, groupList, filters);
		
		// Tweets por fecha
		groupList = ["tweetCreated"]
		def dateList = conceptService.categoryStore(concept, groupList, filters);
		
		// Tweets por hora del dia X (va a ser el actual ahora pongo uno con datos)
		SimpleDateFormat dF = new SimpleDateFormat("dd/MM/yyyy")
		filters["tweetPeriod"] = "201309"
		
		groupList = ["tweetHour"]
		def hourList = conceptService.categoryStore(concept, groupList, filters);

		[concept : concept, sexList : sexList, dateList : dateList, hourList:hourList]
		
		groupList = ["tweetMinute"]
		def minuteList = conceptService.categoryStore(concept, groupList, filters);

		[concept : concept, sexList : sexList, dateList : dateList, hourList:hourList, minuteList:minuteList]
	}

	def conceptsJson={
		def listJson=Concept.list();
		render listJson as JSON
	}
	
}
