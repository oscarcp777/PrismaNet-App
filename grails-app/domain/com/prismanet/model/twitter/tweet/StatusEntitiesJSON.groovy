package com.prismanet.model.twitter.tweet

import com.prismanet.model.twitter.entity.HashtagJSON
import com.prismanet.model.twitter.entity.SymbolJSON
import com.prismanet.model.twitter.entity.UrlJSON
import com.prismanet.model.twitter.entity.UserMentionJSON

class StatusEntitiesJSON {
	static mapWith = "mongo"
	static embedded = ['hashtags',"symbols","urls","user_mentions"]
	List<HashtagJSON> hashtags;
	List<SymbolJSON> symbols;
	List<UrlJSON> urls;
	List<UserMentionJSON> user_mentions;
	static constraints = {
	}
	static mapping ={
		version false
  }
}
