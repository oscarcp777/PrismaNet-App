package com.prismanet

import groovy.time.TimeCategory

import org.apache.log4j.LogManager

import com.mongodb.DBCursor
import com.prismanet.importer.MongoPostsImporter

class PostJob {
	def grailsApplication
	def facebookCommentService
	def facebookSetupService
	def group = "postsJobs"
	def pathCommand=""
	def minutesBetweenJobs = 2
	static triggers = {
		simple repeatInterval: 1000*60*2 , repeatCount:-1 , startDelay: 90000
	}

	def execute() {
		if(grailsApplication.config.jobs.facebook.disable)
		 return
		 if(grailsApplication.config.jobs.exec.jar.tomcat){
			 def catalina_base = System.getProperty('catalina.base')
			 pathCommand=catalina_base+"/webapps/PrismaNet-jobs/WEB-INF/lib/"
			 log.info "path para correr jar" +pathCommand
		 }
		log.info "PostJob ejecutado: " + new Date()
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
			log.info "Proceso api-facebook ejecutado, ultima modificacion a las : " + grailsApplication.config.facebook.setup.lastUpdated
			
			def d1 = new GregorianCalendar(2013, Calendar.OCTOBER, 27,11,00)
//			def d2 = new GregorianCalendar(2013, Calendar.OCTOBER, 14,1,36)
			Date filteredDate = new Date()-minutesBetweenJobs.minute
			
			def dates = [:]
			dates.put("dateFrom",filteredDate)
//			dates.put("dateFrom",d1.time)
//			dates.put("dateTo",d2.time)
			
			
			DBCursor posts = importer.importPosts([])
			def iterator = posts.iterator()
			def partialList = []
			int i = 0
			log.info "posts: " + posts.size()
			def lastUpdated = facebookCommentService.getLastUpdated()
			log.info "fecha ultimo post: " + lastUpdated
			while (iterator.hasNext()){
				def post = iterator.next()
				def newComments = facebookCommentService.getNewComments(post, lastUpdated)
				log.info "newComments" + newComments
				if (newComments > 0){
					partialList.add(post)
				}
				i += newComments
				if (i >24){
					try {
						log.info "Nuevo Lote Comentarios"
						i = 0
						facebookCommentService.savePosts(partialList, lastUpdated)
						partialList.clear();
					} catch (Exception e) {
						log.error "Importación Fallida: " + e.getMessage()
						log.error e.getCause()
						log.error e.printStackTrace()
						partialList.clear();
					}
				}
			}
			try {
				log.info "List posts: " +partialList.size()
				facebookCommentService.savePosts(partialList, lastUpdated)
			} catch (Exception e) {
				log.error "Importación Fallida: " + e.getMessage()
				log.error e.getCause()
				log.error e.printStackTrace()
			} finally {
				log.info "Cursor Post cerrado"
				posts.close()
			}
			
		}
		importer.close()
	}
	
	
}
