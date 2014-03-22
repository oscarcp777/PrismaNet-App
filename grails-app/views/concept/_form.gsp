<%@ page import="com.prismanet.Concept"%>




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
				<h4 class="header blue bolder smaller">Social</h4>

				<div
					class="form-group fieldcontain ${hasErrors(bean: conceptInstance, field: 'facebookSetup', 'error')}">
					<label class="col-sm-3 control-label no-padding-right"
						for="facebookSetup"> <g:message
							code="concept.facebookSetup.label" default="Facebook Setup" /></label>

					<div class="col-sm-9">
						<span class="input-icon"> <g:select class="form-control"
								id="facebookSetup" name="facebookSetup.id"
								from="${com.prismanet.FacebookSetup.list()}" optionKey="id"
								value="${conceptInstance?.facebookSetup?.id}"
								 noSelection="['null': '']" /><i
							class="fa fa-facebook blue"></i>
						</span>
					</div>
				</div>

				<div class="space-4"></div>

				<div
					class="form-group fieldcontain ${hasErrors(bean: conceptInstance, field: 'facebookSetup', 'error')}">
					<label class="col-sm-3 control-label no-padding-right"
						for="twitterSetup"> <g:message
							code="concept.twitterSetup.label" default="Twitter Setup" /></label>

					<div class="col-sm-9">
						<span class="input-icon"> <g:select class="form-control" style="padding-left=10px;"
								id="twitterSetup" name="twitterSetup.id"
								from="${com.prismanet.TwitterSetup.list()}" optionKey="id"
								value="${conceptInstance?.twitterSetup?.id}" 
								noSelection="['null': '']" /> <i
							class="fa fa-twitter light-blue"></i>
						</span>
					</div>
				</div>

</div>
				</div>
				
