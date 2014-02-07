package com.prismanet

import groovy.time.TimeCategory

import com.prismanet.GenericService.FilterType
import com.prismanet.context.Filter
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils


abstract class GenericCoreService extends GenericService {

	
	
	def getFilterList(DayFilterType type){
		switch (type) {
			case DayFilterType.TODAY:
				def cal = new GregorianCalendar()
				def day = DateUtils.getDateFormat(DateTypes.DAY_PERIOD, cal.time) ;
				return [new Filter(attribute:getProyectionForServiceType(ServiceType.BY_DATE), value:day, type:FilterType.EQ)]
			case DayFilterType.YESTERDAY:
				use ( TimeCategory ) {
					def cal = new GregorianCalendar()
					def date = cal.getTime() - 1.day
					def day = DateUtils.getDateFormat(DateTypes.DAY_PERIOD, date) ;
					return [new Filter(attribute:getProyectionForServiceType(ServiceType.BY_DATE), value:day, type:FilterType.EQ)]
				}
			case DayFilterType.LAST_7_DAYS:
				use ( TimeCategory ) {
					def cal = new GregorianCalendar()
					def dateFrom = cal.getTime() - 7.day
					def dateTo = cal.getTime() - 1.day
					def valueFrom = DateUtils.getDateFormat(DateTypes.DAY_PERIOD, dateFrom) ;
					def valueTo = DateUtils.getDateFormat(DateTypes.DAY_PERIOD, dateTo) ;
					return [new Filter(attribute:getProyectionForServiceType(ServiceType.BY_DATE), value:valueFrom, type:FilterType.GE),
						new Filter(attribute:getProyectionForServiceType(ServiceType.BY_DATE), value:valueTo, type:FilterType.LE)]
				}
				break
			
			default:
				return []	
		}
	}
	
	
	def abstract String getProyectionForServiceType(ServiceType type)
}


/**
 * Enumeracion que contiene los distintos tipos de filtros por fecha.
 */
protected enum DayFilterType{
	TODAY,
	YESTERDAY,
	LAST_7_DAYS;
}


/**
 * Enumeracion que contiene los distintos tipos de proyecciones existentes
 */
protected enum ServiceType{
	BY_MINUTE,
	BY_HOUR,
	BY_DATE;
}