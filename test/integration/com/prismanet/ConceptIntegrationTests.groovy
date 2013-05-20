package com.prismanet

import static org.junit.Assert.*
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
		concept.addToKeywords(new Keyword(word:'filmus'))
		concept.addToKeywords(new Keyword(word:'politica'))
		concept.addToTwitterAccounts(new TwitterAccount(name:'@Filmus'))
        assertNotNull concept.save()
        assertNotNull concept.id

        def foundConcept = Concept.get(concept.id)
        assertEquals 'Filmus', foundConcept.conceptName
    }

    @Test
    void testSaveAndUpdate() {
        def concept = new Concept(conceptName: 'Filmus')
		concept.addToKeywords(new Keyword(word:'filmus'))
		concept.addToKeywords(new Keyword(word:'politica'))
		concept.addToTwitterAccounts(new TwitterAccount(name:'@Filmus'))
		assertNotNull concept.save()

        def foundConcept = Concept.get(concept.id)
		def keyword = new Keyword(word:'Cristina')
        foundConcept.addToKeywords(keyword)
        foundConcept.save()

        def editedConcept = Concept.get(concept.id)
        assertTrue editedConcept.keywords.contains(keyword)
    }

    @Test
    void testSaveThenDelete() {
        def concept = new Concept(conceptName: 'Filmus')
		concept.addToKeywords(new Keyword(word:'filmus'))
		concept.addToKeywords(new Keyword(word:'politica'))
		concept.addToTwitterAccounts(new TwitterAccount(name:'@Filmus'))
		
        assertNotNull concept.save()
        
        def foundConcept = Concept.get(concept.id)
        foundConcept.delete()
        
        assertFalse Concept.exists(foundConcept.id)
    }

//    @Test
//    void testEvilSave() {
//        def concept = new Concept(conceptName: 'Filmus')
//		concept.addToKeywords(new Keyword(word:'filmus'))
//		concept.addToKeywords(new Keyword(word:'politica'))
//		concept.addToTwitterAccounts(new TwitterAccount(name:'Twitter Invalido'))
//						
//		assertFalse concept.validate()
//        assertTrue concept.hasErrors()
//        def errors = concept.errors
//        assertEquals "Twitter invalido", errors.getFieldError("twitterAccounts").rejectedValue
//        
//        // Campo valido no esta en el objeto errors
//        assertNull errors.getFieldError("conceptName")
//        
//    }

}
