package com.prismanet

import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.OrderType
import com.prismanet.GenericService.ProjectionType
import com.prismanet.context.Filter
import com.prismanet.context.UserAttributeContext

class UserService extends GenericCoreService {

   	UserService(){
		super(User, new UserAttributeContext())
	}
	
    def categoryStore(User entity, def groups, def filters, def orders) {
		filters.add(new Filter(attribute:"id",value: entity.id, type:FilterType.EQ))
		def projection = ["mentionId" : ProjectionType.COUNT]
		return groupBy(User, new UserAttributeContext(), groups, filters, projection, orders)
	}
	
	@Override
	protected String getDateGroupProperty(DateServiceType type){
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
	
	
	def getMentionsBy(filters, dateFrom, dateTo){
		def groups = ["conceptsName",getDateGroupProperty(dateFrom, dateTo)]
		filters.addAll(getFilterList(dateFrom, dateTo))
		def result = groupBy(User, new UserAttributeContext(),
						groups, filters, ["mentionId" : ProjectionType.COUNT],
						[[attribute:"created",value:OrderType.ASC]]);
		result
	}

}
