<%@ page import="com.prismanet.Concept"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="home">
<g:set var="entityName"
	value="${message(code: 'concept.label', default: 'Concept')}" />
<title><g:message code="default.create.label"
		args="[entityName]" /></title>
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>
				<g:message code="default.create.label" args="[entityName]" />

			</h1>
		</div>
		<div class="nav" role="navigation">
			<g:link class="btn btn-info btn-sm" action="list">
				<g:message code="default.list.label" args="[entityName]" />
				<i class="ace-icon fa fa-arrow-right bigger-110"></i>
			</g:link>
		</div>
		<hr>
		<div id="create-concept" class="content scaffold-create" role="main">
			<g:if test="${flash.message}">
			<div class="alert alert-info">
					<button type="button" class="close" data-dismiss="alert">
						<i class="ace-icon fa fa-remove"></i>
					</button>
				<div class="message" role="status">
					${flash.message}
				</div>
				<br>
				</div>
				
			</g:if>
			<g:hasErrors bean="${conceptInstance}">
			<div class="alert alert-block alert-danger">
				<button type="button" class="close" data-dismiss="alert">
					<i class="ace-icon fa fa-remove"></i>
				</button>
				<ul class="fa-ul" >
					<g:eachError bean="${conceptInstance}" var="error">
						<li 
							<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"
							</g:if>>
							<i class="fa-li ace-icon fa fa-times"></i> <g:message	error="${error}" />
						</li>
					</g:eachError>
				</ul>
			   <br>
			</div>
			</g:hasErrors>
			<g:form class="form-horizontal" action="save">
				<div class="form">

					<div class="tabbable">
						<ul class="nav nav-tabs padding-16">
							<li class="active"><a data-toggle="tab" href="#edit-basic">
									<i class="green fa fa-edit bigger-125"></i> <g:message
										code="form.new.concept" />
							</a></li>
						</ul>
						<g:render template="form" />
					</div>
				</div>


				<div class="clearfix form-actions">
					<div class="col-md-offset-3 col-md-9">
						<button class="btn btn-info" type="submit" name="create">
							<i class="ace-icon fa fa-check bigger-110"></i>
							<g:message code="default.button.create.label" default="Create" />

						</button>


					</div>
				</div>
			</g:form>
		</div>
	</div>

</body>
</html>
