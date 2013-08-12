package com.prismanet.model.twitter.place

class PlaceJSON {
	static mapWith = "mongo"
	String name;
	String street_address;
	String country_code;
	String id;
	String country;
	String place_type;
	String url;
	String full_name;
	String bounding_box_type;
	String geometry_type;
	//     GeoLocation{}{} geometry_coordinates;
	//     Place{} containedWithIn;
	//     GeoLocation{}{} bounding_box_coordinates;
	static constraints = {
	}
	static mapping ={
		version false
  }
}
