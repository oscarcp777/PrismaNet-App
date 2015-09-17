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
		String tweetText
		use(TimeCategory){
			currentDate = currentDate - 1.hour
			tweetText = getRandomText(currentDate[Calendar.HOUR].toString())
		}
		String hourParameter = DateUtils.getDateFormat(DateTypes.HOUR_SIMPLIFIED, currentDate)
		tweetService.sendTweet(tweetText +
				" info.prisma-net.com.ar/public/report?dt="+hourParameter
		)
	}
	
	
	private String getRandomText(String hour){
		def vector = ["Mirá que paso en la última hora en al ambiente político",
					  "Reporte de las " + hour +"hs",
					  "Resumen de las " + hour +"hs",
					  "Esto fue lo mas importante de las " + hour +"hs",
					  "Mirá lo mas importante de las " + hour +"hs",
					  "#presidenciales #politica No te pierdas este resumen",
					  "Mira quienes, como y cuanto se habló de política en esta hora",
					  "Panorama político de las " + hour +"hs",
					  "#elecciones queres saber como viene el panorama político?",
					  "Mirá en 2 minutos lo que pasó en un hora"]
		
		Random rand = new Random()
		int max = vector.size()
		vector[rand.nextInt(max)]
		
	}
	
}
