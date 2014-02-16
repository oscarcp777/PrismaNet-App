<%@ page import="com.prismanet.TwitterSetup" %>



<div class="form-group fieldcontain ${hasErrors(bean: twitterSetupInstance, field: 'includedAccounts', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="includedAccounts">
		<g:message code="twitterSetup.includedAccounts.label" default="Included Accounts" />
		
	</label>
	<g:textField name="includedAccounts" value="${twitterSetupInstance?.includedAccounts}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: twitterSetupInstance, field: 'excludedAccounts', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="excludedAccounts">
		<g:message code="twitterSetup.excludedAccounts.label" default="Excluded Accounts" />
		
	</label>
	<g:textField name="excludedAccounts" value="${twitterSetupInstance?.excludedAccounts}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: twitterSetupInstance, field: 'keywords', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="keywords">
		<g:message code="twitterSetup.keywords.label" default="Keywords" />
		
	</label>
	<g:textField name="keywords" value="${twitterSetupInstance?.keywords}"/>
</div>

