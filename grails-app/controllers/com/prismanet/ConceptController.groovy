package com.prismanet

class ConceptController {
	
	def scaffold = true
	def conceptService
	
	def stats = {
		Concept concept = Concept.findById(params.id)
		def groupList = ["tweets.author"]
		def sexList = conceptService.categoryStore(concept, groupList);
		[concept : concept, sexList : sexList]
	}
		
    
}
