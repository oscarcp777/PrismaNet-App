package com.prismanet

import org.apache.commons.lang.StringUtils

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
		String stringConfig = ""
		configs.each{ TwitterSetup setup ->
			stringConfig = addTerms(stringConfig, setup.includedAccounts)
			stringConfig = addTerms(stringConfig, setup.keywords)
			stringConfig = addTerms(stringConfig, setup.neutralHashtags)
			stringConfig = addTerms(stringConfig, setup.positiveHashtags)
			stringConfig = addTerms(stringConfig, setup.negativeHashtags)
		}
		stringConfig = stringConfig[0..-2]
		log.info "la configuracion es: " + stringConfig
		stringConfig
	}
	
	private String addTerms(String finalConfig, String terms){
		terms?.split(',').each{ term ->
			if (term[0]=='\"' && term[term.length()-1]=='\"')
				term = term[1..-2]
			if (!StringUtils.containsIgnoreCase(finalConfig,term))
				finalConfig += term + ","
		}
		return finalConfig
	}
		
}
