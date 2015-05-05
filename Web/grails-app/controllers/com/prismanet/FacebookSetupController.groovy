package com.prismanet
import grails.plugins.springsecurity.Secured
@Secured(['ROLE_ADMIN'])
class FacebookSetupController {
	
	def scaffold = true
	def facebookSetupService
	def index() {
		redirect action: 'list', controller: 'facebookSetup'
	}
	def save() {
		FacebookSetup facebookSetup = new FacebookSetup(params)
		try {
			
			facebookSetupService.save(facebookSetup)
			flash.message = message(code: 'default.created.message', args: [message(code: 'facebookSetup.label'), facebookSetup.id])
			redirect(action: "show", id: facebookSetup.id)
		} catch (Exception e) {
			flash.message = e.message
			render(view: "create", model: [facebookSetup: facebookSetup])
			return
		}
	}

   
}
