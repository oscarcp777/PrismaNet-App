package com.prismanet

enum Sex {M,F}

class Author {
	
	String name
	
	Sex sex
	Date userSince
	String accountName
	String profileImage
	
	static transients = ['accountNameSingle']
	
	static hasMany = [tweets:Tweet, posts:FacebookComment]
	
    static constraints = {
		name(nullable:true)
		sex(nullable:true)
		profileImage(nullable:true, url:true)
		accountName(nullable:true)
		userSince(nullable:true)
	}
	
	def getAccountNameSingle(){
	}
	
	public getRatio(){
	}
	
	@Override
	public String toString() {
		return accountName;
	}
}
