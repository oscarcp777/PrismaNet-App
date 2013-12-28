package com.prismanet



import grails.test.mixin.*
import org.junit.*

import com.prismanet.sentiment.Opinion
import com.prismanet.sentiment.OpinionValue;

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(TweetService)
@Mock([Tweet])
class TweetServiceTests {
	
	def tweetService = new TweetService()
//	def tweet
//	def user
//	def concept
	
//	@Before
//	void setUp() {
//		
//		
//		tweet = new Tweet()
//		user = new User()
//		concept = new Concept()
//		
////		mockDomain(User, [user])
////		mockDomain(Concept, [concept])
//		
//		def opinion = new Opinion(user:user,concept:concept,tweet:tweet, value:OpinionValue.POSITIVE).save()
//		
//		mockForConstraintsTests(Opinion)
//		mockDomain(Opinion, [opinion])
//	
//	}

    void testGetOpinion() {
//		User.metaClass.encodePassword = { -> }
//		
//		def tweet = new Tweet()
//		def user = new User()
//		def concept = new Concept()
//		def opinion = new Opinion(user:user,concept:concept,tweet:tweet, value:OpinionValue.POSITIVE)
//		
////		mockForConstraintsTests(Opinion)
////		mockDomain(Opinion, [opinion])
//		
//		assertEquals tweetService.getOpinion(user,concept,tweet),OpinionValue.POSITIVE
    }
}
