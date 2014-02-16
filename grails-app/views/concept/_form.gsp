<%@ page import="com.prismanet.Concept" %>



<div class="form-group fieldcontain ${hasErrors(bean: conceptInstance, field: 'conceptName', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="conceptName">
		<g:message code="concept.conceptName.label" default="Concept Name" />
		
	</label>
	<g:textField name="conceptName" value="${conceptInstance?.conceptName}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: conceptInstance, field: 'user', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="user">
		<g:message code="concept.user.label" default="User" />
		
	</label>
	<g:select id="user" name="user.id" from="${com.prismanet.User.list()}" optionKey="id" value="${conceptInstance?.user?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: conceptInstance, field: 'tweets', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="tweets">
		<g:message code="concept.tweets.label" default="Tweets" />
		
	</label>
	<g:select name="tweets" from="${com.prismanet.Tweet.list()}" multiple="multiple" optionKey="id" size="5" value="${conceptInstance?.tweets*.id}" class="many-to-many"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: conceptInstance, field: 'posts', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="posts">
		<g:message code="concept.posts.label" default="Posts" />
		
	</label>
	<g:select name="posts" from="${com.prismanet.Post.list()}" multiple="multiple" optionKey="id" size="5" value="${conceptInstance?.posts*.id}" class="many-to-many"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: conceptInstance, field: 'twitterSetup', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="twitterSetup">
		<g:message code="concept.twitterSetup.label" default="Twitter Setup" />
		
	</label>
	<g:select id="twitterSetup" name="twitterSetup.id" from="${com.prismanet.TwitterSetup.list()}" optionKey="id" value="${conceptInstance?.twitterSetup?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: conceptInstance, field: 'facebookSetup', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="facebookSetup">
		<g:message code="concept.facebookSetup.label" default="Facebook Setup" />
		
	</label>
	<g:select id="facebookSetup" name="facebookSetup.id" from="${com.prismanet.FacebookSetup.list()}" optionKey="id" value="${conceptInstance?.facebookSetup?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

