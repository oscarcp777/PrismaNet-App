<html>
<head>
<meta name='layout' content='main' />
<title><g:message code="springSecurity.login.title" /></title>
<r:require module="login" />
</head>

<body>

   <div class="fondo-login">
   
	<div id='login' >
	
				<div class="logo">
					<a class="logoHolder" title="return to home page" href="#"> <span class="logoFirst"> 
					<i class="icon-rombo" style="font-weight: bold; font-size: 0.9em;"></i> Prisma
					</span>-Net
					</a>
				</div>
		<div class='container'>


			<div class="jumbotron" style="padding-top:10px;padding-bottom:20px;">

			<form action='${postUrl}' method='POST' id='loginForm'
				class='form-signin' autocomplete='off'  role="form">
				<h2 class="form-signin-heading"><g:message code="springSecurity.login.header"/></h2>
				<input type='text' class='form-control' name='j_username' id='username' placeholder="${message(code: "springSecurity.login.username.label")}" autofocus /> 
				<input type='password' class='form-control' name='j_password' id='password' placeholder="${message(code: "springSecurity.login.password.label")}"/>
				<label class="checkbox">
					<input type='checkbox' name='${rememberMeParameter}' id='remember_me' 
					<g:if test='${hasCookie}'>checked='checked'</g:if> /> 
						<g:message code="springSecurity.login.remember.me.label"/>
				</label>
				<g:if test='${flash.message}'>
				<div class='alert alert-danger alert-dismissable' style="font-size: 14px;line-height: 15px;">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
					${flash.message}
				</div>
			   </g:if>
					<button class="btn btn-lg btn-danger btn-block"  type='submit' id="submit"
						value='${message(code: "springSecurity.login.button")}' >${message(code: "springSecurity.login.button")}
					</button>
				
			</form>
			</div>
		</div>
	</div>
	</div>
</body>
</html>
