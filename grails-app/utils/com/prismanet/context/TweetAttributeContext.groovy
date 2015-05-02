package com.prismanet.context


class TweetAttributeContext extends MentionAttributeContext {

	@Override
	protected void initializeRelations() {
		super.initializeRelations()
		addPropertyRelation("authorFollowers", "followers", null, "author");
		addPropertyRelation("retweetCount", "retweetCount", null, null);
		addPropertyRelation("favoriteCount", "favoriteCount", null, null);
	}

	

	
	
}
