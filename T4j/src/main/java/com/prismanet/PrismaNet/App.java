package com.prismanet.PrismaNet;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterObjectFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.json.DataObjectFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;

/**
 * Hello world!
 *
 */
public class App implements StatusListener
{
	static final Logger logger = Logger.getLogger(App.class.getName());
	private static DB db;

	final static String TWITTER="EEE MMM dd HH:mm:ss ZZZZZ yyyy";
	
	static SimpleDateFormat sf;


	public static void main( String[] args ) throws UnknownHostException   {
		
		MongoClient client =  new MongoClient(new MongoClientURI("mongodb://localhost"));
		db = client.getDB("prismanet");
		sf = new SimpleDateFormat(TWITTER, Locale.ENGLISH);
		sf.setLenient(false);

		App app = new App();
		app.startJob();
	}
	public void startJob2(){
		Properties props = new Properties();
		try {
			props.load( App.class.getClassLoader().getResourceAsStream("log4j.properties"));
		} catch (IOException e) {
			logger.info("Error habriendo log4j.properties");
			logger.error("Error habriendo log4j.properties",e);
		}
		PropertyConfigurator.configure(props);
		logger.info("Incio de servicio");
	}
	public void startJob(){
		Properties props = new Properties();
		try {
			props.load( App.class.getClassLoader().getResourceAsStream("log4j.properties"));
		} catch (IOException e) {
			logger.info("Error habriendo log4j.properties");
			logger.error("Error habriendo log4j.properties",e);
		}
		PropertyConfigurator.configure(props);
		logger.info("Incio de servicio");
		DBCollection config = db.getCollection("config");
		if (config.getCount() == 0)
			throw new RuntimeException("No hay una configuracion definida");

		String args = (String)config.findOne().get("config");
		logger.info("Args: " + args);

		
//		DBCursor cursor = db.getCollection("tweets").find(new BasicDBObject("created_at", new BasicDBObject("$lt", new Date())));
//		for (DBObject tweet : cursor){
//			logger.info("@" + ((DBObject)tweet.get("user")).get("screen_name") + " - " + tweet.get("Text"));
//		}

		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
		twitterStream.addListener(this);
		List<Long> follow = new ArrayList<Long>();
		List<String> track = new ArrayList<String>();
		//		for (String arg : args) {
		if (isNumericalArgument(args)) {
			for (String id : args.split(",")) {
				follow.add(Long.parseLong(id));
			}
		} else {
			track.addAll(Arrays.asList(args.split(",")));
		}
		//		}
		long[] followArray = new long[follow.size()];
		for (int i = 0; i < follow.size(); i++) {
			followArray[i] = follow.get(i);
		}
		String[] trackArray = track.toArray(new String[track.size()]);

		// filter() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
		twitterStream.filter(new FilterQuery(0, followArray, trackArray));

	}


	private boolean isNumericalArgument(String argument) {
		boolean isNumericalArgument = true;
		for (String arg : argument.split(", ",-1)) {
			try {
				Integer.parseInt(arg);
			} catch (NumberFormatException nfe) {
				isNumericalArgument = false;
				break;
			}
		}
		return isNumericalArgument;
	}

	private void saveTweet(Status status){
		DBObject tweet = (DBObject) JSON.parse(TwitterObjectFactory.getRawJSON(status));
		Object id = tweet.get("id");
//		tweet.removeField("id");
		tweet.put("_id",id);


		Date tweetCreationDate = null;
		Date userCreationDate = null;
		try {
			String tweetCreation = (String)tweet.get("created_at");
			String userCreation = (String)((DBObject)tweet.get("user")).get("created_at");

			tweetCreationDate = sf.parse(tweetCreation);
			userCreationDate = sf.parse(userCreation);

			tweet.removeField("created_at");
			((DBObject)tweet.get("user")).removeField("user.created_at");
			tweet.put("created_at",tweetCreationDate);
			((DBObject)tweet.get("user")).put("created_at",userCreationDate);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		db.getCollection("tweets").update(new BasicDBObject("_id",tweet.get("_id")),tweet, true, false);
	}

	public void onStatus(Status status) {
		this.saveTweet(status);
	}

	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
		logger.info("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
	}

	public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
		logger.info("Got track limitation notice:" + numberOfLimitedStatuses);
	}

	public void onScrubGeo(long userId, long upToStatusId) {
		logger.info("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
	}

	public void onStallWarning(StallWarning warning) {
		logger.info("Got stall warning:" + warning);
	}

	public void onException(Exception ex) {
		logger.error("error", ex);
	}
}
