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

	MentionService(){
		super()
	}
	
	MentionService(def domainClass,AttributeContext context){
		super(domainClass, context)
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
		getMentions(filters, parameters, context, mentionType, [[attribute:"id",value:OrderType.DESC]]) 
	}
	
	def getMentions(filters, parameters, context, mentionType, orders){
		
		def conceptId
		filters.each {
			if (it.attribute == "conceptsId" && it.type ==  FilterType.EQ)
				conceptId = it.value
		}
		def resultList = []
		Concept concept
		if (conceptId)
			concept = Concept.get(conceptId)
			
		def auxList =	list(mentionType, context, filters, parameters, orders)
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
		query.setFacet(true)
		query.setQuery("*:*")
		query.setFacetLimit(16)
		query.addFacetField("mentionContent")
		query.setRows(1)
		def between = [:]
		filters?.each { filter ->
			if (filter.attribute ==  "dateFrom")
				between.from = filter.value
			if (filter.attribute ==  "dateTo")
				between.to = filter.value
			if (context.hasPropertyRelationForAttribute(filter.attribute)) {
				if (filter.attribute == 'conceptsId')
					query.addFilterQuery("conceptId:" + filter.value)
				if (filter.attribute == 'authorId')
					query.addFilterQuery("authorId:" + filter.value)
				if (filter.attribute == "dateMinute")
					query.addFilterQuery("dateByMinute:\"" + filter.value +"\"")
				if (filter.attribute ==  "dateCreated")
					query.addFilterQuery("date:\"" + filter.value+"\"")
				if (filter.attribute ==  "dateHour")
					query.addFilterQuery("dateByHour:\"" + filter.value+"\"")
			}
		}
		if (between.from && between.to)
			query.addFilterQuery("dateByMinute:[\"" + between.from+
				"\" TO \"" + between.to + "\"]")
		log.info "solrQuery: " + query
		QueryResponse respSolr
		def result = []
		try {
			respSolr = solr.query(query)
			result = []
			respSolr.getFacetFields().get(0).getValues().eachWithIndex{ item, i ->
				if (i != 0 && item.getCount() != 0)
					result.add([text:item.getName(),size:item.getCount()])
			}
			result
		} catch (Exception e) {
			log.error "error solr",e
		}
		
		
	}
	
}
