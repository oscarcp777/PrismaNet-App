package com.prismanet.context


class ConceptAttributeContext extends AttributeContext {

	@Override
	protected void initializeRelations() {
		addPropertyRelation("id", "id", null, null);
		addPropertyRelation("sex", "sex", null, "tweets.author");
		addPropertyRelation("authorFollowers", "followers", null, "tweets.author");
		addPropertyRelation("tweetCreated", "date", null, "tweets");
		addPropertyRelation("tweetPeriod", "period", null, "tweets");
		addPropertyRelation("tweetYear", "year", null, "tweets");
		addPropertyRelation("tweetMonth", "month", null, "tweets");
		addPropertyRelation("tweetHour", "hour", null, "tweets");
		addPropertyRelation("tweetMinute", "minute", null, "tweets");
		addPropertyRelation("tweetTime", "time", null, "tweets");
		addPropertyRelation("tweetDay", "day", null, "tweets");
		addPropertyRelation("tweetsId", "id", null, "tweets");
		addPropertyRelation("created", "created", null, "tweets");
	}

	

	
	
}
