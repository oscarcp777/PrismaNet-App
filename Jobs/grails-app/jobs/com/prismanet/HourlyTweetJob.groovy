package com.prismanet

import groovy.time.TimeCategory

import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils

class HourlyTweetJob {
	def grailsApplication
	def hourlyConceptStatsService
	def tweetService
	def group = "hourlyTweetJobs"
	static triggers = {
		cron name: 'hourlyTweetTrigger', startDelay:10000, cronExpression: "0 4 0-2,7-23 * * ?"
	}

	def execute() {
		if(grailsApplication.config.jobs.hourlyTweet.disable)
			return
		
		Date currentDate = new Date()
		log.info "HourlyTweetJob ejecutado: " + currentDate
		String tweetText = ""
		
		use(TimeCategory){
			currentDate = currentDate - 1.hour
			def parameters = [:]
			parameters.max = 3
			def result = hourlyConceptStatsService.getStatsForUser(ParamConfig.get(1).config['user.report.id'], currentDate, parameters)
			tweetText = getRandomText(currentDate[Calendar.HOUR_OF_DAY].toString(), result.accountNames)
		}
		String hourParameter = DateUtils.getDateFormat(DateTypes.HOUR_SIMPLIFIED, currentDate)
		tweetService.sendTweet(
		tweetText +
				" info.prisma-net.com.ar/report/reportPublic?dt="+hourParameter
		)
	}
	
	
	private String getRandomText(String hour, List accountNames){
		def vector = [
					  "Mirá que paso en la última hora en al ambiente político",
					  "Reporte de las " + hour +"hs, " +  accountNames.get(0) + " lo más mencionado",
					  "Resumen de las " + hour +"hs, " +  accountNames.get(0) + " lo más mencionado",
					  "Esto fue lo mas importante de las " + hour +"hs",
					  "Mirá lo mas importante de las " + hour +"hs",
					  "#Eleccion2015 No te pierdas este resumen",
					  "Mira quienes, como y cuanto se habló de política en esta hora",
					  "Panorama político de las " + hour +"hs",
					  "#Eleccion2015 queres saber como viene el panorama político?",
					  "Mirá en 2 minutos lo que pasó en un hora",
					  "#Eleccion2015 " + accountNames.get(0) + " fue la cuenta más mencionada en esta hora",
					  "Panorama político de las " + hour +"hs, " + accountNames.get(0) + " lo más mencionado", 
					  accountNames.get(0) +", "+ accountNames.get(1)+" y "+accountNames.get(2)+" los mas mencionados de la hora" 
					  ]
		
		Random rand = new Random()
		int max = vector.size()
		vector[rand.nextInt(max)]
		
	}
	
}
