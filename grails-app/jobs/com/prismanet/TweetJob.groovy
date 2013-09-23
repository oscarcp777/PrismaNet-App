package com.prismanet

import groovy.time.TimeCategory

import org.codehaus.groovy.grails.web.json.JSONObject

import twitter4j.internal.org.json.JSONObject;
import twitter4j.StallWarning
import twitter4j.Status
import twitter4j.StatusDeletionNotice
import twitter4j.StatusListener
import twitter4j.internal.json.StatusJSONImpl

import com.mongodb.BasicDBObject
import com.mongodb.DB
import com.mongodb.DBCollection
import com.mongodb.DBCursor
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI

class TweetJob {

	def tweetService
	def group = "tweetsJobs"
	
	MongoClient client 
	
	public TweetJob(){
		client = new MongoClient(new MongoClientURI("mongodb://localhost"))
	}

	static triggers = {
		simple repeatInterval: 60000l, repeatCount:-1 , startDelay: 30000
	}

	def execute() {
		print "Starting the Tweets job."
		DB db = client.getDB("prismanet")
		DBCollection tweets = db.getCollection("tweets")
		use ( TimeCategory ) {
			Date filteredDate = new Date()-1.minute
			print "fecha a filtrar: " + filteredDate
			def filters = [created_at:['$gte': filteredDate]]
			DBCursor cursor = tweets.find(filters as BasicDBObject)
			for (BasicDBObject tweet : cursor){
				JSONObject obj = new JSONObject(tweet)
				Status status = new StatusJSONImpl(obj)
				System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
				tweetService.saveTweet(status)
			}
			cursor.close()
		}
		println "Running Job!" + new Date()
	}
	
	
}
