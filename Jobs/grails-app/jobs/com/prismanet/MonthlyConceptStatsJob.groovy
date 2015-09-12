package com.prismanet

import groovy.time.TimeCategory

import org.apache.commons.io.IOUtils

import com.mongodb.DBCursor
import com.prismanet.importer.MongoTweetsImporter
import com.prismanet.job.JobState
import com.prismanet.job.JobType
import org.springframework.util.StopWatch

class MonthlyConceptStatsJob {
	def grailsApplication
	def monthlyConceptStatsService
	def group = "monthlyConceptStatsJobs"
	static triggers = {
		cron name: 'monthlyTrigger', cronExpression: "0 30 02 ? * MON-SUN"
	}

	def execute() {
		if(grailsApplication.config.jobs.monthlyConceptStats.disable)
			return
		monthlyConceptStatsService.loadStats()
	}
	
}
