package com.prismanet.importer

import com.mongodb.BasicDBObject
import com.mongodb.DB
import com.mongodb.DBCollection
import com.mongodb.DBCursor
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI

class MongoTweetsImporter {
	
	MongoClient client
	
	public MongoTweetsImporter(def uri){
		client = new MongoClient(new MongoClientURI(uri))
	}
	
	
	/**
	 * @param dates - map con valores posibles dateFrom y dateTo, ambos opcionales, indican fecha desde y hasta
	 * 		para realizar la consulta
	 * @return lista de objetos que machean con la busqueda
	 */
	def importTweets(dates){
		DB db = client.getDB("prismanet")
		DBCollection tweets = db.getCollection("tweets")
		
		def filters = []
		if (dates.dateFrom && dates.dateTo){
			print "entra aca"
			filters = ['$and':[[created_at:['$gte': dates.dateFrom]], [created_at:['$lte': dates.dateTo]]]]
		}else{
			if (dates.dateFrom)
		 		filters.add(created_at:['$gte': dates.dateFrom])
			if (dates.dateTo)
			 filters.add(created_at:['$lte': dates.dateTo])
		}
		DBCursor cursor = tweets.find(filters as BasicDBObject)
		cursor.addOption(com.mongodb.Bytes.QUERYOPTION_NOTIMEOUT)
		
		cursor
	}

}
