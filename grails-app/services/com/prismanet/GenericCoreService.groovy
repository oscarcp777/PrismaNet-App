package com.prismanet

import groovy.time.TimeCategory

import com.prismanet.GenericService.FilterType
import com.prismanet.context.AttributeContext;
import com.prismanet.context.Filter
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils


class GenericCoreService extends GenericService {

	GenericCoreService(def domainClass,AttributeContext context){
		super(domainClass, context)
	}
	
	/**
	 * Dadas una fecha desde y una fecha hasta que definen un intervalo de tiempo 
	 * se devuelve el filtro correspondiente para el mismo
	 * @param type intervalo de tiempo
	 * @return filtro obtenido
	 */
	def getFilterList(dateFrom, dateTo){
		
		if (!dateFrom)
			return []
		if (!dateTo){
			def cal = new GregorianCalendar()
			dateTo = cal.getTime()
		}
		
		DateServiceType type = getChartType(dateFrom, dateTo)
		Calendar calendarFrom = Calendar.getInstance();
		calendarFrom.setTime(dateFrom);
		calendarFrom.set(Calendar.SECOND, 0);
		Calendar calendarTo = Calendar.getInstance();
		calendarTo.setTime(dateTo);
		calendarTo.set(Calendar.SECOND, 0);
		switch (type) {
			case DateServiceType.BY_MINUTE:
//				def valueFrom = DateUtils.getDateFormat(DateTypes.MINUTE_PERIOD, dateFrom)
//				def valueTo = DateUtils.getDateFormat(DateTypes.MINUTE_PERIOD, dateTo)
				return [new Filter(attribute:"created", value:calendarFrom.getTime(), type:FilterType.GE),
					new Filter(attribute:"created", value:calendarTo.getTime(), type:FilterType.LE)]
				break
			case DateServiceType.BY_HOUR:
				calendarFrom.set(Calendar.MINUTE, 0);
				calendarTo.set(Calendar.MINUTE, 0);
//				def valueFrom = DateUtils.getDateFormat(DateTypes.HOUR_PERIOD, dateFrom)
//				def valueTo = DateUtils.getDateFormat(DateTypes.HOUR_PERIOD, dateTo)
				return [new Filter(attribute:"created", value:calendarFrom.getTime(), type:FilterType.GE),
					new Filter(attribute:"created", value:calendarTo.getTime(), type:FilterType.LE)]
				break
			case DateServiceType.BY_DATE:
				calendarFrom.set(Calendar.MINUTE, 0);
				calendarTo.set(Calendar.MINUTE, 0);
				calendarFrom.set(Calendar.HOUR, 0);
				calendarTo.set(Calendar.HOUR, 0);
				print "From:" + calendarFrom.getTime()
				print "To:" + calendarTo.getTime()
//				def valueFrom = DateUtils.getDateFormat(DateTypes.DAY_PERIOD, dateFrom)
//				def valueTo = DateUtils.getDateFormat(DateTypes.DAY_PERIOD, dateTo)
				return [new Filter(attribute:"created", value:calendarFrom.getTime(), type:FilterType.GE),
					new Filter(attribute:"created", value:calendarTo.getTime(), type:FilterType.LE)]
				break
			case DateServiceType.BY_MONTH:
				def valueFrom = DateUtils.getDateFormat(DateTypes.DAY_PERIOD, dateFrom)
				def valueTo = DateUtils.getDateFormat(DateTypes.DAY_PERIOD, dateTo)
				return [new Filter(attribute:"created", value:valueFrom, type:FilterType.GE),
					new Filter(attribute:"created", value:valueTo, type:FilterType.LE)]
				break
			default:
				return []	
		}
	}
	
	/**
	 * Las subclases deber�n implementar el mismo debiendo devolver el nombre de la propiedad en el contexto 
	 * que corresponda con el tipo de servicio pedido (ej: DateServiceType.BY_MINUTE -> "tweetByMinute")
	 * solo aplica para controladores que ejecuten querys en funcion del tiempo
	 * @param type
	 * @return
	 */
	protected String getGroupForDateServiceType(DateServiceType type){
		return null
	}
	
	/**
	 * Dado un rango determina el tipo de grafico
	 * @param dateFrom - fecha desde del rango
	 * @param dateTo - fecha hasta del rango
	 * @return tipo de grafico 
	 */
	def DateServiceType getChartType(Date dateFrom, Date dateTo){
		if (!dateFrom)
			return DateServiceType.BY_MONTH
		if (!dateTo){
			def cal = new GregorianCalendar()
			dateTo = cal.getTime()
		}	
		
		use (TimeCategory){
			def duration = dateTo - dateFrom
			println "days: ${duration.days}, Hours: ${duration.hours}"
			if (duration.hours < 3 && duration.days < 1)
				return DateServiceType.BY_MINUTE
			if (duration.hours < 24 && duration.days < 2)
				return DateServiceType.BY_HOUR
			if (duration.days < 61)
				return DateServiceType.BY_DATE
			return DateServiceType.BY_MONTH
		}
	}
}



