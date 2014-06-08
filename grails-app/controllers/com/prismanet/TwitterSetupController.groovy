package com.prismanet

class TwitterSetupController {
	
	def scaffold = true
	def index() {
		redirect action: 'list', controller: 'twitterSetup'
	}
	def update(){
		println 'llrgo'
		TwitterSetup  ts= TwitterSetup.get(params.id);
		if(params.field=='keywords'){
			ts.setKeywords(params.newValue)
		}
		if(params.field=='neutralHashtags'){
			ts.setNeutralHashtags(params.newValue)
		}
		if(params.field=='positiveHashtags'){
			ts.setPositiveHashtags(params.newValue)
		}
		if(params.field=='negativeHashtags'){
			ts.setNegativeHashtags(params.newValue)
		}
		ts.save(flush:true)
		render 'ok'
	}
}
