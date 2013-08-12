package com.prismanet.model.twitter.user

class UserEntitiesJSON {
	static mapWith = "mongo"
	static embedded = ['description']
	DescriptionJSON description;
 	static constraints = {
	}
	static mapping ={
		version false
  }
}
