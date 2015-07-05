package com.prismanet


class MonthlyConceptStatsController {

	def monthlyConceptStatsService
	
	def loadStats={
		monthlyConceptStatsService.loadStats()
		render "Estadisticas cargadas correctamente"
	}
	
	def statsForUser={
		def result = monthlyConceptStatsService.getStatsForUser(1, new Date())
		render "Estadisticas por usuario obtenidas: " + result
	}
}
