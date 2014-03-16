package com.prismanet

import com.prismanet.GenericService.OrderType
import com.prismanet.context.FacebookSetupAttributeContext

class FacebookSetupService extends GenericCoreService {
		
	
	def getLastUpdated(){
		def filters = []
		def parameters = [max:1]
		def result = list(FacebookSetup, new FacebookSetupAttributeContext(),
						filters, parameters, 
						[[attribute:"lastUpdated",value:OrderType.DESC]]);
		result?.results[0].lastUpdated
	}
	
	def getConfiguration(){
		def configs = FacebookSetup.findAll()
		def stringConfig = ""
		configs.each{ FacebookSetup setup ->
			if (setup.keywords)
				stringConfig += setup.keywords + ","
		}
		stringConfig = stringConfig[0..-2]
		print "la configuracion es: " + stringConfig
		stringConfig
	}
	
		
}
