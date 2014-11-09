package com.prismanet

class FacebookComment extends Mention{
	
	String postId
	String commentId
	
	static constraints = {
	}
	
	@Override
	public String toString() {
		return author?.accountName + "-" + content;
	}
	
	public MentionType getMentionType(){
		return MentionType.FACEBOOK
	}
}
