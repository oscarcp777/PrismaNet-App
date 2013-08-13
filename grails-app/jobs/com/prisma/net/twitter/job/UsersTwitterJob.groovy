package com.prisma.net.twitter.job

import twitter4j.*
import twitter4j.json.DataObjectFactory

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import com.prismanet.model.twitter.user.UserJSON
import com.prismanet.twitter.service.UserJSONService

class UsersTwitterJob {
	
	UserJSONService userJSONService;
    static triggers = {
      simple repeatInterval: 600000l, repeatCount:-1 , startDelay: 50000000// execute job once in 5 seconds
	  
	  }

    def execute() {
		def json =loadUserTwitter("oscarcp777")
		UserJSON userJson = parserUser(json)
        userJSONService.saveUserJSON(userJson);

       println "Running Job!"+new Date()
    }
	
	String loadUserTwitter(def user4j){
		Twitter twitter = new TwitterFactory().getInstance()
        User user = twitter.showUser(user4j)
		return DataObjectFactory.getRawJSON(user)
	}
	UserJSON parserUser(def json){
		Gson myGson = new GsonBuilder().setDateFormat("EEE MMM dd HH:mm:ss z yyyy").serializeNulls().create()
		JsonReader jsonReader = new JsonReader(new StringReader(json))
		UserJSON userJson=myGson.fromJson(jsonReader,UserJSON)
		userJson.id=null //TODO el parser pisa el id auto por eso hay que reinciarlo
		return userJson
	}
}
