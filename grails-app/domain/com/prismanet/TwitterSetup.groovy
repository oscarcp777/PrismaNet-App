package com.prismanet

class TwitterSetup {

	static belongsTo = Concept

	// Cuentas de Twitter incluidas
	String includedAccounts
	// Cuentas de Twitter excluidas
	String excludedAccounts
	// Palabras clave
	String keywords

	static constraints = {
		// Ver constraint twitterAccount en TwitterAccountConstraint (usa plugin)
		includedAccounts(nullable:true, twitterAccount:true)
		excludedAccounts(nullable:true, twitterAccount:true)
		keywords(nullable:true, validator:{value ->
			// Validacion cadena de palabras clave
			if (value != null)
				for (String twitterAccount in value.split(',')) {
					if (!twitterAccount.matches("^[0-9A-Za-z]+")) {
						return false
					}
				}
		})
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
