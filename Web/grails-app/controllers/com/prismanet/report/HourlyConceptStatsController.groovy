package com.prismanet.report


class HourlyConceptStatsController {

	def hourlyConceptStatsService
	
	def loadStats={
		hourlyConceptStatsService.loadStats()
		render "Estadisticas cargadas correctamente"
	}
	
	def statsForUser={
		def result = hourlyConceptStatsService.getStatsForUser(1)
		render "Estadisticas por usuario obtenidas: " + result
	}
}
