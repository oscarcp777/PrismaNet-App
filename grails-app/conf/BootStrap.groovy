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

		def user1 = User.findByUsername('oscarcp777') ?: new User(username: 'oscarcp777', account:trialAccount,enabled: true, password: 'pass', firstName: 'Oscar', lastName: 'C�ceres').save(failOnError: true)
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
				def twitterConfigCapi= new TwitterSetup(includedAccounts:"@jmcapitanich").save()
				

				// Autores
//				def author1 = new Author(accountName:"@oscarcp777", followers:10, userSince:new Date(), sex: Sex.M).save()
//				def author2 = new Author(accountName:"@SLDbacktoF2", followers:20, userSince:new Date(), sex: Sex.M).save()
//				def author3 = new Author(accountName:"@oscarcp78", followers:30, userSince:new Date(), sex: Sex.F).save()
//				def author4 = new Author(accountName:"@OscarCP7", followers:40, userSince:new Date(), sex: Sex.F).save()


				// Conceptos
				def conceptIns = new Concept(conceptName: 'Insaurralde',twitterSetup:twitterConfigIns, user:user).save()
				def conceptMass = new Concept(conceptName: 'Massa',twitterSetup:twitterConfigMass, user:user).save()
				def conceptColo = new Concept(conceptName: 'De Narvaez',twitterSetup:twitterConfigColo, user:user).save()
				def conceptPresi = new Concept(conceptName: 'CFK',twitterSetup:twitterConfigPresi, user:user).save()
				
				def conceptStolb= new Concept(conceptName: 'Stolbizer',twitterSetup:twitterConfigsStolb, user:user).save()
				def conceptScioli = new Concept(conceptName: 'Scioli',twitterSetup:twitterConfigScioli, user:user).save()
				def conceptBinner= new Concept(conceptName: 'Binner',twitterSetup:twitterConfigBinner, user:user).save()
				def conceptMacri = new Concept(conceptName: 'Macri',twitterSetup:twitterConfigMacri, user:user).save()
				def conceptCarrio = new Concept(conceptName: 'Carrio',twitterSetup:twitterConfigCarrio, user:user).save()
				def conceptSota = new Concept(conceptName: 'De la Sota',twitterSetup:twitterConfigSota, user:user).save()
				def conceptCobos = new Concept(conceptName: 'Cobos',twitterSetup:twitterConfigCobos, user:user).save()
				def conceptCapi = new Concept(conceptName: 'Capitanich',twitterSetup:twitterConfigCapi, user:user).save()

				// Tweets
//				def tweetIns1 = new Tweet(created:new Date()-3.days, content:"@minsaurralde bastante bien pero perdio", retweet: false, author:author1, tweetId:1l).save()
//				def tweetIns2 = new Tweet(created:new Date()-2.days, content:"@minsaurralde pelado boton", retweet: false, author:author2, tweetId:2l).save()
//				def tweetIns3 = new Tweet(created:new Date()-1.days, content:"@minsaurralde con cristina", retweet: false,  author:author3, tweetId:3l).save()
//
//				def tweetMass1 = new Tweet(created:new Date()-3.days, content:"ojo con el boludo de @SergioMassa", retweet: false, author:author1, tweetId:4l).save()
//				def tweetMass2 = new Tweet(created:new Date()-2.days, content:"@SergioMassa presidente", retweet: false, author:author2, tweetId:5l).save()
//				def tweetMass3 = new Tweet(created:new Date()-1.days, content:"@SergioMassa ta con @macri", retweet: false, author:author3, tweetId:6l).save()
//				def tweetMass4 = new Tweet(created:new Date()-3.days, content:"@SergioMassa gilll", retweet: false, author:author4, tweetId:7l).save()
//
//				def tweetColo1 = new Tweet(created:new Date()-2.days, content:"@denarvaez muertoooo", retweet: false, author:author1, tweetId:8l).save()
//				def tweetColo2 = new Tweet(created:new Date()-1.days, content:"@denarvaez vagoooo", retweet: false, author:author1, tweetId:9l).save()
//
//
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
//
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
//				
				

				
				user = User.findByUsername("oscarcp777");
				def concepts=[conceptMass.clone(),conceptPresi.clone(),conceptScioli.clone(),
					conceptBinner.clone(),conceptMacri.clone(),conceptCapi.clone()]
				concepts.each { 				
					it.user = user
					it.save()
				}
				
				
				user = User.findByUsername("sdonik");
				concepts=[conceptIns.clone(),conceptColo.clone(),conceptStolb.clone(), conceptCarrio.clone(),conceptSota.clone() ,conceptCobos.clone() ]
				concepts.each {
					it.user = user
					it.save()
				}
				
			} else {
				println "Existing admin user, skipping creation"
			}
		}
	}

}
