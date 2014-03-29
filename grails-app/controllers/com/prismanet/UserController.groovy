package com.prismanet
import grails.converters.*
import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService

import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.OrderType
import com.prismanet.GenericService.ProjectionType
import com.prismanet.context.Filter
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils
@Secured(['ROLE_USER'])
class UserController extends GenericController{
	
	def scaffold = true
	def userService
	SpringSecurityService springSecurityService;
	def beforeInterceptor = {
		session.user=springSecurityService.currentUser
	}
	def stats = {
		def groupList = ["tweetCreated","conceptsName"]
		def dateList = userService.categoryStore(session.user, groupList, [],null);
		[user: session.user,  dateList : dateList]
	}
	
	
	def monthStats = {
		//TODO agregar mensaje de error o 404 si no encuentra el user
		def groupList = ["conceptsName"]
		def criteria = User.createCriteria();
		def filters = [new Filter(attribute:"id",value: session.user.id, type:FilterType.EQ)]
		def projection = ["tweetsId" : ProjectionType.COUNT, "authorId" : ProjectionType.COUNT]
		def statsList = userService.groupBy(groupList, filters, projection,null)
		
		[user: session.user, statsList : statsList]
	}
	def conceptTweetsJson ={
		def container=params.div
		def dateList = userService.categoryStore(session.user, ["conceptsName"], [],null).sort{a,b -> a[1] <=> b[1] }
		def resultMap = [container:container,data:dateList,title:'Porcentajes de tweets por Concepto',name : 'Tweets']
		render resultMap as JSON
	}
	
	
	def getGroupedTweets(){
		
		log.info "getGroupedTweets params: " + params
		def container = params.div
		
		def filters = []
		filters.add(new Filter(attribute:"id",value: session.user.id, type:FilterType.EQ))
		Date dateFrom = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateFrom)
		Date dateTo = DateUtils.parseDate(DateTypes.MINUTE_PERIOD, params.dateTo)
			
		DateServiceType type = userService.getChartType(dateFrom, dateTo)
		
		
		// Obtengo tweets por hora
		def dateList = userService.getTweetsBy(filters, dateFrom, dateTo)

		def redirectOnClick = "../../tweet/list?"
		def resultMap = [:]
		//TODO localizar todos los textos
		// Parseo resultado para generar el grafico
		
		switch (type) {
			case DateServiceType.BY_MINUTE:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.MINUTE_PERIOD,
												'Tweets por minuto','Cantidad de tweets','Tweets',
												redirectOnClick+"tweetMinute=")
			break
			case DateServiceType.BY_HOUR:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.HOUR_PERIOD,
											   'Tweets por hora','Cantidad de tweets','Tweets',
											   redirectOnClick+"tweetHour=")
			break
			case DateServiceType.BY_DATE:
				resultMap = getChartLineFormat(dateList, 2, container, DateTypes.DAY_PERIOD,
												'Tweets por Dia','Cantidad de tweets','Tweets',
												redirectOnClick+"tweetCreated=")
			break
		}
		render resultMap as JSON
	}
	
	
	
//	def getComparativeConceptChartByMinute(){
//		log.debug "comparativeConceptChartByMinute params: " + params
//		def container = params.div
//		def cal = new GregorianCalendar()
//		def hourFilter=DateUtils.getDateFormat(DateTypes.HOUR_PERIOD, cal.time)
//		def dateList = userService.categoryStore(session.user, ["conceptsName","tweetByMinute"], [new Filter(attribute:"tweetByHour",value:hourFilter, type:FilterType.EQ)], 
//			[[attribute:"conceptsId",value:OrderType.ASC],[attribute:"created",value:OrderType.ASC]]);
//		log.debug "Formato del servicio: " + dateList
//		
//		def resultMap = getChartLineFormat(dateList, 2, container, DateTypes.MINUTE_PERIOD, 
//											'Tweets por minuto','Cantidad de tweets','Tweets',
//											"../../tweet/list?tweetMinute=")
//		render resultMap as JSON
//	}
//	
//	
//	def getComparativeConceptChartByHour(){
//		log.debug "comparativeConceptChartByHour params: " + params
//		def container = params.div
//		def cal = new GregorianCalendar()
//		def day = DateUtils.getDateFormat(DateTypes.DAY_PERIOD, cal.time) 
//		def dateList = userService.categoryStore(session.user, ["conceptsName","tweetByHour"], [new Filter(attribute:"tweetCreated",value:day, type:FilterType.EQ)],
//			 [[attribute:"conceptsId",value:OrderType.ASC],[attribute:"created",value:OrderType.ASC]]);
//		log.debug "Formato del servicio: " + dateList
//		
//		def resultMap = getChartLineFormat(dateList, 2, container, DateTypes.HOUR_PERIOD,
//											'Tweets por hora','Cantidad de tweets','Tweets',
//											"../../tweet/list?tweetHour=")
//		render resultMap as JSON
//	}
//	
//	def getComparativeConceptChartByDate(){
//		log.debug "comparativeConceptChartByHour params: " + params
//		def container = params.div
//		def cal = new GregorianCalendar()
//		def period = DateUtils.getDateFormat(DateTypes.MONTH_PERIOD, cal.time) ;
//		def dateList = userService.categoryStore(session.user, ["conceptsName","tweetCreated"], [new Filter(attribute:"tweetPeriod",value:period, type:FilterType.EQ)],
//			 [[attribute:"conceptsId",value:OrderType.ASC],[attribute:"created",value:OrderType.ASC]]);
//		log.debug "Formato del servicio: " + dateList
//		
//		def resultMap = getChartLineFormat(dateList, 2, container, DateTypes.DAY_PERIOD,
//											'Tweets por Dia','Cantidad de tweets','Tweets',
//											"../../tweet/list?tweetCreated=")
//		render resultMap as JSON
//	}
	
}
