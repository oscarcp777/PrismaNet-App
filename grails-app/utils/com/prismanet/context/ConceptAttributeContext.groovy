package com.prismanet.context


class ConceptAttributeContext extends AttributeContext {

	@Override
	protected void initializeRelations() {
		addPropertyRelation("id", "id", null, null);
		addPropertyRelation("conceptName", "conceptName", null, null);
		
		addPropertyRelation("sex", "sex", null, "tweets.author");
		addPropertyRelation("authorFollowers", "followers", null, "tweets.author");
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
		addPropertyRelation("tweetTimeCreated", "created", null, "tweets");
		
		
		addPropertyRelation("postAuthorFollowers", "followers", null, "posts.author");
		addPropertyRelation("postCreated", "date", null, "posts");
		addPropertyRelation("postPeriod", "period", null, "posts");
		addPropertyRelation("postYear", "year", null, "posts");
		addPropertyRelation("postMonth", "month", null, "posts");
		addPropertyRelation("postHour", "hour", null, "posts");
		addPropertyRelation("postMinute", "minute", null, "posts");
		addPropertyRelation("postByHour", "dateByHour", null, "posts");
		addPropertyRelation("postByMinute", "dateByMinute", null, "posts");
		addPropertyRelation("postTime", "time", null, "posts");
		addPropertyRelation("postDay", "day", null, "posts");
		addPropertyRelation("postsId", "id", null, "posts");
		addPropertyRelation("postTimeCreated", "created", null, "posts");
	}

	

	
	
}
