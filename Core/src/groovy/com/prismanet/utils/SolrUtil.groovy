package com.prismanet.utils

import org.apache.solr.client.solrj.SolrServer
import org.apache.solr.client.solrj.impl.HttpSolrServer

class SolrUtil {
				
	private static solrServer = [:]
	
	private SolrUtil() {
		
	}
	
	public static SolrServer getSolrServerInstance() {
		getSolrServerInstance(grails.util.Holders.getGrailsApplication().config.solr.server.url)
	}
	
	public static SolrServer getSolrServerInstance(String solrServerUrl) {
		if (solrServer[solrServerUrl] == null) {
			solrServer[solrServerUrl] = new HttpSolrServer(solrServerUrl)
		}else {
			return solrServer[solrServerUrl]
		}
		
	}

}
