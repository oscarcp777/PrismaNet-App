<html>
<head>
<meta name="layout" content="home" />
	
</head>
<body>
	<g:render template="tabFacebook"  model="['concept':concept,'tabMain':'','tabPosts':'active','tabChar':'','tabSentimental':'']">
	</g:render>
	<script type="text/javascript">
	activeItemMenuLevel2('${concept.id}','${concept.id}-face');
	 
	</script>
</body>
</html>
