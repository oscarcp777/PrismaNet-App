package com.prismanet

import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils

class Post{
	
	Date lastUpdated
	Date created
	String date
	String period
	String time
	String dateByHour
	String dateByMinute
	Integer day
	Integer month
	Integer year
	Integer hour
	String minute
	
	String postId
	String icon
	String picture
	String link
	String name
	Integer totalLikes
	Integer totalComments
	static belongsTo = Concept
	static hasMany = [concepts:Concept]
	
	
	
	static constraints = {
		icon(nullable:true)
		picture(nullable:true,maxSize: 5000)
		link(nullable:true)
		name(nullable:true)
		totalLikes(nullable:true)
    }
	
	void setCreated(Date inputDate){
		
		year = DateUtils.getDateFormat(DateTypes.YEAR, inputDate) as Integer
		month = DateUtils.getDateFormat(DateTypes.MONTH, inputDate) as Integer
		day =DateUtils.getDateFormat(DateTypes.DAY, inputDate) as Integer
		period = DateUtils.getDateFormat(DateTypes.MONTH_PERIOD, inputDate)
		time = DateUtils.getDateFormat(DateTypes.TIME, inputDate)
		hour = DateUtils.getDateFormat(DateTypes.HOUR, inputDate) as Integer
		minute = DateUtils.getDateFormat(DateTypes.MINUTE, inputDate)
		date = DateUtils.getDateFormat(DateTypes.DAY_PERIOD, inputDate)
		dateByHour = DateUtils.getDateFormat(DateTypes.HOUR_PERIOD, inputDate)
		dateByMinute = DateUtils.getDateFormat(DateTypes.MINUTE_PERIOD, inputDate)

		created = inputDate as Date
	}
	
}
