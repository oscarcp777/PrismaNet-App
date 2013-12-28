package com.prismanet

import static org.junit.Assert.*

import org.junit.*

import com.prismanet.GenericService.ProjectionType
import com.prismanet.sentiment.Opinion
import com.prismanet.sentiment.OpinionValue


class TweetIntegrationTests {

   
	
	@Test
	void testGetTweets(){
		def d1 = new GregorianCalendar(2013, Calendar.OCTOBER, 14,1,35)
		def twitterConfig = new TwitterSetup(includedAccounts:"@CFKArgentina,@twitter2",
		keywords:"politica,filmus").save()
		def author1 = new Author(accountName:"@oscar", followers:10, userSince:new Date(), sex: Sex.M).save()
		def tweet1 = new Tweet(content:"@CFKArgentina", author:author1, created:d1.time).save()
		
		def user = new User(firstName: "sant",lastName:"donik").save()
		
		
		def concept = new Concept(conceptName: 'Filmus',twitterSetup:twitterConfig, user: user)
		concept.addToTweets(tweet1)
		concept.save()
		
		
		def opinion = new Opinion(concept:concept, tweet:tweet1, value:OpinionValue.POSITIVE).save()
		
		
		def tweetService = new TweetService()
		def tweetList = tweetService.getTweets(concept.id)
		assert tweetList.size() == 1
		assert tweetList.get(0).value == OpinionValue.POSITIVE
	}
	
	

}
