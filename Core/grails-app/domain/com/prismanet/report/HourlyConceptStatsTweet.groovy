package com.prismanet.report

import com.prismanet.Author

/**
 * Tweet importante de una hora/concepto
 * @author santiago
 *
 */
class HourlyConceptStatsTweet {
	
	HourlyConceptStats parent
	Boolean retweet
	Long retweetCount = 0
	Long favoriteCount = 0
	Long tweetId
	Long retweetId
	String content
	Date created
	Author author

    static constraints = {
		content(maxSize: 5000)
		retweet(nullable:true)
		tweetId(nullable:true)
		retweetId(nullable:true)
    }
	
	
}
