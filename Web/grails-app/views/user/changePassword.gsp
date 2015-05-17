<%@ page import="com.prismanet.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="home">
	</head>
	<body>
	<div class="page-content">
	<div class="page-header">
		<h1>
		<i class="ace-icon fa fa-key"></i>
			<g:message code="user.change.password.title" />
			
		</h1>
	</div>

			<g:if test="${flash.message}">
				<div class="alert alert-block alert-danger">
					<button type="button" class="close" data-dismiss="alert">
						<i class="ace-icon fa fa-remove"></i>
					</button>
					<i class="ace-ion fa fa-check icon.lg"></i>
					${flash.message}
					<br>
				</div>

			</g:if>
	<g:form class="form-horizontal" action="updatePassword" >
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="username">
				<g:message code="user.username.label" default="Username" />
			</label>
			<div class="col-sm-9">
				 <p class="form-control-static">
				 <g:fieldValue bean="${user}" field="username"/>
				 </p>
				
			</div>
		</div>

		<div class="form-group  ">
			<label class="col-sm-3 control-label no-padding-right" for="password">
				<g:message code="user.change.password.current"  />
			</label>
			<div class="col-sm-4">
				<span class="input-icon">
					<g:passwordField name="password" class="form-control" value="" placeholder="${message(code: 'user.change.password.current')}"/>
					<i class="ace-icon fa fa-key blue"></i>
				</span>
			</div>
		</div>
		<div class="form-group  ">
			<label class="col-sm-3 control-label no-padding-right" for="password">
				<g:message code="user.change.password.new"  />
			</label>
			<div class="col-sm-4">
				<span class="input-icon">
					<g:passwordField name="passwordNew" class="form-control" value="" placeholder="${message(code: 'user.change.password.new')}"/>
					<i class="ace-icon fa fa-key blue"></i>
				</span>
			</div>
		</div>
		<div class="form-group  ">
			<label class="col-sm-3 control-label no-padding-right" for="password">
				<g:message code="user.change.password.confirm"  />
			</label>
			<div class="col-sm-4">
				<span class="input-icon">
					<g:passwordField name="passwordConfirm" class="form-control" value="" placeholder="${message(code: 'user.change.password.confirm')}"/>
					<i class="ace-icon fa fa-key blue"></i>
				</span>
			</div>
		</div>
				<div class="clearfix form-actions">
					<div class="col-md-offset-5 col-md-9">
						<button class="btn btn-info" type="submit" name="changePassword">
							<i class="ace-icon fa fa-key bigger-110"></i>
							<g:message code="user.change.password.title"  />
						</button>
					</div>
				</div>
			</g:form>
		</div>
	</body>
</html>
