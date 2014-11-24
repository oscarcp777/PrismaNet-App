package com.prismanet
import twitter4j.ResponseList
import twitter4j.Twitter
import twitter4j.TwitterFactory

import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.OrderType
import com.prismanet.GenericService.ProjectionType
import com.prismanet.context.Filter
import com.prismanet.context.UserAttributeContext
import com.prismanet.importer.MongoPostsImporter;
import com.prismanet.model.security.SecRole
import com.prismanet.model.security.SecUserSecRole
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils

import facebook4j.Facebook
import facebook4j.FacebookFactory
import grails.converters.*
import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService
import groovy.time.TimeCategory
@Secured(['ROLE_USER'])
class UserController extends GenericController{
	
	def scaffold = true
	def userService
	
	
	private Filter getTwitterFilter(){
		new Filter(attribute:"sourceType",value:Tweet.class, type:FilterType.EQ)
	}
	
	private Filter getFacebookFilter(){
		new Filter(attribute:"sourceType",value:FacebookComment.class, type:FilterType.EQ)
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
		def strings = [(MentionType.FACEBOOK) :[name:'Comentarios', controller:'post'], 
						(MentionType.TWITTER): [name:'Tweets', controller: 'tweet'],
						(MentionType.ALL): [name:'Menciones']]
	}
	def  nameMentionType(type){
		def namesMentions = [(MentionType.FACEBOOK) :'FACEBOOK',
						(MentionType.TWITTER): 'TWITTER',(MentionType.ALL): 'ALL']
		namesMentions[type]
	}
	
	
	def stats = {
		[user: session.user]
	}
		
	def monthStats = {
		def groupList = ["conceptsName"]
		def sourceType = params.channel as MentionType
		def filters = loadFilters([sourceType:sourceType, userId:session.user.id])
		def projection = [:]
		projection["mentionId"] = ProjectionType.COUNT
		projection["authorId"] = ProjectionType.COUNT_DISTINCT
		
		// Manejo datepicker
		Date dateFrom, dateTo
		if (params.dateFrom)
			dateFrom = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateFrom)
		if (params.dateTo)
			dateTo = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateTo)
		filters.addAll(userService.getFilterList(dateFrom, dateTo))
		
		def statsList = userService.groupBy(User, new UserAttributeContext(), groupList, filters, projection,[[attribute:"mentionId",value:OrderType.DESC]])
		
		render(template: "monthStats", model: [statsList:statsList])
	}
	
	
	def postsStats = {
		PostService postService = new PostService()
		def filters = []
		if (session.user.id){
			filters.add(new Filter(attribute:"userId",value: session.user.id, type:FilterType.EQ))
		}
		Date dateFrom, dateTo
		if (params.dateFrom)
			dateFrom = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateFrom)
		if (params.dateTo)
			dateTo = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateTo)
		filters.addAll(userService.getFilterList(dateFrom, dateTo))
		def result = postService.getPosts(filters, [max:10])
		def statsList = result.results.collect{
			
			[created:it.created,name:it.name,postId:it.postId,picture:it.icon,link:createLing(it.postId),totalLikes:it.totalLikes, totalComments:it.totalComments, faceName:it.concepts[0]?.facebookSetup?.keywords]
		}
		render(template: "postStats", model: [statsList:statsList])
	}
	
	def conceptTweetsJson ={
		def sourceType = params.channel as MentionType
		def strings = getPropertiesByMentionType()
		def container=params.div
		def filters = loadFilters([sourceType:sourceType,userId:session.user.id])
		Date dateFrom, dateTo
		if (params.dateFrom)
			dateFrom = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateFrom)
		if (params.dateTo)
			dateTo = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateTo)
		filters.addAll(userService.getFilterList(dateFrom, dateTo))
		def dateList = userService.categoryStore(session.user, ["conceptsName"], filters, null).sort{a,b -> a[1] <=> b[1] }
		def resultMap = [container:container,data:dateList, divTitle: strings[sourceType].name  + ' por Concepto', title:'Porcentajes de ' + strings[sourceType].name + ' por Concepto', name : strings[sourceType].name]
		render resultMap as JSON
	}
	
	def totalFollowers ={
		def resultMap=[:]
		def channel=params.channel as MentionType
		
		if(channel == MentionType.TWITTER){
		 resultMap=getTotalFollowers(params.div)
		}else if(channel == MentionType.FACEBOOK){
		 resultMap=getTotalikes(params.div)
		} else{
		resultMap=[container:params.div,data:[],title: message(code: "user.stats.all.message"),name : '']
		}
		render resultMap as JSON
	}
	def getTotalikes(container){
		def dateList =[]
		session.concepts=springSecurityService.currentUser.concepts
		def usersName=[]
		session.concepts.each {
			if(it.facebookSetup !=null ){
				usersName.add(it.facebookSetup.accounts.replace(",",""))
			}
		}
		MongoPostsImporter importer = new MongoPostsImporter()
		dateList = importer.getUsersByLikes(usersName)
		dateList = dateList.collect{
			[it._id,it.likes]
		}
		def resultMap=[container:container,data:dateList,title: message(code: "user.stats.facebook.likes.sub"),name : message(code: "user.stats.title.likes")]
		resultMap
	}
	def getTotalFollowers(container){
		def dateList =[]
		session.concepts=springSecurityService.currentUser.concepts
		def usersName=[]
		session.concepts.each {
			usersName.add(it.twitterSetup.includedAccounts.replace("@",""))
		}
		if(!grailsApplication.config.grails.twitter.offline){
		Twitter twitter = new TwitterFactory().getInstance();
		ResponseList<twitter4j.User> users = null
		if (usersName.size>0)
			users = twitter.lookupUsers((String[])usersName.toArray());
		for (twitter4j.User user : users) {
			dateList.add([user.screenName,user.followersCount])
		}
		}
		def resultMap=[container:container,data:dateList,title: message(code: "user.stats.tweets.followers.sub"),name : message(code: "user.stats.title.followers")]
		resultMap
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
		resultMap["series"]=resultMap["series"].sort{a,b -> a[1] <=> b[1] }
		def json =[id:session.user.id,"subTitle":"Actualizacion en tiempo Real de la cantidad de " +strings[sourceType].name
			      ,dateProp:"dateMinute",ajaxMethodReload:'conceptsRealTimeForOneMinute',channel:nameMentionType(sourceType)]
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
		resultMap["series"]=resultMap["series"].sort{a,b -> a[1] <=> b[1] }
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
											redirectOnClick+"datePeriod=")
		break
		}
		render resultMap as JSON
	}
	
	def save(){
		log.info "user save params: " + params
		def user = new User (params)
		if (!user.save(flush: true)) {
			user.errors.each {
				log.info it
			}
			render(view: "create", model: [user: user])
			return
		}
		
		def roleUser = SecRole.findByAuthority('ROLE_USER')
		SecUserSecRole.create user, roleUser, true
		
		flash.message = message(code: 'default.created.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), user.id])
		redirect(action: "show", id: user.id)
	}
	
	
	
}
