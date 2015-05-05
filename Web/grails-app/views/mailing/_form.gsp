<%@ page import="com.prismanet.Mailing" %>



<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'postLink1', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="postLink1">
		<g:message code="mailing.postLink1.label" default="Post Link1" />
		
	</label>
	<g:textArea name="postLink1" cols="40" rows="5" maxlength="1000" value="${mailingInstance?.postLink1}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'postPhoto1', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="postPhoto1">
		<g:message code="mailing.postPhoto1.label" default="Post Photo1" />
		
	</label>
	<g:textArea name="postPhoto1" cols="40" rows="5" maxlength="1000" value="${mailingInstance?.postPhoto1}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'postLink2', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="postLink2">
		<g:message code="mailing.postLink2.label" default="Post Link2" />
		
	</label>
	<g:textArea name="postLink2" cols="40" rows="5" maxlength="1000" value="${mailingInstance?.postLink2}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'postPhoto2', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="postPhoto2">
		<g:message code="mailing.postPhoto2.label" default="Post Photo2" />
		
	</label>
	<g:textArea name="postPhoto2" cols="40" rows="5" maxlength="1000" value="${mailingInstance?.postPhoto2}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'postLink3', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="postLink3">
		<g:message code="mailing.postLink3.label" default="Post Link3" />
		
	</label>
	<g:textArea name="postLink3" cols="40" rows="5" maxlength="1000" value="${mailingInstance?.postLink3}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'postPhoto3', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="postPhoto3">
		<g:message code="mailing.postPhoto3.label" default="Post Photo3" />
		
	</label>
	<g:textArea name="postPhoto3" cols="40" rows="5" maxlength="1000" value="${mailingInstance?.postPhoto3}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'postLink4', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="postLink4">
		<g:message code="mailing.postLink4.label" default="Post Link4" />
		
	</label>
	<g:textArea name="postLink4" cols="40" rows="5" maxlength="1000" value="${mailingInstance?.postLink4}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'postPhoto4', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="postPhoto4">
		<g:message code="mailing.postPhoto4.label" default="Post Photo4" />
		
	</label>
	<g:textArea name="postPhoto4" cols="40" rows="5" maxlength="1000" value="${mailingInstance?.postPhoto4}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'author1', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="author1">
		<g:message code="mailing.author1.label" default="Author1" />
		
	</label>
	<g:textField name="author1" value="${mailingInstance?.author1}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'author2', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="author2">
		<g:message code="mailing.author2.label" default="Author2" />
		
	</label>
	<g:textField name="author2" value="${mailingInstance?.author2}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'author3', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="author3">
		<g:message code="mailing.author3.label" default="Author3" />
		
	</label>
	<g:textField name="author3" value="${mailingInstance?.author3}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'author4', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="author4">
		<g:message code="mailing.author4.label" default="Author4" />
		
	</label>
	<g:textField name="author4" value="${mailingInstance?.author4}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'day1', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="day1">
		<g:message code="mailing.day1.label" default="Day1" />
		
	</label>
	<g:textField name="day1" value="${mailingInstance?.day1}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'day2', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="day2">
		<g:message code="mailing.day2.label" default="Day2" />
		
	</label>
	<g:textField name="day2" value="${mailingInstance?.day2}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'day3', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="day3">
		<g:message code="mailing.day3.label" default="Day3" />
		
	</label>
	<g:textField name="day3" value="${mailingInstance?.day3}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'day4', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="day4">
		<g:message code="mailing.day4.label" default="Day4" />
		
	</label>
	<g:textField name="day4" value="${mailingInstance?.day4}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'fromDate', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="fromDate">
		<g:message code="mailing.fromDate.label" default="From Date" />
		
	</label>
	<g:textField name="fromDate" value="${mailingInstance?.fromDate}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'hour1', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="hour1">
		<g:message code="mailing.hour1.label" default="Hour1" />
		
	</label>
	<g:textField name="hour1" value="${mailingInstance?.hour1}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'hour2', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="hour2">
		<g:message code="mailing.hour2.label" default="Hour2" />
		
	</label>
	<g:textField name="hour2" value="${mailingInstance?.hour2}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'hour3', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="hour3">
		<g:message code="mailing.hour3.label" default="Hour3" />
		
	</label>
	<g:textField name="hour3" value="${mailingInstance?.hour3}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'hour4', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="hour4">
		<g:message code="mailing.hour4.label" default="Hour4" />
		
	</label>
	<g:textField name="hour4" value="${mailingInstance?.hour4}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'postComments1', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="postComments1">
		<g:message code="mailing.postComments1.label" default="Post Comments1" />
		
	</label>
	<g:textField name="postComments1" value="${mailingInstance?.postComments1}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'postComments2', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="postComments2">
		<g:message code="mailing.postComments2.label" default="Post Comments2" />
		
	</label>
	<g:textField name="postComments2" value="${mailingInstance?.postComments2}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'postComments3', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="postComments3">
		<g:message code="mailing.postComments3.label" default="Post Comments3" />
		
	</label>
	<g:textField name="postComments3" value="${mailingInstance?.postComments3}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'postComments4', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="postComments4">
		<g:message code="mailing.postComments4.label" default="Post Comments4" />
		
	</label>
	<g:textField name="postComments4" value="${mailingInstance?.postComments4}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'postLikes1', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="postLikes1">
		<g:message code="mailing.postLikes1.label" default="Post Likes1" />
		
	</label>
	<g:textField name="postLikes1" value="${mailingInstance?.postLikes1}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'postLikes2', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="postLikes2">
		<g:message code="mailing.postLikes2.label" default="Post Likes2" />
		
	</label>
	<g:textField name="postLikes2" value="${mailingInstance?.postLikes2}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'postLikes3', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="postLikes3">
		<g:message code="mailing.postLikes3.label" default="Post Likes3" />
		
	</label>
	<g:textField name="postLikes3" value="${mailingInstance?.postLikes3}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'postLikes4', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="postLikes4">
		<g:message code="mailing.postLikes4.label" default="Post Likes4" />
		
	</label>
	<g:textField name="postLikes4" value="${mailingInstance?.postLikes4}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'tent1', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="tent1">
		<g:message code="mailing.tent1.label" default="Tent1" />
		
	</label>
	<g:textField name="tent1" value="${mailingInstance?.tent1}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'tent2', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="tent2">
		<g:message code="mailing.tent2.label" default="Tent2" />
		
	</label>
	<g:textField name="tent2" value="${mailingInstance?.tent2}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'tent3', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="tent3">
		<g:message code="mailing.tent3.label" default="Tent3" />
		
	</label>
	<g:textField name="tent3" value="${mailingInstance?.tent3}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'tent4', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="tent4">
		<g:message code="mailing.tent4.label" default="Tent4" />
		
	</label>
	<g:textField name="tent4" value="${mailingInstance?.tent4}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'tentFrom', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="tentFrom">
		<g:message code="mailing.tentFrom.label" default="Tent From" />
		
	</label>
	<g:textField name="tentFrom" value="${mailingInstance?.tentFrom}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'tentTo', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="tentTo">
		<g:message code="mailing.tentTo.label" default="Tent To" />
		
	</label>
	<g:textField name="tentTo" value="${mailingInstance?.tentTo}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'toDate', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="toDate">
		<g:message code="mailing.toDate.label" default="To Date" />
		
	</label>
	<g:textField name="toDate" value="${mailingInstance?.toDate}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'tweets1', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="tweets1">
		<g:message code="mailing.tweets1.label" default="Tweets1" />
		
	</label>
	<g:textField name="tweets1" value="${mailingInstance?.tweets1}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'tweets2', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="tweets2">
		<g:message code="mailing.tweets2.label" default="Tweets2" />
		
	</label>
	<g:textField name="tweets2" value="${mailingInstance?.tweets2}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'tweets3', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="tweets3">
		<g:message code="mailing.tweets3.label" default="Tweets3" />
		
	</label>
	<g:textField name="tweets3" value="${mailingInstance?.tweets3}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'tweets4', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="tweets4">
		<g:message code="mailing.tweets4.label" default="Tweets4" />
		
	</label>
	<g:textField name="tweets4" value="${mailingInstance?.tweets4}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'type', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="type">
		<g:message code="mailing.type.label" default="Type" />
		
	</label>
	<g:textField name="type" value="${mailingInstance?.type}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'wordsDay1', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="wordsDay1">
		<g:message code="mailing.wordsDay1.label" default="Words Day1" />
		
	</label>
	<g:textField name="wordsDay1" value="${mailingInstance?.wordsDay1}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'wordsDay2', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="wordsDay2">
		<g:message code="mailing.wordsDay2.label" default="Words Day2" />
		
	</label>
	<g:textField name="wordsDay2" value="${mailingInstance?.wordsDay2}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'wordsDay3', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="wordsDay3">
		<g:message code="mailing.wordsDay3.label" default="Words Day3" />
		
	</label>
	<g:textField name="wordsDay3" value="${mailingInstance?.wordsDay3}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'wordsDay4', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="wordsDay4">
		<g:message code="mailing.wordsDay4.label" default="Words Day4" />
		
	</label>
	<g:textField name="wordsDay4" value="${mailingInstance?.wordsDay4}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'wordsHour1', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="wordsHour1">
		<g:message code="mailing.wordsHour1.label" default="Words Hour1" />
		
	</label>
	<g:textField name="wordsHour1" value="${mailingInstance?.wordsHour1}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'wordsHour2', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="wordsHour2">
		<g:message code="mailing.wordsHour2.label" default="Words Hour2" />
		
	</label>
	<g:textField name="wordsHour2" value="${mailingInstance?.wordsHour2}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'wordsHour3', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="wordsHour3">
		<g:message code="mailing.wordsHour3.label" default="Words Hour3" />
		
	</label>
	<g:textField name="wordsHour3" value="${mailingInstance?.wordsHour3}"/>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: mailingInstance, field: 'wordsHour4', 'error')} ">
	<label class="col-sm-3 control-label no-padding-right" for="wordsHour4">
		<g:message code="mailing.wordsHour4.label" default="Words Hour4" />
		
	</label>
	<g:textField name="wordsHour4" value="${mailingInstance?.wordsHour4}"/>
</div>

