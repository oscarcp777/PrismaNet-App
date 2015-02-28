package com.prismanet.security
import com.prismanet.Contact
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
class PublicController {

    def springSecurityService

    def index() { 
		println 'pasooooo'
		
        if (springSecurityService.isLoggedIn()) {
            redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
        }
		[contact:new Contact ()]
    }
	
	def save(){
		println "user save params: " + params
		redirect action: 'index', controller: 'public'
	}
}
