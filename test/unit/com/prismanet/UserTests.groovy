
package com.prismanet



import grails.test.GrailsUnitTestCase


class UserTests extends GrailsUnitTestCase{

    void testConstraints() {
        def account = new AccountType(type:'free')
        def user = new User(userId: 'oscar', password: 'fiuba', account:account)
		mockForConstraintsTests(User, [user])
        assertTrue user.validate()
        
		def testUser = new User(userId: 'santiago', password: 'fiuba')
		assertFalse testUser.validate()
		assertEquals testUser.errors.getErrorCount(),1
		assertEquals "nullable", testUser.errors["account"]
		
		testUser = new User(userId: 'oscar', password: 'fiuba', account:account)
		assertFalse testUser.validate()
		assertEquals testUser.errors.getErrorCount(),1
		assertEquals "unique", testUser.errors["userId"]
		
		testUser = new User(account:account)
		assertFalse testUser.validate()
		assertEquals testUser.errors.getErrorCount(),2
		assertEquals "nullable", testUser.errors["userId"]
		assertEquals "nullable", testUser.errors["password"]
		
		testUser = new User(userId: 'sa', password: 'fiuba', account:account)
		assertFalse testUser.validate()
		assertEquals testUser.errors.getErrorCount(),1
		assertEquals "size", testUser.errors["userId"]
	}
}
