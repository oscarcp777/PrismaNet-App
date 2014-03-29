package com.prismanet

import static org.junit.Assert.*

import org.junit.*

import com.prismanet.GenericService.FilterType
import com.prismanet.context.Filter
import com.prismanet.sentiment.Opinion
import com.prismanet.sentiment.OpinionValue


class TweetIntegrationTests {

	@Before
	void setUp() {
		def setup = new TwitterSetup(includedAccounts:"@CFKArgentina,@twitter2", keywords:"politica,filmus").save()
		def author = new TwitterAuthor(name:"oscar", accountName:"@oscar", followers:10, userSince:new Date(), sex: Sex.M).save()
		def account = new AccountType(type:'free').save()
		def user = new User(username: 'oscar', password: 'fiuba', firstName:'oscar', lastName:'Caceres',account:account).save()
		def tweet = new Tweet(content:"@CFKArgentina", author:author, created:new Date(), tweetId:1l, retweet:false).save()
		
	}
	
	/*@Test
	void testGetTweetsWithConcept(){
		
		def concept = new Concept(conceptName: 'Filmus',twitterSetup:TwitterSetup.first(), user: User.first()).save()
		concept.addToTweets(Tweet.first())
		
		def opinion = new Opinion(concept:concept, tweet:Tweet.first(), value:OpinionValue.POSITIVE).save()
		
		
		def tweetService = new TweetService()
		def filters = [new Filter(attribute:"conceptsId",value: concept.id, type:FilterType.EQ)]
		def tweetList = tweetService.getTweets(filters, [:])
		assert tweetList.resultList.size() == 1
		assert tweetList.resultList.get(0).value == OpinionValue.POSITIVE
	}*/
	
	
	@Test
	void testGetTweetsWithoutConcept(){
		
		def tweetService = new TweetService()
		def tweetList = tweetService.getTweets([],[:])
		assert tweetList.resultList.size() == 1
	}
	
	

}
