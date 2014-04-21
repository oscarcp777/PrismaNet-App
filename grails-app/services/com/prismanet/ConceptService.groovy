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
	
	
	def getTweetsRealTime(conceptId,timeMin){
		getConceptsRealTime(conceptId, timeMin, "tweetByMinute", "tweetsId", "tweetTimeCreated")
	}
	
	def getPostsRealTime(conceptId,timeMin){
		getConceptsRealTime(conceptId, timeMin, "postByMinute", "postsId", "postTimeCreated")
	}
	
	@Transactional(readOnly = true)
	def getConceptsRealTime(conceptId, timeMin, groupDateProperty, projectionProperty, filterDateProperty){
		Date from,to
		to=DateUtils.getDateWithoutSeconds(new Date())
		def dateValueList = [:]
		use ( TimeCategory ) {
			from = to-timeMin.minutes
			def dateList = categoryStore(["conceptName", groupDateProperty], [new Filter(attribute:"id", value : conceptId, type:FilterType.EQ),new Filter(attribute:filterDateProperty,value:from, type:FilterType.GE)], [(projectionProperty) : ProjectionType.COUNT],null);
			dateList.each{ i ->
				dateValueList.put(DateUtils.parseDate(DateTypes.MINUTE_PERIOD, i.getAt(1)).time,i.getAt(2))
			}
		}
		DateUtils.loadZerosForMinute(dateValueList,from,to)
	}
	
	def getTweetsBy(filters, dateFrom, dateTo){
		TweetService tweetService = new TweetService()
		def dateGroupProperty = tweetService.getDateGroupProperty(dateFrom, dateTo)
		def result = getMentionBy(filters, dateFrom, dateTo, dateGroupProperty, "tweetsId", "tweetTimeCreated")
		result
	}
	
	def getPostsBy(filters, dateFrom, dateTo){
		PostService postService = new PostService()
		def dateGroupProperty = postService.getDateGroupProperty(dateFrom, dateTo)
		def result = getMentionBy(filters, dateFrom, dateTo, dateGroupProperty, "postsId", "postTimeCreated")
		result
	}
	
	private def getMentionBy(filters, dateFrom, dateTo, dateGroupProperty, countProperty, orderProperty){
		def groups = ["conceptName",dateGroupProperty]
		filters.addAll(getFilterList(dateFrom, dateTo, orderProperty))
		def result = groupBy(Concept, new ConceptAttributeContext(),
						groups, filters, [(countProperty) : ProjectionType.COUNT],
						[[attribute:(orderProperty),value:OrderType.ASC]])
		result
	}
	
	def getWeightBy(filters, dateFrom, dateTo){
		TweetService tweetService = new TweetService()
		def dateGroupProperty = tweetService.getDateGroupProperty(dateFrom, dateTo)
		def groups = ["conceptName", dateGroupProperty]
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
		groupBy(TwitterAuthor, new TwitterAuthorAttributeContext(),
						groups, filters, [:],
						[[attribute:"followers",value:OrderType.DESC]], [max:maxAuthors]);
	}
	
}
