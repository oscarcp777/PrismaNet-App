package com.prismanet.model.twitter.entity

import java.util.List;

class UserMentionJSON {
	static mapWith = "mongo"
	String id_str;
	List<Integer> indices;
	String name;
	String screen_name;
	static constraints = {
	}
	static mapping ={
		version false
  }
}
