package com.prismanet

import org.springframework.transaction.annotation.Transactional

import com.mongodb.BasicDBObject
import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.OrderType
import com.prismanet.context.Filter
import com.prismanet.context.PostAttributeContext
import com.prismanet.exception.ApplicationException

import facebook4j.Comment
import facebook4j.internal.json.PostJSONImpl
import facebook4j.internal.org.json.JSONObject


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
				print "fecha ultimo post: " + lastUpdated 
				if (!lastUpdated || lastUpdated.after(comment.createdTime))
					continue
				
				Author author = FacebookAuthor.findByName(comment.getFrom().getName().trim())
				if (!author){
					author = new FacebookAuthor(name:comment.getFrom().getName().trim(), facebookAuthorId: comment.getFrom().getId().trim()/*, sex: Sex.M, userSince:status.getUser().getCreatedAt(), profileImage:status.getUser().getProfileImageURL()*/).save(validate:false)
					if (!author.id){
						author.discard()
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
								post.discard()
								throw ApplicationException.create(post)
							}
							log.info "Post guardado con ID :  " + post.id
						}
						concept.doAddToMentions(post)
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
	
	def getPosts(filters, parameters){
		filters.add(new Filter(attribute:"sourceType",value:Post.class, type:FilterType.EQ))
		getMentions(filters, parameters, new PostAttributeContext(), Post)
	}
	
	public String getDateGroupProperty(DateServiceType type){
		switch (type) {
			case DateServiceType.BY_MINUTE:
				return "postByMinute"
			case DateServiceType.BY_HOUR:
				return "postByHour"
			case DateServiceType.BY_DATE:
				return "postCreated"
			case DateServiceType.BY_MONTH:
				return "postPeriod"
		}
		return null
	}

}
