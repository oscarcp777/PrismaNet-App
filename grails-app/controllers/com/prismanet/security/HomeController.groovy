package com.prismanet.security

import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService

@Secured(['ROLE_USER'])
class HomeController {
	SpringSecurityService springSecurityService;
    def index() {
		redirect action: 'stats', controller: 'user'
	}
	
	@Secured(['ROLE_ADMIN'])
	def controlPanel(){
		
	}
}
