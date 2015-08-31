package com.prismanet

import groovy.time.TimeCategory

import org.apache.commons.io.IOUtils

import com.mongodb.DBCursor
import com.prismanet.importer.MongoTweetsImporter
import com.prismanet.job.JobState
import com.prismanet.job.JobType
import org.springframework.util.StopWatch

class HourlyConceptStatsJob {
	def grailsApplication
	def hourlyConceptStatsService
	def tweetService
	def group = "monthlyConceptStatsJobs"
	static triggers = {
		cron name: 'customTrigger', cronExpression: "0 2 7-2 * * *"
	}

	def execute() {
		if(grailsApplication.config.jobs.hourlyConceptStats.disable)
			return
		hourlyConceptStatsService.loadStats()
	}
	
}
