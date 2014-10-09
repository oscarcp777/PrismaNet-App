



package com.prismanet

import org.apache.solr.client.solrj.SolrQuery
import org.apache.solr.client.solrj.SolrServer
import org.apache.solr.client.solrj.response.QueryResponse
import org.apache.solr.common.params.ModifiableSolrParams

import com.prismanet.context.AttributeContext
import com.prismanet.context.MentionAttributeContext
import com.prismanet.utils.SolrUtil

class SolrJob {
	def group = "solrJobs"
	static triggers = {
		simple repeatInterval: 60000, repeatCount:-1 , startDelay: 60100
	}

	def execute() {
		log.info "SolrJob ejecutado: " + new Date()
		AttributeContext context = new MentionAttributeContext()
		SolrServer solr = SolrUtil.getSolrServerInstance()
		SolrQuery query = new SolrQuery()
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		solrParams.set("qt", "/dataimport");
		solrParams.set("command", "delta-import");
		
		QueryResponse respSolr = solr.query(solrParams)
		log.info "SolrJob finalizado: " + new Date()
	}


}
