package com.prismanet

import org.springframework.transaction.annotation.Transactional

import com.mongodb.BasicDBObject
import com.prismanet.GenericService.OrderType
import com.prismanet.context.PostAttributeContext
import com.prismanet.exception.ApplicationException

import facebook4j.Comment
import facebook4j.internal.json.PostJSONImpl
import facebook4j.internal.org.json.JSONObject
import groovy.time.TimeCategory;


class PostService extends MentionService{
	
	PostService(){
		super(Post, new PostAttributeContext())
	}

	@Transactional
	def savePosts(def posts){
		def index = 0
		def List<Concept> concepts = Concept.list()
		Date lastUpdated = getLastUpdated()

		for (BasicDBObject postObj : posts){

			JSONObject obj = new JSONObject(postObj)
			facebook4j.Post status = new PostJSONImpl(obj)
			index++
			
			for (Comment comment : status.getComments()){
				
				if (lastUpdated.after(comment.createdTime))
					continue
				
				Author author = FacebookAuthor.findByName(comment.getFrom().getName().trim())
				if (!author){
					author = new FacebookAuthor(name:comment.getFrom().getName().trim(), facebookAuthorId: comment.getFrom().getId().trim()/*, sex: Sex.M, userSince:status.getUser().getCreatedAt(), profileImage:status.getUser().getProfileImageURL()*/).save(validate:false)
					if (!author.id){
						throw ApplicationException.create(author)
					}
				}

				def message = ""
				if (comment.getMessage())
					message = comment.getMessage().trim()

				def dateCreated = new Date()
				if (comment.getCreatedTime())
					dateCreated = comment.getCreatedTime()

				Post post = new Post(content:message,
				author:author,
				created:dateCreated,
				postId:status.getId(),
				commentId:comment.getId())


				concepts.each{ concept->
					if (concept.testAddPost(post)){
						log.info "valido para concepto: " +  concept

						if (!post.id){
							post.save(validate:false)
							if (!post.id){
								throw ApplicationException.create(post)
							}
							log.info "Post guardado con ID :  " + post.id
						}
						concept.doAddToPost(post)
					}
				}
			}

		}
		cleanUpGorm()
	}
	
	
	def getLastUpdated(){
		def filters = []
		def parameters = [max:1]
		def result = list(Post, new PostAttributeContext(),
						filters, parameters,
						[[attribute:"created",value:OrderType.DESC]]);
		result?.results[0]?.created
	}

}
