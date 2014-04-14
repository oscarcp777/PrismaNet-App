package com.prismanet

import groovy.time.TimeCategory

import org.springframework.transaction.annotation.Transactional

import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.OrderType
import com.prismanet.GenericService.ProjectionType
import com.prismanet.context.TwitterAuthorAttributeContext
import com.prismanet.context.ConceptAttributeContext
import com.prismanet.context.Filter
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils

class ConceptException extends RuntimeException {
	String message
	Concept concept
}

class ConceptService extends GenericCoreService {
		
	ConceptService(){
		super(Concept, new ConceptAttributeContext())
	}
	@Transactional(readOnly = true)
    def categoryStore(def groups, def filters, def projection, def orders) {
		return groupBy(Concept, new ConceptAttributeContext(), groups, filters, projection, orders)
	}
	 @Transactional(readOnly = true)
	def getConceptsRealTime( conceptId,timeMin){
		Date from,to
		to=DateUtils.getDateWithoutSeconds(new Date())
		def dateValueList = [:]
		use ( TimeCategory ) {
			from = to-timeMin.minutes
			def dateList = categoryStore(["conceptName","tweetByMinute"], [new Filter(attribute:"id", value : conceptId, type:FilterType.EQ),new Filter(attribute:"created",value:from, type:FilterType.GE)], ["tweetsId" : ProjectionType.COUNT],null);
			dateList.each{ i ->
				dateValueList.put(DateUtils.parseDate(DateTypes.MINUTE_PERIOD, i.getAt(1)).time,i.getAt(2))
			}
		}
		DateUtils.loadZerosForMinute(dateValueList,from,to)
	}
	
	@Override
	protected String getGroupForDateServiceType(DateServiceType type, MentionType mentionType){
		def value = getPrefixForMentionType(mentionType)
		switch (type) {
			case DateServiceType.BY_MINUTE:
				return value + "ByMinute"
			case DateServiceType.BY_HOUR:
				return value + "ByHour"
			case DateServiceType.BY_DATE:
				return value + "Created"
			case DateServiceType.BY_MONTH:
				return value + "Period"
		}
		return null
	}
	
	def getTweetsBy(filters, dateFrom, dateTo){
		def result = getMentionBy(filters, dateFrom, dateTo, MentionType.TWITTER)
		result
	}
	
	def getPostsBy(filters, dateFrom, dateTo){
		def result = getMentionBy(filters, dateFrom, dateTo, MentionType.FACEBOOK)
		result
	}
	
	def getMentionBy(filters, dateFrom, dateTo, MentionType mentionType){
		def groups = ["conceptName",getGroupForDateServiceType(getChartType(dateFrom, dateTo), mentionType)]
		filters.addAll(getFilterList(dateFrom, dateTo))
		def countValue = getPrefixForMentionType(mentionType) +"sId"
		def orderValue = getPrefixForMentionType(mentionType) +"TimeCreated"
		def result = groupBy(Concept, new ConceptAttributeContext(),
						groups, filters, [(countValue) : ProjectionType.COUNT],
						[[attribute:orderValue,value:OrderType.ASC]]);
		result
	}
	
	def getWeightBy(filters, dateFrom, dateTo){
		def groups = ["conceptName",getGroupForDateServiceType(getChartType(dateFrom, dateTo), MentionType.TWITTER)]
		filters.addAll(getFilterList(dateFrom, dateTo))
		def result = groupBy(Concept, new ConceptAttributeContext(),
						groups, filters, ["authorFollowers" : ProjectionType.SUM],
						[[attribute:"created",value:OrderType.ASC]]);
		result
	}
	
	
	def getRelevantAuthors(filters, dateFrom, dateTo, maxAuthors){
		// No lo hago con un select porque duplica los autores (un autor tiene puede
		// tener muchas menciones de un concepto)
		def groups = ["id", "accountName","followers"]
		filters.addAll(getFilterList(dateFrom, dateTo))
		def result = groupBy(TwitterAuthor, new TwitterAuthorAttributeContext(),
						groups, filters, [:],
						[[attribute:"followers",value:OrderType.DESC]], [max:maxAuthors]);
		result
	}
	
	private getPrefixForMentionType(MentionType type){
		def value
		if (type == MentionType.FACEBOOK)
			value = 'post'
		else
			value = 'tweet'
		value
	}
	
}
