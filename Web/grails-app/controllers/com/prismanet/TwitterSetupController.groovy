package com.prismanet
import grails.plugins.springsecurity.Secured
@Secured(['ROLE_ADMIN'])
class TwitterSetupController {
	
	def scaffold = true
	def index() {
		redirect action: 'list', controller: 'twitterSetup'
	}
	
	@Secured(['ROLE_USER','ROLE_USER_ADVANCE','ROLE_ADMIN'])
	def update(Long id, Long version) {
        def twitterSetup = TwitterSetup.get(id)
        if (!twitterSetup) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'controlPanel.twitter.setup', default: 'TwitterSetup'), id])
            redirect(action: "list")
            return
        }
		if (version != null) {
			if (twitterSetup.version > version) {
				twitterSetup.errors.rejectValue("version", "default.optimistic.locking.failure",
						  [message(code: 'controlPanel.twitter.setup', default: 'TwitterSetup')] as Object[],
						  "Another user has updated this TwitterSetup while you were editing")
				render(view: "edit", model: [twitterSetup: twitterSetup])
				return
			}
		}

		twitterSetup.properties = params
				
		if (!twitterSetup.save(flush: true)) {
			twitterSetup.errors.each {
				log.info it
			}
			render(view: "create", model: [twitterSetup: twitterSetup])
			return
		}
		
		flash.message = message(code: 'default.updated.message', args: [message(code: 'controlPanel.twitter.setup', default: 'TwitterSetup'), twitterSetup.id])
		redirect(action: "show", id: twitterSetup.id)
	}
}
