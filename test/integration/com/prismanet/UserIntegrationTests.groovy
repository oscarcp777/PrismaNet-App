package com.prismanet

import static org.junit.Assert.*
import org.junit.*

class UserIntegrationTests {
	
	
    @Before
    void setUp() {
    }

    @After
    void tearDown() {
        // Tear down logic here
    }


    @Test
    void testFirstSaveEver() {
		def account = new AccountType(type:'free').save()
        def user = new User(username: 'oscar', password: 'fiuba', firstName:'oscar', lastName:'Caceres', account:account)
		def springSecurityService = new Object()
		springSecurityService.metaClass.encodePassword = {String password -> "ENCODED_PASSWORD"}
		user.springSecurityService = springSecurityService
        assertNotNull user.save()
        assertNotNull user.id

        def foundUser = User.get(user.id)
        assertEquals 'oscar', foundUser.username
    }

    @Test
    void testSaveAndUpdate() {
		def account = new AccountType(type:'free').save()
        def user = new User(username: 'oscar', password: 'fiuba', firstName:'oscar', lastName:'Caceres', account:account)
        assertNotNull user.save()

        def foundUser = User.get(user.id)
        foundUser.password = 'nuevaPass'
        foundUser.save()

        def editedUser = User.get(user.id)
        assertEquals 'nuevaPass', editedUser.password
    }

    @Test
    void testSaveThenDelete() {
		def account = new AccountType(type:'free').save()
        def user = new User(username: 'oscar', password: 'fiuba', firstName:'oscar', lastName:'Caceres', account:account)
        assertNotNull user.save()
        
        def foundUser = User.get(user.id)
        foundUser.delete()
        
        assertFalse User.exists(foundUser.id)
    }

	
    @Test
    void testEvilSave() {
		def account = new AccountType(type:'free').save()
        def user = new User(password: 'pass', account:account)
        assertFalse user.validate()
        assertTrue user.hasErrors()
        def errors = user.errors
        assertEquals "nullable", errors.getFieldError("username").code
        
        // Campo valido no esta en el objeto errors
        assertNull errors.getFieldError("pass")
    }


}
