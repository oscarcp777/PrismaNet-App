package com.prismanet

import grails.test.GrailsUnitTestCase
import grails.test.mixin.*
import grails.test.mixin.support.*

import org.junit.*


class ConceptTests extends GrailsUnitTestCase{

	void testConstraints() {
        def concept = new Concept(conceptName : 'Comida')
		mockForConstraintsTests(Concept, [concept])
        assertTrue concept.validate()
        
		def testConcept = new Concept()
		assertFalse testConcept.validate()
		assertEquals testConcept.errors.getErrorCount(),1
		assertEquals "nullable", testConcept.errors["conceptName"]
	}
}
