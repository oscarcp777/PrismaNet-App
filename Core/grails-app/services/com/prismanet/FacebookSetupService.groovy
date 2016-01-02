package com.prismanet

import org.springframework.context.i18n.LocaleContextHolder

import com.prismanet.GenericService.OrderType
import com.prismanet.context.FacebookSetupAttributeContext

import facebook4j.Facebook
import facebook4j.FacebookFactory

class FacebookSetupService extends GenericCoreService {
		
	def messageSource
	def conceptService
	
	def getLastUpdated(){
		def filters = []
		def parameters = [max:1]
		def result = list(FacebookSetup, new FacebookSetupAttributeContext(),
						filters, parameters, 
						[[attribute:"lastUpdated",value:OrderType.DESC]]);
		result?.results[0]?.lastUpdated ?: new Date()
	}
	
	def getConfiguration(){
		def concepts = conceptService.getActiveConcepts()
		def stringConfig = ""
		concepts.each{ Concept c ->
			if (c.facebookSetup?.keywords && !stringConfig.contains(c.facebookSetup?.keywords))
				stringConfig += c.facebookSetup.keywords + ","
		}
		if (stringConfig.size() > 0)
			stringConfig = stringConfig[0..-2]
		log.info "la configuracion de facebook es: " + stringConfig
		stringConfig
	}
	
	def save(FacebookSetup setup){
		Facebook facebook = new FacebookFactory().getInstance()
		try {
			for (String keyword in setup?.keywords?.split(',')) {
				facebook4j.User user = facebook.getUser(keyword);
				if (user == null)
					throw new RuntimeException(messageSource.getMessage("facebookSetup.keywords.invalid", [keyword] as Object[], LocaleContextHolder.locale))
				else
					setup.accounts = setup.accounts ? setup.accounts + user.getId() + "," : user.getId() + ","
			}
			setup.save(flush: true)
		} catch (Exception e) {
			throw new RuntimeException(messageSource.getMessage("facebookSetup.keywords.connection.error", [e.message] as Object[],LocaleContextHolder.locale))
			
		}
	}
	
		
}
