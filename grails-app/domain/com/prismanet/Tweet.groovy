package com.prismanet

class Tweet {
	
	String content
	Boolean retweet
	
	static belongsTo = [author:Author]

    static constraints = {
    }
}
