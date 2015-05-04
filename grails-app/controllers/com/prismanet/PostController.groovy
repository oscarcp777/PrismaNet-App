package com.prismanet
import grails.plugins.springsecurity.Secured
@Secured(['ROLE_USER','ROLE_USER_ADVANCE'])
class PostController extends MentionController{

	def facebookCommentService
	
	def list(Integer max) {
		
		log.debug "postController->list params: " + params
		Concept concept = chooseConcept(params)
		def map = loadMentionFilters()
		params.max = Math.min(max ?: 6, 100)
		def posts = facebookCommentService.getPosts(map.filters,params)
		if(params.offset==null){
			session.relevantWords=[]//facebookCommentService.getRelevantWords(loadSolrFilters())
		}
		
		[postList: posts.resultList, postTotal: posts.totalCount, concept: concept,dateFrom:map.dateFrom,dateTo:map.dateTo]
	}
	
	protected MentionService getService(){
		facebookCommentService
	}
	
}
