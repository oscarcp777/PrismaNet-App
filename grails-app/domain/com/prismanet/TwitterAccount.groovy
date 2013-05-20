package com.prismanet

class TwitterAccount {
	
	String name
	
	static belongsTo = [concept:Concept]

    static constraints = {
		name(matches:'@.*')
    }
}
