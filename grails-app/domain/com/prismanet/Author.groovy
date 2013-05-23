package com.prismanet

class Author {
	
	Date userSince
	String accountName
	byte[] photo
	
	static hasMany = [tweets:Tweet, posts:Post]
	
    static constraints = {
    }
}
