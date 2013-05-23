package com.prismanet

class Post {
	
	String content
	
	static belongsTo = [author:Author]

    static constraints = {
    }
}
