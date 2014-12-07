package com.prismanet


class PostController extends MentionController{

	def facebookCommentService
	
	def list(Integer max) {
		
		log.debug "postController->list params: " + params
		Concept concept = chooseConcept(params)
		def filters = loadMentionFilters()
		params.max = Math.min(max ?: 6, 100)
		def posts = facebookCommentService.getPosts(filters,params)
		if(params.offset==null){
			session.relevantWords=facebookCommentService.getRelevantWords(loadSolrFilters())
		}
		
		[postList: posts.resultList, postTotal: posts.totalCount, concept: concept, relevantWords:session.relevantWords]
	}
	
	protected MentionService getService(){
		facebookCommentService
	}
	
}
