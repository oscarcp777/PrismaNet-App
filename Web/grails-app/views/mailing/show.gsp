

<%@ page import="com.prismanet.Mailing" %>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="home">
<g:set var="entityName"
	value="${message(code: 'mailing.label', default: 'Mailing')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>
				<g:message code="default.show.label" args="[entityName]" />

			</h1>
		</div>
		<div class="nav" role="navigation">
			<g:link class="btn btn-info btn-sm" action="list">
				<g:message code="default.list.label" args="[entityName]" />
				<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
			</g:link>
			<g:link class="btn btn-info btn-sm" action="create">
				<g:message code="default.new.label" args="[entityName]" />
				<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
			</g:link>
		</div>
		<hr>
		<div id="show-mailing"
			class="content scaffold-show" role="main">
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div
				class="profile-user-info profile-user-info-striped property-list mailing">
				
				<g:if test="${mailingInstance?.postLink1}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="postLink1-label" class="property-label">
					<g:message code="mailing.postLink1.label" default="Post Link1" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="postLink1-label"><g:fieldValue bean="${mailingInstance}" field="postLink1"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.postPhoto1}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="postPhoto1-label" class="property-label">
					<g:message code="mailing.postPhoto1.label" default="Post Photo1" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="postPhoto1-label"><g:fieldValue bean="${mailingInstance}" field="postPhoto1"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.postLink2}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="postLink2-label" class="property-label">
					<g:message code="mailing.postLink2.label" default="Post Link2" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="postLink2-label"><g:fieldValue bean="${mailingInstance}" field="postLink2"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.postPhoto2}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="postPhoto2-label" class="property-label">
					<g:message code="mailing.postPhoto2.label" default="Post Photo2" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="postPhoto2-label"><g:fieldValue bean="${mailingInstance}" field="postPhoto2"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.postLink3}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="postLink3-label" class="property-label">
					<g:message code="mailing.postLink3.label" default="Post Link3" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="postLink3-label"><g:fieldValue bean="${mailingInstance}" field="postLink3"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.postPhoto3}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="postPhoto3-label" class="property-label">
					<g:message code="mailing.postPhoto3.label" default="Post Photo3" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="postPhoto3-label"><g:fieldValue bean="${mailingInstance}" field="postPhoto3"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.postLink4}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="postLink4-label" class="property-label">
					<g:message code="mailing.postLink4.label" default="Post Link4" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="postLink4-label"><g:fieldValue bean="${mailingInstance}" field="postLink4"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.postPhoto4}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="postPhoto4-label" class="property-label">
					<g:message code="mailing.postPhoto4.label" default="Post Photo4" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="postPhoto4-label"><g:fieldValue bean="${mailingInstance}" field="postPhoto4"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.author1}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="author1-label" class="property-label">
					<g:message code="mailing.author1.label" default="Author1" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="author1-label"><g:fieldValue bean="${mailingInstance}" field="author1"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.author2}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="author2-label" class="property-label">
					<g:message code="mailing.author2.label" default="Author2" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="author2-label"><g:fieldValue bean="${mailingInstance}" field="author2"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.author3}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="author3-label" class="property-label">
					<g:message code="mailing.author3.label" default="Author3" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="author3-label"><g:fieldValue bean="${mailingInstance}" field="author3"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.author4}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="author4-label" class="property-label">
					<g:message code="mailing.author4.label" default="Author4" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="author4-label"><g:fieldValue bean="${mailingInstance}" field="author4"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.day1}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="day1-label" class="property-label">
					<g:message code="mailing.day1.label" default="Day1" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="day1-label"><g:fieldValue bean="${mailingInstance}" field="day1"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.day2}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="day2-label" class="property-label">
					<g:message code="mailing.day2.label" default="Day2" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="day2-label"><g:fieldValue bean="${mailingInstance}" field="day2"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.day3}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="day3-label" class="property-label">
					<g:message code="mailing.day3.label" default="Day3" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="day3-label"><g:fieldValue bean="${mailingInstance}" field="day3"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.day4}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="day4-label" class="property-label">
					<g:message code="mailing.day4.label" default="Day4" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="day4-label"><g:fieldValue bean="${mailingInstance}" field="day4"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.fromDate}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="fromDate-label" class="property-label">
					<g:message code="mailing.fromDate.label" default="From Date" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="fromDate-label"><g:fieldValue bean="${mailingInstance}" field="fromDate"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.hour1}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="hour1-label" class="property-label">
					<g:message code="mailing.hour1.label" default="Hour1" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="hour1-label"><g:fieldValue bean="${mailingInstance}" field="hour1"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.hour2}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="hour2-label" class="property-label">
					<g:message code="mailing.hour2.label" default="Hour2" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="hour2-label"><g:fieldValue bean="${mailingInstance}" field="hour2"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.hour3}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="hour3-label" class="property-label">
					<g:message code="mailing.hour3.label" default="Hour3" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="hour3-label"><g:fieldValue bean="${mailingInstance}" field="hour3"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.hour4}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="hour4-label" class="property-label">
					<g:message code="mailing.hour4.label" default="Hour4" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="hour4-label"><g:fieldValue bean="${mailingInstance}" field="hour4"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.postComments1}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="postComments1-label" class="property-label">
					<g:message code="mailing.postComments1.label" default="Post Comments1" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="postComments1-label"><g:fieldValue bean="${mailingInstance}" field="postComments1"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.postComments2}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="postComments2-label" class="property-label">
					<g:message code="mailing.postComments2.label" default="Post Comments2" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="postComments2-label"><g:fieldValue bean="${mailingInstance}" field="postComments2"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.postComments3}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="postComments3-label" class="property-label">
					<g:message code="mailing.postComments3.label" default="Post Comments3" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="postComments3-label"><g:fieldValue bean="${mailingInstance}" field="postComments3"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.postComments4}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="postComments4-label" class="property-label">
					<g:message code="mailing.postComments4.label" default="Post Comments4" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="postComments4-label"><g:fieldValue bean="${mailingInstance}" field="postComments4"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.postLikes1}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="postLikes1-label" class="property-label">
					<g:message code="mailing.postLikes1.label" default="Post Likes1" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="postLikes1-label"><g:fieldValue bean="${mailingInstance}" field="postLikes1"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.postLikes2}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="postLikes2-label" class="property-label">
					<g:message code="mailing.postLikes2.label" default="Post Likes2" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="postLikes2-label"><g:fieldValue bean="${mailingInstance}" field="postLikes2"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.postLikes3}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="postLikes3-label" class="property-label">
					<g:message code="mailing.postLikes3.label" default="Post Likes3" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="postLikes3-label"><g:fieldValue bean="${mailingInstance}" field="postLikes3"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.postLikes4}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="postLikes4-label" class="property-label">
					<g:message code="mailing.postLikes4.label" default="Post Likes4" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="postLikes4-label"><g:fieldValue bean="${mailingInstance}" field="postLikes4"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.tent1}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="tent1-label" class="property-label">
					<g:message code="mailing.tent1.label" default="Tent1" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="tent1-label"><g:fieldValue bean="${mailingInstance}" field="tent1"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.tent2}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="tent2-label" class="property-label">
					<g:message code="mailing.tent2.label" default="Tent2" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="tent2-label"><g:fieldValue bean="${mailingInstance}" field="tent2"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.tent3}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="tent3-label" class="property-label">
					<g:message code="mailing.tent3.label" default="Tent3" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="tent3-label"><g:fieldValue bean="${mailingInstance}" field="tent3"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.tent4}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="tent4-label" class="property-label">
					<g:message code="mailing.tent4.label" default="Tent4" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="tent4-label"><g:fieldValue bean="${mailingInstance}" field="tent4"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.tentFrom}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="tentFrom-label" class="property-label">
					<g:message code="mailing.tentFrom.label" default="Tent From" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="tentFrom-label"><g:fieldValue bean="${mailingInstance}" field="tentFrom"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.tentTo}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="tentTo-label" class="property-label">
					<g:message code="mailing.tentTo.label" default="Tent To" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="tentTo-label"><g:fieldValue bean="${mailingInstance}" field="tentTo"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.toDate}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="toDate-label" class="property-label">
					<g:message code="mailing.toDate.label" default="To Date" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="toDate-label"><g:fieldValue bean="${mailingInstance}" field="toDate"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.tweets1}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="tweets1-label" class="property-label">
					<g:message code="mailing.tweets1.label" default="Tweets1" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="tweets1-label"><g:fieldValue bean="${mailingInstance}" field="tweets1"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.tweets2}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="tweets2-label" class="property-label">
					<g:message code="mailing.tweets2.label" default="Tweets2" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="tweets2-label"><g:fieldValue bean="${mailingInstance}" field="tweets2"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.tweets3}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="tweets3-label" class="property-label">
					<g:message code="mailing.tweets3.label" default="Tweets3" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="tweets3-label"><g:fieldValue bean="${mailingInstance}" field="tweets3"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.tweets4}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="tweets4-label" class="property-label">
					<g:message code="mailing.tweets4.label" default="Tweets4" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="tweets4-label"><g:fieldValue bean="${mailingInstance}" field="tweets4"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.type}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="type-label" class="property-label">
					<g:message code="mailing.type.label" default="Type" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="type-label"><g:fieldValue bean="${mailingInstance}" field="type"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.wordsDay1}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="wordsDay1-label" class="property-label">
					<g:message code="mailing.wordsDay1.label" default="Words Day1" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="wordsDay1-label"><g:fieldValue bean="${mailingInstance}" field="wordsDay1"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.wordsDay2}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="wordsDay2-label" class="property-label">
					<g:message code="mailing.wordsDay2.label" default="Words Day2" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="wordsDay2-label"><g:fieldValue bean="${mailingInstance}" field="wordsDay2"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.wordsDay3}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="wordsDay3-label" class="property-label">
					<g:message code="mailing.wordsDay3.label" default="Words Day3" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="wordsDay3-label"><g:fieldValue bean="${mailingInstance}" field="wordsDay3"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.wordsDay4}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="wordsDay4-label" class="property-label">
					<g:message code="mailing.wordsDay4.label" default="Words Day4" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="wordsDay4-label"><g:fieldValue bean="${mailingInstance}" field="wordsDay4"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.wordsHour1}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="wordsHour1-label" class="property-label">
					<g:message code="mailing.wordsHour1.label" default="Words Hour1" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="wordsHour1-label"><g:fieldValue bean="${mailingInstance}" field="wordsHour1"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.wordsHour2}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="wordsHour2-label" class="property-label">
					<g:message code="mailing.wordsHour2.label" default="Words Hour2" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="wordsHour2-label"><g:fieldValue bean="${mailingInstance}" field="wordsHour2"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.wordsHour3}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="wordsHour3-label" class="property-label">
					<g:message code="mailing.wordsHour3.label" default="Words Hour3" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="wordsHour3-label"><g:fieldValue bean="${mailingInstance}" field="wordsHour3"/></span>
					
					</div>
					</div>
				</g:if>
			
				<g:if test="${mailingInstance?.wordsHour4}">
				<div class="profile-info-row">
					<div class="profile-info-name"><span id="wordsHour4-label" class="property-label">
					<g:message code="mailing.wordsHour4.label" default="Words Hour4" />
				    </span>
					</div>
				<div class="profile-info-value">
					
						<span class="property-value" aria-labelledby="wordsHour4-label"><g:fieldValue bean="${mailingInstance}" field="wordsHour4"/></span>
					
					</div>
					</div>
				</g:if>
			
			</div>
			<g:form>
				<div class="clearfix form-actions">
					<div class="col-md-offset-3 col-md-9">
					    <g:hiddenField name="id" value="${mailingInstance?.id}" />
						<g:link class="btn btn-info" action="edit" id="${mailingInstance?.id}">
						<i class="ace-icon fa fa-ok bigger-110"></i>
						<g:message code="default.button.edit.label" default="Edit" />
					</g:link>
						&nbsp; &nbsp;
						<g:actionSubmit class="btn btn-reverse" action="delete"
						value="${message(code: 'default.button.delete.label', default: 'Delete')}"
						onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</div>
				</div>
			</g:form>
		</div>
	</div>
</body>
</html>
