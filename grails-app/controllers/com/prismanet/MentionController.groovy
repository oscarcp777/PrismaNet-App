package com.prismanet

import grails.converters.JSON
import groovy.time.TimeCategory

import com.prismanet.GenericService.FilterType
import com.prismanet.context.Filter
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils

class MentionController extends GenericController{
	
	protected MentionService getService(){
		
	}

	protected def loadMentionFilters(){
		def filters=[]
		if (params["conceptsId"])
			filters.add(new Filter(attribute:"conceptsId",value: params.conceptsId.toLong(), type:FilterType.EQ))

		if (params["dateMinute"]){
			def cal = new GregorianCalendar()
			cal.setTimeInMillis(params["dateMinute"] as Long)
			use (TimeCategory){
				Date dateFrom = cal.getTime()
				Date dateTo = dateFrom + 1.minute -1.second
				filters.addAll(getService().getFilterList(dateFrom, dateTo, "created", false))
			}
		}

		if (params["dateCreated"]){
			def cal = new GregorianCalendar()
			cal.setTimeInMillis(params["dateCreated"] as Long)
			use (TimeCategory){
				Date dateFrom = cal.getTime()
				Date dateTo = dateFrom + 1.day -1.second
				filters.addAll(getService().getFilterList(dateFrom, dateTo, "created", false))
			}
		}

		if (params["dateHour"]){
			def cal = new GregorianCalendar()
			cal.setTimeInMillis(params["dateHour"] as Long)
			use (TimeCategory){
				Date dateFrom = cal.getTime()
				Date dateTo = dateFrom + 1.hour -1.second
				filters.addAll(getService().getFilterList(dateFrom, dateTo, "created", false))
			}
		}
		
		if (params["datePeriod"]){
			def cal = new GregorianCalendar()
			cal.setTime(DateUtils.parseDate(DateTypes.MONTH_PERIOD, params.datePeriod))
			use (TimeCategory){
				Date dateFrom = cal.getTime()
				Date dateTo = dateFrom + 1.month -1.second
				filters.addAll(getService().getFilterList(dateFrom, dateTo, "created", false))
			}
		}

		filters
	}
	
	protected def loadSolrFilters(){
		def filters=[]

		if (params["conceptsId"])
			filters.add(new Filter(attribute:"conceptsId",value: params.conceptsId.toLong(), type:FilterType.EQ))

		if (params["dateMinute"]){
			def cal = new GregorianCalendar()
			cal.setTimeInMillis(params["dateMinute"] as Long)
			def minuteFilter=DateUtils.getDateFormat(DateTypes.MINUTE_PERIOD, cal.time)
			filters.add(new Filter(attribute:"dateMinute",value: minuteFilter, type:FilterType.EQ))
		}

		if (params["dateCreated"]){
			def cal = new GregorianCalendar()
			cal.setTimeInMillis(params["dateCreated"] as Long)
			def day = DateUtils.getDateFormat(DateTypes.DAY_PERIOD, cal.time)
			filters.add(new Filter(attribute:"dateCreated",value: day, type:FilterType.EQ))
		}

		if (params["dateHour"]){
			def cal = new GregorianCalendar()
			cal.setTimeInMillis(params["dateHour"] as Long)
			def hourFilter=DateUtils.getDateFormat(DateTypes.HOUR_PERIOD, cal.time)
			filters.add(new Filter(attribute:"dateHour",value: hourFilter, type:FilterType.EQ))
		}
		
		if (params["datePeriod"]){
			def cal = new GregorianCalendar()
			cal.setTime(DateUtils.parseDate(DateTypes.MONTH_PERIOD, params.datePeriod))
			def periodFilter=DateUtils.getDateFormat(DateTypes.MONTH_PERIOD, cal.time)
			filters.add(new Filter(attribute:"datePeriod",value: periodFilter, type:FilterType.EQ))
		}
		filters
	}
	
	def wordsCloud() {
		log.info "tweetController->wordsCloud params: " + params
		def listWords=[]
		int counter=0
		
		
		if(session.relevantWords?.empty)
			session.relevantWords= tweetService.getRelevantWords(loadSolrFilters())
		
		def relevantWords = session.relevantWords
		def maxPercent = 45, minPercent = 7
		def max =1 ,min = 0
		if (relevantWords){
			max = relevantWords.get(0).size
			min = relevantWords.get(relevantWords.size -1).size
		}
		
		def multiplier
		if (max != min)
			multiplier = (maxPercent-minPercent)/(max-min)
		else
			multiplier = (maxPercent-minPercent)
		
		for (var in relevantWords) {
			int size = minPercent + ((max-(max-(var.size-min)))*multiplier);
			listWords.add([var.text,size]);
		}
		if (listWords.size() == 0)
			render  "No se encontraron terminos"
		def mapJson=[div:params['div'],json:listWords,mapWords:relevantWords]
		render  mapJson as JSON
	}

	
}
