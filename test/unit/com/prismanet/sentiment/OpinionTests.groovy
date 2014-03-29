package com.prismanet.sentiment

import com.prismanet.User
import com.prismanet.Tweet
import com.prismanet.Concept


/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Opinion)
class OpinionTests {

    void testConstraints() {
		
		def existingOpinion = new Opinion(
			tweet: new Tweet(),
			concept: new Concept(),
			value: OpinionValue.POSITIVE,
		)
		mockForConstraintsTests( Opinion, [ existingOpinion ] )
		
		// valido las propiedades en null
		def opinion = new Opinion()
		
		assert !opinion.validate()
		assertEquals opinion.errors.getErrorCount(),3
		assert "nullable" == opinion.errors["concept"]
		assert "nullable" == opinion.errors["tweet"]
		assert "nullable" == opinion.errors["value"]
		
		
		// valido que pase.
		assert existingOpinion.validate()

	}
}
