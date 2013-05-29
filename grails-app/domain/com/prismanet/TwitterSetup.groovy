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
		includedAccounts(nullable:true, validator:{value ->
			// Validacion cadena de cuentas de twitter
			if (value != null)
				for (String twitterAccount in value.split(',')) {
					if (!twitterAccount.matches("^@[0-9A-Za-z]+")) {
						return false
					}
				}
		}) 
		excludedAccounts(nullable:true, validator:{value ->
			// Validacion cadena de cuentas de twitter
			if (value != null)
				for (String twitterAccount in value.split(',')) {
					if (!twitterAccount.matches("^@[0-9A-Za-z]+")) {
						return false
					}
				}
		}) 
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
	
}
