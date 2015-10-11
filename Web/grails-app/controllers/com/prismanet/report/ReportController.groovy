package com.prismanet.report


import groovy.time.TimeCategory

import com.prismanet.GenericController
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils


class ReportController extends GenericController {
	
	def reports={
		Date currentDate = new Date()
		use(TimeCategory){
			currentDate = currentDate - 1.hour
		}
		log.info "Report : " + currentDate
		String hourParameter = DateUtils.getDateFormat(DateTypes.HOUR_SIMPLIFIED, currentDate)
		hourParameter='15052021'
		[dateReport:hourParameter]
	}
	def reportPublic={
		[dateReport:hourParameter]
	}
}