package com.prismanet

import org.springframework.transaction.annotation.Transactional;

import grails.util.Holders;
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
	def grailsApplication
	def tweetService
	def group = "tweetsJobs"
	
	MongoClient client 
	
	public TweetJob(){
		client = new MongoClient(new MongoClientURI("mongodb://localhost"))
	}

	static triggers = {
		simple repeatInterval: 60000, repeatCount:-1 , startDelay: 60000
	}

	def execute() {
		if(grailsApplication.config.grails.jobs.disable)
		 return
		 
		println "TweetJob ejecutado: " + new Date()
		
		
		DB db = client.getDB("prismanet")
		DBCollection tweets = db.getCollection("tweets")
		use ( TimeCategory ) {
			
			def newyears = new GregorianCalendar(2013, Calendar.OCTOBER, 14,1,35)
			Date filteredDate = new Date()-1.minute
//			Date filteredDate = newyears.time
			print "fecha a filtrar: " + filteredDate
			def filters = [created_at:['$gte': filteredDate]]
			DBCursor cursor = tweets.find(filters as BasicDBObject)
			cursor.addOption(com.mongodb.Bytes.QUERYOPTION_NOTIMEOUT)
//			def List<Concept> concepts = new ConceptService().listConcept()
			for (BasicDBObject tweet : cursor){
				JSONObject obj = new JSONObject(tweet)
				Status status = new StatusJSONImpl(obj)
//				System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
				tweetService.saveTweet(status)
			}
			cursor.close()
		}
		
	}
	
	
}
