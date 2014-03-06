package com.prismanet.context


class TwitterSetupAttributeContext extends AttributeContext {

	@Override
	protected void initializeRelations() {
		addPropertyRelation("lastUpdated", "lastUpdated", null, null);
	}

	

	
	
}
