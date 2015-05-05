package com.prismanet.sentiment

import com.prismanet.Concept
import com.prismanet.DateServiceType
import com.prismanet.GenericCoreService
import com.prismanet.Mention
import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.OrderType
import com.prismanet.GenericService.ProjectionType
import com.prismanet.context.Filter
import com.prismanet.context.OpinionAttributeContext

class OpinionService extends GenericCoreService{
	
	def addOpinion(Concept concept, Mention mention, OpinionValue value){
		def opinion = new Opinion(concept:concept,mention:mention, value:value)
		opinion.save()
	}

	def OpinionValue getOpinion(Concept concept, Mention mention){
		def opinion = Opinion.findByConceptAndMention(concept,mention)
		if (!opinion)
			return OpinionValue.NEUTRAL
		opinion.value
	}
	
	def getOpinionsBy(filters, dateFrom, dateTo){
		def groups = ["value",getDateGroupProperty(dateFrom, dateTo)]
		filters.addAll(getFilterList(dateFrom, dateTo))
		filters.add(new Filter(attribute:"value",value:OpinionValue.NEUTRAL, type:FilterType.NE))
		def result = groupBy(Opinion, new OpinionAttributeContext(),
						groups, filters, ["mentionId" : ProjectionType.COUNT],
						[[attribute:"created",value:OrderType.ASC]])
		result
	}
	
	public String getDateGroupProperty(DateServiceType type){
		switch (type) {
			case DateServiceType.BY_MINUTE:
				return "dateByMinute"
			case DateServiceType.BY_HOUR:
				return "dateByHour"
			case DateServiceType.BY_DATE:
				return "dateCreated"
			case DateServiceType.BY_MONTH:
				return "datePeriod"
		}
		return null
	}
	
}
