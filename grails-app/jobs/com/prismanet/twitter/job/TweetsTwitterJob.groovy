package com.prismanet.twitter.job

import twitter4j.*

import com.prismanet.TweetService

class TweetsTwitterJob {

/*	def tweetService


	def startDelay = 100000
	def group = "tweetsJobs"*/
	
	//	UserJSONService userJSONService;
	//    static triggers = {
	//      simple repeatInterval: 600000l, repeatCount:-1 , startDelay: 50000000// execute job once in 5 seconds
	//	}

	/*def execute(StatusListener listener) {
		
		print "Starting the Tweets job."
		
		//		def json =loadUserTwitter("oscarcp777")
		//		UserJSON userJson = parserUser(json)
		//        userJSONService.saveUserJSON(userJson);
//		tweetService = service
		tweetService.

		println "Running Job!" + new Date()
	}*/

	/*def void saveTweet(Status status){
		Tweet tweet = new Tweet(content:status.getText());
		tweetService.saveFromTwitter(tweet);
	}*/

	


	//	String loadUserTwitter(def user4j){
	//		Twitter twitter = new TwitterFactory().getInstance()
	//        User user = twitter.showUser(user4j)
	//		return DataObjectFactory.getRawJSON(user)
	//	}
	//	UserJSON parserUser(def json){
	//		Gson myGson = new GsonBuilder().setDateFormat("EEE MMM dd HH:mm:ss z yyyy").serializeNulls().create()
	//		JsonReader jsonReader = new JsonReader(new StringReader(json))
	//		UserJSON userJson=myGson.fromJson(jsonReader,UserJSON)
	//		userJson.id=null //TODO el parser pisa el id auto por eso hay que reinciarlo
	//		return userJson
	//	}
}
