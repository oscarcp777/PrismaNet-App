package com.prismanet

class FacebookController extends GenericController{

    def list = {
		Concept concept = getConcept(params.id)
		[concept :concept]
	}
}
