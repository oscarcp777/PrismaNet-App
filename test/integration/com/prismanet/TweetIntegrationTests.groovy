package com.prismanet

import static org.junit.Assert.*

import org.junit.*

import com.prismanet.GenericService.FilterType
import com.prismanet.context.Filter
import com.prismanet.sentiment.Opinion
import com.prismanet.sentiment.OpinionValue


class TweetIntegrationTests {

   
	
	@Test
	void testGetTweetsWithConcept(){
		def d1 = new GregorianCalendar(2013, Calendar.OCTOBER, 14,1,35)
		def twitterConfig = new TwitterSetup(includedAccounts:"@CFKArgentina,@twitter2",
		keywords:"politica,filmus").save()
		def author1 = new Author(accountName:"@oscar", followers:10, userSince:new Date(), sex: Sex.M).save()
		def tweet1 = new Tweet(content:"@CFKArgentina", author:author1, created:d1.time).save()
		
		def user = new User(firstName: "sant",lastName:"donik").save()
		
		
		def concept = new Concept(conceptName: 'Filmus',twitterSetup:twitterConfig, user: user).save()
		concept.addToTweets(tweet1)
		
		
		
		def opinion = new Opinion(concept:concept, tweet:tweet1, value:OpinionValue.POSITIVE).save()
		
		
		def tweetService = new TweetService()
		def filters = []
		Filter filter = new Filter(attribute:"conceptsId",value: concept.id, type:FilterType.EQ)
		filters.add(filter)
		def tweetList = tweetService.getTweets(filters, [:])
		assert tweetList.resultList.size() == 1
		assert tweetList.resultList.get(0).value == OpinionValue.POSITIVE
	}
	
	
	@Test
	void testGetTweetsWithoutConcept(){
		def d1 = new GregorianCalendar(2013, Calendar.OCTOBER, 14,1,35)
		def twitterConfig = new TwitterSetup(includedAccounts:"@CFKArgentina,@twitter2",
		keywords:"politica,filmus").save()
		def author1 = new Author(accountName:"@oscar", followers:10, userSince:new Date(), sex: Sex.M).save()
		def tweet1 = new Tweet(content:"@CFKArgentina", author:author1, created:d1.time).save()
		
		def tweetService = new TweetService()
		def tweetList = tweetService.getTweets([],[:])
		assert tweetList.resultList.size() == 1
	}
	
	

}
