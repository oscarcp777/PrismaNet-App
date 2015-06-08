package com.prismanet.context


class MonthlyConceptStatsAttributeContext extends AttributeContext {

	@Override
	protected void initializeRelations() {
		addPropertyRelation("userId", "id", null, "concept.user");
		addPropertyRelation("period", "period", null, null);
		addPropertyRelation("mentions", "mentions", null, null);
		addPropertyRelation("authors", "authors", null, null);
	}

	

	
	
}
