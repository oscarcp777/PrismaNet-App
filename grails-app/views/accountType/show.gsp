

<%@ page import="com.prismanet.AccountType" %>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="home">
<g:set var="entityName"
	value="${message(code: 'accountType.label', default: 'AccountType')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>
				<g:message code="default.show.label" args="[entityName]" />

			</h1>
		</div>
		<div class="nav" role="navigation">
			<g:link class="btn btn-info btn-sm" action="list">
				<g:message code="default.list.label" args="[entityName]" />
				<i class="fa fa-arrow-right  bigger-110"></i>
			</g:link>
			<g:link class="btn btn-info btn-sm" action="create">
				<g:message code="default.new.label" args="[entityName]" />
				<i class="fa fa-arrow-right bigger-110"></i>
			</g:link>
		</div>
		<hr>
		<div id="show-accountType"
			class="content scaffold-show" role="main">
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div
				class="profile-user-info profile-user-info-striped property-list accountType">
				
				<g:if test="${accountTypeInstance?.type}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="type-label" class="property-label">
					<g:message code="accountType.type.label" default="Type" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="type-label"><g:fieldValue bean="${accountTypeInstance}" field="type"/></span>
					
					</div>
					</div>
				</g:if>
			
			</div>
			<g:form>
				<div class="clearfix form-actions">
					<div class="col-md-offset-3 col-md-9">
					    <g:hiddenField name="id" value="${accountTypeInstance?.id}" />
						<g:link class="btn btn-info" action="edit" id="${accountTypeInstance?.id}">
						<i class="fa fa-ok bigger-110"></i>
						<g:message code="default.button.edit.label" default="Edit" />
					</g:link>
						&nbsp; &nbsp;
						<g:actionSubmit class="btn btn-reverse" action="delete"
						value="${message(code: 'default.button.delete.label', default: 'Delete')}"
						onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</div>
				</div>
			</g:form>
		</div>
	</div>
</body>
</html>
