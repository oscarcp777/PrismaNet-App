package com.prismanet

import java.util.Date;

class FacebookSetup {

	static belongsTo = Concept
	String keywords
	String accounts
	
	Date lastUpdated
	
	static constraints = {
		keywords(nullable:true, wordSetup:true)
		accounts(nullable:true)
		lastUpdated(nullable:true)
	}
	
	@Override
	public String toString() {
		return keywords
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		FacebookSetup setup = new FacebookSetup()
		setup.keywords = keywords
		return setup
	}
}
