package com.prismanet.sentiment

import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder

enum OpinionValue implements org.springframework.context.MessageSourceResolvable{
	POSITIVE,
	NEGATIVE,
	NEUTRAL
	
	MessageSource messageSource = ApplicationHolder.application.mainContext.getBean('messageSource')
	
	Object[] getArguments() { [] as Object[] }
	
	String[] getCodes() {
		["${getClass().name}.${name()}"] as String[]
	}
	
	String getDefaultMessage() { name() }
	
	public String localize(){
		messageSource.getMessage("${getClass().name}.${name()}", null, LocaleContextHolder.getLocale())
	}
	
}
