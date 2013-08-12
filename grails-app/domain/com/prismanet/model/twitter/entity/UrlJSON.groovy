package com.prismanet.model.twitter.entity

import java.util.List;

class UrlJSON {
	static mapWith = "mongo"
	String url;
	String  display_url;
	String  expanded_url;
	List<Integer> indices;
	static constraints = {
	}
	static mapping ={
		version false
  }
}
