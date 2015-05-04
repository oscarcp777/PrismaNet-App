package com.prismanet.security

import com.prismanet.GenericController;

import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService

@Secured(['ROLE_USER','ROLE_USER_ADVANCE'])
class HomeController extends GenericController{
	
    def index() {
		if(!session.user){
			session.user=springSecurityService.currentUser
		}
		

		if(!session.user.concepts.isEmpty() && !session.concepts){
			session.concepts=springSecurityService.currentUser.concepts
		}
		redirect action: 'stats', controller: 'user'
	}
	
	@Secured(['ROLE_ADMIN'])
	def controlPanel(){
		print 'entro al panel'
	}
	def setDataMenu={
		session.menu=params.state;
		render session.menu
	}
}
