package com.prismanet.sentiment

import com.prismanet.Concept;
import com.prismanet.Tweet;
import com.prismanet.User;

class OpinionService {
	
	def addOpinion(User user, Concept concept, Tweet tweet, OpinionValue value){
		def opinion = new Opinion(user:user,concept:concept,tweet:tweet, value:value)
	}

	def OpinionValue getOpinion(User user, Concept concept, Tweet tweet){
		def opinion = Opinion.findByUserAndConceptAndTweet(user,concept,tweet)
		if (!opinion)
			return OpinionValue.NEUTRAL
		
		opinion.value
	}
}
