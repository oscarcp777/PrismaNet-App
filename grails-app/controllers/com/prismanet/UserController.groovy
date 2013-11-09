package com.prismanet
import grails.converters.*
import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService

import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.ProjectionType
import com.prismanet.context.Filter
@Secured(['ROLE_USER'])
class UserController {
	
	def scaffold = true
	def userService
	SpringSecurityService springSecurityService;
	def beforeInterceptor = {
		session.user=springSecurityService.currentUser
	}
	def stats = {
		def groupList = ["tweetCreated","conceptsId"]
		def dateList = userService.categoryStore(session.user, groupList);
		[user: session.user,  dateList : dateList]
	}
	
	
	def monthStats = {
		//TODO agregar mensaje de error o 404 si no encuentra el user
		def groupList = ["conceptsId"]
		def criteria = User.createCriteria();
		def filters = [new Filter(attribute:"id",value: session.user.id, type:FilterType.EQ)]
		def projection = ["tweetsId" : ProjectionType.COUNT, "authorId" : ProjectionType.COUNT]
		def statsList = userService.categoriesService(criteria, groupList, filters, projection)
		
		[user: session.user, statsList : statsList]
	}
	def conceptTweetsJson ={
		def dateList = userService.categoryStore(session.user, ["conceptsId"]).sort{a,b -> a[1] <=> b[1] }
		render dateList as JSON
	}
	
}
