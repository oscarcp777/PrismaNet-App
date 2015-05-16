package com.prismanet


class Tweet extends Mention{
	
	Boolean retweet
	Long retweetCount = 0
	Long favoriteCount = 0
	Long tweetId
	Long retweetId
	
	static transients = ['contentHtml']
    static constraints = {
		retweet(nullable:true)
		tweetId(nullable:true)
		retweetId(nullable:true)
	}
	
	
	
	def getContentHtml(){
		def listWords = content.tokenize(' ')
		def contentHtml=""
		listWords.each {it -> 
			
			if(it.contains("@")){
				def name=it.replace("@","")
			    contentHtml+=" <a href='https://twitter.com/${name}' target='_blanck'>${it}</a> "
			}
			else if(it.contains("http")|| it.contains("https")){
				contentHtml+=" <a href='${it}' target='_blanck'>${it}</a> "
			}
			else if(it.contains("#")){
				def name=it.replace("#","")
				contentHtml+=" <a href='https://twitter.com/search?q=%23${name}&src=hash' target='_blanck'>${it}</a> "
			}
			else{
				contentHtml+=it+" "
			}
			
		}
		contentHtml
	}
	
	
	public MentionType getMentionType(){
		return MentionType.TWITTER
	}
	
	
}
