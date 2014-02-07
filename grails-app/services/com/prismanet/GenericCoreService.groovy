package com.prismanet

import groovy.time.TimeCategory

import com.prismanet.GenericService.FilterType
import com.prismanet.context.Filter
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils


class GenericCoreService extends GenericService {

	
	/**
	 * Dado un DateFilterType que define un intervalo de tiempo se devuelve el filtro correspondiente para el mismo
	 * @param type intervalo de tiempo
	 * @return filtro obtenido
	 */
	def getFilterList(DateFilterType type){
		switch (type) {
			case DateFilterType.TODAY:
				def cal = new GregorianCalendar()
				def day = DateUtils.getDateFormat(DateTypes.DAY_PERIOD, cal.time) ;
				return [new Filter(attribute:getProyectionForDateServiceType(DateServiceType.BY_DATE), value:day, type:FilterType.EQ)]
			case DateFilterType.YESTERDAY:
				use ( TimeCategory ) {
					def cal = new GregorianCalendar()
					def date = cal.getTime() - 1.day
					def day = DateUtils.getDateFormat(DateTypes.DAY_PERIOD, date) ;
					return [new Filter(attribute:getProyectionForDateServiceType(DateServiceType.BY_DATE), value:day, type:FilterType.EQ)]
				}
			case DateFilterType.LAST_7_DAYS:
				use ( TimeCategory ) {
					def cal = new GregorianCalendar()
					def dateFrom = cal.getTime() - 7.day
					def dateTo = cal.getTime() - 1.day
					def valueFrom = DateUtils.getDateFormat(DateTypes.DAY_PERIOD, dateFrom) ;
					def valueTo = DateUtils.getDateFormat(DateTypes.DAY_PERIOD, dateTo) ;
					return [new Filter(attribute:getProyectionForDateServiceType(DateServiceType.BY_DATE), value:valueFrom, type:FilterType.GE),
						new Filter(attribute:getProyectionForDateServiceType(DateServiceType.BY_DATE), value:valueTo, type:FilterType.LE)]
				}
				break
			
			default:
				return []	
		}
	}
	
	/**
	 * Las subclases deberán implementar el mismo debiendo devolver el nombre de la propiedad en el contexto 
	 * que corresponda con el tipo de servicio pedido (ej: DateServiceType.BY_MINUTE -> "tweetByMinute")
	 * solo aplica para controladores que ejecuten querys en funcion del tiempo
	 * @param type
	 * @return
	 */
	protected String getProyectionForDateServiceType(DateServiceType type){
		return null
	}
}



