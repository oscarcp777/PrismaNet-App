package com.prismanet

/**
 * Tipo de cuenta
 * @author santiago
 *
 */
class AccountType {

	String type
	static constraints = {
    }

	@Override
	public String toString() {
		return type;
	}	
	
}
