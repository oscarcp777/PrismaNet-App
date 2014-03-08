package com.prismanet.utils

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
	
	
	static def getDateFormat(String format, Date date){
		new SimpleDateFormat(format).format(date)
	}
	
	

}
