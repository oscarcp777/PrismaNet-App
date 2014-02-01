package com.prismanet

import com.prismanet.GenericService.FilterType
import com.prismanet.context.Filter
import com.prismanet.utils.DateUtils

class GenericController {
	
	static listMethods = ["max", "offset", "order", "sort", "ignoreCase", "fetch", "readOnly", "fetchSize", "flushMode", "timeout"]
	
	def loadFilters(parameters, context) {
		def filters = []
		
		parameters.each { p ->
			if (context.hasPropertyRelationForAttribute(p.key)) {
				if (p.key.contains("id") || p.key.contains("Id")){
					print "VALOR A CASTEAR:" + p.value
					long val = p.value as Long
					filters.add(new Filter(attribute:p.key, value : p.value.toLong(), type:FilterType.EQ))
				}else	
					filters.add(new Filter(attribute:p.key, value : p.value, type:FilterType.EQ))
			}
		}
		filters
	}
	
	
	def cleanParams(parameters){
		def listParams = [:]
		params.each {
			if (listMethods.contains(it.key)) {
				listParams[it.key] = it.value
			}
		}
		return listParams
	}
	
	
	/**
	 * Recibe lo necesario para armar un grafico de lineas
	 * @param serviceResultList - Lista con formato [{cat1,cat2..,value1},{cat1,cat2..,value2}]
	 * @param container - div donde se ubicara el grafico
	 * @param interval - date type que indica separacion entre datos
	 * @param title - titulo grafico
	 * @param titleX - titulo eje x
	 * @param title - titulo eje y
	 * @param actionOnClick - url con filtros incluidos de la accion destino al clikear un punto del grafico
	 * @return mapa con la data necesaria para formar un grafico de lineas
	 */
	private getChartLineFormat(serviceResultList, container, interval, title, titleX, titleY, actionOnClick){
		def dateValueList = []
		serviceResultList.each{ i ->
			dateValueList.add([x:DateUtils.parseDate(interval, i.getAt(0)).getTime(),y:i.getAt(1)])
		}
		def from,to
		if (serviceResultList.size() > 0){
			from = DateUtils.parseDate(interval,serviceResultList.get(0).getAt(0))
			to = DateUtils.parseDate(interval,serviceResultList.get(serviceResultList.size()-1).getAt(0))
		}
		def series = []
		series << [name:"none",data:dateValueList]
		if (serviceResultList.size() > 0)
			series=DateUtils.loadZeros(series,from,to, interval)
		log.debug "Formato parseado para grafico: " + series
		
	   [series:series,
//		   year:year as Integer, month:month as Integer, day:day as Integer, hour:hour as Integer,
		   container: container, /*interval:interval,*/
		   title:title, titleY:titleY,titleX:titleX, cursorEvent:actionOnClick]
	}

}
