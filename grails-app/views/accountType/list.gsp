
<%@ page import="com.prismanet.AccountType" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="home">
		<g:set var="entityName" value="${message(code: 'accountType.label', default: 'AccountType')}" />
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
		<div id="list-accountType" class="content scaffold-list" role="main">
			<div class="table-header">
											<g:message code="default.list.label" args="[entityName]" />
			</div>
			<table class="table table-condensed table-bordered table-hover table-striped dataTable">
				<thead>
					<tr>
					
						<g:sortableColumn property="type" title="${message(code: 'accountType.type.label', default: 'Type')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${accountTypeInstanceList}" status="i" var="accountTypeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${accountTypeInstance.id}">${fieldValue(bean: accountTypeInstance, field: "type")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			
				<div class="col-lg-8" style="text-align: center;">
					<ul class="pagination pagination-sm">
						<li><g:paginate total="${accountTypeInstanceTotal}" /></li>
					</ul>
			</div>
		</div>
		</div>
	</body>
</html>
