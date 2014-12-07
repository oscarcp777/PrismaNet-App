package com.prismanet.PrismaNet;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;

import facebook4j.Comment;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Like;
import facebook4j.Post;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.Summary;
import facebook4j.User;
import facebook4j.json.DataObjectFactory;

/**
 * Hello world!
 *
 */
public class App
{
	static final Logger logger = Logger.getLogger(App.class.getName());
	private static MongoClient client;
	private static DB db;
	final static String FACEBOOK="yyyy-MM-dd'T'HH:mm:ss+SSSS";
	static SimpleDateFormat sf;
	
	public static void main( String[] args ) throws FacebookException, UnknownHostException   {
		PropertyConfigurator.configure("log4j.properties");
		
		client =  new MongoClient(new MongoClientURI("mongodb://localhost"));
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
		logger.info("Args: " + args);
		
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
				for (Comment comment : post.getComments()){
					comment.getFrom().getId();
					comment.getMessage();
					comment.getCreatedTime();
					comment.getId();
				}
				logger.info("Post Id:" +post.getId());
				logger.info("Post Link:" +post.getLink());
				logger.info("Fecha Creacion: " + post.getCreatedTime());
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
	}
	
	private void saveUser(DBObject user){
		logger.info(user);
		db.getCollection("users").insert(new BasicDBObject("id", user.get("id")).append("username", user.get("username")).append("name", user.get("name")).append("likes", user.get("likes")).append("talking_about_count", user.get("talking_about_count")).append("updated_time", new Date()));
	}

	
	private void savePost(DBObject post){
		logger.info((String)post.get("type")+":"+post);
		Object id = post.get("id");
//		post.removeField("id");
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
