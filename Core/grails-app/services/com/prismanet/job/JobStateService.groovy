package com.prismanet.job

import groovy.time.TimeCategory

import org.springframework.transaction.annotation.Transactional

import com.prismanet.GenericService.FilterType
import com.prismanet.GenericService.OrderType
import com.prismanet.GenericService.ProjectionType
import com.prismanet.context.TwitterAuthorAttributeContext
import com.prismanet.context.ConceptAttributeContext
import com.prismanet.context.Filter
import com.prismanet.utils.DateTypes
import com.prismanet.utils.DateUtils


class JobStateService {
		
	public JobState getLastUpdate(JobType type){
		return JobState.findByType(type)
	}
	
	public void save(JobState state){
		state.save(flush: true)
		
	}
	
}
