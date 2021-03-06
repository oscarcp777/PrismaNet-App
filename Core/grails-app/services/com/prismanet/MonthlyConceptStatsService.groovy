package com.prismanet

import groovy.time.TimeCategory

import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.ProjectionType
import com.prismanet.context.Filter
import com.prismanet.context.MonthlyConceptStatsAttributeContext
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils

class MonthlyConceptStatsService extends GenericCoreService{
	
	def conceptService

	MonthlyConceptStatsService(){
		super()
	}
	
	def getPeriods(){
		def results = MonthlyConceptStats.withCriteria {
			projections {
			  distinct("period")
			}
		  }
		results
	}
	def getStatsForUser(Long userId, Date dateProcess){
		use (TimeCategory){
			String periodProcess = DateUtils.getDateFormat(DateTypes.MONTH_PERIOD,dateProcess)
			def filters = []
			filters.add(new Filter(attribute:"userId", value:userId, type:FilterType.EQ))
			filters.add(new Filter(attribute:"period", value:periodProcess, type:FilterType.EQ))
			def categories = ["userId"]
			def projections = ["mentions" : ProjectionType.SUM, "authors":ProjectionType.SUM]
			def result = groupBy(MonthlyConceptStats, new MonthlyConceptStatsAttributeContext(), categories, filters, projections, null)
			def mentions = 0, authors = 0
			
			categories = ["conceptName"]
			def resultByConcept = groupBy(MonthlyConceptStats, new MonthlyConceptStatsAttributeContext(), categories, filters, projections, null)
			if (result.size()>0){
				mentions = result[0][1]
				authors = result[0][2]
			}
			['mentions': mentions, 'authors': authors, 'resultByConcept':resultByConcept]
		}
	}
	
	def void loadStats(){
		use (TimeCategory){
//			def d1 = new GregorianCalendar(2015, Calendar.JUNE, 8,11,00)
			Date dateProcess = new Date()-1.day
			String periodProcess = DateUtils.getDateFormat(DateTypes.MONTH_PERIOD,dateProcess)
			log.debug("Periodo: "+periodProcess)
			def filters = getDateStatsFilters(dateProcess)
						
			def List<Concept> concepts = conceptService.getActiveConcepts()
			concepts.each(){ concept->
				def newFilters = []
				newFilters.addAll(filters)
				newFilters.add(new Filter(attribute:"id", value:concept.id, type:FilterType.EQ))
				// Menciones del mes
				def categories = ["conceptName"]
				def projections = ["mentionId" : ProjectionType.COUNT, "authorId":ProjectionType.COUNT_DISTINCT]
				def result = conceptService.categoryStore(categories, newFilters, projections, null);
				def mentions = 0, authors = 0
				if (result.size()>0){
					mentions = result[0][1]
					authors = result[0][2]
				}
				def monthlyStats = MonthlyConceptStats.findByConceptAndPeriod(concept,periodProcess)
				if (!monthlyStats){
					// Creo nueva estadistica mensual
					monthlyStats = new MonthlyConceptStats(concept:concept, period:periodProcess)
				}
				// Actualizo estadistica mensual
				monthlyStats.mentions = mentions
				monthlyStats.authors = authors
				monthlyStats.save()
			}
		}
	}
	
	/**
	 * Devuelve dos filtros 
	 * filtro desde - primer dia del mes informado a las 0hs
	 * filtro hasta - fecha informada a las 23:59:59hs
	 *  
	 */
	private def getDateStatsFilters(Date dateProcess){
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateProcess);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DATE, 1);
		cal.add(Calendar.SECOND, -1);
		Date dateTo = cal.getTime();

		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date dateFrom = cal.getTime();
		log.debug("Desde: "+dateFrom)
		log.debug("Hasta: "+dateTo)
		
		getFilterList(dateFrom, dateTo)
	}
	
}
