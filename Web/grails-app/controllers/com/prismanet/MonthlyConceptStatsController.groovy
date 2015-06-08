package com.prismanet


class MonthlyConceptStatsController {

	def monthlyConceptStatsService
	
	def loadStats={
		monthlyConceptStatsService.loadStats()
		render "Estadisticas cargadas correctamente"
	}
	
	def statsForUser={
		print monthlyConceptStatsService.getStatsForUser(1)
		render "Estadisticas por usuario obtenidas"
	}
}
