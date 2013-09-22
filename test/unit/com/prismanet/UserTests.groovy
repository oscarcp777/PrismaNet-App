
package com.prismanet



import grails.test.GrailsUnitTestCase


class UserTests extends GrailsUnitTestCase{

    void testConstraints() {
        def account = new AccountType(type:'free')
        def user = new User(username: 'oscar', password: 'fiuba', firstName:'oscar', lastName:'Caceres',account:account)
		mockForConstraintsTests(User, [user])
        assertTrue user.validate()
        
		def testUser = new User(username: 'santiago', password: 'fiuba', firstName:'oscar', lastName:'Caceres')
		assertFalse testUser.validate()
		assertEquals testUser.errors.getErrorCount(),1
		assertEquals "nullable", testUser.errors["account"]
		
		testUser = new User(username: 'oscar', password: 'fiuba', account:account, firstName:'oscar', lastName:'Caceres')
		assertFalse testUser.validate()
		assertEquals testUser.errors.getErrorCount(),1
		assertEquals "unique", testUser.errors["username"]
		
		testUser = new User(account:account, firstName:'oscar', lastName:'Caceres')
		assertFalse testUser.validate()
		assertEquals testUser.errors.getErrorCount(),2
		assertEquals "nullable", testUser.errors["username"]
		assertEquals "nullable", testUser.errors["password"]
		
		testUser = new User(username: 'sa', password: 'fiuba', account:account, firstName:'oscar', lastName:'Caceres')
		assertFalse testUser.validate()
		assertEquals testUser.errors.getErrorCount(),1
		assertEquals "size", testUser.errors["username"]
	}
}
