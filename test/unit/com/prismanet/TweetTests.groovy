package com.prismanet

import grails.test.GrailsUnitTestCase
import grails.test.mixin.*
import grails.test.mixin.support.*

class TweetTests extends GrailsUnitTestCase{
	
	void testConstraints() {
		def tweet = new Tweet(content:"texto",author:new Author(), created:new Date(), tweetId:1l, retweet:false)
//		def concept = new Concept(conceptName : 'Comida', user: new User())
//		tweet.addToConcepts(concept)
		mockForConstraintsTests(Tweet, [tweet])
		boolean res = tweet.validate()
		assertTrue res

		def testTweet = new Tweet(created:new Date())
		assertFalse testTweet.validate()
		assertEquals testTweet.errors.getErrorCount(),4
		assertEquals "nullable", testTweet.errors["content"]
		assertEquals "nullable", testTweet.errors["author"]
		assertEquals "nullable", testTweet.errors["retweet"]
		assertEquals "nullable", testTweet.errors["tweetId"]

	}

	void testParseDate() {
		GregorianCalendar gC = new GregorianCalendar()
		gC.set(2013, 0, 20, 22, 9, 33);
		
		Tweet tweet = new Tweet()
		tweet.setCreated(gC.getTime())
		
		assertEquals tweet.year,2013
		assertEquals tweet.month,1
		assertEquals tweet.day,20
		assertEquals tweet.period,"201301"
		assertEquals tweet.time,"22:09:33"
		assertEquals tweet.date,"20/01/2013"
		assertEquals tweet.hour,22
		assertEquals tweet.minute,"22:09"
		assertEquals tweet.dateByHour,"20/01/2013 22"
		assertEquals tweet.dateByMinute,"20/01/2013 22:09"
	}
	
	
	void testMentionType() {
		Tweet tweet = new Tweet()
		assertEquals tweet.mentionType,MentionType.TWITTER
	}
	void testContentHtml(){
		
		Tweet tweet = new Tweet()
		tweet.content="#TodosenOctubre"
		assertEquals tweet.contentHtml," <a href='https://twitter.com/search?q=%23TodosenOctubre&src=hash' target='_blanck'>#TodosenOctubre</a> "
		tweet.content="http://t.co/oySTCUHGze"
		assertEquals tweet.contentHtml," <a href='http://t.co/oySTCUHGze' target='_blanck'>http://t.co/oySTCUHGze</a> "
		tweet.content="https://t.co/oySTCUHGze"
		assertEquals tweet.contentHtml," <a href='https://t.co/oySTCUHGze' target='_blanck'>https://t.co/oySTCUHGze</a> "
		tweet.content="@ucrmendoza"
		assertEquals tweet.contentHtml," <a href='https://twitter.com/ucrmendoza' target='_blanck'>@ucrmendoza</a> "
		tweet.content="#NochesArgentinas no lo asusten a Robertito jajajajajajaja @LucioDiMatteo"
		assertEquals tweet.contentHtml," <a href='https://twitter.com/search?q=%23NochesArgentinas&src=hash' target='_blanck'>#NochesArgentinas</a> no lo asusten a Robertito jajajajajajaja  <a href='https://twitter.com/LucioDiMatteo' target='_blanck'>@LucioDiMatteo</a> "
	}
	
	
	
}
