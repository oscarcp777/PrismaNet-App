package com.prismanet

class ConceptController {
	
	def scaffold = true
	def conceptService
	
	def listBySex = {
		def categoryList = conceptService.categoryStore();
		[ categoryList : categoryList]
	}
		
    
}
