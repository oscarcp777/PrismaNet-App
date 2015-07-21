package com.prismanet

class TwitterAuthor extends Author{
	
	Integer followers
	Integer following
	Integer tweetsCount
	Long twitterAuthorId
	Integer quantity
	static transients = ['quantity']
	static constraints = {
		followers(nullable:true, min: 0)
		following(nullable:true, min: 0)
		tweetsCount(nullable:true, min: 0)
		twitterAuthorId(nullable:true)
	}
	
	def getAccountNameSingle(){
		accountName.trim().replace("@","")
	}
	
	public getRatio(){
		following!=null&& following>1? followers/following:followers
	}
}
