package com.prisma.net.twitter.model

class MediaEntityJSON {
	static mapWith = "mongo"
	 Long id;
	 String url;
	 String mediaURL;
	 String mediaURLHttps;
	 String expandedURL;
	 String displayURL;
	 Map<Integer, MediaEntitySizeJSON> sizes;
	 String type;
    static constraints = {
    }
}
