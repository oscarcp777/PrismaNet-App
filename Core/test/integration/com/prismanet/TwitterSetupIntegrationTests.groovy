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
		def twitterConfig1 = new TwitterSetup(includedAccounts:"@twitter1,@twitter2")
		assertNotNull twitterConfig1.save()
		
		def twitterConfig2 = new TwitterSetup(includedAccounts:"@dsfa")
		assertNotNull twitterConfig2.save()

		assertNotNull twitterConfig1.id

        def foundConfig = TwitterSetup.get(twitterConfig1.id)
		assertEquals(twitterConfig1.id,foundConfig.id)

    }

    @Test
    void testSaveAndUpdate() {
        def twitterConfig = new TwitterSetup(includedAccounts:"@Filmus")
		assertNotNull twitterConfig.save()

        def foundConfig = TwitterSetup.get(twitterConfig.id)
		assertNotNull foundConfig.save()
        foundConfig.includedAccounts += ",@Pichetto"
		

        def editedConfig = TwitterSetup.get(twitterConfig.id)
		assertTrue editedConfig.includedAccounts.contains(",@Pichetto")
    }

    @Test
    void testSaveThenDelete() {
        def twitterConfig = new TwitterSetup(includedAccounts:"@Filmus")
		assertNotNull twitterConfig.save()
		
        def foundConfig = TwitterSetup.get(twitterConfig.id)
        foundConfig.delete()
        
        assertFalse TwitterSetup.exists(foundConfig.id)
    }
	
	
	@Test
	void testEvilSave() {
		def twitterConfig =  new TwitterSetup(includedAccounts:"234141")
		assertFalse twitterConfig.validate()
		assertTrue twitterConfig.hasErrors()
		def errors = twitterConfig.errors
		assertEquals "twitterAccount.invalid", errors.getFieldError("includedAccounts").code
		
		twitterConfig =  new TwitterSetup(excludedAccounts:"234141")
		assertFalse twitterConfig.validate()
		assertTrue twitterConfig.hasErrors()
		errors = twitterConfig.errors
		assertEquals "twitterAccount.invalid", errors.getFieldError("excludedAccounts").code
		
		
		twitterConfig =  new TwitterSetup(excludedAccounts:"@Osqui, no anda")
		assertFalse twitterConfig.validate()
		assertTrue twitterConfig.hasErrors()
		errors = twitterConfig.errors
		assertEquals "twitterAccount.invalid", errors.getFieldError("excludedAccounts").code
		
		twitterConfig =  new TwitterSetup(keywords:"palabra valida, no@valida")
		assertFalse twitterConfig.validate()
		assertTrue twitterConfig.hasErrors()
		errors = twitterConfig.errors
		assertEquals "wordSetup.invalid", errors.getFieldError("keywords").code
		// Campo valido no esta en el objeto errors
		assertNull errors.getFieldError("excludedAccounts")
		
	}
}
