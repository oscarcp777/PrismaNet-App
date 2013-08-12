package com.prismanet.model.twitter.user

import com.google.gson.GsonBuilder
import com.prismanet.model.twitter.tweet.StatusJSON


class UserJSON {
	static mapWith = "mongo"
	static embedded = ['entities', 'status']
	String id_str;
	String name;
	String screen_name;
	String location;
	String description;
	String url;
	UserEntitiesJSON entities;
	Boolean isProtected;
	Integer followers_count;
	Integer friends_count;
	Integer listed_count;
	Date created_at;
	Integer favourites_count;
	Integer utc_offset;
	String time_zone;
	Boolean geo_enabled;
	String lang;
	Integer statuses_count;
	StatusJSON status;
	Boolean verified;
	Boolean contributors_enabled;
	Boolean is_translator;
	String profile_background_color;
	String profile_background_image_url;
	String profile_background_image_url_https;
	Boolean profile_background_tile;
	String profile_image_url;
	String profile_image_url_https;
	String profile_text_color;
	String profile_link_color;
	String profile_sidebar_fill_color;
	String profile_sidebar_border_color;
	Boolean profile_use_background_image;
	Boolean default_profile;
	Boolean default_profile_image;
	Boolean following;
	Boolean follow_request_sent;
	Boolean notifications;
	
	String toString(){
		return new GsonBuilder().setPrettyPrinting().create().toJson(this);
	  }
    static constraints = {
	}
	static mapping ={
		version false
  }
}






