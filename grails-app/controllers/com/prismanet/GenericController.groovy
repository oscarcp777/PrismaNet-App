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
	 * @param numCategories - Numero de categorias del origen de los datos (cat1..catN) se envia N
	 * @param container - div donde se ubicara el grafico
	 * @param interval - date type que indica separacion entre datos
	 * @param title - titulo grafico
	 * @param titleX - titulo eje x
	 * @param title - titulo eje y
	 * @param actionOnClick - url con filtros incluidos de la accion destino al clikear un punto del grafico
	 * @return mapa con la data necesaria para formar un grafico de lineas
	 */
	private getChartLineFormat(serviceResultList, numCategories, container, interval, title, titleX, titleY, actionOnClick){
		def mapByCategory = [:]
		def series = []
		def level = numCategories
		serviceResultList.each{ i ->
			
			if (level == 1){
				if (!mapByCategory["Serie"])
					mapByCategory["Serie"] = []
				mapByCategory["Serie"].add([x:DateUtils.parseDate(interval, i.getAt(level-1)).getTime(),y:i.getAt(level)]) 
			}	
			if (level == 2){
				if (!mapByCategory[i.getAt(level-2)])
					mapByCategory[i.getAt(level-2)] = []
				mapByCategory[i.getAt(level-2)].add([x:DateUtils.parseDate(interval, i.getAt(level-1)).getTime(),y:i.getAt(level)])
			}
//			dateValueList.add([x:DateUtils.parseDate(interval, i.getAt(0)).getTime(),y:i.getAt(1)])
		}
		def from,to
		mapByCategory.each {
			def dateValueList = []
			if (it.value.size()>0){
				def cal = new GregorianCalendar()
				cal.setTimeInMillis(it.value.get(0).x)
				from = cal.getTime()
				cal.setTimeInMillis(it.value.get(it.value.size()-1).x)
				to = cal.getTime()
				dateValueList = DateUtils.loadZeros(it.value,from,to, interval)
			}
			series << [name:it.key,data:dateValueList]
		}
		log.debug "Formato parseado para grafico: " + series
		
	   [series:series,
//		   year:year as Integer, month:month as Integer, day:day as Integer, hour:hour as Integer,
		   container: container, /*interval:interval,*/
		   title:title, titleY:titleY,titleX:titleX, cursorEvent:actionOnClick]
	}

}
