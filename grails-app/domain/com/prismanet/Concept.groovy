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
	
	static hasMany = [keywords: Keyword, twitterAccounts:TwitterAccount, 
							tweets:Tweet, posts:Post]

    static constraints = {
		conceptName()
		keywords(nullable:true)
		twitterAccounts(nullable:true)
		tweets(nullable:true)
		posts(nullable:true)
    }
	
}
