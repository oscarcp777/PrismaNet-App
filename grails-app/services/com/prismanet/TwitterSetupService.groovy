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
		result.results[0].lastUpdated
	}
	
		
}
