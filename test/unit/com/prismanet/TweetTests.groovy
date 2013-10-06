package com.prismanet

import grails.test.GrailsUnitTestCase
import grails.test.mixin.*
import grails.test.mixin.support.*

class TweetTests extends GrailsUnitTestCase{

	void testParseDate() {
		GregorianCalendar gC = new GregorianCalendar()
		gC.set(2013, 0, 20, 22, 9, 33);
		
		Tweet tweet = new Tweet()
		def resultMap = tweet.parseDate(gC.getTime())
		
		assertEquals resultMap['year'],2013
		assertEquals resultMap['month'],1
		assertEquals resultMap['day'],20
		assertEquals resultMap['period'],"201301"
		assertEquals resultMap['time'],"22:09:33"
		assertEquals resultMap['date'],"20/01/2013"
		assertEquals resultMap['hour'],22
		assertEquals resultMap['minute'],"22:09"
		

	}
	
	
	
	
}
