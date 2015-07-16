package com.prismanet.report

import grails.converters.JSON

import com.prismanet.GenericController
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils


class HourlyConceptStatsController extends GenericController {

	def hourlyConceptStatsService
	def mentionService
	
	def loadStats={
		hourlyConceptStatsService.loadStats()
		render "Estadisticas cargadas correctamente"
	}
	
	def statsForUser={
		
		
		/*
		 * [ dateFrom:15/06/2015 16:29,  dateTo:14/07/2015 16:29, conceptsId:23]
		 */
		
		def parameters = [:]
		parameters.max = 3
		def solrFilters = [dateFrom:"07/01/2015 21:00", dateTo:"07/06/2015 21:59"]
		//Date dateProcess = new Date()
		GregorianCalendar d1 = new GregorianCalendar(2015, Calendar.JUNE, 7,21,00)
		Date dateProcess = d1.getTime()
		String hourProcess = DateUtils.getDateFormat(DateTypes.HOUR_PERIOD,dateProcess)
		def result = hourlyConceptStatsService.getStatsForUser(1, dateProcess, parameters)
		def categories = []
		def series = [name:"De 13 a 14 hs"]
		def data = []
		def topTweets = [:]
		def topWords = [:]
		result.result.each{
			categories.add(it.concept.conceptName)
			data.add(it.mentions)
			topTweets.put(it.concept.conceptName, it.tweets)
			solrFilters['conceptsId'] = it.concept.id
			def listWords=[]
			def relevantWords = mentionService.getRelevantWords(loadSolrFilters(solrFilters))
			def maxPercent = 35, minPercent = 9
			def max =1 ,min = 0
			if (relevantWords){
				max = relevantWords.get(0).size
				min = relevantWords.get(relevantWords.size -1).size
			}
			
			def multiplier
			if (max != min)
				multiplier = (maxPercent-minPercent)/(max-min)
			else
				multiplier = (maxPercent-minPercent)
			
			for (var in relevantWords) {
				int size = minPercent + ((max-(max-(var.size-min)) +1 )*multiplier);
				listWords.add([var.text,size]);
			}
			topWords.put(it.concept.conceptName, listWords)
		}
		series.put("data", data)
		def map = [title: "Politicos mas mencionados de la hora",
			categories:categories,series:series]
		def finalResult = [principalChart:map, 'topTweets':topTweets,'topWords':topWords]
		render finalResult as JSON
	}
}
