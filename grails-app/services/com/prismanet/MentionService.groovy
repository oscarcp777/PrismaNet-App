package com.prismanet

import com.prismanet.context.AttributeContext;
import com.prismanet.sentiment.Opinion
import com.prismanet.sentiment.OpinionValue

class MentionService extends GenericService{

	def sessionFactory
	def propertyInstanceMap = org.codehaus.groovy.grails.plugins.DomainClassGrailsPlugin.PROPERTY_INSTANCE_MAP
	//boolean transactional = false
	
	MentionService(){
		super()
	}
	
	MentionService(def domainClass,AttributeContext context){
		super(domainClass, context)
	}
	
	def cleanUpGorm() {
		try {
			sessionFactory.currentSession.flush()
			sessionFactory.currentSession.clear()
			propertyInstanceMap.get().clear()
		} catch (Exception e) {
			print e.getCause()
		}
	}

	
	def OpinionValue getOpinion(User user, Concept concept){
		def opinion = Opinion.findByUserandConcept(user,concept)
		if (!opinion)
			return OpinionValue.NEUTRAL
		
		opinion.value
	}
	
	
	private boolean isNumericalArgument(String argument) {
		def String arguments = argument.split(",");
		boolean isNumericalArgument = true;
		for (String arg : arguments) {
			try {
				Integer.parseInt(arg);
			} catch (NumberFormatException nfe) {
				isNumericalArgument = false;
				break;
			}
		}
		return isNumericalArgument;
	}
	
	
}
