

package com.prismanet.exception

import grails.util.Holders

import org.springframework.context.i18n.LocaleContextHolder


class ApplicationException extends Exception {

	/**
	 * @param message
	 * @param cause
	 */
	
	def ApplicationException(String message, Throwable cause) {
		super(message, cause)
	}

	def ApplicationException(String message) {
		super(message)
	}

	def ApplicationException(Collection messages) {
		super(messages)
	}

	/**
	 * @param cause
	 */
	def ApplicationException(Throwable cause) {
		super(cause)
	}

	private ApplicationException() {
	}

	/**
	 * This method will create an ApplicationException for the model passed as parameter, this espcific model should have errors.
	 * This is for the case where you try to save a model and there is errors, this method will create an ApplicationException with the messages.
	 * @param model (grails model) with errors.
	 * @return ApplicationException
	 */
	def static ApplicationException create(model) {
		def messageSource = Holders.getApplicationContext().messageSource
		ApplicationException applicationException = new ApplicationException()
		model.errors.fieldErrors.each { applicationException.addMessage("${messageSource.getMessage(it, LocaleContextHolder.getLocale())}") }
		return applicationException
	}
}
