package com.prismanet

/**
 * Representa un concepto/idea a ser analizada
 * @author santiago
 *
 */
class Concept {
	
	String conceptName
	//Palabras clave
	String[] keywords
	Date dateCreated
	Date lastUpdated
	
	static hasMany = {tweets:Tweet; posts:Post}

    static constraints = {
    }
}
