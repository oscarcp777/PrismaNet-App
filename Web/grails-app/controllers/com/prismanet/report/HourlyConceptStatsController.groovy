package com.prismanet.report

import grails.converters.JSON

import com.prismanet.GenericController
import com.prismanet.Tweet
import com.prismanet.ParamConfig
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils


class HourlyConceptStatsController extends GenericController {

	def hourlyConceptStatsService
	def tweetService
	
	def loadStats={
		hourlyConceptStatsService.loadStats()
		render "Estadisticas cargadas correctamente"
	}
	
	def getIdUser(){
		def idUser=Long.valueOf(getConfig('user.report.id'))
		if(session.user){
			idUser=session.user.id
		}
		idUser
	}
	def statsForUser={
		print params.dateReport
		Date dateReport = DateUtils.parseDate(DateTypes.HOUR_SIMPLIFIED, params.dateReport)
		def parameters = [:]
		parameters.max = 3
		def solrFilters = [dateHour:dateReport.getTime()]
		//TODO hardcodeado el user
		def idUser=getConfig('user.report.id')
		def result = hourlyConceptStatsService.getStatsForUser(getIdUser(), dateReport, parameters)
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
			topTweets.put(it.concept.conceptName, result.orderTweets[it.id].tweetId)
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
		def listWords=topWords[defConcept]
		def title= !session.user?"Políticos más mencionados en la hora":"Conceptos con más menciones en la hora"
		def map = [title: title
			       ,categories:categories,
				   series:series,
				   "xAxis":'Políticos',"yAxis":'Cantidad']
		def newList=[]
		def mapTweets=[:]
		topTweets.each{ iter -> 
			def parcialList=Tweet.getAll(iter.value)
			def tempList=loadDataTweets(parcialList)
			if(defConcept==iter.key){
				newList=tempList
			}
			mapTweets.putAt(iter.key, tempList)
		}
		session.mapTweets=mapTweets
		session.topWords=topWords;
		def finalResult = [hourReport:dateReport.time,charData:map,'topWords':topWords,topTweets:topTweets ,listTweets:newList,listWords:listWords,defConcept:defConcept]
		render finalResult as JSON
	}

	def dataForConcept(){
		def newList=session.mapTweets[params.concept]
		def listWords=session.topWords[params.concept]
		def finalResult =[listTweets:newList,listWords:listWords]
		render finalResult as JSON
	}
	
	def loadDataTweets(tweets){
		def newList=[]
		if(getConfig('api.twitter.offline')!='true'){
		tweetService.loadAvatarUsers(tweets)
		}
		tweets.each{
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
		newList
	} 
	
	def sendTweets={
		tweetService.sendTweet(
			"Mira que paso en la última hora en al ambiente político info.prisma-net.com.ar/public/report")
		render "ok"
	}
}
