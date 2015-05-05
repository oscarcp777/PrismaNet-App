package com.prismanet

import grails.test.GrailsUnitTestCase
import grails.test.mixin.*
import grails.test.mixin.support.*

class FacebookCommentTests extends GrailsUnitTestCase{
	
	void testConstraints() {
		def post = new FacebookComment(content:"texto",author:new Author(), created:new Date(), postId:1l)
		mockForConstraintsTests(FacebookComment, [post])
		assertTrue post.validate()
		
		def testpost = new FacebookComment(created:new Date())
		assertFalse testpost.validate()
		assertEquals testpost.errors.getErrorCount(),2
		assertEquals "nullable", testpost.errors["content"]
		assertEquals "nullable", testpost.errors["author"]
	}

	
	
	
	
	
}
