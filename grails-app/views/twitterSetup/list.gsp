
<%@ page import="com.prismanet.TwitterSetup" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="home">
		<g:set var="entityName" value="${message(code: 'twitterSetup.label', default: 'TwitterSetup')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
	<div class="page-content">
	<div class="page-header">
							<h1>
								<g:message code="default.list.label" args="[entityName]" />
								<small>
									<i class="fa fa-angle-double-right"></i>
									<g:message code="default.list.label.and" />
								</small>
							</h1>
		</div>
		<div class="nav" role="navigation">
			<g:link class="btn btn-sm btn-info" action="create">
				<g:message code="default.new.label" args="[entityName]" />
				<i class="fa fa-arrow-right  bigger-110"></i>
			</g:link>
		</div>
		<hr>
		<div id="list-twitterSetup" class="content scaffold-list" role="main">
			<div class="table-header">
											<g:message code="default.list.label" args="[entityName]" />
			</div>
			<table class="table table-condensed table-bordered table-hover table-striped dataTable">
				<thead>
					<tr>
					
						<g:sortableColumn property="includedAccounts" title="${message(code: 'twitterSetup.includedAccounts.label', default: 'Included Accounts')}" />
					
						<g:sortableColumn property="excludedAccounts" title="${message(code: 'twitterSetup.excludedAccounts.label', default: 'Excluded Accounts')}" />
					
						<g:sortableColumn property="keywords" title="${message(code: 'twitterSetup.keywords.label', default: 'Keywords')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${twitterSetupInstanceList}" status="i" var="twitterSetupInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${twitterSetupInstance.id}">${fieldValue(bean: twitterSetupInstance, field: "includedAccounts")}</g:link></td>
					
						<td>${fieldValue(bean: twitterSetupInstance, field: "excludedAccounts")}</td>
					
						<td>${fieldValue(bean: twitterSetupInstance, field: "keywords")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			
				<div class="col-lg-8" style="text-align: center;">
					<ul class="pagination pagination-sm">
						<li><g:paginate total="${twitterSetupInstanceTotal}" /></li>
					</ul>
			</div>
		</div>
		</div>
	</body>
</html>
