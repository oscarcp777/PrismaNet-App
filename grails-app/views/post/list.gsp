
<%@ page import="com.prismanet.FacebookComment"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="home">
<g:set var="entityName"
	value="${message(code: 'post.label', default: 'Comentario')}" />
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'twitter.css')}" type="text/css">
</head>
<body>
	<div class="page-content">
		
		<g:render contextPath="../concept" template="tabFacebook"  model="['concept':concept,'tabMain':'','tabPosts':'active','tabChar':'','tabSentimental':'']"></g:render>
		
		<div class="row well center-block " style="background-color: #e7ebf2">
       <g:render template="listPosts" model="['concept':concept,'postList':postList,'postTotal':postTotal]"></g:render>			
		</div>
	</div>
	<script type="text/javascript">
	activeItemMenuLevel2('${concept.id}','${concept.id}-face','${concept.conceptName}');
	  jQuery(function($) {
		  $(".tooltips").tooltip();
		  });
	</script>
</body>
</html>
