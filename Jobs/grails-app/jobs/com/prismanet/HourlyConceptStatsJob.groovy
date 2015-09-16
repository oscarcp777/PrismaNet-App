package com.prismanet

import groovy.time.TimeCategory

import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils

class HourlyConceptStatsJob {
	def grailsApplication
	def hourlyConceptStatsService
	def tweetService
	def group = "hourlyConceptStatsJobs"
	static triggers = {
		cron name: 'hourlyTrigger', startDelay:10000, cronExpression: "0 2 0-2,7-23 * * ?"
	}

	def execute() {
		if(grailsApplication.config.jobs.hourlyConceptStats.disable)
			return
		
		Date currentDate = new Date()
		log.info "HourlyJob ejecutado: " + currentDate
		hourlyConceptStatsService.loadStats()
		use(TimeCategory){
			currentDate = currentDate - 1.hour
		}
		String hourParameter = DateUtils.getDateFormat(DateTypes.HOUR_SIMPLIFIED, currentDate)
		tweetService.sendTweet(
			"Mira que paso en la última hora en al ambiente político "+
				"info.prisma-net.com.ar/public/report?dt="+hourParameter
		)
	}
	
}
