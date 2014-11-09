package com.prismanet.context


class UserAttributeContext extends AttributeContext {

	@Override
	protected void initializeRelations() {
		addPropertyRelation("id", "id", null, null);
		addPropertyRelation("conceptsId", "id", null, "concepts");
		addPropertyRelation("conceptsName", "conceptName", null, "concepts");
		addPropertyRelation("authorId", "id", null, "concepts.mentions.author");
		addPropertyRelation("sex", "sex", null, "concepts.mentions.author");
		addPropertyRelation("dateCreated", "date", null, "concepts.mentions");
		addPropertyRelation("mentionId", "id", null, "concepts.mentions");
		addPropertyRelation("datePeriod", "period", null, "concepts.mentions");
		addPropertyRelation("dateYear", "year", null, "concepts.mentions");
		addPropertyRelation("dateMonth", "month", null, "concepts.mentions");
		addPropertyRelation("dateHour", "hour", null, "concepts.mentions");
		addPropertyRelation("dateByHour", "dateByHour", null, "concepts.mentions");
		addPropertyRelation("dateByMinute", "dateByMinute", null, "concepts.mentions");
		addPropertyRelation("dateMinute", "minute", null, "concepts.mentions");
		addPropertyRelation("dateTime", "time", null, "concepts.mentions");
		addPropertyRelation("dateDay", "day", null, "concepts.mentions");
		addPropertyRelation("created", "created", null, "concepts.mentions");
		addPropertyRelation("sourceType", "class", null, "concepts.mentions");
		
		addPropertyRelation("postCreated", "created", null, "concepts.posts");
		addPropertyRelation("totalLikes", "totalLikes", null, "concepts.posts");

	}

	

	
	
}
