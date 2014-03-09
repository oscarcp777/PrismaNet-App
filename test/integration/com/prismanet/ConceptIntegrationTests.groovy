package com.prismanet

import static org.junit.Assert.*

import org.junit.*

import com.prismanet.GenericService.ProjectionType
import com.prismanet.sentiment.Opinion
import com.prismanet.sentiment.OpinionValue


class ConceptIntegrationTests {

    @Before
    void setUp() {
		def setup = new TwitterSetup(includedAccounts:"@CFKArgentina,@twitter2", keywords:"politica,filmus").save()
		def author = new Author(accountName:"@oscar", followers:10, userSince:new Date(), sex: Sex.M).save()
		def account = new AccountType(type:'free').save()
		def user = new User(username: 'oscar', password: 'fiuba', firstName:'oscar', lastName:'Caceres',account:account).save()
		def tweet = new Tweet(content:"@CFKArgentina", author:author, created:new Date(), tweetId:1l, retweet:false).save()
		def concept = new Concept(conceptName: 'Filmus',twitterSetup:setup, user: user).save()
		concept.addToTweets(tweet)
		def opinion = new Opinion(concept:concept, tweet:tweet, value:OpinionValue.POSITIVE).save()
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

     @Test
    void testFirstSaveEver() {
		def concept = new Concept(conceptName: 'Filmus', user:User.first())
		assertNotNull concept.save()
        assertNotNull concept.id

        def foundConcept = Concept.get(concept.id)
        assertEquals 'Filmus', foundConcept.conceptName
    }

    @Test
    void testSaveAndUpdate() {
        def concept = new Concept(conceptName: 'Filmus', user:User.first())
		assertNotNull concept.save()

        def foundConcept = Concept.get(concept.id)
		foundConcept.conceptName = "Daniel Filmus"
        foundConcept.save()

        def editedConcept = Concept.get(concept.id)
        assertTrue editedConcept.conceptName == "Daniel Filmus"
    }

    @Test
    void testSaveThenDelete() {
        def concept = new Concept(conceptName: 'Filmus', user:User.first())
		assertNotNull concept.save()
        
        def foundConcept = Concept.get(concept.id)
        foundConcept.delete()
        
        assertFalse Concept.exists(foundConcept.id)
    }

    @Test
    void testEvilSave() {
        def concept = new Concept()
		assertFalse concept.validate()
        assertTrue concept.hasErrors()
        def errors = concept.errors
		assertEquals "nullable", errors.getFieldError("conceptName").code
    }
	
	@Test
	void testTweetValidator(){
		def twitterConfig = new TwitterSetup(includedAccounts:"@twitter1,@twitter2",
		keywords:"politica,filmus")
		
		def concept = new Concept(conceptName:"Filmus",twitterSetup:twitterConfig, user:User.first()).save()
		def tweet1 = new Tweet(content:"El @twitter1 no existe")
		def tweet2 = new Tweet(content:"El @twitter2 no existe")
		concept.addToTweets(tweet1)
		concept.addToTweets(tweet2)

		assertTrue concept.validate()
		assertFalse concept.hasErrors()
		
	}
	
	@Test
	void testCategorizationByAuthorSexService(){
		def quantityProjection = ["tweetsId" : ProjectionType.COUNT]
		def conceptService = new ConceptService()
		def concept = new Concept(conceptName: 'Filmus', user:User.first())
		assertNotNull concept.save()
		def groupList = ["sex"]
		def categoryList = conceptService.categoryStore(groupList, null, quantityProjection, null);
		int j = 1
		for (i in categoryList) {
			 println j + "- Sexo: " + i[0] + ", Valor: " + i[1]
			 j++ 
		}
				
	}
	
	
	@Test
	void testCategorizationByTweetDateService(){
		def quantityProjection = ["tweetsId" : ProjectionType.COUNT]
		def conceptService = new ConceptService()
		def groupList = ["tweetCreated"]
		def list = Concept.list()
		def categoryList = conceptService.categoryStore(groupList, null, quantityProjection, null);
		int j = 1
		for (i in categoryList) {
			 println j + "- Fecha: " + i[0] + ", Valor: " + i[1]
			 
			 j++
		}
				
	}

	
	@Test
	void testTweetValidator2(){
		def twitterConfig = new TwitterSetup(includedAccounts:"@CFKArgentina,@twitter2",
		keywords:"politica,filmus")
		def author1 = new Author(accountName:"@oscar", followers:10, userSince:new Date(), sex: Sex.M)
		def tweet1 = new Tweet(content:"La cristi ta loca", author:author1)
		def tweet2 = new Tweet(content:"El @CFKArgentina no existe", author:author1)
		
		def concept = new Concept(conceptName: 'Filmus',twitterSetup:twitterConfig)
		concept.addToTweets(tweet1)
		concept.addToTweets(tweet2)
		
		assertTrue concept.hasErrors()
		def errors = concept.errors
		assertEquals "concept.tweets.invalid", errors.getFieldError("tweets").code
	}
	
	
	void testExistingAuthor(){
		// Twitter's Setup
		def twitterConfigIns = new TwitterSetup(includedAccounts:"@minsaurralde",keywords:"politica,filmus").save()
		// Autor
//		def author = new Author(accountName:"@oscar", followers:10, userSince:new Date(), sex: Sex.M).save()
		// Concepto
		def conceptIns = new Concept(conceptName: 'Insaurralde',twitterSetup:twitterConfigIns)
		
		Author testAuthor = Author.findByAccountName("@oscar")
		if (!testAuthor)
			testAuthor = new Author(accountName:"@oscar", followers:10, userSince:new Date(), sex: Sex.M)
		// Tweets
		def tweetIns1 = new Tweet(created:new Date(), content:"@minsaurralde bastante bien pero perdio", retweet: false, author:testAuthor)
		
		testAuthor.save()
		tweetIns1.save()
		conceptIns.addToTweets(tweetIns1)
		conceptIns.save()
	}


	@Test
	void testList(){
		def conceptService = new ConceptService()
		def conceptList = conceptService.list(null,[:],[])
		assert conceptList.results.size() == 1
	}
	
	
	
	

}
