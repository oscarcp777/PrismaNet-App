package com.prismanet

import static org.junit.Assert.*
import org.junit.*

class FacebookSetupIntegrationTests {

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
		def facebookConfig = new FacebookSetup()
		assertNotNull facebookConfig.save()
		facebookConfig.addToKeywords(new Keyword(word:'filmus'))
		facebookConfig.addToKeywords(new Keyword(word:'politica'))
		
        assertNotNull facebookConfig.id

        def foundConfig = FacebookSetup.get(facebookConfig.id)
        def keywords = foundConfig.keywords*.word
		assertEquals([ 'filmus', 'politica'] , keywords.sort())

    }

    @Test
    void testSaveAndUpdate() {
        def facebookConfig = new FacebookSetup()
		assertNotNull facebookConfig.save()
		facebookConfig.addToKeywords(new Keyword(word:'filmus'))
		facebookConfig.addToKeywords(new Keyword(word:'politica'))
		

        def foundConfig = FacebookSetup.get(facebookConfig.id)
		assertNotNull foundConfig.save()
		def keyword = new Keyword(word:'Cristina')
        foundConfig.addToKeywords(keyword)
        

        def editedConfig = FacebookSetup.get(facebookConfig.id)
        assertTrue editedConfig.keywords.contains(keyword)
    }

    @Test
    void testSaveThenDelete() {
        def facebookConfig = new FacebookSetup()
		assertNotNull facebookConfig.save()
		facebookConfig.addToKeywords(new Keyword(word:'filmus'))
		facebookConfig.addToKeywords(new Keyword(word:'politica'))
		
        def foundConfig = FacebookSetup.get(facebookConfig.id)
        foundConfig.delete()
        
        assertFalse FacebookSetup.exists(foundConfig.id)
    }

}
