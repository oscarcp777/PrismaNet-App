
<%@ page import="com.prismanet.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="home">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
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
		<div id="list-user" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="table table-condensed table-bordered table-hover table-striped">
				<thead>
					<tr>
					
						<g:sortableColumn property="username" title="${message(code: 'user.username.label', default: 'Username')}" />
					
						<g:sortableColumn property="password" title="${message(code: 'user.password.label', default: 'Password')}" />
					
						<g:sortableColumn property="firstName" title="${message(code: 'user.firstName.label', default: 'First Name')}" />
					
						<g:sortableColumn property="lastName" title="${message(code: 'user.lastName.label', default: 'Last Name')}" />
					
						<th><g:message code="user.account.label" default="Account" /></th>
					
						<g:sortableColumn property="accountExpired" title="${message(code: 'user.accountExpired.label', default: 'Account Expired')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${userInstanceList}" status="i" var="userInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${userInstance.id}">${fieldValue(bean: userInstance, field: "username")}</g:link></td>
					
						<td>${fieldValue(bean: userInstance, field: "password")}</td>
					
						<td>${fieldValue(bean: userInstance, field: "firstName")}</td>
					
						<td>${fieldValue(bean: userInstance, field: "lastName")}</td>
					
						<td>${fieldValue(bean: userInstance, field: "account")}</td>
					
						<td><g:formatBoolean boolean="${userInstance.accountExpired}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			
			<div class="col-lg-8" style="text-align: center;">
					<ul class="pagination pagination-sm">
						<li><g:paginate total="${userInstanceTotal}" /></li>
					</ul>
			</div>
			
			
		</div>
		</div>
	</body>
</html>
