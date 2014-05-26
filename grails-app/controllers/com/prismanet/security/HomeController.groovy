package com.prismanet.security

import com.prismanet.GenericController;

import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService

@Secured(['ROLE_USER'])
class HomeController extends GenericController{
	
    def index() {
		if(!session.user){
			session.user=springSecurityService.currentUser
		}
		if(!session.concepts){
			session.concepts=springSecurityService.currentUser.concepts
		}
		redirect action: 'stats', controller: 'user'
	}
	
	@Secured(['ROLE_ADMIN'])
	def controlPanel(){
		
	}
	def setDataMenu={
		session.menu=params.state;
		render session.menu
	}
}
