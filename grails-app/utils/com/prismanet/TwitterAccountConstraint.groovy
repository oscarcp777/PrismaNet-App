package com.prismanet

class TwitterAccountConstraint {
	def validate = { val ->
		if (val != null)
		   for (String twitterAccount in val.split(',')) {
			   if (!twitterAccount.matches("^@[0-9A-Za-z]+")) {
				   return false
			   }
		   }
	}
}
