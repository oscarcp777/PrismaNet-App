<%@ page import="com.prismanet.AccountType" %>



<div class="fieldcontain ${hasErrors(bean: accountTypeInstance, field: 'type', 'error')} ">
	<label for="type">
		<g:message code="accountType.type.label" default="Type" />
		
	</label>
	<g:textField name="type" value="${accountTypeInstance?.type}"/>
</div>

