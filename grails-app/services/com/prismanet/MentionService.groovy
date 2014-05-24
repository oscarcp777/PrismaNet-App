package com.prismanet

import org.apache.solr.client.solrj.SolrQuery
import org.apache.solr.client.solrj.SolrServer
import org.apache.solr.client.solrj.response.QueryResponse
import org.apache.solr.common.SolrDocumentList

import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.OrderType
import com.prismanet.context.AttributeContext
import com.prismanet.context.Filter
import com.prismanet.context.MentionAttributeContext
import com.prismanet.sentiment.Opinion
import com.prismanet.sentiment.OpinionValue
import com.prismanet.utils.SolrUtil

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
	
	
	def getRelevantWords(List<Filter> filters){
		AttributeContext context = new MentionAttributeContext()
		SolrServer solr = SolrUtil.getSolrServerInstance()
		SolrQuery query = new SolrQuery()
		query.setQuery("*:*")
		query.setFacet(true)
		query.setFacetLimit(15)
		query.addFacetField("mentionContent")
		query.setRows(1)
		
		filters?.each { filter ->
			if (context.hasPropertyRelationForAttribute(filter.attribute)) {
				if (filter.attribute == 'conceptsId')
					query.addFilterQuery("conceptId:" + filter.value)
				if (filter.attribute == "dateMinute")
					query.addFilterQuery("dateByMinute:" + filter.value)
				if (filter.attribute ==  "dateCreated")
					query.addFilterQuery("date:" + filter.value)
				if (filter.attribute ==  "dateHour")
					query.addFilterQuery("dateByHour:" + filter.value)
				
			}
		}

		QueryResponse respSolr = solr.query(query)
		def result = []
		respSolr.getFacetFields().get(0).getValues().each{
			result.add([name:it.getName(),count:it.getCount()])
		}
		result
	}
	
}
