package com.prismanet

import grails.test.mixin.*

@Mock(TwitterAuthor)
@TestFor(TwitterAuthor)
class TwitterAuthorTests{
	
	void testConstraints() {
		// Solo el nombre es obligatorio
		def author = new TwitterAuthor(name:"sdonik")
		mockForConstraintsTests(TwitterAuthor, [author])
		
		def result = author.validate()		
		author.errors.each {
			println it
		}
		assertTrue result
	}

}
