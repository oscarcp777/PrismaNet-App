<%@ page import="com.prismanet.Concept" %>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="home">
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>
				<i class="ace-icon fa fa-cloud-download"></i> <g:message code="concept.user.advance.show" />
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
			<g:if test="${flash.message}">
				<div class="alert alert-block alert-success">
					<button type="button" class="close" data-dismiss="alert">
						<i class="ace-icon fa fa-remove"></i>
					</button>
					<div class="message" role="status">
						${flash.message}
					</div>
					<br>
				</div>

			</g:if>
		<div class="row">
		   <div class="col-md-8">
			<div class="profile-user-info profile-user-info-striped property-list concept">
				
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
				<g:if test="${conceptInstance?.dateCreated}">
				   <div class="profile-info-row">
					<div class="profile-info-name"><span id="dateCreated-label" class="property-label">
					<g:message code="concept.dateCreated.label" default="Date Created" />
				    </span>
					</div>
				   <div class="profile-info-value">
						<span class="property-value" aria-labelledby="dateCreated-label">
						<g:formatDate date="${conceptInstance?.dateCreated}" format="dd/MM/yyyy HH:mm"/>
						</span>
					</div>
					</div>
				</g:if>
				<g:if test="${conceptInstance?.country}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="conceptName-label" class="property-label">
					<g:message code="concept.country.label" default="Concept Name" />
				    </span>
					</div>
				    <div class="profile-info-value">
						<span class="property-value" aria-labelledby="country-label"><g:fieldValue bean="${conceptInstance}" field="country"/></span>
					</div>
					</div>
				</g:if>	
				<g:if test="${conceptInstance?.lang}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="conceptName-label" class="property-label">
					<g:message code="concept.lang.label" default="Concept Name" />
				    </span>
					</div>
				    <div class="profile-info-value">
						<span class="property-value" aria-labelledby="country-label"><g:fieldValue bean="${conceptInstance}" field="lang"/></span>
					</div>
					</div>
				</g:if>	
		    </div>
		<g:if test="${conceptInstance?.facebookSetup}">
		<h4 class="header blue bolder smaller">Facebook</h4>	
		<div class="profile-user-info profile-user-info-striped property-list concept">			
				
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="twitterSetup-label" class="property-label">
					<g:message code="facebookSetup.keywords.label" default="Keywords" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="twitterSetup-label">
						
						<g:link class="btn btn-xs btn-info" controller="twitterSetup" action="show" id="${conceptInstance?.twitterSetup?.id}">${conceptInstance?.twitterSetup?.encodeAsHTML()}
						<i class="ace-icon fa fa-arrow-right "></i>
						</g:link>
						</span>
					
					</div>
					</div>
				
		</div>
		</g:if>
	<g:if test="${conceptInstance?.facebookSetup}">
				<h4 class="header blue bolder smaller">Twitter</h4>	
				<div class="profile-user-info profile-user-info-striped property-list concept">		
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="facebookSetup-label" class="property-label">
					<g:message code="concept.facebookSetup.label" default="Facebook Setup" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="facebookSetup-label">
						<g:link class="btn btn-xs btn-info" controller="facebookSetup" action="show" id="${conceptInstance?.facebookSetup?.id}">${conceptInstance?.facebookSetup?.encodeAsHTML()}
						<i class="ace-icon fa fa-arrow-right "></i>
						</g:link></span>
					
					</div>
					</div>
			</div>
	</g:if>
	</div>
</div>

			
				<div class="clearfix form-actions">
					<div class="col-md-offset-3 col-md-9">
					    <g:hiddenField name="id" value="${conceptInstance?.id}" />
						<g:link class="btn btn-info" action="editAdvance" id="${conceptInstance?.id}">
						<i class="ace-icon fa fa-check bigger-110"></i>
						<g:message code="default.button.edit.label" default="Edit" />
					</g:link>
				</div>
		</div>
</div>
</body>
</html>
