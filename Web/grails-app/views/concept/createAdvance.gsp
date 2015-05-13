<%@ page import="com.prismanet.Concept"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="home">
<g:set var="entityName" value="${message(code: 'concept.label', default: 'Concept')}" />
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>
				<i class="ace-icon fa fa-cloud-download"></i> <g:message code="default.create.label" args="[entityName]" />

			</h1>
		</div>
		<div class="nav" role="navigation">
			<g:link class="btn btn-white btn-info btn-bold btn-round" action="listAdvance">
				
				<i class="ace-icon fa fa-list-ol bigger-120 blue"></i> 
				<g:message code="concept.user.advance.go.list"/> <i class="ace-icon fa fa-arrow-right bigger-120 blue"></i> 
			</g:link>
		</div>
		<hr>
		
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
				<ul class="errors" role="alert">
					<g:eachError bean="${conceptInstance}" var="error">
						<li
							<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
								error="${error}" /></li>
					</g:eachError>
				</ul>
			   <br>
			</div>
			</g:hasErrors>
			<g:form class="form-horizontal" action="save">
				
						<g:render template="formAdvance" />
					


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
<script type="text/javascript">
	$(function() {
	$('textarea[class*=autosize]').autosize({append: "\n"});
	});
</script>
</body>
</html>