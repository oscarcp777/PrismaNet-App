<%@ page import="com.prismanet.FacebookSetup" %>



<div class="form-group fieldcontain ${hasErrors(bean: facebookSetupInstance, field: 'keywords', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="keywords">
		<g:message code="facebookSetup.keywords.label" default="Keywords" />
		
	</label>
	<g:select name="keywords" from="${com.prismanet.Keyword.list()}" multiple="multiple" optionKey="id" size="5" value="${facebookSetupInstance?.keywords*.id}" class="many-to-many"/>
</div>

