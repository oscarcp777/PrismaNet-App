package com.prismanet

import groovy.time.TimeCategory

import org.apache.commons.io.IOUtils

import com.mongodb.DBCursor
import com.prismanet.importer.MongoTweetsImporter
import com.prismanet.job.JobState
import com.prismanet.job.JobType
import org.springframework.util.StopWatch

class TweetJob {
	def grailsApplication
	def tweetService
	def twitterSetupService
	def jobStateService
	def group = "tweetsJobs"
	def pathCommand=""
	static triggers = {
		simple repeatInterval: 60000, repeatCount:-1 , startDelay: 60000
	}

	def execute() {
		
		use(TimeCategory){

			if(grailsApplication.config.jobs.twitter.disable)
				return
			if(grailsApplication.config.jobs.exec.jar.tomcat){
				def catalina_base = System.getProperty('catalina.base')
				pathCommand=catalina_base+"/webapps/PrismaNet-jobs/WEB-INF/lib/"
				log.info "path para correr jar" +pathCommand
			}
			
			Date currentMinute = new Date()
			Date previousMinute
			JobState state = jobStateService.getLastUpdate(JobType.TWITTER)
			if (!state){
				previousMinute = currentMinute-1.minute+1.second
				state = new JobState(type:JobType.TWITTER,lastUpdate:currentMinute)
			}else
				previousMinute = state.lastUpdate +1.second
				
			state.lastUpdate = currentMinute
			jobStateService.save(state)
			log.info "TweetJob ejecutado: " + currentMinute
			log.info "Proceso tweets de " + previousMinute + " hasta " + currentMinute
				
			MongoTweetsImporter importer = new MongoTweetsImporter("mongodb://localhost")


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
			
			if (result == null || result.size()<2){
				//No esta corriendo por lo tanto lo ejecuto
				initProcess(importer, lastUpdate)
			}else{
				// Si la ultima actualizacion de una configuracion fue en el último minuto reinicio el proceso
				if (lastUpdate.after(previousMinute)){
					log.info "Se reinicia proceso por actualizacion de la configuracion"
					// Detencion del proceso actual
					try {
						ProcessBuilder kill = new ProcessBuilder("/bin/sh", "-c", "kill -9 " + result.get(0)[0..4])
						kill.start()
						log.info "Kill proceso: " + result.get(0)[0..4]
					} catch (Exception e) {
						log.error "error ProcessBuilder",e
					}
					// Re-ejecuto proceso
					initProcess(importer, lastUpdate)
				}
			}
			def d1 = new GregorianCalendar(2013, Calendar.OCTOBER, 27,11,00)
//			def d2 = new GregorianCalendar(2013, Calendar.OCTOBER, 14,1,36)

			StopWatch timer = new StopWatch()
			timer.start()
			
			def dates = [:]
			dates.put("dateFrom",previousMinute)
			dates.put("dateTo",currentMinute)
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
						log.debug "Nuevo Lote Tweets"
						i = 0
						tweetService.saveTweets(partialList)
						partialList.clear();
					}
				} catch (Exception e) {
					log.error "Importación Fallida: " + e.getMessage()
					log.error e.getCause()
					log.error e.printStackTrace()
					partialList.clear();
				}
			}
			try {
				log.info "Lista Tweets: " +partialList.size()
				tweetService.saveTweets(partialList)
			} catch (Exception e) {
				log.error "Importación Fallida: " + e.getMessage()
				log.error e.getCause()
				log.error e.printStackTrace()
			} finally {
				log.info "Cursor Tweets cerrado"
				tweets.close()
			}
			importer.close()
			timer.stop()
			log.info "tiempo tweetJob: " + timer.getTotalTimeMillis()
		}
	}
	
	
	private initProcess(MongoTweetsImporter importer, Date lastUpdate){
		// Actualizo config. de mongo
		importer.setConfiguration(twitterSetupService.getConfiguration())
		Runtime.getRuntime().exec("java -jar "+pathCommand+"prismanet-twitter-api.jar")
		log.info "Proceso api-twitter iniciado, ultima modificacion a las : " + lastUpdate
	}
	
}
