package com.prismanet

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
	
    
}
