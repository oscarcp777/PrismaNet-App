package com.prismanet

import groovy.time.TimeCategory

import org.apache.commons.io.IOUtils

import com.mongodb.DBCursor
import com.prismanet.importer.MongoTweetsImporter

class TweetJob {
	def grailsApplication
	def tweetService
	def twitterSetupService
	def group = "tweetsJobs"
	def pathCommand=""
	static triggers = {
		simple repeatInterval: 60000, repeatCount:-1 , startDelay: 60000
	}

	def execute() {
		if(grailsApplication.config.jobs.twitter.disable)
		 return
		 if(grailsApplication.config.jobs.exec.jar.tomcat){
			 def catalina_base = System.getProperty('catalina.base')
			 pathCommand=catalina_base+"/webapps/PrismaNet-jobs/WEB-INF/lib/"
			 log.info "path para correr jar" +pathCommand
		 }
		
		log.info "TweetJob ejecutado: " + new Date()
		MongoTweetsImporter importer = new MongoTweetsImporter("mongodb://localhost")
		
		use(TimeCategory){
			// Determino la fecha de ultima actualizacion de una configuracion de twitter
			Date lastUpdate = twitterSetupService.getLastUpdated()
			
			List<String> result
			try {
				// Determino si el proceso esta corriendo
				ProcessBuilder b = new ProcessBuilder("/bin/sh", "-c", "ps ax | grep \"java -jar\" | grep \"twitter\"")
				Process p = b.start()
				result = IOUtils.readLines(p.getInputStream());
			} catch (Exception e) {
				log.error "error ProcessBuilder",e
			}
			
			
			if (result == null || result.size()<1){
				//No esta corriendo por lo tanto lo ejecuto
				Runtime.getRuntime().exec("java -jar "+pathCommand+"prismanet-twitter-api.jar")
				log.info "Proceso api-twitter iniciado, ultima modificacion a las : " + lastUpdate
			}
			
			// Si la ultima actualizacion de una configuracion fue en el último minuto reinicio el proceso
			if (lastUpdate.after(new Date()-1.minute)){
				// Detencion del proceso actual
				try {
					ProcessBuilder kill = new ProcessBuilder("/bin/sh", "-c", "kill -9 " + result.get(0)[0..4])
					kill.start()
					log.info "Kill proceso: " + result.get(0)[0..4]
				} catch (Exception e) {
					log.error "error ProcessBuilder",e
				}
				// Actualizo config. de mongo
				importer.setConfiguration(twitterSetupService.getConfiguration())
				// Re-ejecuto proceso
				Runtime.getRuntime().exec("java -jar prismanet-twitter-api.jar")
				log.info "Proceso api-twitter reiniciado por modificacion a las : " + lastUpdate
			}
			
			def d1 = new GregorianCalendar(2013, Calendar.OCTOBER, 27,11,00)
//			def d2 = new GregorianCalendar(2013, Calendar.OCTOBER, 14,1,36)
			Date filteredDate = new Date()-1.minute
			
			def dates = [:]
			dates.put("dateFrom",filteredDate)
//			dates.put("dateFrom",d1.time)
//			dates.put("dateTo",d2.time)
			
			
			DBCursor tweets = importer.importTweets(dates)
			def iterator = tweets.iterator()
			def partialList = []
			int i = 0
			while (iterator.hasNext()){
				partialList.add(iterator.next())
				i++
				try {
					if (i % 25 == 0){
						log.info "Nuevo Lote Tweets"
						i = 0
						tweetService.saveTweets(partialList)
						partialList.clear();
					}
				} catch (Exception e) {
					log.error "Importación Fallida: " + e.getMessage()
					log.error e.getCause()
					log.error e.getStackTrace()
					partialList.clear();
				}
			}
			try {
				log.info "Lista Tweets: " +partialList.size()
				tweetService.saveTweets(partialList)
			} catch (Exception e) {
				log.error "Importación Fallida: " + e.getMessage()
				log.error e.getCause()
				log.error e.getStackTrace()
			} finally {
				log.info "Cursor Tweets cerrado"
				tweets.close()
			}
		}
		importer.close()
	}
	
	
}
