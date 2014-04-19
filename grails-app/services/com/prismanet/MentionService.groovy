package com.prismanet

import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.OrderType
import com.prismanet.context.AttributeContext
import com.prismanet.context.TweetAttributeContext
import com.prismanet.sentiment.Opinion
import com.prismanet.sentiment.OpinionValue

class MentionService extends GenericCoreService{

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
			log.info "Gorm clean"
		} catch (Exception e) {
			log.error e.getCause()
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
	
	def getMentions(filters, parameters, context, mentionType){
		
		def conceptId
		filters.each {
			if (it.attribute == "conceptsId" && it.type ==  FilterType.EQ)
				conceptId = it.value
		}
		def resultList = []
		Concept concept
		if (conceptId)
			concept = Concept.get(conceptId)
			
		def auxList =	list(mentionType, context, filters, parameters, [[attribute:"id",value:OrderType.DESC]])
		for (mention in auxList.results){
			def opValue
			if (conceptId){
				opValue = OpinionValue.NEUTRAL
				Opinion op = Opinion.findByMentionAndConcept(mention,concept)
				if (op)
					opValue = op.value
			}
			resultList << [tweet:mention, value:opValue]
		}
		
		[resultList:resultList,totalCount:auxList.totalCount]
		
	}
	
}
