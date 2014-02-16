
<%@ page import="com.prismanet.TwitterSetup" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="home">
		<g:set var="entityName" value="${message(code: 'twitterSetup.label', default: 'TwitterSetup')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-twitterSetup" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-twitterSetup" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list twitterSetup">
			
				<g:if test="${twitterSetupInstance?.includedAccounts}">
				<li class="fieldcontain">
					<span id="includedAccounts-label" class="property-label"><g:message code="twitterSetup.includedAccounts.label" default="Included Accounts" /></span>
					
						<span class="property-value" aria-labelledby="includedAccounts-label"><g:fieldValue bean="${twitterSetupInstance}" field="includedAccounts"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${twitterSetupInstance?.excludedAccounts}">
				<li class="fieldcontain">
					<span id="excludedAccounts-label" class="property-label"><g:message code="twitterSetup.excludedAccounts.label" default="Excluded Accounts" /></span>
					
						<span class="property-value" aria-labelledby="excludedAccounts-label"><g:fieldValue bean="${twitterSetupInstance}" field="excludedAccounts"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${twitterSetupInstance?.keywords}">
				<li class="fieldcontain">
					<span id="keywords-label" class="property-label"><g:message code="twitterSetup.keywords.label" default="Keywords" /></span>
					
						<span class="property-value" aria-labelledby="keywords-label"><g:fieldValue bean="${twitterSetupInstance}" field="keywords"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${twitterSetupInstance?.id}" />
					<g:link class="edit" action="edit" id="${twitterSetupInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
