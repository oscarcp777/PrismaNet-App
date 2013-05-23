package com.prismanet

import static org.junit.Assert.*
import org.junit.*

class TwitterSetupIntegrationTests {

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
		def twitterConfig = new TwitterSetup()
		assertNotNull twitterConfig.save()
		twitterConfig.addToKeywords(new Keyword(word:'filmus'))
		twitterConfig.addToKeywords(new Keyword(word:'politica'))
		
        assertNotNull twitterConfig.id

        def foundConfig = TwitterSetup.get(twitterConfig.id)
        def keywords = foundConfig.keywords*.word
		assertEquals([ 'filmus', 'politica'] , keywords.sort())

    }

    @Test
    void testSaveAndUpdate() {
        def twitterConfig = new TwitterSetup()
		assertNotNull twitterConfig.save()
		twitterConfig.addToKeywords(new Keyword(word:'filmus'))
		twitterConfig.addToKeywords(new Keyword(word:'politica'))
		

        def foundConfig = TwitterSetup.get(twitterConfig.id)
		assertNotNull foundConfig.save()
		def keyword = new Keyword(word:'Cristina')
        foundConfig.addToKeywords(keyword)
        

        def editedConfig = TwitterSetup.get(twitterConfig.id)
        assertTrue editedConfig.keywords.contains(keyword)
    }

    @Test
    void testSaveThenDelete() {
        def twitterConfig = new TwitterSetup()
		assertNotNull twitterConfig.save()
		twitterConfig.addToKeywords(new Keyword(word:'filmus'))
		twitterConfig.addToKeywords(new Keyword(word:'politica'))
		
        def foundConfig = TwitterSetup.get(twitterConfig.id)
        foundConfig.delete()
        
        assertFalse TwitterSetup.exists(foundConfig.id)
    }
}
