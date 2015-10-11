package com.prismanet.report

import groovy.time.TimeCategory

import com.prismanet.Concept
import com.prismanet.GenericCoreService
import com.prismanet.Tweet
import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.OrderType
import com.prismanet.GenericService.ProjectionType
import com.prismanet.TweetService.SamplingType
import com.prismanet.context.Filter
import com.prismanet.context.HourlyConceptStatsAttributeContext
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils

class HourlyConceptStatsService extends GenericCoreService{
	
	def conceptService
	def tweetService

	HourlyConceptStatsService(){
		super()
	}
	
	def getStatsForUser(Long userId, Date dateProcess){
		getStatsForUser(userId, dateProcess, [:])
	}
	
	def getStatsForUser(Long userId, Date dateProcess, Map parameters){
//		GregorianCalendar d1 = new GregorianCalendar(2015, Calendar.OCTOBER, 1,17,00)
//		dateProcess = d1.getTime()
		use (TimeCategory){
			String hourProcess = DateUtils.getDateFormat(DateTypes.HOUR_PERIOD,dateProcess)
			def filters = []
			filters.add(new Filter(attribute:"userId", value:userId, type:FilterType.EQ))
			filters.add(new Filter(attribute:"hour", value:hourProcess, type:FilterType.EQ))
			def result = list(HourlyConceptStats, new HourlyConceptStatsAttributeContext(), filters, parameters, [[attribute:"mentions",value:OrderType.DESC]])
			def orderTweets = [:]
			def accountsNames = []
			result.results.each{con ->
				orderTweets.put(con.id, con.tweets.sort{-it.author.followers})
				accountsNames.add(con?.concept.twitterSetup?.includedAccounts)
			}
			['result':result.results, 'orderTweets':orderTweets,'accountNames':accountsNames]
		}
	}
	
	def void loadStats(){
		use (TimeCategory){
//			GregorianCalendar d1 = new GregorianCalendar(2015, Calendar.JULY, 18,13,00)
//			Date dateProcess = d1.getTime()
			Date dateProcess = new Date()-1.hour
			String hourProcess = DateUtils.getDateFormat(DateTypes.HOUR_PERIOD, dateProcess)
			log.debug("Hora: "+hourProcess)
			def filters = getDateStatsFilters(dateProcess)
						
			def List<Concept> concepts = conceptService.getActiveConcepts()
			concepts.each(){ concept->
				def newFilters = []
				newFilters.addAll(filters)
				newFilters.add(new Filter(attribute:"id", value:concept.id, type:FilterType.EQ))
				// Menciones y autores de la hora
				def categories = ["conceptName"]
				def projections = ["mentionId" : ProjectionType.COUNT, "authorId":ProjectionType.COUNT_DISTINCT]
				def result = conceptService.categoryStore(categories, newFilters, projections, null);
				def mentions = 0, authors = 0
				if (result.size()>0){
					mentions = result[0][1]
					authors = result[0][2]
					log.debug "hora: " + hourProcess + ", menciones: " + mentions + ", autores: " + authors
				}
				def hourlyStats = HourlyConceptStats.findByConceptAndHour(concept,hourProcess)
				if (!hourlyStats){
					// Creo nueva estadistica mensual
					hourlyStats = new HourlyConceptStats(concept:concept, hour:hourProcess)
				}
				
				// Obtengo top 3 tweets mas importantes por cant de seguidores
				SamplingType orderType = SamplingType.TOP_RELEVANT_AUTHORS
				def parameters = [:]
				parameters.max = 10
				def tweetFilters = []
				tweetFilters.addAll(filters)
				tweetFilters.add(new Filter(attribute:"conceptsId", value:concept.id, type:FilterType.EQ))
				def tweetList = tweetService.getTweets(tweetFilters, parameters, orderType).resultList
				for (def cur : tweetList){
					Tweet tweet = cur.tweet
					HourlyConceptStatsTweet hT = new HourlyConceptStatsTweet(
						retweet:tweet.retweet,
						retweetCount:tweet.retweetCount,
						favoriteCount:tweet.favoriteCount,
						tweetId:tweet.id,
						retweetId:tweet.retweetId,
						content:tweet.content,
						created:tweet.created)
					hT.author = tweet.getAuthor()
					hourlyStats.addToTweets(hT)
					print hourlyStats
//					hT.save(validate:false)
				}
				// Actualizo estadistica mensual
				hourlyStats.mentions = mentions
				hourlyStats.authors = authors
				hourlyStats.save(validate:false)
			}
		}
	}
	
	/**
	 * Devuelve dos filtros 
	 * filtro desde - primer minuto de la hora informada 
	 * filtro hasta - ultimo minuto hora informada
	 *  
	 */
	private def getDateStatsFilters(Date dateProcess){
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateProcess);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.HOUR_OF_DAY, 1);
		cal.add(Calendar.SECOND, -1);
		Date dateTo = cal.getTime();

		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date dateFrom = cal.getTime();
		log.debug("Desde: "+dateFrom)
		log.debug("Hasta: "+dateTo)
		
		getFilterList(dateFrom, dateTo)
	}
	
}
