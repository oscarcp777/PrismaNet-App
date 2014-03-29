package com.prismanet

class FacebookAuthor extends Author{
	
	String facebookAuthorId 
	
	static constraints = {
		facebookAuthorId(nullable:true)
	}
	
}
