package com.prismanet.context


class PostAttributeContext extends AttributeContext {

	@Override
	protected void initializeRelations() {
		addPropertyRelation("id", "id", null, null);
		addPropertyRelation("dateCreated", "date", null, null);
		addPropertyRelation("datePeriod", "period", null, null);
		addPropertyRelation("dateYear", "year", null, null);
		addPropertyRelation("dateMonth", "month", null, null);
		addPropertyRelation("dateHour", "dateByHour", null, null);
		addPropertyRelation("dateMinute", "dateByMinute", null, null);
		addPropertyRelation("dateTime", "time", null, null);
		addPropertyRelation("dateDay", "day", null, null);
		addPropertyRelation("created", "created", null, null);
		addPropertyRelation("conceptsId", "id", null, "concepts");
		addPropertyRelation("userId", "id", null, "concepts.user");
		addPropertyRelation("totalLikes", "totalLikes", null, null);
	}

	

	
	
}
