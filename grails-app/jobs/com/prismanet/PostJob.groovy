package com.prismanet

import groovy.time.TimeCategory

import com.prismanet.importer.MongoPostsImporter

class PostJob {
	def grailsApplication
	def facebookService
	def facebookSetupService
	def group = "postsJobs"
	
	static triggers = {
		simple repeatInterval: 300000, repeatCount:-1 , startDelay: 60000
	}

	def execute() {
		if(grailsApplication.config.jobs.facebook.disable)
		 return
		 
		println "PostJob ejecutado: " + new Date()
		MongoPostsImporter importer = new MongoPostsImporter("mongodb://localhost")
		
		use(TimeCategory){
			Date aux = facebookSetupService.getLastUpdated()

			// Si el momento de ultima actualizacion no esta seteado se setea
			if (!grailsApplication.config.facebook.setup.lastUpdated ||
				aux.after(grailsApplication.config.facebook.setup.lastUpdated)){
				grailsApplication.config.facebook.setup.lastUpdated = aux
				importer.setConfiguration(facebookSetupService.getConfiguration())
			}
			
			Process p = Runtime.getRuntime().exec("java -jar prismanet-facebook-api.jar")
			println "Proceso api-facebook ejecutado, ultima modificacion a las : " + grailsApplication.config.facebook.setup.lastUpdated
			
			def d1 = new GregorianCalendar(2013, Calendar.OCTOBER, 27,11,00)
//			def d2 = new GregorianCalendar(2013, Calendar.OCTOBER, 14,1,36)
			Date filteredDate = new Date()-5.minute
			
			def dates = [:]
			dates.put("dateFrom",filteredDate)
//			dates.put("dateFrom",d1.time)
//			dates.put("dateTo",d2.time)
			
//			print "filtros: " + dates
			
			def posts = importer.importPosts(dates)
//			print "-------------------------"
//			print posts
			def iterator = posts.iterator()
			def partialList = []
			int i = 0
//			print "posts: " + iterator.size()
			while (iterator.hasNext()){
				partialList.add(iterator.next())
				i++
//				print i
				if (i % 100 == 0){
					println "Nuevo Lote" 
					i = 0
					//TODO
//					postsService.saveTweets(partialList)
					partialList.clear();
				}
			}
			print "Lista: " +partialList.size()
			//TODO
//			postsService.saveTweets(partialList)
		}
		
	}
	
	
}
