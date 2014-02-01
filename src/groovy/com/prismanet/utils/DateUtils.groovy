package com.prismanet.utils

import groovy.time.TimeCategory

import java.text.SimpleDateFormat

enum DateTypes {
	// Unicos en el tiempo, una vez que pasan no se repiten
	final static String YEAR = "yyyy"
	final static String MONTH_PERIOD = "yyyyMM"
	final static String DAY_PERIOD = "dd/MM/yyyy"
	final static String HOUR_PERIOD = "dd/MM/yyyy HH"
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
	
	static def loadZeros(data,from,to,interval){
		def series = []
		def results=[]
		data.each { serie ->
			def actualTime = from.time
			if (serie.data.size() == 0){
				while (actualTime<to.time) {
					results.add([x: actualTime, y:0])
					actualTime=actualTime + DateUtils.getMilisecondsInterval(interval)
				}
			}else{
				serie.data.each { value ->
					while (actualTime<value.x) {
						results.add([x: actualTime, y:0])
						actualTime=actualTime + DateUtils.getMilisecondsInterval(interval)
					}
					results.add(value)
					actualTime=actualTime + DateUtils.getMilisecondsInterval(interval)
				}
			}
			series << [name:serie.name,data:results]
		}
		series
	}
}
