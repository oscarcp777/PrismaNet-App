
<%@ page import="com.prismanet.Mailing" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="home">
		<g:set var="entityName" value="${message(code: 'mailing.label', default: 'Mailing')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-mailing" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-mailing" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="postLink1" title="${message(code: 'mailing.postLink1.label', default: 'Post Link1')}" />
					
						<g:sortableColumn property="postPhoto1" title="${message(code: 'mailing.postPhoto1.label', default: 'Post Photo1')}" />
					
						<g:sortableColumn property="postLink2" title="${message(code: 'mailing.postLink2.label', default: 'Post Link2')}" />
					
						<g:sortableColumn property="postPhoto2" title="${message(code: 'mailing.postPhoto2.label', default: 'Post Photo2')}" />
					
						<g:sortableColumn property="postLink3" title="${message(code: 'mailing.postLink3.label', default: 'Post Link3')}" />
					
						<g:sortableColumn property="postPhoto3" title="${message(code: 'mailing.postPhoto3.label', default: 'Post Photo3')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${mailingInstanceList}" status="i" var="mailingInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${mailingInstance.id}">${fieldValue(bean: mailingInstance, field: "postLink1")}</g:link></td>
					
						<td>${fieldValue(bean: mailingInstance, field: "postPhoto1")}</td>
					
						<td>${fieldValue(bean: mailingInstance, field: "postLink2")}</td>
					
						<td>${fieldValue(bean: mailingInstance, field: "postPhoto2")}</td>
					
						<td>${fieldValue(bean: mailingInstance, field: "postLink3")}</td>
					
						<td>${fieldValue(bean: mailingInstance, field: "postPhoto3")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${mailingInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
