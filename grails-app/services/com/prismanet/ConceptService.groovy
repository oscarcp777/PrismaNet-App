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
	
    def categoryStore(Concept entity, def groups/*, def filters*/) {
		def criteria = Concept.createCriteria();
		def filters = ["id" : entity.id]
		def projection = ["tweetsId" : ProjectionType.COUNT]
		return categoriesService(criteria, groups, filters, projection)
	}
}
