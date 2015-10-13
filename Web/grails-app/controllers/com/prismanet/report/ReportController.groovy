package com.prismanet.report


import groovy.time.TimeCategory

import com.prismanet.GenericController
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils


class ReportController  extends GenericController{
	
	
	def conceptTab(){
		render(template: "concept-tab",model:['':null])
		return
	}
	def listTweets(){
		render(template: "list-tweets",model:['':null])
		return
	}
	def reports={
		Date currentDate = new Date()
		use(TimeCategory){
			currentDate = currentDate - 1.hour
			currentDate =currentDate-Calendar.getInstance().get(Calendar.MINUTE).minute
		}
		log.info "Report : " + currentDate
		String hourParameter = DateUtils.getDateFormat(DateTypes.HOUR_SIMPLIFIED, currentDate)
//		hourParameter='15052021'
		[dateReport:hourParameter,dateShow:currentDate]
	}
	def reportPublic={
		Date dateShow = DateUtils.parseDate(DateTypes.HOUR_SIMPLIFIED, params.dt)
		[dateReport:params.dt,dateShow:dateShow]
	}
}