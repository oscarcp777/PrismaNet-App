package com.prismanet.context


class OpinionAttributeContext extends AttributeContext {

	@Override
	protected void initializeRelations() {
		addPropertyRelation("id", "id", null, null);
		addPropertyRelation("conceptId", "id", null, "concept");
		addPropertyRelation("conceptName", "conceptName", null, "concept");
		
		addPropertyRelation("sex", "sex", null, "mention.author");
		addPropertyRelation("authorFollowers", "followers", null, "mention.author");
		addPropertyRelation("dateCreated", "date", null, "mention");
		addPropertyRelation("datePeriod", "period", null, "mention");
		addPropertyRelation("dateYear", "year", null, "mention");
		addPropertyRelation("dateMonth", "month", null, "mention");
		addPropertyRelation("dateHour", "hour", null, "mention");
		addPropertyRelation("dateMinute", "minute", null, "mention");
		addPropertyRelation("dateByHour", "dateByHour", null, "mention");
		addPropertyRelation("dateByMinute", "dateByMinute", null, "mention");
		addPropertyRelation("dateTime", "time", null, "mention");
		addPropertyRelation("dateDay", "day", null, "mention");
		addPropertyRelation("mentionId", "id", null, "mention");
		addPropertyRelation("created", "created", null, "mention");
		addPropertyRelation("sourceType", "class", null, "mention");
		addPropertyRelation("value", "value", null, null);
		
		
	}

	

	
	
}
