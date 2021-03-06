
<%@ page import="com.prismanet.FacebookSetup" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="home">
		<g:set var="entityName" value="${message(code: 'facebookSetup.label', default: 'FacebookSetup')}" />
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
				<i class="ace-icon fa fa-arrow-right  bigger-110"></i>
			</g:link>
		</div>
		<hr>
		<div id="list-facebookSetup" class="content scaffold-list" role="main">
			<div class="table-header">
											<g:message code="default.list.label" args="[entityName]" />
			</div>
			<table class="table table-condensed table-bordered table-hover table-striped dataTable">
				<thead>
					<tr>
								<g:sortableColumn property="accounts" title="${message(code: 'facebookSetup.accounts.label', default: 'Accounts')}" />
					
						<g:sortableColumn property="keywords" title="${message(code: 'facebookSetup.keywords.label', default: 'Keywords')}" />
			
					</tr>
				</thead>
				<tbody>
				<g:each in="${facebookSetupInstanceList}" status="i" var="facebookSetupInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link action="show" id="${facebookSetupInstance.id}">
						${fieldValue(bean: facebookSetupInstance, field: "accounts")}</g:link></td>
					
						<td>${fieldValue(bean: facebookSetupInstance, field: "keywords")}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			
				<div class="col-lg-8" style="text-align: center;">
					<ul class="pagination pagination-sm">
						<li><g:paginate total="${facebookSetupInstanceTotal}" /></li>
					</ul>
			</div>
		</div>
		</div>
	</body>
</html>
