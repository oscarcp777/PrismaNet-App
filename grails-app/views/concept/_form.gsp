<%@ page import="com.prismanet.Concept"%>

<form class="form-horizontal">
	<div class="tabbable">
		<ul class="nav nav-tabs padding-16">
			<li class="active"><a data-toggle="tab" href="#edit-basic">
					<i class="green icon-edit bigger-125"></i> Crear Concepto
			</a></li>
		</ul>

		<div class="tab-content profile-edit-tab-content">
			<div id="edit-basic" class="tab-pane active">
				<h4 class="header blue bolder smaller">General</h4>

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
						<span class="input-icon input-icon-right"> <g:select
								class="form-control" id="user" name="user.id"
								from="${com.prismanet.User.list()}" optionKey="id"
								value="${conceptInstance?.user?.id}" class="many-to-one"
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
								class="many-to-one" noSelection="['null': '']" /><i
							class="icon-facebook blue"></i>
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
						<span class="input-icon"> <g:select class="form-control"
								id="twitterSetup" name="twitterSetup.id"
								from="${com.prismanet.TwitterSetup.list()}" optionKey="id"
								value="${conceptInstance?.twitterSetup?.id}" class="many-to-one"
								noSelection="['null': '']" /> <i
							class="icon-twitter light-blue"></i>
						</span>
					</div>
				</div>


</form>
