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
	def getMentionsRealTime(filters, timeMin){
		Date from,to
		to=DateUtils.getDateWithoutSeconds(new Date())
		def dateValueList = [:]
		use ( TimeCategory ) {
			from = to-timeMin.minutes
			filters.add(new Filter(attribute:"created",value:from, type:FilterType.GE))
			def dateList = categoryStore(["conceptName", "dateByMinute"], 
											filters, 
											["mentionId" : ProjectionType.COUNT],
											null);
			dateList.each{ i ->
				dateValueList.put(DateUtils.parseDate(DateTypes.MINUTE_PERIOD, i.getAt(1)).time,i.getAt(2))
			}
		}
		DateUtils.loadZerosForMinute(dateValueList,from,to)
	}
	
	def getMentionsBy(filters, dateFrom, dateTo){
		def groups = ["conceptName",getDateGroupProperty(dateFrom, dateTo)]
		filters.addAll(getFilterList(dateFrom, dateTo))
		def result = groupBy(Concept, new ConceptAttributeContext(),
						groups, filters, ["mentionId" : ProjectionType.COUNT],
						[[attribute:"created",value:OrderType.ASC]])
		result
	}
	
	def getWeightBy(filters, dateFrom, dateTo){
		def groups = ["conceptName", getDateGroupProperty(dateFrom, dateTo)]
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
		def authorsList =groupBy(TwitterAuthor, new TwitterAuthorAttributeContext(),
				groups, filters, [:],
				[[attribute:"followers",value:OrderType.DESC]], [max:maxAuthors]);
		def listids=[]
		authorsList.each {
			listids.add(it[0].toLong())
		}
		TwitterAuthor.findAllByIdInList(listids,[sort: "followers", order: "desc"])
	}
	
	public String getDateGroupProperty(DateServiceType type){
		switch (type) {
			case DateServiceType.BY_MINUTE:
				return "dateByMinute"
			case DateServiceType.BY_HOUR:
				return "dateByHour"
			case DateServiceType.BY_DATE:
				return "dateCreated"
			case DateServiceType.BY_MONTH:
				return "datePeriod"
		}
		return null
	}
	
}
