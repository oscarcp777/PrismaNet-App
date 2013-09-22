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
//	TreeSet tweets

    static constraints = {
		conceptName(nullable:false)
		tweets()
		posts()
		twitterSetup(nullable:true)
		facebookSetup(nullable:true)
    }
	
	
	public void addToTweets(Tweet tweet){
		def add = testAddTweet(tweet)
		if (add == false){
//			this.errors.reject(
//					'concept.tweets.invalid',
//					['tweets', 'class Concept'] as Object[],
//					'[Property [{0}] of class [{1}] is invalid]')

			// The following helps with field highlighting in your view
			this.errors.rejectValue(
					'tweets',
					'concept.tweets.invalid')
		}else{
			if (!tweets)
				tweets = new ArrayList<Tweet>()
			tweets.add(tweet);
		}

	}
	
	public boolean testAddTweet(Tweet tweet){
		def add = false
		for (String twitterAccount in this.twitterSetup?.includedAccounts?.split(',')) {
			if (tweet.content.contains(twitterAccount)){
				add = true
			}
		}
		for (String keyword in this.twitterSetup?.keywords?.split(',')) {
			if (tweet.content.contains(keyword)){
				add = true
			}
		}
		return add
	}
	
	@Override
	public String toString() {
		return conceptName;
	}	
	
	
}
