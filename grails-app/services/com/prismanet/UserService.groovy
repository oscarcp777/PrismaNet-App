package com.prismanet

import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.ProjectionType
import com.prismanet.context.Filter
import com.prismanet.context.UserAttributeContext

class UserService extends GenericService {

   	UserService(){
		super(User, new UserAttributeContext())
	}
	
    def categoryStore(User entity, def groups, def filters, def orders) {
		filters.add(new Filter(attribute:"id",value: entity.id, type:FilterType.EQ))
		def projection = ["tweetsId" : ProjectionType.COUNT]
		return groupBy(groups, filters, projection, orders)
	}

}
