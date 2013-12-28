package com.prismanet.sentiment



import org.junit.*
import grails.test.mixin.*
import com.prismanet.*

@TestFor(OpinionController)
@Mock(Opinion)
class OpinionControllerTests {

    def populateValidParams(params) {
        assert params != null
        params["concept"] = new Concept(conceptName:"presi")
		params["tweet"] = new Tweet(content:"prueba",tweetId:1000000l)
		params["value"] = OpinionValue.POSITIVE
    }

    void testIndex() {
        controller.index()
        assert "/opinion/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.opinionInstanceList.size() == 0
        assert model.opinionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.opinionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.opinionInstance != null
        assert view == '/opinion/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/opinion/show/1'
        assert controller.flash.message != null
        assert Opinion.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/opinion/list'

        populateValidParams(params)
        def opinion = new Opinion(params)

        assert opinion.save() != null

        params.id = opinion.id

        def model = controller.show()

        assert model.opinionInstance == opinion
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/opinion/list'

        populateValidParams(params)
        def opinion = new Opinion(params)

        assert opinion.save() != null

        params.id = opinion.id

        def model = controller.edit()

        assert model.opinionInstance == opinion
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/opinion/list'

        response.reset()

        populateValidParams(params)
        def opinion = new Opinion(params)

        assert opinion.save() != null

        // test invalid parameters in update
        params.id = opinion.id
        params.concept = null

        controller.update()

        assert view == "/opinion/edit"
        assert model.opinionInstance != null

        opinion.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/opinion/show/$opinion.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        opinion.clearErrors()

        populateValidParams(params)
        params.id = opinion.id
        params.version = -1
        controller.update()

        assert view == "/opinion/edit"
        assert model.opinionInstance != null
        assert model.opinionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/opinion/list'

        response.reset()

        populateValidParams(params)
        def opinion = new Opinion(params)

        assert opinion.save() != null
        assert Opinion.count() == 1

        params.id = opinion.id

        controller.delete()

        assert Opinion.count() == 0
        assert Opinion.get(opinion.id) == null
        assert response.redirectedUrl == '/opinion/list'
    }
}
