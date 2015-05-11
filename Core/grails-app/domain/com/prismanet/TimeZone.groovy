package com.prismanet

class TimeZone {

    Country country
	String code
	String name
	Float gmtOffset
	Float dstOffset
	Float rawOffset
	
	static constraints = {
	country(nullable:true)
    }
}
