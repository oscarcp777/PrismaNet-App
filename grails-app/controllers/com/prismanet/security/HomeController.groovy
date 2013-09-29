package com.prismanet.security

import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService

@Secured(['ROLE_USER'])
class HomeController {
	SpringSecurityService springSecurityService;
    def index() {
		session.user=springSecurityService.currentUser;
	}
	
	
	@Secured(['ROLE_ADMIN'])
	def onlyAdmin(){render 'admin'}
}
