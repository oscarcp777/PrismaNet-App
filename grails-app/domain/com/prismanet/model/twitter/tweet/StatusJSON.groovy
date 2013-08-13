package com.prismanet.model.twitter.tweet

import com.google.gson.GsonBuilder
import com.prismanet.model.twitter.place.GeoLocationJSON
import com.prismanet.model.twitter.place.PlaceJSON

class StatusJSON {
	static mapWith = "mongo"
	static embedded = ['contributors',"coordinates","entities","geo","place"]
	
	Date dateCreated;
	List<Long> contributors;
	GeoLocationJSON coordinates;
	Date created_at;
	StatusEntitiesJSON entities;
	Long favorite_count;
	Boolean favorited;
	GeoLocationJSON geo;
	String id_str;
	String in_reply_to_screen_name;
	Long in_reply_to_status_id;
	String in_reply_to_status_id_str;
	Long in_reply_to_user_id;
	String in_reply_to_user_id_str;
	String lang;
	PlaceJSON place;
	Long retweet_count;
	Boolean retweeted;
	String source;
	String text;
	Boolean truncated;
	String toString(){
		return new GsonBuilder().setPrettyPrinting().create().toJson(this);
	  }
	static constraints = {
	}
	static mapping ={
		version false
  }
}
