package com.prismanet

enum Sex {M,F}

class Author {
	
	Date userSince
	String accountName
	String profileImage
	Integer followers
	Integer following
	Integer tweetsCount
	String name
	Sex sex
	static transients = ['accountNameSingle']
	
	static hasMany = [tweets:Tweet, posts:Post]
	
    static constraints = {
		profileImage(nullable:true, url:true)
		accountName(unique:true, validator:{value ->
			// Validacion cadena de cuentas de twitter
			if (!value.matches("^@[0-9A-Za-z_]+")) {
				return false
			}

		})
		name(nullable:true)
		followers(nullable:true, min: 0)
		following(nullable:true, min: 0)
		tweetsCount(nullable:true, min: 0)
		sex(nullable:true)
		userSince(nullable:true)  
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
	public getRatio(){
		following!=null&& following>1? followers/following:followers
	}
}
