package com.prismanet.context


class ConceptAttributeContext extends AttributeContext {

	@Override
	protected void initializeRelations() {
		addPropertyRelation("id", "id", null, null);
		addPropertyRelation("conceptName", "conceptName", null, null);
		
		addPropertyRelation("sex", "sex", null, "mentions.author");
		addPropertyRelation("authorFollowers", "followers", null, "mentions.author");
		addPropertyRelation("dateCreated", "date", null, "mentions");
		addPropertyRelation("datePeriod", "period", null, "mentions");
		addPropertyRelation("dateYear", "year", null, "mentions");
		addPropertyRelation("dateMonth", "month", null, "mentions");
		addPropertyRelation("dateHour", "hour", null, "mentions");
		addPropertyRelation("dateMinute", "minute", null, "mentions");
		addPropertyRelation("dateByHour", "dateByHour", null, "mentions");
		addPropertyRelation("dateByMinute", "dateByMinute", null, "mentions");
		addPropertyRelation("dateTime", "time", null, "mentions");
		addPropertyRelation("dateDay", "day", null, "mentions");
		addPropertyRelation("mentionId", "id", null, "mentions");
		addPropertyRelation("created", "created", null, "mentions");
		addPropertyRelation("sourceType", "class", null, "mentions");
		
		
	}

	

	
	
}
