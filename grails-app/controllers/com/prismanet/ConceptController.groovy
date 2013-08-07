package com.prismanet

class ConceptController extends GenericService {
	
	def scaffold = true
	def conceptService
	
	def stats = {
		Concept concept = Concept.findById(params.id)
		def groupList = ["sex"]
		def sexList = conceptService.categoryStore(concept, groupList);
		groupList = ["tweetCreated"]
		def dateList = conceptService.categoryStore(concept, groupList);
		
		[concept : concept, sexList : sexList, dateList : dateList]
	}
		
    
}
