package com.prismanet.twitter.service

import org.springframework.transaction.annotation.Transactional

import com.prismanet.model.twitter.user.UserJSON

class UserJSONService {

	@Transactional
	def saveUserJSON(UserJSON userJson) {
		userJson.save(flush:true)
		println "save userJson con ID :  " +userJson.id
		
	}
}
