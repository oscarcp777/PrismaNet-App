package com.prisma.net.twitter.model

class UserJSON {
	static mapWith = "mongo"
	 Long id;
	 String name;
	 String screenName;
	 String location;
	 String description;
	 URLEntityJSON[] descriptionURLEntities;
	 URLEntityJSON urlEntity;
	 Boolean isContributorsEnabled;
	 String profileImageUrl;
	 String profileImageUrlHttps;
	 String url;
	 Boolean isProtected;
	 Integer followersCount;

	 StatusJSON status;

	 String profileBackgroundColor;
	 String profileTextColor;
	 String profileLinkColor;
	 String profileSidebarFillColor;
	 String profileSidebarBorderColor;
	 Boolean profileUseBackgroundImage;
	 Boolean showAllInlineMedia;
	 Integer friendsCount;
	 Date createdAt;
	 Integer favouritesCount;
	 Integer utcOffset;
	 String timeZone;
	 String profileBackgroundImageUrl;
	 String profileBackgroundImageUrlHttps;
	 String profileBannerImageUrl;
	 Boolean profileBackgroundTiled;
	 String lang;
	 Integer statusesCount;
	 Boolean isGeoEnabled;
	 Boolean isVerified;
	 Boolean translator;
	 Integer listedCount;
	 Boolean isFollowRequestSent;
	
	
    static constraIntegers = {
    }
}
