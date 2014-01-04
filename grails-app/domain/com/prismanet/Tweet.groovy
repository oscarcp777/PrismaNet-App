package com.prismanet


class Tweet extends Mention{
	String content
	Boolean retweet
	Long tweetId
	static hasMany = [concepts:Concept]
	Author author
	static belongsTo = Concept
	static transients = ['contentHtml']
    static constraints = {
		content(maxLength:140)
		tweetId unique:true  
    }
	
	public Tweet(){
		mentionType = MentionType.TWITTER
	}
	
	
	@Override
	public String toString() {time
		return author?.accountName + "-" + content;
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
	
	
}
