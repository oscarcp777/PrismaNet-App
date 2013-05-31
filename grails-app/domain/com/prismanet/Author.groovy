package com.prismanet

enum Sex {M,F}

class Author {
	
	Date userSince
	String accountName
	Integer followers
	Sex sex
	byte[] photo
	
	static hasMany = [tweets:Tweet, posts:Post]
	
    static constraints = {
		photo(nullable:true)
		accountName(unique:true, validator:{value ->
			// Validacion cadena de cuentas de twitter
			if (!value.matches("^@[0-9A-Za-z]+")) {
				return false
			}

		})
		followers(min: 0)
		sex()
		userSince()  
		tweets(nullable:true)
		posts(nullable:true)
    }
	
	@Override
	public String toString() {
		return accountName;
	}
}
