<%@ page import="com.prismanet.TwitterSetup"%>

<div class="tabbable">
	<ul class="nav nav-tabs padding-16">
		<li class="active"><a data-toggle="tab" href="#edit-basic"> <i
				class="green icon-edit bigger-125"></i> <g:message
					code="form.new.facebook" />
		</a></li>
	</ul>

	<div class="tab-content profile-edit-tab-content">
		<div id="edit-basic" class="tab-pane active">
			<h4 class="header blue bolder smaller">
				<g:message code="form.general" />
			</h4>

			<div
				class="form-group fieldcontain ${hasErrors(bean: twitterSetupInstance, field: 'includedAccounts', 'error')} ">
				<label class="col-sm-3 control-label no-padding-right"
					for="includedAccounts"> <g:message
						code="twitterSetup.includedAccounts.label"
						default="Included Accounts" />

				</label>
				<div class="col-sm-9">
						<span class="input-icon input-icon-right"> 
					<g:textField name="includedAccounts"
					value="${twitterSetupInstance?.includedAccounts}" />
						</span>
					</div>
				
			</div>

			<div
				class="form-group fieldcontain ${hasErrors(bean: twitterSetupInstance, field: 'excludedAccounts', 'error')} ">
				<label class="col-sm-3 control-label no-padding-right"
					for="excludedAccounts"> <g:message
						code="twitterSetup.excludedAccounts.label"
						default="Excluded Accounts" />

				</label>
				
					<div class="col-sm-9">
						<span class="input-icon input-icon-right"> 
					<g:textField name="excludedAccounts"
					value="${twitterSetupInstance?.excludedAccounts}" />
						</span>
					</div>
			</div>

			<div
				class="form-group fieldcontain ${hasErrors(bean: twitterSetupInstance, field: 'keywords', 'error')} ">
				<label class="col-sm-3 control-label no-padding-right"
					for="keywords"> <g:message
						code="twitterSetup.keywords.label" default="Keywords" />

				</label>
			
					<div class="col-sm-9">
						<span class="input-icon input-icon-right"> 
						<g:textField name="keywords"
					value="${twitterSetupInstance?.keywords}" />
						</span>
					</div>
			</div>


			<div class="space-4"></div>

		</div>
	</div>
</div>