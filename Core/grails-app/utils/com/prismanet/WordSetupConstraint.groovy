package com.prismanet

class WordSetupConstraint {
	def validate = { val ->
		if (val != null)
		print "valor: " + val
		    for (String twitterAccount in val.split(',')) {
			   twitterAccount = twitterAccount.trim()
			   if (!twitterAccount.matches("^[@#\"0-9A-Za-z_. ñáéíóú]+")) {
				   return false
			   }
		   }
	}
}
