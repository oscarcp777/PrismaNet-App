
<%@ page import="com.prismanet.sentiment.Opinion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="home">
		<g:set var="entityName" value="${message(code: 'opinion.label', default: 'Opinion')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-opinion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-opinion" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="table table-condensed table-bordered table-hover table-striped">
				<thead>
					<tr>
					
						<th><g:message code="opinion.user.label" default="User" /></th>
					
						<th><g:message code="opinion.tweet.label" default="Tweet" /></th>
					
						<th><g:message code="opinion.concept.label" default="Concept" /></th>
					
						<g:sortableColumn property="value" title="${message(code: 'opinion.value.label', default: 'Value')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${opinionInstanceList}" status="i" var="opinionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${opinionInstance.id}">${fieldValue(bean: opinionInstance, field: "user")}</g:link></td>
					
						<td>${fieldValue(bean: opinionInstance, field: "tweet")}</td>
					
						<td>${fieldValue(bean: opinionInstance, field: "concept")}</td>
					
						<td>${fieldValue(bean: opinionInstance, field: "value")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${opinionInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
