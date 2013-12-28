package com.prismanet.sentiment

import com.prismanet.User
import com.prismanet.Tweet
import com.prismanet.Concept

enum OpinionValue {
	POSITIVE,
	NEGATIVE,
	NEUTRAL
}

class Opinion {
	
	Tweet tweet
	Concept concept
	OpinionValue value

     static constraints = {
		tweet(nullable:false)
		concept(nullable:false)
		value(nullable:false)
    }
}
