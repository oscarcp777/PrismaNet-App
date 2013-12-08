package com.prismanet

import java.text.SimpleDateFormat

import com.prismanet.utils.DateTypes;
import com.prismanet.utils.DateUtils;

enum MentionType {TWITTER,FACEBOOK}

abstract class Mention {
	
	MentionType mentionType
	
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

    static constraints = {
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

		created = inputDate
	}
	
}
