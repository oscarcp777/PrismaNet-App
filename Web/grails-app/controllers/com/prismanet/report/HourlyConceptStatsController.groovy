package com.prismanet.report

import grails.converters.JSON

import com.prismanet.GenericController
import com.prismanet.Tweet;
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils


class HourlyConceptStatsController extends GenericController {

	def hourlyConceptStatsService
	def tweetService
	def beforeInterceptor = {
		print "pasoo"
	}
	
	def loadStats={
		hourlyConceptStatsService.loadStats()
		render "Estadisticas cargadas correctamente"
	}
	
	def statsForUser={
		
		print params.dateReport
		/*
		 * [ dateFrom:15/06/2015 16:29,  dateTo:14/07/2015 16:29, conceptsId:23]
		 */
		
		def parameters = [:]
		parameters.max = 3
		def solrFilters = [dateFrom:"20/05/2015 21:00", dateTo:"20/05/2015 21:59"]
		//Date dateProcess = new Date()
		GregorianCalendar d1 = new GregorianCalendar(2015, Calendar.MAY, 20,21,00)
		Date dateProcess = d1.getTime()
		String hourProcess = DateUtils.getDateFormat(DateTypes.HOUR_PERIOD,dateProcess)
		//TODO hardcodeado el user
		def result = hourlyConceptStatsService.getStatsForUser(Long.valueOf(11), dateProcess, parameters)
		def categories = []
		def dataTws=[]
		def dataAut=[]
		def series = [['name': 'Tweets',data: dataTws],['name': 'Autores',data: dataAut]]
		def topTweets = [:]
		def topWords = [:]
		def defConcept=''
		result.result.each{
			categories.add(it.concept.conceptName)
			dataTws.add(it.mentions)
			dataAut.add(it.authors)
			topTweets.put(it.concept.conceptName, it.tweets.tweetId)
			solrFilters['conceptsId'] = it.concept.id
			def listWords=[]
			def relevantWords = tweetService.getRelevantWords(loadSolrFilters(solrFilters))
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
			if(defConcept ==''){
				defConcept=it.concept.conceptName
			}
			topWords.put(it.concept.conceptName, listWords)
		}
		def map = [title: "Politicos mas mencionados de la hora"
			       ,categories:categories,
				   series:series,
				   "xAxis":'Presidenciales',"yAxis":'Cantidad']
		def newList=[]
		topTweets.each{ iter -> 
			if(defConcept==iter.key){
			def parcialList=Tweet.getAll(iter.value)
			if(!grailsApplication.config.grails.twitter.offline)
				tweetService.loadAvatarUsers(parcialList)
			
			
				parcialList.each{
					def tweet=[:]
					def author=[:]
					author.profileImage=it.author.profileImage
					author.tweetsCount=it.author.tweetsCount
					author.followers=it.author.followers
					author.following=it.author.following
					author.accountNameSingle=it.author.accountNameSingle
					author.accountName=it.author.accountName
					
					tweet.author=author
					tweet.tweetId=it.tweetId
					tweet.created=g.formatDate( date:it.created, type:"datetime", style:"LONG", timeStyle:"SHORT")
					tweet.content=it.contentHtml
					tweet.retweetCount=it.retweetCount
					tweet.favoriteCount=it.favoriteCount
					tweet.id=it.id
					newList.add(tweet)
				}
			}
		}
		
		def finalResult = [charData:map,'topWords':topWords,topTweets:topTweets ,listTweets:newList,defConcept:defConcept]
		render finalResult as JSON
	}
	
	
}
