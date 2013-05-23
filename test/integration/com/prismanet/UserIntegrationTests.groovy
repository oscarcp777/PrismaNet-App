package com.prismanet

import static org.junit.Assert.*
import org.junit.*

class UserIntegrationTests {

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
		def account = new AccountType(type:'free').save()
        def user = new User(userId: 'oscar', password: 'fiuba', account:account)
        assertNotNull user.save()
        assertNotNull user.id

        def foundUser = User.get(user.id)
        assertEquals 'oscar', foundUser.userId
    }

    @Test
    void testSaveAndUpdate() {
		def account = new AccountType(type:'free').save()
        def user = new User(userId: 'oscar', password: 'fiuba', account:account)
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
        def user = new User(userId: 'oscar', password: 'fiuba', account:account)
        assertNotNull user.save()
        
        def foundUser = User.get(user.id)
        foundUser.delete()
        
        assertFalse User.exists(foundUser.id)
    }

    @Test
    void testEvilSave() {
		def account = new AccountType(type:'free').save()
        def user = new User(userId: 'oscar',password: 'no', account:account)
        assertFalse user.validate()
        assertTrue user.hasErrors()
        def errors = user.errors
        assertEquals "size.toosmall", errors.getFieldError("password").code
        assertEquals "no", errors.getFieldError("password").rejectedValue
        
        // Campo valido no esta en el objeto errors
        assertNull errors.getFieldError("userId")
        
    }


}
