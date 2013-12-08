package com.prismanet

import grails.test.GrailsUnitTestCase
import grails.test.mixin.*
import grails.test.mixin.support.*

class TweetTests extends GrailsUnitTestCase{

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
	
	
	
	
}
