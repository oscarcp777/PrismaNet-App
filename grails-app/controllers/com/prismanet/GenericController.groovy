package com.prismanet

import grails.plugins.springsecurity.SpringSecurityService;

import com.prismanet.GenericService.FilterType
import com.prismanet.context.Filter
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils

class GenericController {
	
	static listMethods = ["max", "offset", "order", "sort", "ignoreCase", "fetch", "readOnly", "fetchSize", "flushMode", "timeout"]
			SpringSecurityService springSecurityService;
	def beforeInterceptor = {
		if(!session.user){
			session.user=springSecurityService.currentUser
			session.concepts=springSecurityService.currentUser.concepts
		}
	}
	def getConcept(id){
		def concept
		session.concepts.each{
			if(id.toLong()==it.id){
			concept=it
			return true
			}
		}
		concept
	}
	def loadFilters(parameters, context) {
		def filters = []
		
		parameters.each { p ->
			if (context.hasPropertyRelationForAttribute(p.key)) {
				if (p.key.contains("id") || p.key.contains("Id")){
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
		def serieX = null
		log.debug "serviceResultList: " + serviceResultList
		serviceResultList.each{ i ->

			def xValue
			if (!interval  || interval == DateTypes.MONTH_PERIOD)
				xValue = i.getAt(level-1)
			else
				xValue = DateUtils.parseDate(interval, i.getAt(level-1)).getTime()
				if (level == 1){
					if (!mapByCategory["Serie"])
						mapByCategory["Serie"] = []
					mapByCategory["Serie"].add([x:xValue,y:i.getAt(level)])
				}
				if (level == 2){
					if (!mapByCategory[i.getAt(level-2)])
						mapByCategory[i.getAt(level-2)] = []
					mapByCategory[i.getAt(level-2)].add([x:xValue,y:i.getAt(level)])
				}
		}
		log.debug "mapByCategory: " + mapByCategory
		
		def minFrom, maxTo
		if (interval && serviceResultList.size()>0){
			minFrom = getMinDate(mapByCategory, interval)
			maxTo = getMaxDate(mapByCategory, interval)
			if (interval != DateTypes.MONTH_PERIOD){
				def cal = new GregorianCalendar()
				cal.setTimeInMillis(minFrom)
				minFrom = cal.getTime()
				cal.setTimeInMillis(maxTo)
				maxTo = cal.getTime()
			}	
		}
		mapByCategory.each {
			def dateValueList = []
			if (it.value.size()>0){
				if (interval){
					log.debug "entro para: " + it.key
					dateValueList = loadZeros(it.value, minFrom, maxTo, interval)
				}else
					dateValueList = it.value

				log.debug "dateValueList: " + dateValueList
				if (!interval || interval == DateTypes.MONTH_PERIOD){
					series << [name:it.key,data:dateValueList.collect{[y:it.y, url:it.x]}]
					serieX = dateValueList.collect{it.x}
				}else
					series << [name:it.key,data:dateValueList]
			}
		}
		log.debug "serieX: " + serieX
		
	   [series:series, serieX:serieX, 
		   container: container, 
		   title:title, titleY:titleY,titleX:titleX, cursorEvent:actionOnClick]
	}
	
	private def getMinDate(mapByCategory, interval){
		def min
		mapByCategory.each {
			def aux = it.value.get(0).x
			if (!min)
				min = aux
			else{
				if (min>aux)
					min = aux
			}
		}
		min
	}
	
	private def getMaxDate(mapByCategory, interval){
		def max
		mapByCategory.each {
			def aux = it.value.get(it.value.size()-1).x
			if (!max)
				max = aux
			else{
				if (max<aux)
					max = aux
			}
		}
		max
	}
	
	private def loadZeros(data,from,to,interval){
		def results=[]
		def actualTime
		def finalTime
		if (interval == DateTypes.MONTH_PERIOD){
			actualTime = from
			finalTime = to
		}else{
			actualTime = from.time
			finalTime = to.time
		}
		if (data.size() == 0){
			while (actualTime < finalTime) {
				results.add([x: actualTime, y:0])
				actualTime = getNextInterval(actualTime, interval)
			}
		}else{
			data.each { value ->
				while (actualTime < value.x) {
					results.add([x: actualTime, y:0])
					actualTime = getNextInterval(actualTime, interval)
				}
				results.add(value)
				actualTime = getNextInterval(actualTime, interval)
			}
			while (actualTime <= finalTime) {
				results.add([x: actualTime, y:0])
				actualTime = getNextInterval(actualTime, interval)
			}
		}
		results
	}
	
	private def getNextInterval(time, interval){
		if (interval == DateTypes.MONTH_PERIOD)
			time =  DateUtils.getNextPeriod(time)
		else
			time = time + DateUtils.getMilisecondsInterval(interval)
		time	
	}
	
	
	Concept chooseConcept(params){
		Concept concept
		if(params.conceptsId)
		return getConcept(params.conceptsId)
		
		session.concepts.each {it ->
		   if(params.conceptName!=null && it.conceptName==params.conceptName){
			concept=it
			params.conceptsId=it.id
			return true
			}
		}
		concept
	}
	
	String createLing(postId){
		def values = postId.split('_')
		'https://www.facebook.com/'+values[0]+'/posts/'+values[1]
	}
	
}
