package com.prisma.net.twitter.job

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

class UsersTwitterJob {
    static triggers = {
      simple repeatInterval: 60000l, repeatCount:-1 , startDelay: 5000// execute job once in 5 seconds
	  
	  }

    def execute() {
		Twitter twitter = new TwitterFactory().getInstance()
		User user = twitter.showUser("RichyDubini")
		println user
       println "Running Job!"+new Date()
    }
}
