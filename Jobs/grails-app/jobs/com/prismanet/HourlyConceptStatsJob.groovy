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
		cron name: 'hourlyConceptStatsTrigger', startDelay:10000, cronExpression: "0 2 0-2,7-23 * * ?"
	}

	def execute() {
		if(grailsApplication.config.jobs.hourlyConceptStats.disable)
			return
		
		Date currentDate = new Date()
		log.info "HourlyJob ejecutado: " + currentDate
		hourlyConceptStatsService.loadStats()
	}
	
}
