package com.prismanet


class Tweet extends Mention{
	String content
	Boolean retweet
	Long tweetId
	
	
	static belongsTo = [author:Author]

    static constraints = {
		content(maxLength:140)
		tweetId unique:true  
    }
	
	public Tweet(){
		mentionType = MentionType.TWITTER
	}
	
	
	@Override
	public String toString() {time
		return author?.accountName + "-" + content;
	}
	
	
	
}
