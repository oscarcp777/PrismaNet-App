package com.prismanet

enum Sex {M,F}

class Author {
	
	Date userSince
	String accountName
	String profileImage
	Integer followers
	Sex sex
	static transients = ['accountNameSingle']
	
	static hasMany = [tweets:Tweet, posts:Post]
	
    static constraints = {
		profileImage(url:true)
		accountName(unique:true, validator:{value ->
			// Validacion cadena de cuentas de twitter
			if (!value.matches("^@[0-9A-Za-z_]+")) {
				return false
			}

		})
		followers(min: 0)
		sex()
		userSince()  
		tweets(nullable:true)
		posts(nullable:true)
    }
	def getAccountNameSingle(){
		accountName.replace("@","")
	}
	@Override
	public String toString() {
		return accountName;
	}
}
