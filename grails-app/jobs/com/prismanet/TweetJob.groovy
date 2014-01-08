package com.prismanet

import groovy.time.TimeCategory
import twitter4j.Status
import twitter4j.internal.json.StatusJSONImpl
import twitter4j.internal.org.json.JSONObject

import com.mongodb.BasicDBObject
import com.mongodb.DB
import com.mongodb.DBCollection
import com.mongodb.DBCursor
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.prismanet.importer.MongoTweetsImporter

class TweetJob {
	def grailsApplication
	def tweetService
	def group = "tweetsJobs"
	
	static triggers = {
		simple repeatInterval: 60000000, repeatCount:-1 , startDelay: 60000
	}

	def execute() {
		if(grailsApplication.config.grails.jobs.disable)
		 return
		 
		println "TweetJob ejecutado: " + new Date()
		
		
		use ( TimeCategory ) {
			
			def d1 = new GregorianCalendar(2013, Calendar.OCTOBER, 27,11,00)
//			def d2 = new GregorianCalendar(2013, Calendar.OCTOBER, 14,1,36)
			Date filteredDate = new Date()-1.minute
			
			def dates = [:]
//			dates.put("dateFrom",filteredDate)
			dates.put("dateFrom",d1.time)
//			dates.put("dateTo",d2.time)
			
			print "filtros: " + dates
			MongoTweetsImporter importer = new MongoTweetsImporter("mongodb://localhost")
			def tweets = importer.importTweets(dates)
			print "-------------------------"
			print tweets
			def iterator = tweets.iterator()
			def partialList = []
			int i = 0
			print "tweets: " + iterator.size()
			while (iterator.hasNext()){
				partialList.add(iterator.next())
				i++
//				print i
				if (i % 100 == 0){
//					println "Nuevo Lote" 
					i = 0
					tweetService.saveTweets(partialList)
					partialList = []
				}
			}
			tweetService.saveTweets(partialList)
		}
		
	}
	
	
}
