package com.prismanet

class FacebookComment extends Mention{
	
	String postId
	String commentId
	
	
	
	static constraints = {
		postId(nullable:true)
    }
	
	@Override
	public String toString() {time
		return author?.accountName + "-" + content;
	}
	
	public MentionType getMentionType(){
		return MentionType.FACEBOOK
	}
}
