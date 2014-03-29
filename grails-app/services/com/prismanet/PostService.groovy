package com.prismanet

import org.springframework.transaction.annotation.Transactional

import com.mongodb.BasicDBObject
import com.prismanet.context.PostAttributeContext
import com.prismanet.exception.ApplicationException

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

		for (BasicDBObject postObj : posts){

			JSONObject obj = new JSONObject(postObj)
			facebook4j.Post status = new PostJSONImpl(obj)
			index++

			def start = System.currentTimeMillis()
			Author author = FacebookAuthor.findByName(status.getFrom().getName())
			if (!author){
				author = new FacebookAuthor(name:status.getFrom().getName(), facebookAuthorId: status.getFrom().getId(), sex: Sex.M/*, userSince:status.getUser().getCreatedAt(), profileImage:status.getUser().getProfileImageURL()*/).save()
				if (!author.id){
					throw ApplicationException.create(author)
				}
			}else{
				// Variables a actualizar
				//					author.followers = status.getUser().getFollowersCount()
				//					author.profileImage = status.getUser().getProfileImageURL()
			}

			def message = ""
			if (status.getMessage())
				message = status.getMessage().trim()

			def dateCreated = new Date()
			if (status.getCreatedTime())
				dateCreated = status.getCreatedTime()

			Post post = new Post(content:message,
			author:author,
			created:dateCreated,
			postId:status.getId())


			concepts.each{ concept->
				if (concept.testAddPost(post)){
					log.info "valido para concepto: " +  concept

					if (!post.id){
						post.save()
						if (!post.id){
							throw ApplicationException.create(post)
						}
						log.info "Post guardado con ID :  " + post.id
					}
					concept.doAddToPost(post)
				}
			}


		}
		cleanUpGorm()
	}

}
