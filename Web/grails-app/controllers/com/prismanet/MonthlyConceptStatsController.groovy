package com.prismanet


class MonthlyConceptStatsController {

	def monthlyConceptStatsService
	
	def loadStats={
		print "Entro"
		monthlyConceptStatsService.loadStats()
		render "Estadisticas cargadas correctamente"
	}
}
