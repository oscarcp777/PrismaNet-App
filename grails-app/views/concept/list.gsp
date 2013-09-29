
<%@ page import="com.prismanet.Concept" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="home">
		<g:set var="entityName" value="${message(code: 'concept.label', default: 'Concept')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-concept" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-concept" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="table table-condensed table-bordered table-hover table-striped">
				<thead>
					<tr>
					
						<g:sortableColumn property="conceptName" title="${message(code: 'concept.conceptName.label', default: 'Concept Name')}" />
					
						<th><g:message code="concept.twitterSetup.label" default="Twitter Setup" /></th>
					
						<th><g:message code="concept.facebookSetup.label" default="Facebook Setup" /></th>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'concept.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'concept.lastUpdated.label', default: 'Last Updated')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${conceptInstanceList}" status="i" var="conceptInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${conceptInstance.id}">${fieldValue(bean: conceptInstance, field: "conceptName")}</g:link></td>
					
						<td>${fieldValue(bean: conceptInstance, field: "twitterSetup")}</td>
					
						<td>${fieldValue(bean: conceptInstance, field: "facebookSetup")}</td>
					
						<td><g:formatDate date="${conceptInstance.dateCreated}" /></td>
					
						<td><g:formatDate date="${conceptInstance.lastUpdated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${conceptInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
