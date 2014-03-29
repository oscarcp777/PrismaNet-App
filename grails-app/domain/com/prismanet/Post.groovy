package com.prismanet

class Post extends Mention{
	
	String postId
	String commentId
	
	static belongsTo = Concept
	static hasMany = [concepts:Concept]
	
	static constraints = {
		postId(nullable:true)
    }
	
	@Override
	public String toString() {time
		return author?.accountName + "-" + content;
	}
}
