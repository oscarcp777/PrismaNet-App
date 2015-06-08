package com.prismanet




import grails.test.mixin.*


@TestFor(MonthlyConceptStatsService)
@Mock([MonthlyConceptStats,Concept])
class MonthlyConceptStatsServiceTests {
	
	def monthlyConceptStatsService = new MonthlyConceptStatsService()
	
	void testLoadStats(){
		monthlyConceptStatsService.conceptService = new ConceptService()
		monthlyConceptStatsService.loadStats()
		
	}
	
 
}
