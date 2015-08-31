package com.prismanet.utils

import groovy.time.TimeCategory

import java.text.SimpleDateFormat

import org.apache.solr.common.util.DateUtil

enum DateTypes {
	// Unicos en el tiempo, una vez que pasan no se repiten
	final static String YEAR = "yyyy"
	final static String MONTH_PERIOD = "yyyyMM"
	final static String DAY_PERIOD = "dd/MM/yyyy"
	final static String HOUR_PERIOD = "dd/MM/yyyy HH"
	final static String HOUR_SIMPLIFIED = "yyMMddHH"
	final static String MINUTE_PERIOD = "dd/MM/yyyy HH:mm"
	
	
	// Repetitivos, se repiten estos valores en el tiempo (ej: todos los eneros)
	final static String MONTH = "MM"
	final static String DAY = "dd"
	final static String HOUR = "HH"
	final static String MINUTE = "HH:mm"
	final static String TIME = "HH:mm:ss"
}

class DateUtils {
	
	static def getDateWithoutSeconds(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.getTime();
	}
	static def getDateFormat(String format, Date date){
		new SimpleDateFormat(format).format(date)
	}
	
	static def parseDate(String format, String date){
		new SimpleDateFormat(format).parse(date)
	}
    static def loadZerosForMinute(mapData,from,to){
		def results=[]
		use ( TimeCategory ) {
		
		while (from!=to) {
			if(mapData.containsKey(from.time)){
			 results.add([ from.time, mapData.get(from.time)])
			}else{
				results.add([ from.time,0])
			}
			from=from+1.minutes
			}
		}
		
		results
	}
	
	static def getMilisecondsInterval(def dateType){
		switch(dateType){
			case DateTypes.YEAR:
				return 365 * 24 * 3600 *1000
			case DateTypes.MONTH:
			case DateTypes.MONTH_PERIOD:
				return 30 * 24 * 3600 *1000
			case DateTypes.DAY:
			case DateTypes.DAY_PERIOD:
				return 24 * 3600 *1000
			case DateTypes.HOUR:
			case DateTypes.HOUR_PERIOD:
				return 3600 *1000
			case DateTypes.MINUTE:
			case DateTypes.MINUTE_PERIOD:
				return 60 *1000
			default:
				return 60 *1000
		}
	}
	
	static def getNextPeriod(period){
		def year = period[0..3] as Integer
		def month = period[4..5] as Integer
		if (month + 1 == 13){
			year++
			month = 1
		}else
			month++
		def sMonth = month.toString()
		if (month<10)
			sMonth = '0' + month.toString()
		return year.toString()+ sMonth
	}

	
	static String toSolrDate(String dateStr) {
		def dateFormats = [DateTypes.MINUTE_PERIOD];
		try {
			Date date
			use ( TimeCategory ) {
				date = DateUtil.parseDate(dateStr, dateFormats)
				date = date + 4.hours
			}
			String result = DateUtil.getThreadLocalDateFormat().format(date)
			return result;
		} catch (Exception ignore) {
			throw new IllegalArgumentException("Invalid date: " + dateStr);
		}

		throw new IllegalArgumentException("Invalid date: " + dateStr);
	}
	
}
