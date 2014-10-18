package com.prismanet

import org.springframework.transaction.annotation.Transactional
import org.springframework.util.StopWatch

import com.mongodb.BasicDBObject
import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.OrderType
import com.prismanet.context.Filter
import com.prismanet.context.FacebookCommentAttributeContext
import com.prismanet.exception.ApplicationException

import facebook4j.Comment
import facebook4j.internal.json.PostJSONImpl
import facebook4j.internal.org.json.JSONObject
import groovy.sql.Sql


class FacebookCommentService extends MentionService{
	
	FacebookCommentService(){
		super(FacebookComment, new FacebookCommentAttributeContext())
	}

	@Transactional
	def savePosts(def posts, def lastUpdated){
		def index = 0
		def List<Concept> concepts = Concept.list()
		StopWatch timer = new StopWatch()
		for (BasicDBObject postObj : posts){

			JSONObject obj = new JSONObject(postObj)
			facebook4j.Post status = new PostJSONImpl(obj)
			index++
			
			for (Comment comment : status.getComments()){
				if (lastUpdated){ 
					if (!lastUpdated.before(comment.createdTime))
						continue
				}
				timer = new StopWatch()
				timer.start()
				Author author = FacebookAuthor.findByName(comment.getFrom().getName().trim())
				if (!author){
					author = new FacebookAuthor(name:comment.getFrom().getName().trim(), facebookAuthorId: comment.getFrom().getId().trim()/*, sex: Sex.M, userSince:status.getUser().getCreatedAt(), profileImage:status.getUser().getProfileImageURL()*/).save(validate:false)
					if (!author.id){
						author.discard()
						throw ApplicationException.create(author)
					}
				}
				timer.stop()
				log.debug "tiempo autor: " + timer.getTotalTimeMillis()
				timer = new StopWatch()
				timer.start()
				def message = ""
				if (comment.getMessage())
					message = comment.getMessage().trim()

				log.debug "fecha comentario: " + comment.getCreatedTime()
					
				FacebookComment post = new FacebookComment(content:message,
				author:author,
				created:comment.getCreatedTime(),
				postId:status.getId(),
				commentId:comment.getId())


				concepts.each{ concept->
					if (concept.testAddPost(post)){
						log.debug "valido para concepto: " +  concept

						if (!post.id){
							post.save(validate:false)
							if (!post.id){
								post.discard()
								throw ApplicationException.create(post)
							}
							log.info "Comentario Facebook guardado con ID :  " + post.id
							timer.stop()
							log.debug "tiempo post: " + timer.getTotalTimeMillis()
						}
						if (timer.isRunning()){
							timer.stop()
						}
						timer = new StopWatch()
						timer.start()
						def sql = new Sql(sessionFactory.currentSession.connection())
						sql.execute("insert into concept_mentions values(?,?)", [concept.id, post.id])
						timer.stop()
						log.debug  "tiempo concept: " + timer.getTotalTimeMillis()
					}
				}
			}

		}
		cleanUpGorm()
	}
	
	def getNewComments(def post, def lastUpdated){
		JSONObject obj = new JSONObject(post)
		facebook4j.Post status = new PostJSONImpl(obj)
		def i = 0
		
		for (Comment comment : status.getComments()){
			if (lastUpdated){
				if (!lastUpdated.after(comment.createdTime) ){
					log.debug "post message: " +status.message
					log.debug "foto: " +status.picture
					log.debug "comentario agreagado -fecha: " + comment.createdTime + " texto: "+ comment.getMessage()
					i++
				}else{
					log.debug "comentario ignorado -fecha: " + comment.createdTime + " texto: "+ comment.getMessage()
					continue
				}
			}
			
		}
		i
	}
	
	
	def getLastUpdated(){
		def filters = []
		def parameters = [max:1]
		def result = list(FacebookComment, new FacebookCommentAttributeContext(),
						filters, parameters,
						[[attribute:"created",value:OrderType.DESC]]);
		result?.results[0]?.created
	}
	
	def getPosts(filters, parameters){
		filters.add(new Filter(attribute:"sourceType",value:FacebookComment.class, type:FilterType.EQ))
		getMentions(filters, parameters, new FacebookCommentAttributeContext(), FacebookComment)
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
