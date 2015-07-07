package com.prismanet.context


class HourlyConceptStatsAttributeContext extends AttributeContext {

	@Override
	protected void initializeRelations() {
		addPropertyRelation("userId", "id", null, "concept.user");
		addPropertyRelation("hour", "hour", null, null);
		addPropertyRelation("mentions", "mentions", null, null);
		addPropertyRelation("authors", "authors", null, null);
	}

	

	
	
}
