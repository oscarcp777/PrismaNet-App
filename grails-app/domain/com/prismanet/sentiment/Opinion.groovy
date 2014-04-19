package com.prismanet.sentiment

import com.prismanet.Concept
import com.prismanet.Mention

enum OpinionValue {
	POSITIVE,
	NEGATIVE,
	NEUTRAL
}

class Opinion {
	
	Mention mention
	Concept concept
	OpinionValue value

     static constraints = {
		mention(nullable:false)
		concept(nullable:false)
		value(nullable:false)
    }
}
