package com.prismanet
import grails.converters.*
import com.prismanet.GenericService.ProjectionType
import com.prismanet.chart.json.ChartPieData

class UserController {
	
	def scaffold = true
	def userService
	
	def stats = {
		//TODO agregar mensaje de error o 404 si no encuentra el user
		def groupList = ["sex"]
		def sexList = userService.categoryStore(session.user, groupList);
		groupList = ["tweetCreated","conceptsId"]
		def dateList = userService.categoryStore(session.user, groupList);
		def dataChartList = userService.categoryStore(session.user, ["conceptsId"])
		
		[user: session.user, sexList : sexList, dateList : dateList]
	}
	
	
	def monthStats = {
		//TODO agregar mensaje de error o 404 si no encuentra el user
		def groupList = ["conceptsId"]
		def criteria = User.createCriteria();
		def filters = ["id" : session.user.id]
		def projection = ["tweetsId" : ProjectionType.COUNT, "authorId" : ProjectionType.COUNT]
		def statsList = userService.categoriesService(criteria, groupList, filters, projection)
		
		[user: session.user, statsList : statsList]
	}
	def conceptTweetsJson ={
		def conceptTweets=[];
		def dateList = userService.categoryStore(session.user, ["conceptsId"])
		dateList.each { item ->
			conceptTweets.add(new ChartPieData(label:item[0],data:item[1]))
		}
		render conceptTweets as JSON
	}
	
}
