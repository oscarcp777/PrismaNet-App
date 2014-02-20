package com.prismanet

import com.prismanet.model.security.SecUser
import com.sun.istack.internal.Nullable;

/**
 * Usuario del sistema
 * @author santiago
 *
 */
class User extends SecUser{
	
	String firstName
	String lastName
	Date dateCreated
	AccountType account
	static hasMany = [ concepts : Concept, twitterAccounts:TwitterAccount ]

    static constraints = {
		firstName(blank:false)
		lastName(blank:false)
	}
	
	@Override
	public String toString() {
		return username;
	}
}
