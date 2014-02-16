<html>
<head>
<meta name='layout' content='home' />

</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>
				<i class="icon-cogs"></i>  <g:message code="controlPanel.title"/>
			</h1>
		</div>

		<div class="col-sm-12">
			<div class="widget-box">
				<div class="widget-header header-color-blue">
					<h4>
						<g:message code="controlPanel.title.header" />
					</h4>
				</div>

				<div class="widget-body">
					<div class="widget-main">
						<div class="row">
							<div class="col-xs-12 col-sm-12 center">

								<g:link controller="concept"
									class="btn  btn-app btn-yellow panelButton-md">
									<i class="icon-tags bigger-300"></i>
									<g:message code="controlPanel.concept" />
								</g:link>
								<g:link controller="user"
									class="btn  btn-app btn-inverse panelButton-md">
									<i class="icon-group bigger-300"></i>
									<g:message code="controlPanel.User" />
								</g:link>
								<g:link controller="accountType"
									class="btn btn-app btn-success panelButton-md">
									<i class="icon-money bigger-300"></i>
									<g:message code="controlPanel.account.type" />
								</g:link>
							</div>
							<div class="col-xs-12 col-sm-12 center">
								<g:link controller="facebookSetup"
									class="btn  btn-app btn-primary panelButton-lg">
									<i class="icon-facebook-sign  bigger-300"></i>
									<g:message code="controlPanel.facebook.setup" />
								</g:link>
								<g:link controller="twitterSetup"
									class="btn  btn-app btn-info panelButton-lg">
									<i class="icon-twitter bigger-300"></i>
									<g:message code="controlPanel.twitter.setup" />
								</g:link>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

