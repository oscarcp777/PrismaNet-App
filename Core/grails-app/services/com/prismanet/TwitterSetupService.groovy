package com.prismanet

import org.apache.commons.lang.StringUtils

import com.prismanet.GenericService.OrderType
import com.prismanet.GenericService.ProjectionType
import com.prismanet.context.TwitterSetupAttributeContext

class TwitterSetupService extends GenericCoreService {
		
	def conceptService
	
	def getLastUpdated(){
		def filters = []
		def parameters = [max:1]
		def result = list(TwitterSetup, new TwitterSetupAttributeContext(),
						filters, parameters, 
						[[attribute:"lastUpdated",value:OrderType.DESC]]);
		result?.results[0]?.lastUpdated ?: new Date()
	}
	
	def getConfiguration(){
		def concepts = conceptService.getActiveConcepts()
		String stringConfig = ""
		concepts.each{ Concept c ->
			if (c.twitterSetup){
				stringConfig = addTerms(stringConfig, c.twitterSetup.includedAccounts)
				stringConfig = addTerms(stringConfig, c.twitterSetup.keywords)
				stringConfig = addTerms(stringConfig, c.twitterSetup.neutralHashtags)
				stringConfig = addTerms(stringConfig, c.twitterSetup.positiveHashtags)
				stringConfig = addTerms(stringConfig, c.twitterSetup.negativeHashtags)
			}
		}
		if (stringConfig.size() > 0)
			stringConfig = stringConfig[0..-2]
		log.info "la configuracion es: " + stringConfig
		stringConfig
	}
	
	private String addTerms(String finalConfig, String terms){
		terms?.split(',').each{ term ->
			if (term.length()>2){
				if (term[0]=='\"' && term[term.length()-1]=='\"')
					term = term[1..-2]
				if (!StringUtils.containsIgnoreCase(finalConfig,term))
					finalConfig += term + ","
			}	
		}
		return finalConfig
	}
		
}
