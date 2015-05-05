<%@ page import="com.prismanet.TwitterSetup"%>



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
			<div class="form-group fieldcontain ${hasErrors(bean: twitterSetupInstance, field: 'neutralHashtags', 'error')} ">
				<label class="col-sm-3 control-label no-padding-right"
					for="neutralHashtags"> <g:message
						code="twitterSetup.neutralHashtags.label" default="neutralHashtags" />

				</label>
			
					<div class="col-sm-9">
						<span class="input-icon input-icon-right"> 
						<g:textField name="neutralHashtags"
					value="${twitterSetupInstance?.neutralHashtags}" />
						</span>
					</div>
			</div>
			<div
				class="form-group fieldcontain ${hasErrors(bean: twitterSetupInstance, field: 'positiveHashtags', 'error')} ">
				<label class="col-sm-3 control-label no-padding-right"
					for="positiveHashtags"> <g:message
						code="twitterSetup.positiveHashtags.label" default="positiveHashtags" />

				</label>
			
					<div class="col-sm-9">
						<span class="input-icon input-icon-right"> 
						<g:textField name="positiveHashtags"
					value="${twitterSetupInstance?.positiveHashtags}" />
						</span>
					</div>
			</div>
				<div
				class="form-group fieldcontain ${hasErrors(bean: twitterSetupInstance, field: 'negativeHashtags', 'error')} ">
				<label class="col-sm-3 control-label no-padding-right"
					for="negativeHashtags"> <g:message
						code="twitterSetup.negativeHashtags.label" default="negativeHashtags" />

				</label>
			
					<div class="col-sm-9">
						<span class="input-icon input-icon-right"> 
						<g:textField name="negativeHashtags"
					value="${twitterSetupInstance?.negativeHashtags}" />
						</span>
					</div>
			</div>

			<div class="space-4"></div>

		</div>
	</div>
