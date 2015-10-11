package com.prismanet.utils

import org.apache.solr.client.solrj.SolrServer
import org.apache.solr.client.solrj.impl.HttpSolrServer
import com.prismanet.ParamConfig
class SolrUtil {
				
	private static solrServer = [:]
	
	private SolrUtil() {
		
	}
	
	public static SolrServer getSolrServerInstance() {
		def url=ParamConfig.get(1).config['solr.server.url']
		getSolrServerInstance(url)
	}
	
	public static SolrServer getSolrServerInstance(String solrServerUrl) {
		if (solrServer[solrServerUrl] == null) {
			solrServer[solrServerUrl] = new HttpSolrServer(solrServerUrl)
		}else {
			return solrServer[solrServerUrl]
		}
		
	}

}
