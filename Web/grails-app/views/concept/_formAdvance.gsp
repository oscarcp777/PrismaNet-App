<%@ page import="com.prismanet.Concept"%>
<%@ page import="com.prismanet.FacebookSetup"%>
<%@ page import="com.prismanet.TwitterSetup"%>

		<h4 class="header blue bolder smaller"><g:message code="form.general"/></h4>


		<div class="form-group fieldcontain ${hasErrors(bean: conceptInstance, field: 'conceptName', 'error')}">
			<label class="col-sm-3 control-label no-padding-right" for="conceptName"> 
			     <g:message code="concept.conceptName.label" /></label>
			<div class="col-sm-4">
				<span class="input-icon">
					<g:textField name="conceptName" class="form-control" value="${conceptInstance?.conceptName}" placeholder="${message(code: 'concept.conceptName.label')}"/>
					<i class="ace-icon fa fa-cloud-download blue"></i>
				</span>
			</div>
		</div>
		<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="lang">
						<g:message code="concept.lang.label" />
					</label>
					<div class="col-sm-4">
						<g:select class="form-control" id="lang" name="lang.id"
								from="${com.prismanet.Languages.list()}" optionKey="id"
								value="${conceptInstance?.lang?.id}" 
								noSelection="['null': 'Seleccione un Idioma']" />
					</div>
		</div>
		<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="lang">
						<g:message code="concept.country.label" />
					</label>
					<div class="col-sm-4">
						<g:select class="form-control" id="country" name="country.id"
								from="${com.prismanet.Country.list()}" optionKey="id"
								value="${conceptInstance?.country?.id}" 
								noSelection="['null': 'Seleccione un Pais']" />
					</div>
		</div>
		<h4 class="header blue bolder smaller">Facebook</h4>

		<div class="form-group fieldcontain ${hasErrors(bean: facebookSetup, field: 'keywords', 'error')} ">
		<label class="col-sm-3 control-label no-padding-right" for="keywordsFace">
			<g:message code="facebookSetup.keywords.label" default="Keywords" />
		</label>

		<div class="col-sm-4 input-inline">
			<span class="input-icon">
				<g:textArea name="keywordsFace"  class="autosize-transition form-control" 
				value="${conceptInstance?.facebookSetup?.keywords}" placeholder="${message(code: 'facebookSetup.keywords.label')}"/>
				<i class="ace-icon fa fa-facebook blue"></i>
			</span>
			<span class="help-input help" data-rel="popover" data-trigger="hover" data-placement="left" data-content="${message(code: 'concept.user.advance.help.facebook')}" title="" data-original-title="${message(code: 'label.help')}">
			<i class="ace-icon fa fa-question-circle blue fa-2x"></i>
			</span>
		</div>
	 	</div>
			
		<h4 class="header blue bolder smaller">Twitter</h4>
	

	<div class="form-group fieldcontain ${hasErrors(bean: twitterSetup, field: 'keywords', 'error')} ">
		<label class="col-sm-3 control-label no-padding-right" for="keywordsTw">
			 <g:message code="twitterSetup.keywords.label"  />
		</label>
		<div class="col-sm-4 input-inline">
			<span class="input-icon">
				<g:textArea name="keywordsTw"  class="autosize-transition form-control" 
				value="${conceptInstance?.twitterSetup?.keywords}" placeholder="${message(code: 'twitterSetup.keywords.label')}"/>
				<i class="ace-icon fa fa-plus blue"></i>
			</span>
			<span class="help-input help" data-rel="popover" data-trigger="hover" data-placement="left" data-content="${message(code: 'concept.user.advance.help.facebook')}" title="" data-original-title="${message(code: 'label.help')}">
			<i class="ace-icon fa fa-question-circle blue fa-2x"></i>
			</span>
		</div>
	</div>
	<div class="form-group fieldcontain ${hasErrors(bean: twitterSetup, field: 'excludedAccounts', 'error')} ">
		<label class="col-sm-3 control-label no-padding-right" for="excludedAccounts"> 
			<g:message code="twitterSetup.excludedAccounts.label" />

		</label>
		<div class="col-sm-4 input-inline">
			<span class="input-icon">
				<g:textArea name="excludedAccounts" class="autosize-transition form-control" 
				value="${conceptInstance?.twitterSetup?.excludedAccounts}" placeholder="${message(code: 'twitterSetup.excludedAccounts.label')}"/>
				<i class="ace-icon fa fa-minus blue"></i>
			</span>
			<span class="help-input help" data-rel="popover" data-trigger="hover" data-placement="left" data-content="${message(code: 'concept.user.advance.help.facebook')}" title="" data-original-title="${message(code: 'label.help')}">
			<i class="ace-icon fa fa-question-circle blue fa-2x"></i>
			</span>
		</div>
	</div>

	<div
		class="form-group fieldcontain ${hasErrors(bean: twitterSetup, field: 'positiveHashtags', 'error')} ">
		<label class="col-sm-3 control-label no-padding-right" for="positiveHashtags"> 
			<g:message code="twitterSetup.positiveHashtags.label" default="positiveHashtags" />

		</label>
		<div class="col-sm-4 input-inline">
			<span class="input-icon">
				<g:textArea name="positiveHashtags" class="autosize-transition form-control" 
				value="${conceptInstance?.twitterSetup?.positiveHashtags}" placeholder="${message(code: 'twitterSetup.positiveHashtags.label')}"/>
				<i class="ace-icon blue">#</i>
			</span>
			<span class="help-input help" data-rel="popover" data-trigger="hover" data-placement="left" data-content="${message(code: 'concept.user.advance.help.facebook')}" title="" data-original-title="${message(code: 'label.help')}">
			<i class="ace-icon fa fa-question-circle blue fa-2x"></i>
			</span>
		</div>
	</div>
		<div
		class="form-group fieldcontain ${hasErrors(bean: twitterSetup, field: 'negativeHashtags', 'error')} ">
		<label class="col-sm-3 control-label no-padding-right" for="negativeHashtags"> 
			<g:message code="twitterSetup.negativeHashtags.label" default="negativeHashtags" />
		</label>
		<div class="col-sm-4 input-inline">
			<span class="input-icon">
				<g:textArea name="negativeHashtags" class="autosize-transition form-control" 
				value="${conceptInstance?.twitterSetup?.negativeHashtags}" placeholder="${message(code: 'twitterSetup.negativeHashtags.label')}"/>
				<i class="ace-icon blue">#</i>
			</span>
			<span class="help-input help" data-rel="popover" data-trigger="hover" data-placement="left" data-content="${message(code: 'concept.user.advance.help.facebook')}" title="" data-original-title="${message(code: 'label.help')}">
			<i class="ace-icon fa fa-question-circle blue fa-2x"></i>
			</span>
		</div>
	</div>
