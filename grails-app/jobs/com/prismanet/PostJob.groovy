package com.prismanet

import groovy.time.TimeCategory

import org.apache.log4j.LogManager

import com.mongodb.DBCursor
import com.prismanet.importer.MongoPostsImporter

class PostJob {
	def grailsApplication
	def postService
	def facebookSetupService
	def group = "postsJobs"
	def pathCommand=""
	static triggers = {
		simple repeatInterval: 300000, repeatCount:-1 , startDelay: 60000
	}

	def execute() {
		if(grailsApplication.config.jobs.facebook.disable)
		 return
		 if(grailsApplication.config.jobs.exec.jar.tomcat){
			 def catalina_base = System.getProperty('catalina.base')
			 pathCommand=catalina_base+"/webapps/PrismaNet-jobs/WEB-INF/lib/"
			 log.info "path para correr jar" +pathCommand
		 }
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
			
			Process p = Runtime.getRuntime().exec("java -jar "+pathCommand+"prismanet-facebook-api.jar")
			p.waitFor()
			println "Proceso api-facebook ejecutado, ultima modificacion a las : " + grailsApplication.config.facebook.setup.lastUpdated
			
			def d1 = new GregorianCalendar(2013, Calendar.OCTOBER, 27,11,00)
//			def d2 = new GregorianCalendar(2013, Calendar.OCTOBER, 14,1,36)
			Date filteredDate = new Date()-5.minute
			
			def dates = [:]
			dates.put("dateFrom",filteredDate)
//			dates.put("dateFrom",d1.time)
//			dates.put("dateTo",d2.time)
			
//			print "filtros: " + dates
			
			DBCursor posts = importer.importPosts([])
			def iterator = posts.iterator()
			def partialList = []
			int i = 0
			log.info "posts: " + iterator.size()
			while (iterator.hasNext()){
				def post = iterator.next()
				def newComments = postService.getNewComments(post)
//				log.info "newComments" + newComments
				if (newComments > 0){
					partialList.add(post)
				}
				i += newComments
				if (i >24){
					try {
						log.info "Nuevo Lote Posts"
						i = 0
						postService.savePosts(partialList)
						partialList.clear();
					} catch (Exception e) {
						log.error "Importación Fallida: " + e.getMessage()
						log.error "Error: ${e.message}", e
						partialList.clear();
					}
				}
			}
			try {
				log.info "List posts: " +partialList.size()
				postService.savePosts(partialList)
			} catch (Exception e) {
				log.error "Importación Fallida: " + e.getMessage()
				log.error e.getCause()
				log.error e.getStackTrace()
			} finally {
				log.info "Cursor Post cerrado"
				posts.close()
			}
			
		}
		importer.close()
	}
	
	
}
