package com.prismanet

class User {

	String userId
	String password
	Date dateCreated


    static constraints = {
    	userId(size:3..20, unique: true)
		password(size: 5..8)
	}
}
