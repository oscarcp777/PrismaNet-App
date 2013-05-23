package com.prismanet

import java.util.Date;

class TwitterAccount {
	
	String accountName
	Date dateCreated
	Date lastUpdated
	static hasMany = [authors:Author, changes:HistoricalTwitterAccount]

    static constraints = {
		accountName(matches:'@.*')
		authors(nullable:true)
		changes(nullable:true)
    }
}
