package com.prismanet

/**
 * Tipo de cuenta
 * @author santiago
 *
 */
class Account {

	static belongsTo = {user:User}
	
    static constraints = {
    }
}
