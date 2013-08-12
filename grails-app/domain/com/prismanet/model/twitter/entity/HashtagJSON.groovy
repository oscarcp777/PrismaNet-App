package com.prismanet.model.twitter.entity

import java.util.List;

class HashtagJSON {
	static mapWith = "mongo"
	String text;
	List<Integer> indices;
	static constraints = {
	}
	static mapping ={
		version false
  }
}
