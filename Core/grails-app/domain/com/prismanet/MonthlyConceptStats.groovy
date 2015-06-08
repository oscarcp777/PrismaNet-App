package com.prismanet

import java.text.SimpleDateFormat

import com.prismanet.utils.DateTypes;
import com.prismanet.utils.DateUtils;

class MonthlyConceptStats {
	
	Concept concept
	String period
	
	BigDecimal mentions
	BigDecimal authors
	
	String peakDay
	BigDecimal peakDayQuantity

	Integer peakHour
	BigDecimal peakHourQuantity
	
	String topWords
	
	static belongsTo = Concept

    static constraints = {
		peakDay(nullable:true)
		peakDayQuantity(nullable:true)
		peakHour(nullable:true)
		peakHourQuantity(nullable:true)
		topWords(nullable:true)
    }
	
	
	@Override
	public String toString() {
		return concept?.conceptName + "-" + period;
	}
	

	
}
