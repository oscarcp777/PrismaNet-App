package com.prismanet

class Keyword {
	
	String word
	
	static belongsTo = [concept:Concept]

    static constraints = {
		
    }
}
