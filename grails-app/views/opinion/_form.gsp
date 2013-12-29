<%@ page import="com.prismanet.sentiment.Opinion" %>



<div class="fieldcontain ${hasErrors(bean: opinionInstance, field: 'tweet', 'error')} required">
	<label for="tweet">
		<g:message code="opinion.tweet.label" default="Tweet" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tweet" name="tweet.id" from="${com.prismanet.Tweet.list()}" optionKey="id" required="" value="${opinionInstance?.tweet?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: opinionInstance, field: 'concept', 'error')} required">
	<label for="concept">
		<g:message code="opinion.concept.label" default="Concept" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="concept" name="concept.id" from="${com.prismanet.Concept.list()}" optionKey="id" required="" value="${opinionInstance?.concept?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: opinionInstance, field: 'value', 'error')} ">
	<label for="value">
		<g:message code="opinion.value.label" default="Value" />
		
	</label>
	<g:select name="value" from="${com.prismanet.sentiment.OpinionValue?.values()}" keys="${com.prismanet.sentiment.OpinionValue.values()*.name()}" value="${opinionInstance?.value?.name()}" noSelection="['': '']"/>
</div>

