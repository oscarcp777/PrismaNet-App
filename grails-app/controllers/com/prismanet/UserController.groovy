package com.prismanet

import com.prismanet.GenericService.ProjectionType

class UserController {
	
	def scaffold = true
	def userService
	
	def stats = {
		User user = User.findById(params.id)
		def groupList = ["sex"]
		def sexList = userService.categoryStore(user, groupList);
		groupList = ["tweetCreated","conceptsId"]
		def dateList = userService.categoryStore(user, groupList);
		
		[user: user, sexList : sexList, dateList : dateList]
	}
	
	
	def monthStats = {
		User user = User.findById(params.id)
		def groupList = ["conceptsId"]
		def criteria = User.createCriteria();
		def filters = ["id" : user.id]
		def projection = ["tweetsId" : ProjectionType.COUNT, "authorId" : ProjectionType.COUNT]
		def statsList = userService.categoriesService(criteria, groupList, filters, projection)
		
		[user: user, statsList : statsList]
	}
    
}
