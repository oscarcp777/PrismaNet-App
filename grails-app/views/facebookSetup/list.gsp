
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
									<i class="icon-double-angle-right"></i>
									<g:message code="default.list.label.and" />
								</small>
							</h1>
						</div>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-facebookSetup" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="table table-condensed table-bordered table-hover table-striped">
				<thead>
					<tr>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${facebookSetupInstanceList}" status="i" var="facebookSetupInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
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
