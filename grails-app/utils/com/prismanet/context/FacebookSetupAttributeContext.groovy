package com.prismanet.context


class FacebookSetupAttributeContext extends AttributeContext {

	@Override
	protected void initializeRelations() {
		addPropertyRelation("lastUpdated", "lastUpdated", null, null);
	}

	

	
	
}
