package com.prismanet

import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.ProjectionType
import com.prismanet.context.Filter
import com.prismanet.context.UserAttributeContext

class UserService extends GenericService {

   	UserService(){
		super()
		context = new UserAttributeContext()
	}
	
    def categoryStore(User entity, def groups/*, def filters*/) {
		def criteria = User.createCriteria();
		def filters = [new Filter(attribute:"id",value: entity.id, type:FilterType.EQ)]
		def projection = ["tweetsId" : ProjectionType.COUNT]
		return categoriesService(criteria, groups, filters, projection)
	}

}
