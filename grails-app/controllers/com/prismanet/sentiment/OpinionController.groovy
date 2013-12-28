package com.prismanet.sentiment

import org.springframework.dao.DataIntegrityViolationException

class OpinionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [opinionInstanceList: Opinion.list(params), opinionInstanceTotal: Opinion.count()]
    }

    def create() {
        [opinionInstance: new Opinion(params)]
    }

    def save() {
        def opinionInstance = new Opinion(params)
        if (!opinionInstance.save(flush: true)) {
            render(view: "create", model: [opinionInstance: opinionInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'opinion.label', default: 'Opinion'), opinionInstance.id])
        redirect(action: "show", id: opinionInstance.id)
    }

    def show(Long id) {
        def opinionInstance = Opinion.get(id)
        if (!opinionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'opinion.label', default: 'Opinion'), id])
            redirect(action: "list")
            return
        }

        [opinionInstance: opinionInstance]
    }

    def edit(Long id) {
        def opinionInstance = Opinion.get(id)
        if (!opinionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'opinion.label', default: 'Opinion'), id])
            redirect(action: "list")
            return
        }

        [opinionInstance: opinionInstance]
    }

    def update(Long id, Long version) {
        def opinionInstance = Opinion.get(id)
        if (!opinionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'opinion.label', default: 'Opinion'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (opinionInstance.version > version) {
                opinionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'opinion.label', default: 'Opinion')] as Object[],
                          "Another user has updated this Opinion while you were editing")
                render(view: "edit", model: [opinionInstance: opinionInstance])
                return
            }
        }

        opinionInstance.properties = params

        if (!opinionInstance.save(flush: true)) {
            render(view: "edit", model: [opinionInstance: opinionInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'opinion.label', default: 'Opinion'), opinionInstance.id])
        redirect(action: "show", id: opinionInstance.id)
    }

    def delete(Long id) {
        def opinionInstance = Opinion.get(id)
        if (!opinionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'opinion.label', default: 'Opinion'), id])
            redirect(action: "list")
            return
        }

        try {
            opinionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'opinion.label', default: 'Opinion'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'opinion.label', default: 'Opinion'), id])
            redirect(action: "show", id: id)
        }
    }
}
