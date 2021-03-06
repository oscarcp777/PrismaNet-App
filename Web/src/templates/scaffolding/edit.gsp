<%=packageName%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="home">
		<g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
	<div class="page-content">
		<div class="page-header">
			<h1>
				<g:message code="default.edit.label" args="[entityName]" />

			</h1>
		</div>
		<div class="nav" role="navigation">
			<g:link class="btn btn-info btn-sm" action="list">
				<g:message code="default.list.label" args="[entityName]" />
				<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
			</g:link>
			<g:link class="btn btn-info btn-sm" action="create">
				<g:message code="default.new.label" args="[entityName]" />
				<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
			</g:link>
			
			
		</div>
		<hr>
		<div id="edit-${domainClass.propertyName}" class="content scaffold-edit" role="main">
			<g:if test="\${flash.message}">
			<div class="message" role="status">\${flash.message}</div>
			</g:if>
			<g:hasErrors bean="\${${propertyName}}">
			<ul class="errors" role="alert">
				<g:eachError bean="\${${propertyName}}" var="error">
				<li <g:if test="\${error in org.springframework.validation.FieldError}">data-field-id="\${error.field}"</g:if>><g:message error="\${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" <%= multiPart ? ' enctype="multipart/form-data"' : '' %>>
				<g:hiddenField name="id" value="\${${propertyName}?.id}" />
				<g:hiddenField name="version" value="\${${propertyName}?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<div class="clearfix form-actions">
					<div class="col-md-offset-3 col-md-9">
					<g:actionSubmit class="btn btn-info" action="update" value="\${message(code: 'default.button.update.label', default: 'Update')}" />
					&nbsp; &nbsp;
					<g:actionSubmit class="btn btn-reverse" action="delete" value="\${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</div>
				</div>
			</g:form>
		</div>
		</div>
	</body>
</html>
