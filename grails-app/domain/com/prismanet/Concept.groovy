package com.prismanet

/**
 * Representa un concepto/idea a ser analizada
 * @author santiago
 *
 */
class Concept {
	
	String conceptName
	Date dateCreated
//	Date lastUpdated
	TwitterSetup twitterSetup
	FacebookSetup facebookSetup
	static hasMany = [mentions:Mention]
	User user

    static constraints = {
		conceptName(nullable:false)
		user()
		mentions()
		twitterSetup(nullable:true)
		facebookSetup(nullable:true)
    }
	
	static mapping = {
		version false
	}
	
	public void addTweet(Tweet tweet){
		def add = testAddTweet(tweet)
		if (add == false){
			this.errors.rejectValue(
					'tweets',
					'concept.tweets.invalid')
		}else{
			doAddToMentions(tweet)
		}
	}
	
	public void addPost(Post post){
		def add = testAddPost(post)
		if (add == false){
			this.errors.rejectValue(
					'posts',
					'concept.posts.invalid')
		}else{
			doAddToMentions(post)
		}
	}
	
	public doAddToMentions(Mention mention){
		if (!mentions)
			mentions = new ArrayList<Mention>()
		mentions.add(mention)
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
		for (String neutralHashtag in this.twitterSetup?.neutralHashtags?.split(',')) {
			if (tweet.content.contains(neutralHashtag)){
				add = true
			}
		}
		if(this.twitterSetup?.isPositiveHashtag(tweet) || this.twitterSetup?.isNegativeHashtag(tweet)){
			add = true
		}
		return add
	}
	
	public boolean testAddPost(Post post){
		def add = false
		
		for (String keyword in this.facebookSetup?.accounts?.split(',')) {
			if (post.postId.contains(keyword)){
				add = true
			}
		}
		return add
	}
	
	@Override
	public String toString() {
		return conceptName;
	}	

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Concept newConcept = new Concept()
		newConcept.conceptName = conceptName
		newConcept.twitterSetup = twitterSetup
		newConcept.facebookSetup = facebookSetup
		newConcept.user = user
		newConcept.twitterSetup = twitterSetup?.clone()
		newConcept.facebookSetup = facebookSetup?.clone()
		return newConcept
	}
	
	
	
	
}
