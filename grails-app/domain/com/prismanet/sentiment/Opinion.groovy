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
	
	User user
	Tweet tweet
	Concept concept
	OpinionValue value

     static constraints = {
		user(nullable:false)
		tweet(nullable:false)
		concept(nullable:false)
		value(nullable:false)
    }
}
