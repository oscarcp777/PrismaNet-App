package com.prismanet

import org.apache.commons.lang.StringUtils;

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
	static hasMany = [mentions:Mention, posts:Post]
	User user
	Boolean active = Boolean.TRUE
	Country country
	Languages lang

    static constraints = {
		conceptName(nullable:false)
		user()
		mentions()
		posts()
		twitterSetup(nullable:true)
		facebookSetup(nullable:true)
		country(nullable:true)
		lang(nullable:true)
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
	
	public void addPost(FacebookComment post){
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
		def listnew=tweet.content.replace(',',' ')
		listnew=listnew.replace('.',' ').toLowerCase()
		def listMap=listnew.tokenize()
		def tweetTokenized = listMap.collectEntries{[it, it]}
		
		for (String excludedWord in this.twitterSetup?.excludedAccounts?.split(',')) {
			// Solo excluye palabras, no aplica para frases
			if (tweetTokenized[excludedWord.toLowerCase()]){
				return false
			}
		}
		for (String twitterAccount in this.twitterSetup?.includedAccounts?.split(',')) {
			if (tweetTokenized[twitterAccount.toLowerCase()]){
				add = true
			}
		}
		if (!add)
			for (String neutralHashtag in this.twitterSetup?.neutralHashtags?.split(',')) {
				if (tweetTokenized[neutralHashtag.toLowerCase()]){
					add = true
				}
			}
		if (!add)
			if(this.twitterSetup?.isPositiveHashtag(tweet) || this.twitterSetup?.isNegativeHashtag(tweet)){
				add = true
			}
		if (!add){
			def expressions = this.twitterSetup?.keywords?.split(',')
			if (expressions && expressions.size()>0){
				int i=0
				// Loop de OR corta cuando encuentra el primer verdadero
				while (i<expressions.size() && !add){
					def isValid = true
					expressions[i] = expressions[i].trim()
					// Frases textuales
					if (expressions[i][0]=='\"' && expressions[i][expressions[i].size()-1]=='\"'){
						if(expressions[i][1..-2].tokenize().size()>1){
							// Si son varias palabras busca el texto ignorando mayusculas/minusculas
							if (!StringUtils.containsIgnoreCase(tweet.content,expressions[i][1..-2])){
							  isValid = false
							 }
						 }else{
						 	// Si es una palabra compara con map 
						  	if (!tweetTokenized[expressions[i][1..-2].toLowerCase()]){
							  isValid = false
							}
						 }
					}else{
						def words = expressions[i].split(' ')
						int j=0
						// Loop de AND corta cuando encuentra el primer falso
						while (j<words.size() && isValid){
							if (!tweetTokenized[words[j].toLowerCase()])
								isValid = false
							j++
						}
					}
					if (isValid)
						add = true
					i++
				}
			}
		}
		return add
	}
	
	public boolean testAddPost(FacebookComment post){
		def add = false
		
		for (String keyword in this.facebookSetup?.accounts?.split(',')) {
			if (post.postId.contains(keyword)){
				add = true
			}
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
