package com.prismanet
import grails.converters.*
import grails.plugins.springsecurity.Secured
import groovy.time.TimeCategory

import java.text.SimpleDateFormat

import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.ProjectionType
import com.prismanet.context.Filter
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils
import java.util.Random
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
		println concept
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
	
	def conceptsDayJson={
		Concept concept = Concept.findByConceptName(params.id)
		def dateValueList = []
		def dateList = conceptService.categoryStore(["tweetCreated"], [new Filter(attribute:"id",value: concept.id, type:FilterType.EQ)], ["tweetsId" : ProjectionType.COUNT]);
		dateList.each{ i -> dateValueList << i.getAt(0)}
		def json =["data":dateList,"cat":dateValueList]
		render json as JSON
	}
	def conceptsHourJson={
		Concept concept = Concept.findByConceptName(params.id)
		def dateList = conceptService.categoryStore(["tweetHour"], [new Filter(attribute:"id",value:concept.id, type:FilterType.EQ),
					new Filter(attribute:"tweetPeriod",value:"201309", type:FilterType.GE)], ["tweetsId" : ProjectionType.COUNT]);
		render dateList as JSON
	}
	def conceptsMinuteJson={
		Concept concept = Concept.findByConceptName(params.id)
		def dateValueList = []
		def dateList = conceptService.categoryStore(["tweetMinute"], [new Filter(attribute:"id", value : concept.id, type:FilterType.EQ),
			new Filter(attribute:"tweetPeriod",value:"201309", type:FilterType.GE)], ["tweetsId" : ProjectionType.COUNT]);
		dateList.each{ i -> dateValueList << i.getAt(0)}
		def json =["data":dateList,"cat":dateValueList]
		render json as JSON
	}
	def conceptsDateJson={
		Concept concept = Concept.findByConceptName(params.id)
		def dateList = conceptService.categoryStore(["created"], [new Filter(attribute:"id",value : concept.id, type:FilterType.EQ)], ["tweetsId" : ProjectionType.COUNT]);
		def dateValueList = []
		dateList.each{ i -> dateValueList << [i.getAt(0).time,i.getAt(1)]	}
		
		render dateValueList as JSON
	}
	/**
	 * formato que se necesita la lista [[1381448400000,9],[1381448400000,0]]
	 */
	def conceptsRealTime={
		Concept concept = Concept.get(params.id)
		Date from
		Date to=new Date()
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(to);
		calendar.set(Calendar.SECOND, 0);
		to=calendar.getTime();
		def dateValueList = [:]
		use ( TimeCategory ) {
			from = to-20.minutes
//		def dateList = conceptService.categoryStore(["conceptName","tweetMinute"], [new Filter(attribute:"id", value : concept.id, type:FilterType.EQ),new Filter(attribute:"created",value:from, type:FilterType.GE)], ["tweetsId" : ProjectionType.COUNT]);
//		dateList.each{ i ->
//			dateValueList.put(DateUtils.parseDate(DateTypes.MINUTE_PERIOD, i.getAt(1)).time,i.getAt(2))	
//		}
		//TODO MOCK de datos posta de CFK despues comentar
		dateValueList= [(from.time):3,((from+1.minutes).time):3,((from+2.minutes).time):1,((from+3.minutes).time):5,((from+4.minutes).time):1,
			           ((from+5.minutes).time):1,((from+6.minutes).time):1,((from+7.minutes).time):1,((from+8.minutes).time):4,((from+9.minutes).time):2,
					   ((from+10.minutes).time):2,((from+11.minutes).time):3,((from+12.minutes).time):13,((from+13.minutes).time):8,((from+14.minutes).time):5,
					   ((from+15.minutes).time):0,((from+16.minutes).time):1,((from+17.minutes).time):0,((from+18.minutes).time):2,((from+19.minutes).time):10,((from+20.minutes).time):5]
		}
		render DateUtils.loadZerosForMinute(dateValueList,from,to) as JSON
	}
	def conceptsRealTimeForOneMinute={
//		Concept concept = Concept.get(params.id)
		def dateValueList = []
		Date now=new Date()
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.set(Calendar.SECOND, 0);
		now=calendar.getTime();
		use ( TimeCategory ) {
//			def dateList = conceptService.categoryStore(["conceptName","tweetMinute"],
//			[new Filter(attribute:"id", value : concept.id, type:FilterType.EQ),
//				new Filter(attribute:"created",value:new Date()-1.minutes, type:FilterType.GE)], ["tweetsId" : ProjectionType.COUNT]);
//
//			dateList.each{ i ->
//				dateValueList.add([DateUtils.parseDate(DateTypes.MINUTE_PERIOD, i.getAt(1)).time,i.getAt(2)])
//			}
			Random rand = new Random()
			dateValueList = [now.time,rand.nextInt(10+1)]
		}
		println now
		println dateValueList
		render  dateValueList as JSON
	}
}
