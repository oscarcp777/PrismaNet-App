package com.prismanet

import com.prismanet.GenericService.OrderType
import com.prismanet.GenericService.ProjectionType
import com.prismanet.context.TwitterSetupAttributeContext

class TwitterSetupService extends GenericCoreService {
		
	
	def getLastUpdated(){
		def filters = []
		def parameters = [max:1]
		def result = list(TwitterSetup, new TwitterSetupAttributeContext(),
						filters, parameters, 
						[[attribute:"lastUpdated",value:OrderType.DESC]]);
		result?.results[0]?.lastUpdated ?: new Date()
	}
	
	def getConfiguration(){
		def configs = TwitterSetup.findAll()
		def stringConfig = ""
		configs.each{ TwitterSetup setup ->
			if (setup.includedAccounts && !stringConfig.contains(setup.includedAccounts))
				stringConfig += setup.includedAccounts + ","
			if (setup.keywords && !stringConfig.contains(setup.keywords))
				stringConfig += setup.keywords + ","
			if (setup.neutralHashtags && !stringConfig.contains(setup.neutralHashtags))
				stringConfig += setup.neutralHashtags + ","
			if (setup.positiveHashtags && !stringConfig.contains(setup.positiveHashtags))
				stringConfig += setup.positiveHashtags + ","
			if (setup.negativeHashtags && !stringConfig.contains(setup.negativeHashtags ))
				stringConfig += setup.negativeHashtags 
		}
		stringConfig = stringConfig[0..-2]
		print "la configuracion es: " + stringConfig
		stringConfig
	}
	
		
}
