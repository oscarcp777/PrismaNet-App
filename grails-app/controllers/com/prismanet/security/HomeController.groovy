package com.prismanet.security

import grails.plugins.springsecurity.Secured;

@Secured(['ROLE_USER'])
class HomeController {

    def index() {
		println "caaaa"
	}
	
	@Secured(['ROLE_ADMIN'])
	def onlyAdmin(){render 'admin'}
}
