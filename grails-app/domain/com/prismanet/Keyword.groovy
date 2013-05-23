package com.prismanet

class Keyword {
	
	String word
	
	static belongsTo = [TwitterSetup,FacebookSetup] 

    static constraints = {
	}
}
