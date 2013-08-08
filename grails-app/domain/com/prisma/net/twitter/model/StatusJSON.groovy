package com.prisma.net.twitter.model

class StatusJSON {
	static mapWith = "mongo"
	 Date createdAt;
	 Long id;
	 String text;
	 String source;
	 Boolean isTruncated;
	 Long inReplyToStatusId;
	 Long inReplyToUserId;
	 Boolean isFavorited;
	 Boolean isRetweeted;
	 Long favoriteCount;
	 String inReplyToScreenName;
	 GeoLocationJSON geoLocation = null;
	 PlaceJSON place = null;
	 Long retweetCount;
	 Boolean isPossiblySensitive;
	 String isoLanguageCode;

	 Long[] contributorsIDs;

	 StatusJSON retweetedStatus;
	 UserMentionEntityJSON[] userMentionEntities;
	 URLEntityJSON[] urlEntities;
	 HashtagEntityJSON[] hashtagEntities;
	 MediaEntityJSON[] mediaEntities;
	 HashtagEntityJSON[] symbolEntities;
	 Long currentUserRetweetId = -1L;
    static constraints = {
    }
}
