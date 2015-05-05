package com.prismanet.sentiment

import com.prismanet.Concept
import com.prismanet.Mention



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
