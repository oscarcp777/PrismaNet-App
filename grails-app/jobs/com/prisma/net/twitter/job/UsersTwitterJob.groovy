package com.prisma.net.twitter.job

import com.prisma.net.twitter.model.UserJSON;

import twitter4j.*;

class UsersTwitterJob {
    static triggers = {
      simple repeatInterval: 5000l, repeatCount:-1 , startDelay: 5000// execute job once in 5 seconds
	  
	  }

    def execute() {
		Twitter twitter = new TwitterFactory().getInstance()
		User user = twitter.showUser("RichyDubini")
		println user
	
       println "Running Job!"+new Date()
    }
	def UserJSON loadUser(def user4j){
		UserJSON userJson= new UserJSON();
		
	}
}
