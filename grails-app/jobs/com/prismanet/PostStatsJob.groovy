package com.prismanet

import groovy.time.TimeCategory

import org.apache.log4j.LogManager

import com.mongodb.DBCursor
import com.prismanet.PostService;
import com.prismanet.importer.MongoPostsImporter

class PostStatsJob {
	def grailsApplication
	def postService
	def group = "postsStatsJobs"
	def minutesBetweenJobs = 30
	static triggers = {
		simple repeatInterval: 1000*60*30, repeatCount:-1 , startDelay: 130000
	}

	def execute() {
		if(grailsApplication.config.jobs.facebookPost.disable)
			return
		Date executionTimeStamp = new Date()
		log.info "PostStatsJob ejecutado: " + executionTimeStamp
		MongoPostsImporter importer = new MongoPostsImporter("mongodb://localhost")
		def results = importer.getPostStatsDataFromFacebook()
		try {
			postService.savePosts(results.posts)
		} catch (Exception e) {
			log.error "Importación Fallida: " + e.getMessage()
			log.error e.getCause()
			log.error e.printStackTrace()
		}
		importer.close()
	}
}
