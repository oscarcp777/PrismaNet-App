package com.prismanet.context


class ConceptAttributeContext extends AttributeContext {

	@Override
	protected void initializeRelations() {
		addPropertyRelation("sex", "sex", null, "tweets.author");
		addPropertyRelation("tweetCreated", "created", null, "tweets");
		addPropertyRelation("tweetsId", "id", null, "tweets");
	}

	

	
	
}
