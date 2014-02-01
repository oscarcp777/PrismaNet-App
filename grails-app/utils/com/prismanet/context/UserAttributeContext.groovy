package com.prismanet.context


class UserAttributeContext extends AttributeContext {

	@Override
	protected void initializeRelations() {
		addPropertyRelation("id", "id", null, null);
		addPropertyRelation("conceptsId", "id", null, "concepts");
		addPropertyRelation("conceptsName", "conceptName", null, "concepts");
		addPropertyRelation("authorId", "id", null, "concepts.tweets.author");
		addPropertyRelation("sex", "sex", null, "concepts.tweets.author");
		addPropertyRelation("tweetCreated", "date", null, "concepts.tweets");
		addPropertyRelation("tweetsId", "id", null, "concepts.tweets");
		addPropertyRelation("tweetPeriod", "period", null, "concepts.tweets");
		addPropertyRelation("tweetYear", "year", null, "concepts.tweets");
		addPropertyRelation("tweetMonth", "month", null, "concepts.tweets");
		addPropertyRelation("tweetHour", "hour", null, "concepts.tweets");
		addPropertyRelation("tweetMinute", "minute", null, "concepts.tweets");
		addPropertyRelation("tweetTime", "time", null, "concepts.tweets");
		addPropertyRelation("tweetDay", "day", null, "concepts.tweets");
		addPropertyRelation("created", "created", null, "concepts.tweets");
	}

	

	
	
}
