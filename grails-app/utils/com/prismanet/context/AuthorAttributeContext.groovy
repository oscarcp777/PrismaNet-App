package com.prismanet.context


class AuthorAttributeContext extends AttributeContext {

	@Override
	protected void initializeRelations() {
		addPropertyRelation("id", "id", null, null);
		addPropertyRelation("conceptId", "id", null, "tweets.concepts");
		addPropertyRelation("conceptName", "conceptName", null,"tweets.concepts");
		addPropertyRelation("sex", "sex", null, null);
		addPropertyRelation("accountName", "accountName", null, null);
		addPropertyRelation("followers", "followers", null, null);
		addPropertyRelation("tweetCreated", "date", null, "tweets");
		addPropertyRelation("tweetPeriod", "period", null, "tweets");
		addPropertyRelation("tweetYear", "year", null, "tweets");
		addPropertyRelation("tweetMonth", "month", null, "tweets");
		addPropertyRelation("tweetHour", "hour", null, "tweets");
		addPropertyRelation("tweetMinute", "minute", null, "tweets");
		addPropertyRelation("tweetByHour", "dateByHour", null, "tweets");
		addPropertyRelation("tweetByMinute", "dateByMinute", null, "tweets");
		addPropertyRelation("tweetTime", "time", null, "tweets");
		addPropertyRelation("tweetDay", "day", null, "tweets");
		addPropertyRelation("tweetsId", "id", null, "tweets");
		addPropertyRelation("created", "created", null, "tweets");
	}

	

	
	
}
