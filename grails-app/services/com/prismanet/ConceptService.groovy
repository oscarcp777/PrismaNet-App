package com.prismanet

class ConceptException extends RuntimeException {
	String message
	Concept concept
}

class ConceptService {
		

    def categoryStore(/*def categories, def filters*/) {
		
		def tagList = Concept.withCriteria {
			createAlias("tweets", "t")
			createAlias("tweets.author", "a")
//			eq("u.userId", "glen")
			projections {
				groupProperty("a.sex")
				count("t.id")
			}
		}
		return tagList
	}
}
