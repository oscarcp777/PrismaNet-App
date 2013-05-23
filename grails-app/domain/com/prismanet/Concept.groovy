package com.prismanet

/**
 * Representa un concepto/idea a ser analizada
 * @author santiago
 *
 */
class Concept {
	
	String conceptName
	Date dateCreated
	Date lastUpdated
	TwitterSetup twitterSetup
	FacebookSetup facebookSetup
	static hasMany = [tweets:Tweet, posts:Post]

    static constraints = {
		conceptName()
		tweets(nullable:true)
		posts(nullable:true)
		twitterSetup(nullable:true)
		facebookSetup(nullable:true)
    }
	
}
