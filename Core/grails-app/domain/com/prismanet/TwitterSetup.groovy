package com.prismanet

class TwitterSetup {

	static belongsTo = Concept

	// Cuentas de Twitter incluidas
	String includedAccounts
	// Cuentas de Twitter excluidas
	String excludedAccounts
	// Palabras clave
	String keywords
	// Hashtags neutrales
	String neutralHashtags
	// Hashtags positivos
	String positiveHashtags
	// Hashtags negativos
	String negativeHashtags
	
	Date lastUpdated

	static constraints = {
		// Ver constraint twitterAccount en TwitterAccountConstraint (usa plugin)
		includedAccounts(nullable:true, twitterAccount:true, maxSize: 1000)
		excludedAccounts(nullable:true, wordSetup:true, maxSize: 1000)
		keywords(nullable:true, wordSetup:true, maxSize: 1000)
		neutralHashtags(nullable:true, twitterHashtag:true, maxSize: 1000)
		positiveHashtags(nullable:true, twitterHashtag:true, maxSize: 1000)
		negativeHashtags(nullable:true, twitterHashtag:true, maxSize: 1000)
		lastUpdated(nullable:true)
	}

	@Override
	public String toString() {
		return includedAccounts
	}
	
	
	public boolean isPositiveHashtag(Tweet tweet){
		def add = false
		for (String positiveHashtag in this.positiveHashtags?.split(',')) {
			if (tweet.content.contains(positiveHashtag)){
				add = true
			}
		}
		return add
	}
	
	public boolean isNegativeHashtag(Tweet tweet){
		def add = false
		for (String negativeHashtag in this.negativeHashtags?.split(',')) {
			if (tweet.content.contains(negativeHashtag)){
				add = true
			}
		}
		return add
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		TwitterSetup setup = new TwitterSetup()
		setup.includedAccounts =includedAccounts
		setup.excludedAccounts = excludedAccounts
		setup.keywords = keywords
		setup.neutralHashtags = neutralHashtags
		setup.positiveHashtags = positiveHashtags
		setup.negativeHashtags = negativeHashtags
		return setup
	}

}
