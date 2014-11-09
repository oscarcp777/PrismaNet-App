package com.prismanet.importer

import com.mongodb.BasicDBObject
import com.mongodb.DB
import com.mongodb.DBCollection
import com.mongodb.DBCursor
import com.mongodb.DBObject
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.util.JSON

import facebook4j.Comment
import facebook4j.Facebook
import facebook4j.FacebookException
import facebook4j.FacebookFactory
import facebook4j.Like
import facebook4j.Post
import facebook4j.Reading
import facebook4j.ResponseList
import facebook4j.User
import facebook4j.Summary;
import facebook4j.json.DataObjectFactory

class MongoPostsImporter {
	
	MongoClient client
	
	public MongoPostsImporter(def uri){
		client = new MongoClient(new MongoClientURI(uri))
	}
	
	def close(){
		client.close();
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
			filters = ['$and':[[created_time:['$gte': dates.dateFrom]], [created_time:['$lte': dates.dateTo]]]]
		}else{
			if (dates.dateFrom)
		 		filters.add(created_time:['$gte': dates.dateFrom])
			if (dates.dateTo)
			 filters.add(created_time:['$lte': dates.dateTo])
		}
		DBCursor cursor = posts.find(filters as BasicDBObject)
//		cursor.addOption(com.mongodb.Bytes.QUERYOPTION_NOTIMEOUT)
		
		cursor
	}
	
	def setConfiguration(config){
		DB db = client.getDB("prismanet")
		DBCollection setup = db.getCollection("configFacebook")
		BasicDBObject obj = new BasicDBObject()
		obj.append("config", config)
		setup.update(new BasicDBObject(), obj, true, false)
		
	}
	
	
	def getPostStatsDataFromFacebook() throws FacebookException{
		Facebook facebook = new FacebookFactory().getInstance();
		
		DBCollection config = client.getDB("prismanet").getCollection("configFacebook");
		if (config.getCount() == 0)
			throw new RuntimeException("No hay una configuracion definida");

		String args = (String)config.findOne().get("config");
		log.info("Args: " + args);
		
		ArrayList<User> users = new ArrayList<User>();
		for (String id : args.split(",")) {
			User user = facebook.getUser(id);
			this.saveUser((DBObject) JSON.parse(DataObjectFactory.getRawJSON(user)));
			if (user != null)
				users.add(user);
		}
		
		ArrayList<DBObject> posts = new ArrayList<DBObject>();
		for (User currentUser : users) {
			ResponseList<Post> response = facebook.getPosts(currentUser.getId());
			for (Iterator<Post> iterator = response.iterator(); iterator.hasNext();) {
				Post post = (Post) iterator.next();
				DBObject jsonPost = (DBObject) JSON.parse(DataObjectFactory.getRawJSON(post));
				posts.add(jsonPost);
			}
		}
		int i = 0;
		for (DBObject currentPost : posts) {
			ResponseList<Like> likes = facebook.getPostLikes((String)currentPost.get("id"), new Reading().summary());
			Summary summaryLikes = likes != null ? likes.getSummary() : null;
			Integer totalLikes = summaryLikes != null ? summaryLikes.getTotalCount() : 0;
			currentPost.put("totalLikes", totalLikes != null ? totalLikes : 0);
			
			ResponseList<Comment> comments = facebook.getPostComments((String)currentPost.get("id"), new Reading().summary());
			Summary summaryComments = comments != null ? comments.getSummary() : null;
			Integer totalComments = summaryComments != null ? summaryComments.getTotalCount() : 0;
			currentPost.put("totalComments", totalComments != null ? totalComments : 0);
			this.savePost(currentPost);
			i++;
		}

		client.close();
		
		def result = [posts:posts,users:users]
	}
	
	private void saveUser(DBObject user){
		log.info(user);
		client.getDB("prismanet").getCollection("users").insert(new BasicDBObject("id", user.get("id")).append("username", user.get("username")).append("name", user.get("name")).append("likes", user.get("likes")).append("talking_about_count", user.get("talking_about_count")).append("updated_time", new Date()));
	}

	
	private void savePost(DBObject post){
		log.info((String)post.get("type")+":"+post);
		Object id = post.get("id");
		post.put("_id",id);
		client.getDB("prismanet").getCollection("postsStats").update(new BasicDBObject("_id",post.get("_id")),post, true, false);
	}


}
