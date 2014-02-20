package com.prismanet

class FacebookSetup {

	static belongsTo = Concept
	static hasMany = [keywords: Keyword]

	static constraints = {
		keywords(nullable:true)
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		new FacebookSetup(this)
	}
}
