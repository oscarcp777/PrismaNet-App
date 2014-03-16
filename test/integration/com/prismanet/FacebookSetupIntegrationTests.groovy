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
		def facebookConfig = new FacebookSetup(keywords:'filmus,politica')
		assertNotNull facebookConfig.save()
		assertNotNull facebookConfig.id

        def foundConfig = FacebookSetup.get(facebookConfig.id)
        assertEquals("filmus,politica" , foundConfig.keywords)

    }

    @Test
    void testSaveAndUpdate() {
        def facebookConfig = new FacebookSetup(keywords:'filmus,politica')
		assertNotNull facebookConfig.save()
		
        def foundConfig = FacebookSetup.get(facebookConfig.id)
		assertNotNull foundConfig.save()
		def keyword = 'dfilmus,politica'
        foundConfig.keywords = keyword
        

        def editedConfig = FacebookSetup.get(facebookConfig.id)
        assertEquals('dfilmus,politica' , editedConfig.keywords)
    }

    @Test
    void testSaveThenDelete() {
        def facebookConfig = new FacebookSetup()
		assertNotNull facebookConfig.save()
		
        def foundConfig = FacebookSetup.get(facebookConfig.id)
        foundConfig.delete()
        
        assertFalse FacebookSetup.exists(foundConfig.id)
    }

}
