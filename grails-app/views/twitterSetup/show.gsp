

<%@ page import="com.prismanet.TwitterSetup" %>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="home">
<g:set var="entityName"
	value="${message(code: 'twitterSetup.label', default: 'TwitterSetup')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>
				<g:message code="default.show.label" args="[entityName]" />

			</h1>
		</div>
		<div class="nav" role="navigation">
			<g:link class="btn btn-info btn-sm" action="list">
				<g:message code="default.list.label" args="[entityName]" />
				<i class="fa fa-arrow-right  bigger-110"></i>
			</g:link>
			<g:link class="btn btn-info btn-sm" action="create">
				<g:message code="default.new.label" args="[entityName]" />
				<i class="fa fa-arrow-right  bigger-110"></i>
			</g:link>
		</div>
		<hr>
		<div id="show-twitterSetup"
			class="content scaffold-show" role="main">
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div
				class="profile-user-info profile-user-info-striped property-list twitterSetup">
				
				<g:if test="${twitterSetupInstance?.includedAccounts}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="includedAccounts-label" class="property-label">
					<g:message code="twitterSetup.includedAccounts.label" default="Included Accounts" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="includedAccounts-label"><g:fieldValue bean="${twitterSetupInstance}" field="includedAccounts"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${twitterSetupInstance?.excludedAccounts}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="excludedAccounts-label" class="property-label">
					<g:message code="twitterSetup.excludedAccounts.label" default="Excluded Accounts" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="excludedAccounts-label"><g:fieldValue bean="${twitterSetupInstance}" field="excludedAccounts"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${twitterSetupInstance?.keywords}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="keywords-label" class="property-label">
					<g:message code="twitterSetup.keywords.label" default="Keywords" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="keywords-label"><g:fieldValue bean="${twitterSetupInstance}" field="keywords"/></span>
					
					</div>
					</div>
				</g:if>
				<g:if test="${twitterSetupInstance?.neutralHashtags}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="neutralHashtags-label" class="property-label">
					<g:message code="twitterSetup.neutralHashtags.label" default="neutralHashtags" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="neutralHashtags-label"><g:fieldValue bean="${twitterSetupInstance}" field="neutralHashtags"/></span>
					
					</div>
					</div>
				</g:if>
				<g:if test="${twitterSetupInstance?.positiveHashtags}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="positiveHashtags-label" class="property-label">
					<g:message code="twitterSetup.positiveHashtags.label" default="positiveHashtags" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="positiveHashtags-label"><g:fieldValue bean="${twitterSetupInstance}" field="positiveHashtags"/></span>
					
					</div>
					</div>
				</g:if>
				<g:if test="${twitterSetupInstance?.negativeHashtags}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="negativeHashtags-label" class="property-label">
					<g:message code="twitterSetup.negativeHashtags.label" default="negativeHashtags" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="negativeHashtags-label"><g:fieldValue bean="${twitterSetupInstance}" field="negativeHashtags"/></span>
					
					</div>
					</div>
				</g:if>
			</div>
			<g:form>
				<div class="clearfix form-actions">
					<div class="col-md-offset-3 col-md-9">
					    <g:hiddenField name="id" value="${twitterSetupInstance?.id}" />
						<g:link class="btn btn-info" action="edit" id="${twitterSetupInstance?.id}">
						<i class="fa fa-ok bigger-110"></i>
						<g:message code="default.button.edit.label" default="Edit" />
					</g:link>
						&nbsp; &nbsp;
						<g:actionSubmit class="btn btn-reverse" action="delete"
						value="${message(code: 'default.button.delete.label', default: 'Delete')}"
						onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</div>
				</div>
			</g:form>
		</div>
	</div>
</body>
</html>
