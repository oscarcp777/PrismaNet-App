package com.prismanet

import org.springframework.context.i18n.LocaleContextHolder

import com.prismanet.GenericService.OrderType
import com.prismanet.context.FacebookSetupAttributeContext

import facebook4j.Facebook
import facebook4j.FacebookFactory

class FacebookSetupService extends GenericCoreService {
		
	def messageSource
	
	def getLastUpdated(){
		def filters = []
		def parameters = [max:1]
		def result = list(FacebookSetup, new FacebookSetupAttributeContext(),
						filters, parameters, 
						[[attribute:"lastUpdated",value:OrderType.DESC]]);
		result?.results[0]?.lastUpdated ?: new Date()
	}
	
	def getConfiguration(){
		def configs = FacebookSetup.findAll()
		def stringConfig = ""
		configs.each{ FacebookSetup setup ->
			if (setup.keywords && !stringConfig.contains(setup.keywords))
				stringConfig += setup.keywords + ","
		}
		stringConfig = stringConfig[0..-2]
		print "la configuracion de facebook es: " + stringConfig
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
