package com.prismanet.importer

import com.mongodb.BasicDBObject
import com.mongodb.DB
import com.mongodb.DBCollection
import com.mongodb.DBCursor
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI

class MongoPostsImporter {
	
	MongoClient client
	
	public MongoPostsImporter(def uri){
		client = new MongoClient(new MongoClientURI(uri))
	}
	
	
	/**
	 * @param dates - map con valores posibles dateFrom y dateTo, ambos opcionales, indican fecha desde y hasta
	 * 		para realizar la consulta
	 * @return lista de objetos que machean con la busqueda
	 */
	def importPosts(dates){
		DB db = client.getDB("prismanet")
		DBCollection posts = db.getCollection("posts")
		
		
		def filters = []
		if (dates.dateFrom && dates.dateTo){
			print "entra aca"
			filters = ['$and':[[created_time:['$gte': dates.dateFrom]], [created_time:['$lte': dates.dateTo]]]]
		}else{
			if (dates.dateFrom)
		 		filters.add(created_time:['$gte': dates.dateFrom])
			if (dates.dateTo)
			 filters.add(created_time:['$lte': dates.dateTo])
		}
		DBCursor cursor = posts.find(filters as BasicDBObject)
		cursor.addOption(com.mongodb.Bytes.QUERYOPTION_NOTIMEOUT)
		
		cursor
	}
	
	def setConfiguration(config){
		DB db = client.getDB("prismanet")
		DBCollection setup = db.getCollection("configFacebook")
		BasicDBObject obj = new BasicDBObject()
		obj.append("config", config)
		setup.update(new BasicDBObject(), obj)
	}

}
