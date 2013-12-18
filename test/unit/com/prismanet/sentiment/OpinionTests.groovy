package com.prismanet.sentiment

import com.prismanet.User
import com.prismanet.Tweet
import com.prismanet.Concept

import grails.test.mixin.*

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Opinion)
class OpinionTests {

    void testConstraints() {
		
		def existingOpinion = new Opinion(
			user: new User(),
			tweet: new Tweet(),
			concept: new Concept(),
			value: OpinionValue.POSITIVE,
		)
		mockForConstraintsTests( Opinion, [ existingOpinion ] )
		
		// valido las propiedades en null
		def opinion = new Opinion()
		
		assert !opinion.validate()
		assert "nullable" == opinion.errors["user"]
		assert "nullable" == opinion.errors["concept"]
		assert "nullable" == opinion.errors["tweet"]
		assert "nullable" == opinion.errors["value"]
		
		
		// valido que pase.
		assert existingOpinion.validate()

	}
}
