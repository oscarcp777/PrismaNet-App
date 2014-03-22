<%@ page import="com.prismanet.FacebookSetup" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="home">
		<g:set var="entityName" value="${message(code: 'facebookSetup.label', default: 'FacebookSetup')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="page-content">
	<div class="page-header">
							<h1>
								<g:message code="default.create.label" args="[entityName]" />
								
							</h1>
						</div>
		<div class="nav" role="navigation">
			<g:link  class="btn btn-info btn-sm" action="list" ><g:message code="default.list.label" args="[entityName]" />
			<i class="fa fa-arrow-right icon-on-right bigger-110"></i>
			</g:link>
		</div>
		<hr>
		<div id="create-facebookSetup" class="content scaffold-create" role="main">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${facebookSetupInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${facebookSetupInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form class="form-horizontal" action="save" >
				<div class="form">
					<g:render template="form"/>
				</div>
				<div class="buttons">
				<div class="clearfix form-actions">
					<div class="col-md-offset-3 col-md-9">
					<g:submitButton name="create" class="btn btn-info" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</div>
				</div>
				</div>
			</g:form>
		</div>
		</div>
	</body>
</html>
