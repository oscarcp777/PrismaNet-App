<%@ page import="com.prismanet.Concept"%>
<%@ page import="com.prismanet.FacebookSetup"%>
<%@ page import="com.prismanet.TwitterSetup"%>



		<div class="tab-content profile-edit-tab-content">
			<div id="edit-basic" class="tab-pane active">
				<h4 class="header blue bolder smaller"><g:message code="form.general"/></h4>

				<div
					class="form-group fieldcontain ${hasErrors(bean: conceptInstance, field: 'conceptName', 'error')}">
					<label class="col-sm-3 control-label no-padding-right"
						for="conceptName"> <g:message
							code="concept.conceptName.label" default="Concept Name" />
					</label>

					<div class="col-sm-9">
						<span class="input-icon input-icon-right"> <g:textField
								name="conceptName" value="${conceptInstance?.conceptName}" />
						</span>
					</div>
				</div>

				<div class="space-4"></div>

				<div
					class="form-group fieldcontain ${hasErrors(bean: conceptInstance, field: 'facebookSetup', 'error')}">
					<label class="col-sm-3 control-label no-padding-right" for="user">
						<g:message code="concept.user.label" default="User" />
					</label>

					<div class="col-sm-9">
						<span class="input-icon input-icon-right"> 
						<g:select
								class="form-control" id="user" name="user.id"
								from="${com.prismanet.User.list()}" optionKey="id"
								value="${conceptInstance?.user?.id}" 
								noSelection="['null': '']" />
						</span>
					</div>
				</div>



				<div class="space"></div>
				<h4 class="header blue bolder smaller">Facebook</h4>

				<div class="form-group fieldcontain ${hasErrors(bean: facebookSetup, field: 'keywords', 'error')} ">
				<label class="col-sm-3 control-label no-padding-right" for="keywords">
					<g:message code="facebookSetup.keywords.label" default="Keywords" />
	
				</label>
	
				<div class="col-sm-9">
					<span class="input-icon input-icon-right"> <g:textField
							name="keywords" value="${conceptInstance?.facebookSetup?.keywords}" />
					</span>
				</div>
			 </div>

				<div class="space-4"></div>

								<div class="space"></div>
				<h4 class="header blue bolder smaller">Twitter</h4>
			<div
				class="form-group fieldcontain ${hasErrors(bean: twitterSetup, field: 'includedAccounts', 'error')} ">
				<label class="col-sm-3 control-label no-padding-right"
					for="includedAccounts"> <g:message
						code="twitterSetup.includedAccounts.label"
						default="Included Accounts" />

				</label>
				<div class="col-sm-9">
						<span class="input-icon input-icon-right"> 
					<g:textField name="includedAccounts"
					value="${conceptInstance?.twitterSetup?.includedAccounts}" />
						</span>
					</div>
				
			</div>

			<div
				class="form-group fieldcontain ${hasErrors(bean: twitterSetup, field: 'excludedAccounts', 'error')} ">
				<label class="col-sm-3 control-label no-padding-right"
					for="excludedAccounts"> <g:message
						code="twitterSetup.excludedAccounts.label"
						default="Excluded Accounts" />

				</label>
				
					<div class="col-sm-9">
						<span class="input-icon input-icon-right"> 
					<g:textField name="excludedAccounts"
					value="${conceptInstance?.twitterSetup?.excludedAccounts}" />
						</span>
					</div>
			</div>

			<div
				class="form-group fieldcontain ${hasErrors(bean: twitterSetup, field: 'keywords', 'error')} ">
				<label class="col-sm-3 control-label no-padding-right"
					for="keywords"> <g:message
						code="twitterSetup.keywords.label" default="Keywords" />

				</label>
			
					<div class="col-sm-9">
						<span class="input-icon input-icon-right"> 
						<g:textField name="keywords"
					value="${conceptInstance?.twitterSetup?.keywords}" />
						</span>
					</div>
			</div>
			<div class="form-group fieldcontain ${hasErrors(bean: twitterSetup, field: 'neutralHashtags', 'error')} ">
				<label class="col-sm-3 control-label no-padding-right"
					for="neutralHashtags"> <g:message
						code="twitterSetup.neutralHashtags.label" default="neutralHashtags" />

				</label>
			
					<div class="col-sm-9">
						<span class="input-icon input-icon-right"> 
						<g:textField name="neutralHashtags"
					value="${conceptInstance?.twitterSetup?.neutralHashtags}" />
						</span>
					</div>
			</div>
			<div
				class="form-group fieldcontain ${hasErrors(bean: twitterSetup, field: 'positiveHashtags', 'error')} ">
				<label class="col-sm-3 control-label no-padding-right"
					for="positiveHashtags"> <g:message
						code="twitterSetup.positiveHashtags.label" default="positiveHashtags" />

				</label>
			
					<div class="col-sm-9">
						<span class="input-icon input-icon-right"> 
						<g:textField name="positiveHashtags"
					value="${conceptInstance?.twitterSetup?.positiveHashtags}" />
						</span>
					</div>
			</div>
				<div
				class="form-group fieldcontain ${hasErrors(bean: twitterSetup, field: 'negativeHashtags', 'error')} ">
				<label class="col-sm-3 control-label no-padding-right"
					for="negativeHashtags"> <g:message
						code="twitterSetup.negativeHashtags.label" default="negativeHashtags" />

				</label>
			
					<div class="col-sm-9">
						<span class="input-icon input-icon-right"> 
						<g:textField name="negativeHashtags"
					value="${conceptInstance?.twitterSetup?.negativeHashtags}" />
						</span>
					</div>
			</div>



			</div>
	</div>
				
