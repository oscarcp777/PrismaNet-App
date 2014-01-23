package com.prismanet

import groovy.time.TimeCategory

import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.ProjectionType
import com.prismanet.context.ConceptAttributeContext
import com.prismanet.context.Filter
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils

class ConceptException extends RuntimeException {
	String message
	Concept concept
}

class ConceptService extends GenericService {
		
	ConceptService(){
		super(Concept, new ConceptAttributeContext())
	}
	
    def categoryStore(def groups, def filters, def projection) {
		return groupBy(groups, filters, projection)
	}
	
	def getConceptsRealTime(Concept concept){
		Date from
		Date to=new Date()
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(to);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		to=calendar.getTime();
		def dateValueList = [:]
		use ( TimeCategory ) {
			from = to-20.minutes
			def dateList = categoryStore(["conceptName","tweetMinute"], [new Filter(attribute:"id", value : concept.id, type:FilterType.EQ),new Filter(attribute:"created",value:from, type:FilterType.GE)], ["tweetsId" : ProjectionType.COUNT]);
			dateList.each{ i ->
				dateValueList.put(DateUtils.parseDate(DateTypes.MINUTE_PERIOD, i.getAt(1)).time,i.getAt(2))
			}
		}
		DateUtils.loadZerosForMinute(dateValueList,from,to)
	}
	def getConceptsHourJson(Concept concept,def day){
		def dateList = categoryStore(["tweetHour"], [new Filter(attribute:"id",value:concept.id, type:FilterType.EQ),
			new Filter(attribute:"tweetPeriod",value:day, type:FilterType.GE)], ["tweetsId" : ProjectionType.COUNT]);
		dateList
	}
}
