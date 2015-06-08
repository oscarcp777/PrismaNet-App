<%@ page import="com.prismanet.MonthlyConceptStats" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="home">
	</head>
	<body>
	<div class="page-content">
	<div class="page-header">
		<h1>
		<i class="ace-icon fa  fa-info-circle"></i>
			<g:message code="user.info.account.title" />
		</h1>
	</div>
	<br>
	<div class="col-lg-8">
	<div class="alert alert-info">
	<button type="button" class="close" data-dismiss="alert">
			<i class="ace-icon fa fa-times"></i>
		</button>
		<h4>Importante!</h4>
        <ul class="fa-ul">
          <li>
            <i class="fa fa-info-circle fa-lg fa-li"></i>
           La Información mostrada corresponde hasta la fecha del 
		<b><g:formatDate date="${infoDate}" type="datetime" style="LONG" timeStyle="MEDIUM"/></b> 
          </li>
        </ul>
      </div>
     </div>
	<div class="col-lg-6">
	<div class="profile-user-info profile-user-info-striped">
	   <g:each in="${listConStats}" status="i" var="conStat">
		<div class="profile-info-row">
			<div class="profile-info-name"> ${conStat.concept?.conceptName} </div>
			<div class="profile-info-value"><span>${conStat.mentions} </span></div>
		</div>
		</g:each>
		<div class="profile-info-row">
			<div class="profile-info-name"> Total de menciones </div>
			<div class="profile-info-value"><span>${totalMentions} </span></div>
		</div>
	</div>
	</div>
	</div>
	</body>
</html>
