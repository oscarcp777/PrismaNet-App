
<%@ page import="com.prismanet.Post"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="home">
<g:set var="entityName"
	value="${message(code: 'post.label', default: 'Post')}" />
<title><g:message code="posts.title" /></title>
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'twitter.css')}" type="text/css">
</head>
<body>
	<div class="page-content">
		
		<g:render contextPath="../concept" template="tabFacebook"  model="['concept':concept,'tabMain':'','tabposts':'active','tabChar':'','tabSentimental':'']"></g:render>
		
		<div class="row well center-block ">
			

			<div class="col-xs-8 ">


				<ul class="posts post-group list-unstyled">
					<g:if test="${flash.message}">
						<div class="message" role="status">${flash.message}</div>
					</g:if>
					<g:each in="${postInstanceList}" status="i" var="postInstance">
					</g:each>

				</ul>

				<div class="col-lg-8" style="text-align: center;">
					<ul class="pagination pagination-sm">
						<li><g:paginate total="${postInstanceTotal}" id="${concept.id}" params="${params}"  /></li>
					</ul>
				</div>
				
			</div>
		</div>
	</div>
	<script type="text/javascript">
	activeItemMenuLevel2('${concept.id}','${concept.id}-face');
	  jQuery(function($) {
		  $(".tooltips").tooltip();
		  });
	</script>
</body>
</html>
