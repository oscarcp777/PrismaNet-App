package com.prismanet.context


class PostAttributeContext extends AttributeContext {

	@Override
	protected void initializeRelations() {
		addPropertyRelation("id", "id", null, null);
		addPropertyRelation("sex", "sex", null, "author");
		addPropertyRelation("authorFollowers", "followers", null, "author");
		addPropertyRelation("tweetCreated", "date", null, null);
		addPropertyRelation("tweetPeriod", "period", null, null);
		addPropertyRelation("tweetYear", "year", null, null);
		addPropertyRelation("tweetMonth", "month", null, null);
		addPropertyRelation("tweetHour", "dateByHour", null, null);
		addPropertyRelation("tweetMinute", "dateByMinute", null, null);
		addPropertyRelation("tweetTime", "time", null, null);
		addPropertyRelation("tweetDay", "day", null, null);
		addPropertyRelation("tweetsId", "id", null, null);
		addPropertyRelation("created", "created", null, null);
		addPropertyRelation("conceptsId", "id", null, "concepts");
	}

	

	
	
}
