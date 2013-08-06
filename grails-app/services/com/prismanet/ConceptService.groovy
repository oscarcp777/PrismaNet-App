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
//		def resultList = criteria {
//			
//			context.clearDefinedAlias()
//			for (item in groups){
//				if(context.getEntityNameFor(item)!=null)
//					context.createAliasFor(criteria, item)
//			}	
//			eq("id", entity.id)
//			projections {
//				for (item in groups){
//					if(context.getEntityNameFor(item)!=null)
//						groupProperty(context.getAliasNameForProperty(item)+"." + context.getAttributePropertyName(item))
//					else
//						groupProperty(context.getAttributePropertyName(item))
//				}
//				if (context.getAliasNameForEntityPath("tweets") == null)
//					context.createAliasFor(criteria, "tweetsId")
//					
//				count(context.getAliasNameForEntityPath("tweets") + ".id")
//			}
//		}
//		return resultList
	}
}
