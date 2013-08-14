import grails.util.Environment

import com.prismanet.AccountType
import com.prismanet.Author
import com.prismanet.Concept
import com.prismanet.Sex
import com.prismanet.Tweet
import com.prismanet.TwitterSetup
import com.prismanet.User
import groovy.time.TimeCategory

class BootStrap {

	def init = { servletContext ->
		switch (Environment.current) {
			case Environment.DEVELOPMENT:
				createAdminUserIfRequired()
				break;
			case Environment.PRODUCTION:
				break;
		}
	}

	def destroy = {
	}


	void createAdminUserIfRequired() {
		use ( TimeCategory ) {
			if (!User.findByUserId("admin")) {
				println "Fresh Database. Creating ADMIN user."

				// Tipos de Cuenta
				def trialAccount = new AccountType(type:'trial').save()
				def premiumAccount = new AccountType(type:'premium').save()

				// Usuario
				def user = new User(userId: 'admin', password: 'admin', account:trialAccount).save()

				// Twitter's Setup
				def twitterConfigIns = new TwitterSetup(includedAccounts:"@minsaurralde",keywords:"politica,filmus").save()
				def twitterConfigMass = new TwitterSetup(includedAccounts:"@SergioMassa").save()
				def twitterConfigColo = new TwitterSetup(includedAccounts:"@denarvaez").save()

				// Autores
				def author1 = new Author(accountName:"@oscar", followers:10, userSince:new Date(), sex: Sex.M).save()
				def author2 = new Author(accountName:"@santiago", followers:20, userSince:new Date(), sex: Sex.M).save()
				def author3 = new Author(accountName:"@raquel", followers:30, userSince:new Date(), sex: Sex.F).save()
				def author4 = new Author(accountName:"@naty", followers:40, userSince:new Date(), sex: Sex.F).save()


				// Conceptos
				def conceptIns = new Concept(conceptName: 'Insaurralde',twitterSetup:twitterConfigIns).save()
				def conceptMass = new Concept(conceptName: 'Massa',twitterSetup:twitterConfigMass).save()
				def conceptColo = new Concept(conceptName: 'De Narvaez',twitterSetup:twitterConfigColo).save()

				// Tweets

				def tweetIns1 = new Tweet(created:new Date()-3.days, content:"@minsaurralde bastante bien pero perdio", retweet: false, author:author1).save()
				def tweetIns2 = new Tweet(created:new Date()-2.days, content:"@minsaurralde pelado boton", retweet: false, author:author2).save()
				def tweetIns3 = new Tweet(created:new Date()-1.days, content:"@minsaurralde con cristina", retweet: false,  author:author3).save()

				def tweetMass1 = new Tweet(created:new Date()-3.days, content:"ojo con el boludo de @SergioMassa", retweet: false, author:author1).save()
				def tweetMass2 = new Tweet(created:new Date()-2.days, content:"@SergioMassa presidente", retweet: false, author:author2).save()
				def tweetMass3 = new Tweet(created:new Date()-1.days, content:"@SergioMassa ta con @macri", retweet: false, author:author3).save()
				def tweetMass4 = new Tweet(created:new Date()-3.days, content:"@SergioMassa gilll", retweet: false, author:author4).save()

				def tweetColo1 = new Tweet(created:new Date()-2.days, content:"@denarvaez muertoooo", retweet: false, author:author1).save()
				def tweetColo2 = new Tweet(created:new Date()-1.days, content:"@denarvaez vagoooo", retweet: false, author:author1).save()


				author1.addToTweets(tweetIns1)
				author1.addToTweets(tweetMass1)
				author1.addToTweets(tweetColo1)
				author1.addToTweets(tweetColo2)

				author2.addToTweets(tweetIns2)
				author2.addToTweets(tweetMass2)
				
				author3.addToTweets(tweetIns3)
				author3.addToTweets(tweetMass3)

				author4.addToTweets(tweetMass4)



				conceptIns.addToTweets(tweetIns1)
				conceptIns.addToTweets(tweetIns2)
				conceptIns.addToTweets(tweetIns3)

				conceptMass.addToTweets(tweetMass1)
				conceptMass.addToTweets(tweetMass2)
				conceptMass.addToTweets(tweetMass3)
				conceptMass.addToTweets(tweetMass4)

				conceptColo.addToTweets(tweetColo1)
				conceptColo.addToTweets(tweetColo2)


				user.addToConcepts(conceptIns)
				user.addToConcepts(conceptMass)
				user.addToConcepts(conceptColo)


			} else {
				println "Existing admin user, skipping creation"
			}
		}
	}

}
