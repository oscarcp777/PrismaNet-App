package com.prismanet

class TwitterSetup {
	
	static belongsTo = Concept
	
	static hasMany = [keywords: Keyword, twitterAccounts:String]

    static constraints = {
		keywords(nullable:true)
		twitterAccounts(nullable:true)
    }
}
