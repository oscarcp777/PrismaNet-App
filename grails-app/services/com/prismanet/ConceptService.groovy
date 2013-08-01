package com.prismanet

class ConceptException extends RuntimeException {
	String message
	Concept concept
}

class ConceptService {
		

    def categoryStore(Concept entity, def groups/*, def filters*/) {
		
		def tagList = Concept.withCriteria {
			createAlias("tweets", "t")
			int i = 0
			for (item in groups){
				createAlias(item,"a"+i)
				i++
			}
//			createAlias("tweets.author", "a")
			eq("id", entity.id)
			projections {
//				groupProperty("id")
				int j = 0
				for (item in groups){
					groupProperty("a"+j+".sex")
					j++
				}	
				count("t.id")
			}
		}
		return tagList
	}
}
