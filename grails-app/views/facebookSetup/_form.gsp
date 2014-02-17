<%@ page import="com.prismanet.FacebookSetup" %>

<div class="tabbable">
	<ul class="nav nav-tabs padding-16">
		<li class="active"><a data-toggle="tab" href="#edit-basic"> <i
				class="green icon-edit bigger-125"></i> <g:message code="form.new.facebook"/>
		</a></li>
	</ul>

	<div class="tab-content profile-edit-tab-content">
		<div id="edit-basic" class="tab-pane active">
			<h4 class="header blue bolder smaller"><g:message code="form.general"/></h4>

<div class="form-group fieldcontain ${hasErrors(bean: facebookSetupInstance, field: 'keywords', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="keywords">
		<g:message code="facebookSetup.keywords.label" default="Keywords" />
		
	</label>
	<div class="col-sm-9">
						<span class="input-icon input-icon-right"> 
						<g:select class="form-control" name="keywords" from="${com.prismanet.Keyword.list()}" multiple="multiple" optionKey="id" size="5" value="${facebookSetupInstance?.keywords*.id}" />

						</span>
					</div>
	</div>

<div class="space-4"></div>

		</div>
	</div>
	</div>