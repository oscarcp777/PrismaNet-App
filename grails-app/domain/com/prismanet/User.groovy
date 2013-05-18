package com.prismanet

/**
 * Usuario del sistema
 * @author santiago
 *
 */
class User {

	String userId
	String password
	Date dateCreated
	Account account
	static hasMany = { concepts : Concept }

    static constraints = {
    	userId(size:3..20, unique: true)
		password(size: 5..8)
	}
}
