
<%@ page import="com.prismanet.Mailing" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="home">
		<g:set var="entityName" value="${message(code: 'mailing.label', default: 'Mailing')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
	<div class="page-content">
	<div class="page-header">
							<h1>
								<g:message code="default.list.label" args="[entityName]" />
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									<g:message code="default.list.label.and" />
								</small>
							</h1>
		</div>
		<div class="nav" role="navigation">
			<g:link class="btn btn-sm btn-info" action="create">
				<g:message code="default.new.label" args="[entityName]" />
				<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
			</g:link>
		</div>
		<hr>
		<div id="list-mailing" class="content scaffold-list" role="main">
			<div class="table-header">
											<g:message code="default.list.label" args="[entityName]" />
			</div>
			<table class="table table-condensed table-bordered table-hover table-striped dataTable">
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
			
				<div class="col-lg-8" style="text-align: center;">
					<ul class="pagination pagination-sm">
						<li><g:paginate total="${mailingInstanceTotal}" /></li>
					</ul>
			</div>
		</div>
		</div>
	</body>
</html>
