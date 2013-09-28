<%@ page import="com.prismanet.Tweet" %>



<div class="fieldcontain ${hasErrors(bean: tweetInstance, field: 'content', 'error')} ">
	<label for="content">
		<g:message code="tweet.content.label" default="Content" />
		
	</label>
	<g:textField name="content" value="${tweetInstance?.content}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tweetInstance, field: 'author', 'error')} ">
	<label for="author">
		<g:message code="tweet.author.label" default="Author" />
		
	</label>
	<g:select id="author" name="author.id" from="${com.prismanet.Author.list()}" optionKey="id" value="${tweetInstance?.author?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tweetInstance, field: 'created', 'error')} ">
	<label for="created">
		<g:message code="tweet.created.label" default="Created" />
		
	</label>
	<g:datePicker name="created" precision="day"  value="${tweetInstance?.created}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: tweetInstance, field: 'month', 'error')} ">
	<label for="month">
		<g:message code="tweet.month.label" default="Month" />
		
	</label>
	<g:field name="month" type="number" value="${tweetInstance.month}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tweetInstance, field: 'period', 'error')} ">
	<label for="period">
		<g:message code="tweet.period.label" default="Period" />
		
	</label>
	<g:textField name="period" value="${tweetInstance?.period}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tweetInstance, field: 'retweet', 'error')} ">
	<label for="retweet">
		<g:message code="tweet.retweet.label" default="Retweet" />
		
	</label>
	<g:checkBox name="retweet" value="${tweetInstance?.retweet}" />
</div>

<div class="fieldcontain ${hasErrors(bean: tweetInstance, field: 'year', 'error')} ">
	<label for="year">
		<g:message code="tweet.year.label" default="Year" />
		
	</label>
	<g:field name="year" type="number" value="${tweetInstance.year}"/>
</div>

