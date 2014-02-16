
<%@ page import="com.prismanet.Concept" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="home">
		<g:set var="entityName" value="${message(code: 'concept.label', default: 'Concept')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-concept" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-concept" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list concept">
			
				<g:if test="${conceptInstance?.conceptName}">
				<li class="fieldcontain">
					<span id="conceptName-label" class="property-label"><g:message code="concept.conceptName.label" default="Concept Name" /></span>
					
						<span class="property-value" aria-labelledby="conceptName-label"><g:fieldValue bean="${conceptInstance}" field="conceptName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${conceptInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="concept.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${conceptInstance?.user?.id}">${conceptInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${conceptInstance?.tweets}">
				<li class="fieldcontain">
					<span id="tweets-label" class="property-label"><g:message code="concept.tweets.label" default="Tweets" /></span>
					
						<g:each in="${conceptInstance.tweets}" var="t">
						<span class="property-value" aria-labelledby="tweets-label"><g:link controller="tweet" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${conceptInstance?.posts}">
				<li class="fieldcontain">
					<span id="posts-label" class="property-label"><g:message code="concept.posts.label" default="Posts" /></span>
					
						<g:each in="${conceptInstance.posts}" var="p">
						<span class="property-value" aria-labelledby="posts-label"><g:link controller="post" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${conceptInstance?.twitterSetup}">
				<li class="fieldcontain">
					<span id="twitterSetup-label" class="property-label"><g:message code="concept.twitterSetup.label" default="Twitter Setup" /></span>
					
						<span class="property-value" aria-labelledby="twitterSetup-label"><g:link controller="twitterSetup" action="show" id="${conceptInstance?.twitterSetup?.id}">${conceptInstance?.twitterSetup?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${conceptInstance?.facebookSetup}">
				<li class="fieldcontain">
					<span id="facebookSetup-label" class="property-label"><g:message code="concept.facebookSetup.label" default="Facebook Setup" /></span>
					
						<span class="property-value" aria-labelledby="facebookSetup-label"><g:link controller="facebookSetup" action="show" id="${conceptInstance?.facebookSetup?.id}">${conceptInstance?.facebookSetup?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${conceptInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="concept.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${conceptInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${conceptInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="concept.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${conceptInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${conceptInstance?.id}" />
					<g:link class="edit" action="edit" id="${conceptInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
