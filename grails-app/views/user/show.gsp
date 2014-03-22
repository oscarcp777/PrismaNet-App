

<%@ page import="com.prismanet.User" %>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="home">
<g:set var="entityName"
	value="${message(code: 'user.label', default: 'User')}" />
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
				<i class="fa fa-arrow-right icon-on-right bigger-110"></i>
			</g:link>
			<g:link class="btn btn-info btn-sm" action="create">
				<g:message code="default.new.label" args="[entityName]" />
				<i class="fa fa-arrow-right icon-on-right bigger-110"></i>
			</g:link>
		</div>
		<hr>
		<div id="show-user"
			class="content scaffold-show" role="main">
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div
				class="profile-user-info profile-user-info-striped property-list user">
				
				<g:if test="${userInstance?.username}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="username-label" class="property-label">
					<g:message code="user.username.label" default="Username" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${userInstance}" field="username"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${userInstance?.password}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="password-label" class="property-label">
					<g:message code="user.password.label" default="Password" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="password-label"><g:fieldValue bean="${userInstance}" field="password"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${userInstance?.firstName}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="firstName-label" class="property-label">
					<g:message code="user.firstName.label" default="First Name" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${userInstance}" field="firstName"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${userInstance?.lastName}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="lastName-label" class="property-label">
					<g:message code="user.lastName.label" default="Last Name" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${userInstance}" field="lastName"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${userInstance?.account}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="account-label" class="property-label">
					<g:message code="user.account.label" default="Account" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="account-label"><g:link controller="accountType" action="show" id="${userInstance?.account?.id}">${userInstance?.account?.encodeAsHTML()}</g:link></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${userInstance?.accountExpired}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="accountExpired-label" class="property-label">
					<g:message code="user.accountExpired.label" default="Account Expired" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="accountExpired-label"><g:formatBoolean boolean="${userInstance?.accountExpired}" /></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${userInstance?.accountLocked}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="accountLocked-label" class="property-label">
					<g:message code="user.accountLocked.label" default="Account Locked" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="accountLocked-label"><g:formatBoolean boolean="${userInstance?.accountLocked}" /></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${userInstance?.concepts}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="concepts-label" class="property-label">
					<g:message code="user.concepts.label" default="Concepts" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<g:each in="${userInstance.concepts}" var="c">
						<span class="property-value" aria-labelledby="concepts-label"><g:link controller="concept" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${userInstance?.dateCreated}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="dateCreated-label" class="property-label">
					<g:message code="user.dateCreated.label" default="Date Created" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${userInstance?.dateCreated}" /></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${userInstance?.enabled}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="enabled-label" class="property-label">
					<g:message code="user.enabled.label" default="Enabled" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="enabled-label"><g:formatBoolean boolean="${userInstance?.enabled}" /></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${userInstance?.passwordExpired}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="passwordExpired-label" class="property-label">
					<g:message code="user.passwordExpired.label" default="Password Expired" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="passwordExpired-label"><g:formatBoolean boolean="${userInstance?.passwordExpired}" /></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${userInstance?.twitterAccounts}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="twitterAccounts-label" class="property-label">
					<g:message code="user.twitterAccounts.label" default="Twitter Accounts" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<g:each in="${userInstance.twitterAccounts}" var="t">
						<span class="property-value" aria-labelledby="twitterAccounts-label"><g:link controller="twitterAccount" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
					
					</div>
					</div>
				</g:if>
			
			</div>
			<g:form>
				<div class="clearfix form-actions">
					<div class="col-md-offset-3 col-md-9">
					    <g:hiddenField name="id" value="${userInstance?.id}" />
						<g:link class="btn btn-info" action="edit" id="${userInstance?.id}">
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
