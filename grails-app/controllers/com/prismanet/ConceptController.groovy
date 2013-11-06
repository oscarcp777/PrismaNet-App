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
	}

	def stats = {
		Concept concept = Concept.findByConceptName(params.id)
		[concept : concept]
	}
	
	
	

	def conceptsJson={
		Concept concept = Concept.findByConceptName(params.id)
		def proyection = ["tweetsId" : ProjectionType.COUNT]
		def filters = ["id" : concept.id]
		def groupList = ["created"]
		def dateList = conceptService.categoryStore(groupList, filters, proyection);
		
		def dateValueList = []
		dateList.each{ i -> dateValueList << [i.getAt(0).time,i.getAt(1)]	}
		
		render dateValueList as JSON
	}
	
}
