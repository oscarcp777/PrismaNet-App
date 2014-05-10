package com.prismanet

import com.prismanet.GenericService.FilterType
import com.prismanet.context.Filter
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils

class PostController extends GenericController{

	def postService
	
	def list(Integer max) {
		
		log.debug "postController->list params: " + params
		Concept concept = chooseConcept(params)
		def filters = loadPostFilters()
		params.max = Math.min(max ?: 6, 100)
		def posts = postService.getPosts(filters,params)
		
		[postList: posts.resultList, postTotal: posts.totalCount, concept: concept]
	}
	
   private def loadPostFilters(){
		def filters=[]

		if (params["conceptsId"])
			filters.add(new Filter(attribute:"conceptsId",value: params.conceptsId.toLong(), type:FilterType.EQ))

		/*if (params["tweetMinute"]){
			def cal = new GregorianCalendar()
			cal.setTimeInMillis(params["tweetMinute"] as Long)
			def minuteFilter=DateUtils.getDateFormat(DateTypes.MINUTE_PERIOD, cal.time)
			filters.add(new Filter(attribute:"tweetMinute",value: minuteFilter, type:FilterType.EQ))
		}

		if (params["tweetCreated"]){
			def cal = new GregorianCalendar()
			cal.setTimeInMillis(params["tweetCreated"] as Long)
			def day = DateUtils.getDateFormat(DateTypes.DAY_PERIOD, cal.time)
			filters.add(new Filter(attribute:"tweetCreated",value: day, type:FilterType.EQ))
		}

		if (params["tweetHour"]){
			def cal = new GregorianCalendar()
			cal.setTimeInMillis(params["tweetHour"] as Long)
			def hourFilter=DateUtils.getDateFormat(DateTypes.HOUR_PERIOD, cal.time)
			filters.add(new Filter(attribute:"tweetHour",value: hourFilter, type:FilterType.EQ))
		}*/
		filters
	}
}
