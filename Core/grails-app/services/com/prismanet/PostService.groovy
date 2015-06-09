package com.prismanet

import org.apache.commons.lang.time.StopWatch
import org.springframework.transaction.annotation.Transactional

import com.mongodb.BasicDBObject
import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.OrderType
import com.prismanet.context.PostAttributeContext
import com.prismanet.exception.ApplicationException

import facebook4j.Comment
import facebook4j.internal.json.PostJSONImpl
import facebook4j.internal.org.json.JSONObject
import groovy.sql.Sql



class PostService extends GenericCoreService{
	
	def conceptService
	
	PostService(){
		super(Post, new PostAttributeContext())
	}

	
	def getPosts(filters, parameters){
		if (!parameters)
			parameters = [:]
		def conceptId
		filters.each {
			if (it.attribute == "conceptsId" && it.type ==  FilterType.EQ)
				conceptId = it.value
		}
		def resultList = []
		Concept concept
		if (conceptId)
			concept = Concept.get(conceptId)
			
		list(Post, new PostAttributeContext(), filters, parameters, [[attribute:"totalLikes",value:OrderType.DESC]])
	}
	
	public String getDateGroupProperty(DateServiceType type){
		switch (type) {
			case DateServiceType.BY_MINUTE:
				return "dateByMinute"
			case DateServiceType.BY_HOUR:
				return "dateByHour"
			case DateServiceType.BY_DATE:
				return "dateCreated"
			case DateServiceType.BY_MONTH:
				return "datePeriod"
		}
		return null
	}
	
	@Transactional
	def savePosts(def posts){
		def List<Concept> concepts = conceptService.getActiveConcepts()
		for (BasicDBObject postObj : posts){
			JSONObject obj = new JSONObject(postObj)
			facebook4j.Post status = new PostJSONImpl(obj)
			
			com.prismanet.Post post = com.prismanet.Post.findByPostId(status.getId())
			if (!post){
				post = new com.prismanet.Post(postId:status.getId(),picture:status.getPicture(), created:status.getCreatedTime(),
						link:status.getLink(),name:status.getName(), totalLikes:postObj.get("totalLikes"),
						totalComments:postObj.get("totalComments"))
			}else{
				post.totalLikes = postObj.get("totalLikes")
				post.totalComments = postObj.get("totalComments")
			}
			concepts.each{ concept->
				if (concept.testAddPost(post)){
					post.addToConcepts(concept)
					post.save(validate:false)
					if (!post.id){
						post.discard()
						throw ApplicationException.create(post)
					}
				}
			}
			

		}
		cleanUpGorm()
	}
	

}
