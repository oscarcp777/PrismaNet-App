package com.prismanet

import com.prismanet.GenericService.FilterType
import com.prismanet.context.Filter
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils

class PostController extends GenericController{

	def facebookCommentService
	
	def list(Integer max) {
		
		log.debug "postController->list params: " + params
		Concept concept = chooseConcept(params)
		def filters = loadPostFilters()
		params.max = Math.min(max ?: 6, 100)
		def posts = facebookCommentService.getPosts(filters,params)
		
		[postList: posts.resultList, postTotal: posts.totalCount, concept: concept]
	}
	
   private def loadPostFilters(){
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
		filters
	}
}
