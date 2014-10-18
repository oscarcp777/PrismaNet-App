package com.prismanet


class PostController extends MentionController{

	def facebookCommentService
	
	def list(Integer max) {
		
		log.debug "postController->list params: " + params
		Concept concept = chooseConcept(params)
		def filters = loadMentionFilters()
		def relevantWords = facebookCommentService.getRelevantWords(loadSolrFilters())
		params.max = Math.min(max ?: 6, 100)
		def posts = facebookCommentService.getPosts(filters,params)
		
		[postList: posts.resultList, postTotal: posts.totalCount, concept: concept, relevantWords:relevantWords]
	}
	
	protected MentionService getService(){
		facebookCommentService
	}
	
}
