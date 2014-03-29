package com.prismanet

import grails.test.GrailsUnitTestCase
import grails.test.mixin.support.*

class ConceptTests extends GrailsUnitTestCase{

	void testConstraints() {
		def concept = new Concept(conceptName : 'Comida', user: new User())
		mockForConstraintsTests(Concept, [concept])
		assertTrue concept.validate()

		def testConcept = new Concept()
		assertFalse testConcept.validate()
		assertEquals testConcept.errors.getErrorCount(),2
		assertEquals "nullable", testConcept.errors["conceptName"]
		assertEquals "nullable", testConcept.errors["user"]

	}
	
	void testAddTweetValidation() {
		def twitterConfig = new TwitterSetup(includedAccounts:"@CFKArgentina,@twitter2",
		keywords:"politica,filmus")
		def author1 = new Author(accountName:"@oscar", followers:10, userSince:new Date(), sex: Sex.M)
		def tweet1 = new Tweet(content:"La cristi ta loca", author:author1)
		def tweet2 = new Tweet(content:"El @CFKArgentina no existe", author:author1)
		
		def concept = new Concept(conceptName: 'Filmus',twitterSetup:twitterConfig)
		
		assertFalse concept.testAddTweet(tweet1)
		assertTrue concept.testAddTweet(tweet2)
		
		concept.addToTweets(tweet2)
		assertEquals concept.errors.getErrorCount(),0
		
		concept.addToTweets(tweet1)
		assertEquals concept.errors.getErrorCount(),1
		assertEquals "concept.tweets.invalid", concept.errors["tweets"]
	}
	
	
}
