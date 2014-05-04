package com.prismanet
import java.security.Policy.Parameters;

import grails.converters.*
import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService
import groovy.time.TimeCategory
import twitter4j.ResponseList
import twitter4j.Twitter
import twitter4j.TwitterFactory

import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.ProjectionType
import com.prismanet.context.Filter
import com.prismanet.context.UserAttributeContext
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils
@Secured(['ROLE_USER'])
class UserController extends GenericController{
	
	def scaffold = true
	def userService
	
	
	private Filter getTwitterFilter(){
		new Filter(attribute:"sourceType",value:Tweet.class, type:FilterType.EQ)
	}
	
	private Filter getFacebookFilter(){
		new Filter(attribute:"sourceType",value:Post.class, type:FilterType.EQ)
	}
	
	private Filter getFilterByMentionType(MentionType type){
		if (type)
			switch (type){
				case MentionType.TWITTER:
					return getTwitterFilter()
				case MentionType.FACEBOOK:
					return getFacebookFilter()
			}
	}
	
	private List loadFilters(Map parameters){
		def filters = []
		def filter = getFilterByMentionType(parameters.sourceType)
		if (filter)
			filters.add(filter)	
		if (parameters.userId){
			filters.add(new Filter(attribute:"id",value: parameters.userId, type:FilterType.EQ))
		}
		filters
	}
	
	private Map getPropertiesByMentionType(){
		def strings = [(MentionType.FACEBOOK) :[name:'Posts', controller:'post'], 
						(MentionType.TWITTER): [name:'Tweets', controller: 'tweet'],
						(MentionType.ALL): [name:'Menciones']]
	}
	
	
	def stats = {
		def groupList = ["dateCreated","conceptsName"]
		def dateList = userService.categoryStore(session.user, groupList, [],null);
		[user: session.user,  dateList : dateList]
	}
	
	
	def monthStats = {
		def groupList = ["conceptsName"]
		def criteria = User.createCriteria();
		def filters = [new Filter(attribute:"id",value: session.user.id, type:FilterType.EQ)]
		def projection = [:]
		projection["mentionId"] = ProjectionType.COUNT
		projection["authorId"] = ProjectionType.COUNT_DISTINCT
		def statsList = userService.groupBy(User, new UserAttributeContext(), groupList, filters, projection,null)
		
		[user: session.user, statsList : statsList]
	}
	def conceptTweetsJson ={
		def sourceType = params.channel as MentionType
		def strings = getPropertiesByMentionType()
		def container=params.div
		def filters = loadFilters([sourceType: sourceType])
		
		def dateList = userService.categoryStore(session.user, ["conceptsName"], filters, null).sort{a,b -> a[1] <=> b[1] }
		def resultMap = [container:container,data:dateList, divTitle: strings[sourceType].name  + ' por Concepto', title:'Porcentajes de ' + strings[sourceType].name + ' por Concepto', name : strings[sourceType].name]
		render resultMap as JSON
	}
	
	def totalFollowers ={
		def container=params.div
		def dateList =[]
		session.concepts=springSecurityService.currentUser.concepts
		def usersName=[]
		session.concepts.each {
			usersName.add(it.twitterSetup.includedAccounts.replace("@",""))
		}
		Twitter twitter = new TwitterFactory().getInstance();
		ResponseList<twitter4j.User> users = null
		users = twitter.lookupUsers((String[])usersName.toArray());
		for (twitter4j.User user : users) {
			dateList.add([user.screenName,user.followersCount])
		}
		def resultMap = [container:container,data:dateList,title: message(code: "user.stats.tweets.followers.sub"),name : 'Seguidores']
		render resultMap as JSON
	}
	
	void loadZerosForMinute(series,from,to){
		def dateValueList = [:]
		def valueList = []
		series.each {
			dateValueList = [:]
			valueList = []
			it["data"].each {
				dateValueList.put(it["x"], it["y"])
			}
			
			it["data"]=DateUtils.loadZerosForMinute(dateValueList,from,to)
		}
	}
	
	void addConceptsEmpty(List series,User user,from,to){
		def conceptsListExits=[]
		def conceptsListNoExits=[]
		series.each {
			conceptsListExits.add(it["name"])
		}
		user.concepts.each {
			 if(!conceptsListExits.contains(it.conceptName)){
				 conceptsListNoExits.add(it.conceptName)
			 }
		}
		conceptsListNoExits.each {
			series.add(
				[name:it,data:DateUtils.loadZerosForMinute([:],from,to)]
				)
		}
	}
	
	def conceptsRealTime={
		def sourceType = params.channel as MentionType
		def filters = loadFilters([sourceType:sourceType,userId:session.user.id])
		def container = params.div
		def strings = getPropertiesByMentionType()

		Date from,to
		to=DateUtils.getDateWithoutSeconds(new Date())
			use ( TimeCategory ) {
	    		from = to-20.minutes
     		}
		
		def dateList = userService.getMentionsBy(filters,from,to);
		def resultMap = getChartLineFormat(dateList, 2, container, DateTypes.MINUTE_PERIOD,
			strings[sourceType].name + ' por minuto','Minutos','Cantidad de ' + strings[sourceType].name, strings[sourceType].controller ? "../"+strings[sourceType].controller+"/list?id=" : "#")
		loadZerosForMinute(resultMap["series"],from,to)
		addConceptsEmpty(resultMap["series"],springSecurityService.currentUser,from,to)
		
		def json =[id:session.user.id,"subTitle":"Actualizacion en tiempo Real de la cantidad de " +strings[sourceType].name
			      ,dateProp:"dateMinute",ajaxMethodReload:'conceptsRealTimeForOneMinute',channel:sourceType]
		resultMap.putAll(json)
		render resultMap as JSON
	}
	
	def conceptsRealTimeForOneMinute={
		Date from,to
		def sourceType = params.channel as MentionType
		def filters = loadFilters([sourceType:sourceType,userId:session.user.id])
		
		to=DateUtils.getDateWithoutSeconds(new Date())
		use ( TimeCategory ) {
				from = to-1.minutes
		}
		
		def dateList= userService.getMentionsBy(filters, from, to)
		def resultMap = getChartLineFormat(dateList, 2,'', DateTypes.MINUTE_PERIOD,'','','','')
		loadZerosForMinute(resultMap["series"],from,to)
		addConceptsEmpty(resultMap["series"],session.user,from,to)
		render resultMap["series"] as JSON
	}
	
	def getGroupedTweets(){
		log.info "getGroupedTweets params: " + params
		def container = params.div
		def sourceType = params.channel as MentionType
		def properties = getPropertiesByMentionType()
		
		def filters = loadFilters([sourceType:sourceType, userId:session.user.id])
		Date dateFrom = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateFrom)
		Date dateTo = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateTo)
		DateServiceType type = userService.getChartType(dateFrom, dateTo)
		
		
		// Obtengo tweets por hora
		def dateList = userService.getMentionsBy(filters, dateFrom, dateTo)
		def redirectOnClick = properties[sourceType]?.controller ? "../"+properties[sourceType].controller+"/list?" : "#"
		def resultMap = [:]
		//TODO localizar todos los textos
		// Parseo resultado para generar el grafico
		
		switch (type) {
			case DateServiceType.BY_MINUTE:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.MINUTE_PERIOD,
												properties[sourceType].name +' por minuto','Minutos','Cantidad de '+properties[sourceType].name,
												redirectOnClick+"dateMinute=")
			break
			case DateServiceType.BY_HOUR:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.HOUR_PERIOD,
											   properties[sourceType].name +' por hora','Horas','Cantidad de '+properties[sourceType].name,
											   redirectOnClick+"dateHour=")
			break
			case DateServiceType.BY_DATE:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.DAY_PERIOD,
												properties[sourceType].name +' por Dia','Dias','Cantidad de '+properties[sourceType].name,
												redirectOnClick+"dateCreated=")
			break
			case DateServiceType.BY_MONTH:
			resultMap = getChartLineFormat(dateList, 2, container, DateTypes.MONTH_PERIOD,
											properties[sourceType].name +' por Mes','Mes','Cantidad de ' +properties[sourceType].name,
											redirectOnClick+"dateCreated=")
		break
		}
		render resultMap as JSON
	}
	
	
	
}
