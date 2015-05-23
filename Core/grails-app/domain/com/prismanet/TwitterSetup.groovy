package com.prismanet

import org.apache.commons.lang.StringUtils

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
	
	void setIncludedAccounts(String includedAccounts){
		this.includedAccounts = trimInput(includedAccounts)
	 }
	
	void setExcludedAccounts(String excludedAccounts){
		this.excludedAccounts = trimInput(excludedAccounts)
	 } 
	
	void setKeywords(String keywords){
	   this.keywords = trimInput(keywords)
	}
	
	void setNeutralHashtags(String neutralHashtags){
		this.neutralHashtags = trimInput(neutralHashtags)
	}
	
	void setPositiveHashtags(String positiveHashtags){
		this.positiveHashtags = trimInput(positiveHashtags)
	}
	
	void setNegativeHashtags(String negativeHashtags){
		this.negativeHashtags = trimInput(negativeHashtags)
	}
	
	private String trimInput(String input){
		if (input && input.length()>0){
			String finalText = ""
			for (String term in input.split(',')) {
				finalText = finalText + term.trim()+","
			}
			return finalText[0..-2]
		}else
			return input
	}
	
	public boolean isPositiveHashtag(Tweet tweet){
		def add = false
		for (String positiveHashtag in this.positiveHashtags?.split(',')) {
			if (StringUtils.containsIgnoreCase(tweet.content,positiveHashtag)){
				add = true
			}
		}
		return add
	}
	
	public boolean isNegativeHashtag(Tweet tweet){
		def add = false
		for (String negativeHashtag in this.negativeHashtags?.split(',')) {
			if (StringUtils.containsIgnoreCase(tweet.content,negativeHashtag)){
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
