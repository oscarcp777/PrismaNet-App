<%@ page import="com.prismanet.AccountType"%>

<div class="tabbable">
	<ul class="nav nav-tabs padding-16">
		<li class="active"><a data-toggle="tab" href="#edit-basic"> <i
				class="green icon-edit bigger-125"></i> <g:message code="form.new.count"/>
		</a></li>
	</ul>

	<div class="tab-content profile-edit-tab-content">
		<div id="edit-basic" class="tab-pane active">
			<h4 class="header blue bolder smaller"><g:message code="form.general"/></h4>

			<div
				class="form-group fieldcontain ${hasErrors(bean: accountTypeInstance, field: 'type', 'error')} ">
				<label class="col-sm-3 control-label no-padding-right" for="type">
					<g:message code="accountType.type.label" default="Type" />

				</label>
				<div class="col-sm-9">
						<span class="input-icon input-icon-right"> 
						<g:textField name="type" value="${accountTypeInstance?.type}" />
						</span>
					</div>
				
			</div>
<div class="space-4"></div>

		</div>
	</div>
</div>