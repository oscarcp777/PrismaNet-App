package com.prisma.net.twitter.model

class PlaceJSON {
	static mapWith = "mongo"
	String name;
	String streetAddress;
	String countryCode;
	String id;
	String country;
	String placeType;
	String url;
	String fullName;
	String boundingBoxType;
	GeoLocationJSON[][] boundingBoxCoordinates;
	String geometryType;
	GeoLocationJSON[][] geometryCoordinates;
	PlaceJSON[] containedWithIn;
	static constraints = {
	}
}
