
<%@ page import="com.prismanet.Concept" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="home">
		<g:set var="entityName" value="${message(code: 'concept.label', default: 'Concept')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
	<div class="page-content">
	<div class="page-header">
							<h1>
								<g:message code="default.list.label" args="[entityName]" />
								<small>
									<i class="icon-double-angle-right"></i>
									<g:message code="default.list.label.and" />
								</small>
							</h1>
		</div>
		<div class="nav" role="navigation">
			<g:link class="btn btn-sm btn-info" action="create">
				<g:message code="default.new.label" args="[entityName]" />
				<i class="icon-arrow-right icon-on-right bigger-110"></i>
			</g:link>
		</div>
		<hr>
		<div id="list-concept" class="content scaffold-list" role="main">
			<div class="table-header">
											<g:message code="default.list.label" args="[entityName]" />
			</div>
			<table class="table table-condensed table-bordered table-hover table-striped dataTable">
				<thead>
					<tr>
					
						<g:sortableColumn property="conceptName" title="${message(code: 'concept.conceptName.label', default: 'Concept Name')}" />
					
						<th><g:message code="concept.user.label" default="User" /></th>
					
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
					
						<td>${fieldValue(bean: conceptInstance, field: "user")}</td>
					
						<td>${fieldValue(bean: conceptInstance, field: "twitterSetup")}</td>
					
						<td>${fieldValue(bean: conceptInstance, field: "facebookSetup")}</td>
					
						<td><g:formatDate date="${conceptInstance.dateCreated}" /></td>
					
						<td><g:formatDate date="${conceptInstance.lastUpdated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			
				<div class="col-lg-8" style="text-align: center;">
					<ul class="pagination pagination-sm">
						<li><g:paginate total="${conceptInstanceTotal}" /></li>
					</ul>
			</div>
		</div>
		</div>
	</body>
</html>
