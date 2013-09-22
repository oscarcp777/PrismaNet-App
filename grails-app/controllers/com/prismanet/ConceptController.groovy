package com.prismanet

class ConceptController extends GenericService {
	
	def scaffold = true
	def conceptService
	
	def stats = {
		Concept concept = Concept.findByConceptName(params.id)
		//TODO agregar mensaje de error o 404 si no encuentra el concept
		def groupList = ["sex"]
		def sexList = conceptService.categoryStore(concept, groupList);
		groupList = ["tweetCreated"]
		def dateList = conceptService.categoryStore(concept, groupList);
		
		[concept : concept, sexList : sexList, dateList : dateList]
	}
		
    
}
