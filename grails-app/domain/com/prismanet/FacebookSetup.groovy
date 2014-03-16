package com.prismanet

import java.util.Date;

class FacebookSetup {

	static belongsTo = Concept
	String keywords
	
	Date lastUpdated
	
	static constraints = {
		keywords(nullable:true, wordSetup:true)
		lastUpdated(nullable:true)
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		new FacebookSetup(this)
	}
}
