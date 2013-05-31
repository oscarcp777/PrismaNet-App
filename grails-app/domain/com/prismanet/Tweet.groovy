package com.prismanet

class Tweet {
	
	String content
	Boolean retweet
	Date created
	static belongsTo = [author:Author]

    static constraints = {
		content(maxLength:140) 
    }
	
	@Override
	public String toString() {
		return author.accountName + "-" + content;
	}
}
