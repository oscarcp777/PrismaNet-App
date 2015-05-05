package com.prismanet.security
import com.prismanet.Contact
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
class PublicController {

    def springSecurityService
	def mailService

    def index() { 
        if (springSecurityService.isLoggedIn()) {
            redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
        }
		[contact:new Contact ()]
    }
	
	def save(){
		mailService.sendMail{ 
			async true
			to "consultas@prisma-net.com.ar" 
			from "info@prisma-net.com.ar"
			cc "santiagodonikian@gmail.com"
//			bcc "santiagodonikian@g2one.com" 
			subject "Consulta desde el site" 
			text """Nombre: $params.name 
                    Mail de contacto: $params.email 
                    Telefono: $params.phone
                    Pagina web: $params.website
                    Consulta: $params.message"""
		}
		redirect action: 'index', controller: 'public'
	}
}
