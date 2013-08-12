package com.prismanet.model.twitter.entity

import java.util.List;

class MediaJSON {
	static mapWith = "mongo"
	static embedded = ["sizes"]
	String id_str;
	String url;
	String  display_url;
	String  expanded_url;
	String media_url;
	String media_url_https;
	String type;
	List<Integer> indices;
	MediaSizesJSON sizes;
	static constraints = {
	}
	static mapping ={
		version false
  }
}
