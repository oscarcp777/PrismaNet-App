import grails.util.Environment

import com.prismanet.AccountType
import com.prismanet.Author
import com.prismanet.Concept
import com.prismanet.Sex
import com.prismanet.Tweet
import com.prismanet.TwitterSetup
import com.prismanet.User
import com.prismanet.model.security.SecRole
import com.prismanet.model.security.SecUserSecRole;

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

	
	void createSystemUser(){
		// Tipos de Cuenta
		def trialAccount = new AccountType(type:'trial').save()
		def premiumAccount = new AccountType(type:'premium').save()

		def adminRole = SecRole.findByAuthority('ROLE_ADMIN') ?: new SecRole(authority: 'ROLE_ADMIN').save(failOnError: true)
		def userRole = SecRole.findByAuthority('ROLE_USER') ?: new SecRole(authority: 'ROLE_USER').save(failOnError: true)

		def user1 = User.findByUsername('oscarcp777') ?: new User(username: 'oscarcp777', account:trialAccount,enabled: true, password: 'pass', firstName: 'Oscar', lastName: 'C‡ceres').save(failOnError: true)
		if (!user1.authorities.contains(userRole)) {
			SecUserSecRole.create user1, userRole, true
		}
		def user3 = User.findByUsername('sdonik') ?: new User(username: 'sdonik', account:trialAccount,enabled: true, password: 'pass', firstName: 'Santiago', lastName: 'Dinikian').save(failOnError: true)
		if (!user3.authorities.contains(userRole)) {
			SecUserSecRole.create user3, userRole, true
		}
		def user2 = User.findByUsername('admin') ?: new User(username: 'admin', account:premiumAccount,enabled: true, password: 'pass', firstName: 'Admin', lastName: 'Admin').save(failOnError: true)
		if (!user2.authorities.contains(userRole)) {
			SecUserSecRole.create user2, userRole, true
		}
		if (!user2.authorities.contains(adminRole)) {
			SecUserSecRole.create user2, adminRole, true
		}

		assert User.count() == 3
		assert SecRole.count() == 2
		assert SecUserSecRole.count() == 4
	}

	void createAdminUserIfRequired() {
		use ( TimeCategory ) {
			if (!User.findByUsername("admin")) {
				println "Fresh Database. Creating  users."
				createSystemUser();
				
				// Usuario
				def user = User.findByUsername("admin");

				// Twitter's Setup ,@Stolbizer,@danielscioli,@HermesBinner,@mauriciomacri,@elisacarrio,@DelaSotaOk,@juliocobos"}
				def twitterConfigIns = new TwitterSetup(includedAccounts:"@minsaurralde",keywords:"politica,filmus").save()
				def twitterConfigMass = new TwitterSetup(includedAccounts:"@SergioMassa").save()
				def twitterConfigColo = new TwitterSetup(includedAccounts:"@denarvaez").save()
				def twitterConfigPresi = new TwitterSetup(includedAccounts:"@CFKArgentina").save()
				
				def twitterConfigsStolb = new TwitterSetup(includedAccounts:"@Stolbizer").save()
				def twitterConfigScioli = new TwitterSetup(includedAccounts:"@danielscioli").save()
				def twitterConfigBinner= new TwitterSetup(includedAccounts:"@HermesBinner").save()
				def twitterConfigMacri = new TwitterSetup(includedAccounts:"@mauriciomacri").save()
				def twitterConfigCarrio= new TwitterSetup(includedAccounts:"@elisacarrio").save()
				def twitterConfigSota= new TwitterSetup(includedAccounts:"@DelaSotaOk").save()
				def twitterConfigCobos= new TwitterSetup(includedAccounts:"@juliocobos").save()

//				// Autores
//				def author1 = new Author(accountName:"@oscar", followers:10, userSince:new Date(), sex: Sex.M).save()
//				def author2 = new Author(accountName:"@santiago", followers:20, userSince:new Date(), sex: Sex.M).save()
//				def author3 = new Author(accountName:"@raquel", followers:30, userSince:new Date(), sex: Sex.F).save()
//				def author4 = new Author(accountName:"@naty", followers:40, userSince:new Date(), sex: Sex.F).save()


				// Conceptos
				def conceptIns = new Concept(conceptName: 'Insaurralde',twitterSetup:twitterConfigIns).save()
				def conceptMass = new Concept(conceptName: 'Massa',twitterSetup:twitterConfigMass).save()
				def conceptColo = new Concept(conceptName: 'De Narvaez',twitterSetup:twitterConfigColo).save()
				def conceptPresi = new Concept(conceptName: 'CFK',twitterSetup:twitterConfigPresi).save()
				
				def conceptStolb= new Concept(conceptName: 'Stolbizer',twitterSetup:twitterConfigsStolb).save()
				def conceptScioli = new Concept(conceptName: 'Scioli',twitterSetup:twitterConfigScioli).save()
				def conceptBinner= new Concept(conceptName: 'Binner',twitterSetup:twitterConfigBinner).save()
				def conceptMacri = new Concept(conceptName: 'Macri',twitterSetup:twitterConfigMacri).save()
				def conceptCarrio = new Concept(conceptName: 'Carrio',twitterSetup:twitterConfigCarrio).save()
				def conceptSota = new Concept(conceptName: 'De la Sota',twitterSetup:twitterConfigSota).save()
				def conceptCobos = new Concept(conceptName: 'Cobos',twitterSetup:twitterConfigCobos).save()

				// Tweets
//				def tweetIns1 = new Tweet(created:new Date()-3.days, content:"@minsaurralde bastante bien pero perdio", retweet: false, author:author1).save()
//				def tweetIns2 = new Tweet(created:new Date()-2.days, content:"@minsaurralde pelado boton", retweet: false, author:author2).save()
//				def tweetIns3 = new Tweet(created:new Date()-1.days, content:"@minsaurralde con cristina", retweet: false,  author:author3).save()
//
//				def tweetMass1 = new Tweet(created:new Date()-3.days, content:"ojo con el boludo de @SergioMassa", retweet: false, author:author1).save()
//				def tweetMass2 = new Tweet(created:new Date()-2.days, content:"@SergioMassa presidente", retweet: false, author:author2).save()
//				def tweetMass3 = new Tweet(created:new Date()-1.days, content:"@SergioMassa ta con @macri", retweet: false, author:author3).save()
//				def tweetMass4 = new Tweet(created:new Date()-3.days, content:"@SergioMassa gilll", retweet: false, author:author4).save()
//
//				def tweetColo1 = new Tweet(created:new Date()-2.days, content:"@denarvaez muertoooo", retweet: false, author:author1).save()
//				def tweetColo2 = new Tweet(created:new Date()-1.days, content:"@denarvaez vagoooo", retweet: false, author:author1).save()


//				author1.addToTweets(tweetIns1)
//				author1.addToTweets(tweetMass1)
//				author1.addToTweets(tweetColo1)
//				author1.addToTweets(tweetColo2)
//				author2.addToTweets(tweetIns2)
//				author2.addToTweets(tweetMass2)
//				author3.addToTweets(tweetIns3)
//				author3.addToTweets(tweetMass3)
//				author4.addToTweets(tweetMass4)


//
//				conceptIns.addToTweets(tweetIns1)
//				conceptIns.addToTweets(tweetIns2)
//				conceptIns.addToTweets(tweetIns3)
//				conceptMass.addToTweets(tweetMass1)
//				conceptMass.addToTweets(tweetMass2)
//				conceptMass.addToTweets(tweetMass3)
//				conceptMass.addToTweets(tweetMass4)
//				conceptColo.addToTweets(tweetColo1)
//				conceptColo.addToTweets(tweetColo2)

				def concepts=[conceptIns,conceptMass,conceptColo,conceptPresi,conceptStolb
					,conceptScioli,conceptBinner,conceptMacri,conceptCarrio,conceptSota ,conceptCobos ]
				user.concepts=concepts
				user.save(flush: true)
				user = User.findByUsername("oscarcp777");
				user.concepts=concepts
				user.save(flush: true)
				user = User.findByUsername("sdonik");
				user.concepts=concepts
				user.save(flush: true)

			} else {
				println "Existing admin user, skipping creation"
			}
		}
	}

}
