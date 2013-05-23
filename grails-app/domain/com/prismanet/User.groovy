package com.prismanet

import com.sun.istack.internal.Nullable;

/**
 * Usuario del sistema
 * @author santiago
 *
 */
class User {

	String userId
	String password
	Date dateCreated
	AccountType account
	static hasMany = [ concepts : Concept, twitterAccounts:TwitterAccount ]

    static constraints = {
    	userId(size:3..20, unique: true)
		password(size: 5..8)
		concepts(nullable:true)
		twitterAccounts(nullable:true)
	}
}
