package com.prismanet
import grails.converters.*
import grails.plugins.springsecurity.Secured
import groovy.time.TimeCategory

import java.text.SimpleDateFormat

import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.OrderType
import com.prismanet.GenericService.ProjectionType
import com.prismanet.context.Filter
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils
@Secured(['ROLE_USER'])
class ConceptController{

	def scaffold = true
	def conceptService
	
	
	
	def weightStats = {
		def weightProjection = ["authorFollowers" : ProjectionType.SUM]
		customStats(weightProjection)
	}

	def stats = {
		Concept concept = Concept.get(params.id)
		[concept : concept]
	}
	
	
	def customStats(proyection){
		
		Concept concept = Concept.findByConceptName(params.id)
		def filters = [new Filter(attribute:"id",value: concept.id, type:FilterType.EQ)]
		// Tweets por fecha
		def groupList = ["tweetCreated"]
		def dateList = conceptService.categoryStore(groupList, filters, proyection);
		println "Tweets por fecha" +dateList
		// Tweets por hora del dia X (va a ser el actual ahora pongo uno con datos)
		SimpleDateFormat dF = new SimpleDateFormat("dd/MM/yyyy")
		filters.add(new Filter(attribute:"tweetPeriod",value: "201309", type:FilterType.GE))
		
		groupList = ["tweetHour"]
		def hourList = conceptService.categoryStore(groupList, filters, proyection);
		println "Tweets por hora" +hourList
		groupList = ["tweetMinute"]
		def minuteList = conceptService.categoryStore(groupList, filters, proyection);
		println "Tweets por minuto" + minuteList
		def dateValueList = []
		minuteList.each{ i -> dateValueList << i.getAt(1)	}
		println "Tweets por solo" +dateValueList
		[concept : concept, dateList : dateList, hourList:hourList, minuteList:minuteList, lineGraphList: dateValueList]
	}
	
/*	def conceptsDayJson={
		def container = params.div
		Concept concept = Concept.get(params.id)
		def dateValueList = [:]
		def dateList = conceptService.categoryStore(["tweetCreated"], [new Filter(attribute:"id",value: concept.id, type:FilterType.EQ)], ["tweetsId" : ProjectionType.COUNT]);
		print "Formato del servicio: " + dateList
	   	dateList.each{ i ->
				dateValueList.put(DateUtils.parseDate(DateTypes.HOUR_PERIOD, i.getAt(0)).time,i.getAt(1))
		}
		   
		print "Formato parseado para grafico: " + dateValueList   
		   //TODO habria 	que llenarlo de ceros y que muestre el ultimo mes
//		dateList=DateUtils.loadZerosForMinute(dateValueList,from,to)
		def json = ["data":dateList,"cat":dateValueList, "container": container, "title":'Tweets por dia',"titleY":'Cantidad de tweets',"titleX":'Tweets']
		render json as JSON
	}*/
	def conceptsHourJson={
		def container = params.div
		def cal = new GregorianCalendar()
		def day = DateUtils.getDateFormat(DateTypes.DAY_PERIOD, cal.time) ;
		Concept concept = Concept.get(params.id)
		def dateList = conceptService.categoryStore(["tweetByHour"], [new Filter(attribute:"id",value:concept.id, type:FilterType.EQ),
			new Filter(attribute:"tweetCreated",value:day, type:FilterType.EQ)], ["tweetsId" : ProjectionType.COUNT], [[attribute:"created",value:OrderType.ASC]]);
		
//		def interval = 60 * 1000
		def resultMap = getChartLineFormat(concept.conceptName, dateList, container, DateTypes.HOUR_PERIOD, 'Tweets por hora','Cantidad de tweets','Tweets')
		render resultMap as JSON
	}
	def conceptsMinuteJson={
		def container = params.div
		Concept concept = Concept.get(params.id)
		def cal = new GregorianCalendar()
		def hourFilter=DateUtils.getDateFormat(DateTypes.HOUR_PERIOD, cal.time)
		print hourFilter
		def dateList = conceptService.categoryStore(["tweetByMinute"], [new Filter(attribute:"id", value : concept.id, type:FilterType.EQ)
			,new Filter(attribute:"tweetByHour",value:hourFilter, type:FilterType.EQ)
//			,new Filter(attribute:"tweetByHour",value:"", type:FilterType.EQ)
			], ["tweetsId" : ProjectionType.COUNT], [[attribute:"created",value:OrderType.ASC]]);
		print "Formato del servicio: " + dateList
		
		
//		def hour,day,month,year
//		hour = DateUtils.getDateFormat(DateTypes.HOUR, cal.time)
//		day = DateUtils.getDateFormat(DateTypes.DAY, cal.time)
//		month = DateUtils.getDateFormat(DateTypes.MONTH, cal.time)
//		year = DateUtils.getDateFormat(DateTypes.YEAR, cal.time) ;
//		def interval = 60 * 1000
		def resultMap = getChartLineFormat(concept.conceptName, dateList, container, DateTypes.MINUTE_PERIOD, 'Tweets por minuto','Cantidad de tweets','Tweets')
		render resultMap as JSON
	}
	def conceptsDateJson={
		def container = params.div
		Concept concept = Concept.get(params.id)
		def cal = new GregorianCalendar()
		def period = DateUtils.getDateFormat(DateTypes.MONTH_PERIOD, cal.time) ;
		def dateList = conceptService.categoryStore(["tweetCreated"], [new Filter(attribute:"id",value : concept.id, type:FilterType.EQ),
			new Filter(attribute:"tweetPeriod",value:period, type:FilterType.EQ)], ["tweetsId" : ProjectionType.COUNT], [[attribute:"created",value:OrderType.ASC]]);
		print "Formato del servicio: " + dateList
		
//		def year = dateList.get(0).getAt(0)[6..9]
//		def month =  dateList.get(0).getAt(0)[3..4]
//		def day =  dateList.get(0).getAt(0)[0..1]
//		def interval = 24 * 3600 * 1000
		def resultMap = getChartLineFormat(concept.conceptName, dateList, container, DateTypes.DAY_PERIOD, 'Tweets por Dia','Cantidad de tweets','Tweets')
		render resultMap as JSON
	}
	
	/**
	 * Recibe lo necesario para armar un grafico de lineas
	 * @param conceptName - nombre del concepto
	 * @param serviceResultList - resultados del servicio
	 * @param container - div donde se ubicara el grafico
	 * @param interval - date type que indica separacion entre datos
//	 * @param year - año inicio
//	 * @param month - mes inicio
//	 * @param day - dia inicio
//	 * @param hora - hora inicio
	 * @param title - titulo grafico
	 * @param titleX - titulo eje x
	 * @param title - titulo eje y
	 * @return
	 */
	private getChartLineFormat(conceptName, serviceResultList, container, interval,/*interval, year, month, day, hour,*/ title, titleX, titleY){
		def dateValueList = []
		serviceResultList.each{ i ->
			dateValueList.add([x:DateUtils.parseDate(interval, i.getAt(0)).getTime(),y:i.getAt(1)])
		}
//		def from = DateUtils.parseDate(interval,serviceResultList.get(0).getAt(0))
//		def to = DateUtils.parseDate(interval,serviceResultList.get(serviceResultList.size()-1).getAt(0))
//		dateValueList=DateUtils.loadZerosForMinute(dateValueList,from,to)
	   def series = []
	   series << [name:conceptName,data:dateValueList]
	   print "Formato parseado para grafico: " + series
	   [series:series, 
//		   year:year as Integer, month:month as Integer, day:day as Integer, hour:hour as Integer,
		   container: container, /*interval:interval,*/
		   title:title, titleY:titleY,titleX:titleX]
	}
	/**
	 * formato que se necesita la lista [[1381448400000,9],[1381448400000,0]]
	 */
	def conceptsRealTime={
		Concept concept = Concept.get(params.id)
		def container = params.div
		def listRealTime=conceptService.getConceptsRealTime(concept)
		def json =["data":listRealTime,"container":container,"id":params.id, "title":'Tweets por minuto',"subTitle":"Actualización en tiempo Real de la cantidad de Tweets" ,"titleY":'Cantidad de tweets',"titleX":'Minutos']
		render json as JSON
	}
	def conceptsRealTimeForOneMinute={
		Concept concept = Concept.get(params.id)
		def dateValueList = [:]
		Date from,to
		Calendar calendar = new GregorianCalendar()
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		to=calendar.getTime();
		use ( TimeCategory ) {
			from = to-1.minutes
			def tweetByMinute=DateUtils.getDateFormat(DateTypes.HOUR_PERIOD, cal.time)
			def dateList = conceptService.categoryStore(["conceptName","tweetByMinute"],
			[new Filter(attribute:"id", value : concept.id, type:FilterType.EQ),
				new Filter(attribute:"tweetByMinute",value:tweetByMinute, type:FilterType.EQ)], ["tweetsId" : ProjectionType.COUNT], null);

			dateList.each{ i ->
				dateValueList.put(DateUtils.parseDate(DateTypes.MINUTE_PERIOD, i.getAt(1)).time,i.getAt(2))	
			}
		}
		render DateUtils.loadZerosForMinute(dateValueList,from,to) as JSON
	}
}
