package com.prismanet

import com.prismanet.GenericService.FilterType
import com.prismanet.context.ConceptAttributeContext
import com.prismanet.context.Filter
import com.prismanet.sentiment.Opinion
import com.prismanet.sentiment.OpinionValue;

class ConceptException extends RuntimeException {
	String message
	Concept concept
}

class ConceptService extends GenericService {
		
	ConceptService(){
		super(Concept, new ConceptAttributeContext())
	}
	
    def categoryStore(def groups, def filters, def projection) {
		return groupBy(groups, filters, projection)
	}
	
	
}
