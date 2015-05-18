<%@ page import="com.prismanet.Concept" %>
<!DOCTYPE html>
<html lang="es">
	<head>
		<meta name="layout" content="home">
	</head>
	<body>
	<div class="page-content">
		<div class="page-header">
			<h1>
				<g:message code="concept.user.advance.edit"  />

			</h1>
		</div>
		<div class="nav" role="navigation">
			<g:link class="btn btn-white btn-info btn-bold btn-round" action="listAdvance">
				<i class="ace-icon fa fa-list-ol bigger-120 blue"></i> 
				<g:message code="concept.user.advance.go.list"/> <i class="ace-icon fa fa-arrow-right bigger-120 blue"></i> 
			</g:link>
			<g:link class="btn btn-white btn-info btn-bold btn-round" action="createAdvance">
				<i class="ace-icon fa fa-cloud-download bigger-120 blue"></i> 
				<g:message code="concept.user.advance.create"/>
				<i class="ace-icon fa fa-arrow-right bigger-120 blue"></i> 
			</g:link>
			
		</div>
		<hr>
		<div id="edit-concept" class="content scaffold-edit" role="main">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${conceptInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${conceptInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form  class="form-horizontal" action="updateAdvance">
				<g:hiddenField name="id" value="${conceptInstance?.id}" />
				<g:hiddenField name="version" value="${conceptInstance?.version}" />
				<div class="form">
				
					<g:render template="formAdvance"/>
				</div>
				<div class="clearfix form-actions">
					<div class="col-md-offset-3 col-md-9">
					<button class="btn btn-info" type="submit" name="updateAdvance">
							<i class="ace-icon fa fa-check bigger-110"></i>
					<g:message code="default.button.update.label" default="Create" />
				</button>
				
				
				</div>
				</div>
			</g:form>
		</div>
		</div>
	</body>
</html>
