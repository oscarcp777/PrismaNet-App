package com.prisma.net.twitter.job

import static org.junit.Assert.*
import twitter4j.*;
import twitter4j.internal.json.UserJSONImpl

import com.prisma.net.twitter.model.UserJSON

import grails.test.mixin.support.*

import org.junit.*



class UsersTwitterJobTests {
	Twitter twitter;
	@Before
    void setUp() {
       
    }
	@After
    void tearDown() {
        // Tear down logic here
    }
    @Test
    void testSomething() {
		Twitter twitter = new TwitterFactory().getInstance()
        User user = twitter.showUser("RichyDubini")
		println user
		
//		UserJSON userJson= new UserJSON(user);
//		println userJson
    }
}
