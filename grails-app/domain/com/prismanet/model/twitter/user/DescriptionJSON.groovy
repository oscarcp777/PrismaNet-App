package com.prismanet.model.twitter.user

import com.prismanet.model.twitter.entity.UrlJSON

class DescriptionJSON {
	static mapWith = "mongo"
	static embedded = ['urls']
	List<UrlJSON> urls;
	static constraints = {
	}
	static mapping ={
		version false
  }
}
