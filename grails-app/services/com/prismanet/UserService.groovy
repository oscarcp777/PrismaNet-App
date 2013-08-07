package com.prismanet

import com.prismanet.GenericService.ProjectionType
import com.prismanet.context.UserAttributeContext

class UserService extends GenericService {

   	UserService(){
		super()
		context = new UserAttributeContext()
	}
	
    def categoryStore(User entity, def groups/*, def filters*/) {
		def criteria = User.createCriteria();
		def filters = ["id" : entity.id]
		def projection = ["tweetsId" : ProjectionType.COUNT]
		return categoriesService(criteria, groups, filters, projection)
	}

}
