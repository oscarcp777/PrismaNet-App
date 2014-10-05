package com.prismanet

import groovy.time.TimeCategory

import com.prismanet.GenericService.FilterType
import com.prismanet.context.AttributeContext;
import com.prismanet.context.Filter
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils


class GenericCoreService extends GenericService {

	GenericCoreService(){
		super()
	}
	
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
		getFilterList(dateFrom, dateTo, "created")
	}
	
	def getFilterList(dateFrom, dateTo, attributeName){
		getFilterList(dateFrom, dateTo, attributeName, true)
	}
	
	
	def getFilterList(dateFrom, dateTo, attributeName, cleanDatesByDiff){
		
		if (!dateFrom)
			return []
		if (!dateTo){
			def cal = new GregorianCalendar()
			dateTo = cal.getTime()
		}
		
		DateServiceType type = getChartType(dateFrom, dateTo)
		Calendar calendarFrom = Calendar.getInstance();
		calendarFrom.setTime(dateFrom);
		Calendar calendarTo = Calendar.getInstance();
		calendarTo.setTime(dateTo);
		
		if (cleanDatesByDiff){
			calendarFrom.set(Calendar.SECOND, 0);
			calendarTo.set(Calendar.SECOND, 0);
			switch (type) {
				case DateServiceType.BY_HOUR:
					calendarFrom.set(Calendar.MINUTE, 0);
					calendarTo.set(Calendar.MINUTE, 0);
					break
				case DateServiceType.BY_DATE:
				case DateServiceType.BY_MONTH:
					calendarFrom.set(Calendar.MINUTE, 0);
					calendarTo.set(Calendar.MINUTE, 0);
					calendarFrom.set(Calendar.HOUR, 0);
					calendarTo.set(Calendar.HOUR, 0);
					break
			}
		}
		return [new Filter(attribute:attributeName, value:calendarFrom.getTime(), type:FilterType.GE),
			new Filter(attribute:attributeName, value:calendarTo.getTime(), type:FilterType.LE)]
	}
	
	/**
	 * Las subclases deber�n implementar el mismo debiendo devolver el nombre de la propiedad en el contexto 
	 * que corresponda con el tipo de servicio pedido (ej: DateServiceType.BY_MINUTE -> "tweetByMinute")
	 * solo aplica para controladores que ejecuten querys en funcion del tiempo
	 * @param type
	 * @return
	 */
	protected String getDateGroupProperty(DateServiceType type){
		return null
	}
	
	/**
	 * Dadas dos fechas retorna un string con el nombre de la propiedad de tiempo a agrupar
	 * @param dateFrom - fecha desde
	 * @param dateTo - fecha hasta
	 * @return
	 */
	public String getDateGroupProperty(Date dateFrom, Date dateTo){
		getDateGroupProperty(getChartType(dateFrom, dateTo))
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



