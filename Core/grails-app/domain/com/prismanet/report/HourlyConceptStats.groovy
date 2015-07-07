package com.prismanet.report

import com.prismanet.Concept;

/**
 * Usuario del sistema
 * @author santiago
 *
 */
class HourlyConceptStats {
	
	String hour
	Concept concept
	
	// Cantidad de menciones de la hora 
	Integer mentions
	// Cantidad de autores de la hora
	Integer authors
	
	static hasMany = [ tweets : HourlyConceptStatsTweet, words:HourlyConceptStatsWord ]

    static constraints = {
	}
	
	@Override
	public String toString() {
		return concept.id + " - " + hour;
	}
}
