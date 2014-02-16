<%@ page import="com.prismanet.AccountType" %>



<div class="form-group fieldcontain ${hasErrors(bean: accountTypeInstance, field: 'type', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="type">
		<g:message code="accountType.type.label" default="Type" />
		
	</label>
	<g:textField name="type" value="${accountTypeInstance?.type}"/>
</div>

