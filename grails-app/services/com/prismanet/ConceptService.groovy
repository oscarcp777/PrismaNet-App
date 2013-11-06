package com.prismanet

import com.prismanet.GenericService.ProjectionType
import com.prismanet.context.ConceptAttributeContext

class ConceptException extends RuntimeException {
	String message
	Concept concept
}

class ConceptService extends GenericService {
		
	ConceptService(){
		super()
		context = new ConceptAttributeContext()
	}
	
    def categoryStore(def groups, def filters, def projection) {
		def criteria = Concept.createCriteria();
		return categoriesService(criteria, groups, filters, projection)
	}
}
