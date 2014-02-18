

<%@ page import="com.prismanet.Concept" %>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="home">
<g:set var="entityName"
	value="${message(code: 'concept.label', default: 'Concept')}" />
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
				<i class="icon-arrow-right icon-on-right bigger-110"></i>
			</g:link>
			<g:link class="btn btn-info btn-sm" action="create">
				<g:message code="default.new.label" args="[entityName]" />
				<i class="icon-arrow-right icon-on-right bigger-110"></i>
			</g:link>
		</div>
		<hr>
		<div id="show-concept"
			class="content scaffold-show" role="main">
			<g:if test="${flash.message}">
				<div class="alert alert-block alert-success">
					<button type="button" class="close" data-dismiss="alert">
						<i class="icon-remove"></i>
					</button>
					<div class="message" role="status">
						${flash.message}
					</div>
					<br>
				</div>

			</g:if>
			<div
				class="profile-user-info profile-user-info-striped property-list concept">
				
				<g:if test="${conceptInstance?.conceptName}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="conceptName-label" class="property-label">
					<g:message code="concept.conceptName.label" default="Concept Name" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="conceptName-label"><g:fieldValue bean="${conceptInstance}" field="conceptName"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${conceptInstance?.user}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="user-label" class="property-label">
					<g:message code="concept.user.label" default="User" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="user-label">
						<g:link class="btn btn-xs btn-info" controller="user" action="show" id="${conceptInstance?.user?.id}">${conceptInstance?.user?.encodeAsHTML()}
						<i class="icon-arrow-right icon-on-right"></i>
						</g:link></span>
					
					</div>
					</div>
				</g:if>
			
				
			
				<g:if test="${conceptInstance?.twitterSetup}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="twitterSetup-label" class="property-label">
					<g:message code="concept.twitterSetup.label" default="Twitter Setup" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="twitterSetup-label">
						
						<g:link class="btn btn-xs btn-info" controller="twitterSetup" action="show" id="${conceptInstance?.twitterSetup?.id}">${conceptInstance?.twitterSetup?.encodeAsHTML()}
						<i class="icon-arrow-right icon-on-right"></i>
						</g:link></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${conceptInstance?.facebookSetup}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="facebookSetup-label" class="property-label">
					<g:message code="concept.facebookSetup.label" default="Facebook Setup" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="facebookSetup-label">
						<g:link class="btn btn-xs btn-info" controller="facebookSetup" action="show" id="${conceptInstance?.facebookSetup?.id}">${conceptInstance?.facebookSetup?.encodeAsHTML()}
						<i class="icon-arrow-right icon-on-right"></i>
						</g:link></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${conceptInstance?.dateCreated}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="dateCreated-label" class="property-label">
					<g:message code="concept.dateCreated.label" default="Date Created" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${conceptInstance?.dateCreated}" /></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${conceptInstance?.lastUpdated}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="lastUpdated-label" class="property-label">
					<g:message code="concept.lastUpdated.label" default="Last Updated" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${conceptInstance?.lastUpdated}" /></span>
					
					</div>
					</div>
				</g:if>
			
			</div>
			<g:form>
				<div class="clearfix form-actions">
					<div class="col-md-offset-3 col-md-9">
					    <g:hiddenField name="id" value="${conceptInstance?.id}" />
						<g:link class="btn btn-info" action="edit" id="${conceptInstance?.id}">
						<i class="icon-ok bigger-110"></i>
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
