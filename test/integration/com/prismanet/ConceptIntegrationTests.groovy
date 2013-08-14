package com.prismanet

import static org.junit.Assert.*

import java.awt.print.Printable;

import org.junit.*

class ConceptIntegrationTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

     @Test
    void testFirstSaveEver() {
		def concept = new Concept(conceptName: 'Filmus')
		assertNotNull concept.save()
        assertNotNull concept.id

        def foundConcept = Concept.get(concept.id)
        assertEquals 'Filmus', foundConcept.conceptName
    }

    @Test
    void testSaveAndUpdate() {
        def concept = new Concept(conceptName: 'Filmus')
		assertNotNull concept.save()

        def foundConcept = Concept.get(concept.id)
		def keyword = new Keyword(word:'Cristina')
        foundConcept.conceptName = "Daniel Filmus"
        foundConcept.save()

        def editedConcept = Concept.get(concept.id)
        assertTrue editedConcept.conceptName == "Daniel Filmus"
    }

    @Test
    void testSaveThenDelete() {
        def concept = new Concept(conceptName: 'Filmus')
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
		
		def concept = new Concept(conceptName:"Filmus",twitterSetup:twitterConfig).save()
		def tweet1 = new Tweet(content:"El @twitter1 no existe")
		def tweet2 = new Tweet(content:"El @twitter2 no existe")
		concept.addToTweets(tweet1)
		concept.addToTweets(tweet2)

		assertTrue concept.validate()
		assertFalse concept.hasErrors()
		
	}
	
//	@Test
//	void testCategorizationService(){
//		def conceptService = new ConceptService()
//		
//		def categoryList = conceptService.categoryStore();
//		int j = 1
//		for (i in categoryList) {
//			 println j + "- Sexo: " + i[0] + ", Valor: " + i[1]
//			 j++ 
//		}
//				
//	}
	
//	@Test
//	void testTweetValidator2(){
//		def twitterConfig = new TwitterSetup(includedAccounts:"@CFKArgentina,@twitter2",
//		keywords:"politica,filmus")
//		def tweet1 = new Tweet(content:"La cristi ta loca")
//		def tweet2 = new Tweet(content:"El @CFKArgentina no existe")
//
//		def concept = new Concept(conceptName: 'Filmus',twitterSetup:twitterConfig)
//		concept.addToTweets(tweet1)
//		concept.addToTweets(tweet2)
//		assertFalse concept.validate()
//		assertTrue concept.hasErrors()
//		def errors = concept.errors
//		assertEquals "concept.tweets.invalid", errors.getFieldError("tweets").code
//	}

}
