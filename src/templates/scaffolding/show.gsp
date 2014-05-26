
<% import grails.persistence.Event %>
<%=packageName%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="home">
<g:set var="entityName"
	value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
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
				<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
			</g:link>
			<g:link class="btn btn-info btn-sm" action="create">
				<g:message code="default.new.label" args="[entityName]" />
				<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
			</g:link>
		</div>
		<hr>
		<div id="show-${domainClass.propertyName}"
			class="content scaffold-show" role="main">
			<g:if test="\${flash.message}">
				<div class="message" role="status">\${flash.message}</div>
			</g:if>
			<div
				class="profile-user-info profile-user-info-striped property-list ${domainClass.propertyName}">
				<%  excludedProps = Event.allEvents.toList() << 'id' << 'version'
				allowedNames = domainClass.persistentProperties*.name << 'dateCreated' << 'lastUpdated'
				props = domainClass.properties.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) }
				Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
				props.each { p -> %>
				<g:if test="\${${propertyName}?.${p.name}}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="${p.name}-label" class="property-label">
					<g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" />
				    </span>
					</div>
				<div class="profile-info-value">
					<%  if (p.isEnum()) { %>
						<span class="property-value" aria-labelledby="${p.name}-label"><g:fieldValue bean="\${${propertyName}}" field="${p.name}"/></span>
					<%  } else if (p.oneToMany || p.manyToMany) { %>
						<g:each in="\${${propertyName}.${p.name}}" var="${p.name[0]}">
						<span class="property-value" aria-labelledby="${p.name}-label"><g:link controller="${p.referencedDomainClass?.propertyName}" action="show" id="\${${p.name[0]}.id}">\${${p.name[0]}?.encodeAsHTML()}</g:link></span>
						</g:each>
					<%  } else if (p.manyToOne || p.oneToOne) { %>
						<span class="property-value" aria-labelledby="${p.name}-label"><g:link controller="${p.referencedDomainClass?.propertyName}" action="show" id="\${${propertyName}?.${p.name}?.id}">\${${propertyName}?.${p.name}?.encodeAsHTML()}</g:link></span>
					<%  } else if (p.type == Boolean || p.type == boolean) { %>
						<span class="property-value" aria-labelledby="${p.name}-label"><g:formatBoolean boolean="\${${propertyName}?.${p.name}}" /></span>
					<%  } else if (p.type == Date || p.type == java.sql.Date || p.type == java.sql.Time || p.type == Calendar) { %>
						<span class="property-value" aria-labelledby="${p.name}-label"><g:formatDate date="\${${propertyName}?.${p.name}}" /></span>
					<%  } else if (!p.type.isArray()) { %>
						<span class="property-value" aria-labelledby="${p.name}-label"><g:fieldValue bean="\${${propertyName}}" field="${p.name}"/></span>
					<%  } %>
					</div>
					</div>
				</g:if>
			<%  } %>
			</div>
			<g:form>
				<div class="clearfix form-actions">
					<div class="col-md-offset-3 col-md-9">
					    <g:hiddenField name="id" value="\${${propertyName}?.id}" />
						<g:link class="btn btn-info" action="edit" id="\${${propertyName}?.id}">
						<i class="ace-icon fa fa-ok bigger-110"></i>
						<g:message code="default.button.edit.label" default="Edit" />
					</g:link>
						&nbsp; &nbsp;
						<g:actionSubmit class="btn btn-reverse" action="delete"
						value="\${message(code: 'default.button.delete.label', default: 'Delete')}"
						onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</div>
				</div>
			</g:form>
		</div>
	</div>
</body>
</html>
