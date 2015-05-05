package com.prismanet




import grails.test.mixin.*

import com.prismanet.GenericService.FilterType
import com.prismanet.context.Filter

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(TweetService)
@Mock([Tweet])
class MentionServiceTests {
	
	def mentionService = new MentionService()
	
	void testGetRelevantWords(){
		def result = mentionService.getRelevantWords()
		assertTrue result.size()>0 
		
		def filters = []
		filters.add(new Filter(attribute:"dateCreated",value: "21/02/2014", type:FilterType.EQ))
		filters.add(new Filter(attribute:"conceptsId",value: 15, type:FilterType.EQ))
		result = mentionService.getRelevantWords(filters)
		
	}
 
}
