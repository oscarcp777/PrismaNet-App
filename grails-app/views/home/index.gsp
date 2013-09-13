<html>
<head>
	<meta name='layout' content='main'/>
</head>
<body>
   	        <sec:ifLoggedIn>
                <sec:username/> -- <g:link controller='logout'>Log Out</g:link>
            </sec:ifLoggedIn>
</body>
</html>