package com.prismanet.context


class UserAttributeContext extends AttributeContext {

	@Override
	protected void initializeRelations() {
		addPropertyRelation("id", "id", null, null);
		addPropertyRelation("conceptsId", "id", null, "concepts");
		addPropertyRelation("sex", "sex", null, "concepts.tweets.author");
		addPropertyRelation("tweetCreated", "created", null, "concepts.tweets");
		addPropertyRelation("tweetsId", "id", null, "concepts.tweets");
	}

	

	
	
}
