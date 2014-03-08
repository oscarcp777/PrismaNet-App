package com.prismanet

class HistoricalTwitterAccount {
	
	Date dateCreated
	Integer followers
	

    static constraints = {
    }
	
	static mapping = {
		sort dateCreated:"desc"
	}
		
}
