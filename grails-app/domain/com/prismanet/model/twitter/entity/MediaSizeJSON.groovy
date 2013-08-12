package com.prismanet.model.twitter.entity

class MediaSizeJSON {
	static mapWith = "mongo"
	Integer width;
	Integer height;
	Integer resize;
	static constraints = {
	}
	static mapping ={
		version false
  }
}
