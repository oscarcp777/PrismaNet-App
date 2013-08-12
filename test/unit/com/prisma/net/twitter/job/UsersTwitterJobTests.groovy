package com.prisma.net.twitter.job


import grails.test.mixin.*
import grails.test.mixin.support.*

import org.junit.Test

import twitter4j.*
import twitter4j.json.DataObjectFactory

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import com.prismanet.model.twitter.user.UserJSON


class UsersTwitterJobTests {
   
	@Test
    void testExecute() {
		UsersTwitterJob job= new  UsersTwitterJob();
		job.execute();
    }
}
