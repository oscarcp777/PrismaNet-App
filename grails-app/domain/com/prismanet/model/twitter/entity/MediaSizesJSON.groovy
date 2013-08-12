package com.prismanet.model.twitter.entity

class MediaSizesJSON {
	static mapWith = "mongo"
	MediaSizeJSON large;
	MediaSizeJSON medium;
	MediaSizeJSON small;
	MediaSizeJSON thumb;
	static constraints = {
	}
	static mapping ={
		version false
  }
}
