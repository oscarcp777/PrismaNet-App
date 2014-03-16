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
		includedAccounts(nullable:true, twitterAccount:true)
		excludedAccounts(nullable:true, twitterAccount:true)
		keywords(nullable:true, wordSetup:true)
		neutralHashtags(nullable:true, twitterHashtag:true)
		positiveHashtags(nullable:true, twitterHashtag:true)
		negativeHashtags(nullable:true, twitterHashtag:true)
		lastUpdated(nullable:true)
	}

	@Override
	public String toString() {
		return includedAccounts;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		TwitterSetup setup = new TwitterSetup(this)
		return setup
	}

}
