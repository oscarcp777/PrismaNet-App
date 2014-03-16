package com.prismanet.PrismaNet;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.User;
import facebook4j.json.DataObjectFactory;

/**
 * Hello world!
 *
 */
public class App
{
	private static DB db;
	final static String FACEBOOK="yyyy-MM-dd'T'HH:mm:ssZZZZ";
	static SimpleDateFormat sf;
	
	public static void main( String[] args ) throws FacebookException, UnknownHostException   {
		
		MongoClient client =  new MongoClient(new MongoClientURI("mongodb://localhost"));
		db = client.getDB("prismanet");
		sf = new SimpleDateFormat(FACEBOOK, Locale.ENGLISH);
		sf.setLenient(false);
		
		App app = new App();
		app.startJob();
	}

	public void startJob() throws FacebookException{
		Facebook facebook = new FacebookFactory().getInstance();
//		AccessToken token = facebook.getOAuthAppAccessToken();
//		System.out.println(token.getToken());	
		
		DBCollection config = db.getCollection("configFacebook");
		if (config.getCount() == 0)
			throw new RuntimeException("No hay una configuracion definida");

		String args = (String)config.findOne().get("config");
		System.out.println("Args: " + args);
		
		ArrayList<User> users = new ArrayList<User>();
		for (String id : args.split(",")) {
			User user = facebook.getUser(id);
			if (user != null)
				users.add(user);
		}
		
		for (User currentUser : users) {
			ResponseList<Post> response = facebook.getPosts(currentUser.getId());
			for (Iterator<Post> iterator = response.iterator(); iterator.hasNext();) {
				Post post = (Post) iterator.next();
				System.out.println("Post: " +post.getName());
				System.out.println(post.getCreatedTime());
				this.savePost(post);
			}
		}
	}
	
	
	private void savePost(Post p){
		DBObject post = (DBObject) JSON.parse(DataObjectFactory.getRawJSON(p));
		System.out.println(post);
		Object id = post.get("id");
		post.removeField("id");
		post.put("_id",id);
		
		Date postCreatedDate = null;
		Date postUpdatedDate = null;
		try {
			String postCreated = (String)post.get("created_time");
			String postUpdated = (String)post.get("updated_time");

			postCreatedDate = sf.parse(postCreated);
			postUpdatedDate = sf.parse(postUpdated);

			post.removeField("created_time");
			post.removeField("updated_time");
			post.put("created_time",postCreatedDate);
			post.put("updated_time",postUpdatedDate);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		db.getCollection("posts").update(new BasicDBObject("_id",post.get("_id")),post, true, false);
	}
	


}
