package com.prismanet

/**
 * Representa un concepto/idea a ser analizada
 * @author santiago
 *
 */
class Concept {
	
	// No marco la relacion de pertenencia a User porque no quiero delete en cascada
	
	String conceptName
	Date dateCreated
	Date lastUpdated
	TwitterSetup twitterSetup
	FacebookSetup facebookSetup
	static hasMany = [tweets:Tweet, posts:Post]

    static constraints = {
		conceptName(nullable:false)
		tweets(
//			, 
//			validator:{value, concept, errors ->
//			// Validacion agregado de tweets al concepto
//			if (value == null)
//				return true
////			def valid = true	
//			value.every {
//				def add = false
//				for (String twitterAccount in concept.twitterSetup.includedAccounts.split(',')) {
//					log.info twitterAccount
//					if (it.content.contains(twitterAccount))
//						add = true
//				}
//				for (String keyword in concept.twitterSetup.keywords.split(',')) {
//					if (it.content.contains(keyword))
//						add = true
//				}
//				if (add == false){
//					errors.reject(
//							'concept.tweets.invalid',
//							['tweets', 'class Concept'] as Object[],
//							'[Property [{0}] of class [{1}] is invalid]')
//
//					// The following helps with field highlighting in your view
//					errors.rejectValue(
//							'tweets',
//							'concept.tweets.invalid')
//					return false
//				}
//			}
//			return true
//		}
			)
		posts()
		twitterSetup(nullable:true)
		facebookSetup(nullable:true)
    }
	
	
//	public void addToTweets(Tweet tweet){
//		def add = false
//		for (String twitterAccount in this.twitterSetup.includedAccounts.split(',')) {
//			if (tweet.content.contains(twitterAccount))
//				add = true
//		}
//		for (String keyword in this.twitterSetup.keywords.split(',')) {
//			if (tweet.content.contains(keyword))
//				add = true
//		}
//		if (add == false){
//			this.errors.reject(
//					'concept.tweets.invalid',
//					['tweets', 'class Concept'] as Object[],
//					'[Property [{0}] of class [{1}] is invalid]')
//
//			// The following helps with field highlighting in your view
//			this.errors.rejectValue(
//					'tweets',
//					'concept.tweets.invalid')
//		}
//		
//	}
	
	@Override
	public String toString() {
		return conceptName;
	}	
	
	
}
