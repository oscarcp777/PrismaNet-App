package com.prismanet

import grails.test.mixin.*

@Mock(Author)
@TestFor(Author)
class AuthorTests{
	
	void testConstraints() {
		def author = new Author(accountName:'@Prueba_Exitosa_')
		def authorFail = new Author(accountName:'PruebaFallida')
		mockForConstraintsTests(Author, [author, authorFail])
		
		assertTrue author.validate()

		
		assertFalse authorFail.validate()
		assertEquals authorFail.errors.getErrorCount(),1
		assertEquals "validator", authorFail.errors["accountName"]

	}

}
