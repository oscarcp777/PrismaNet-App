<html>
<head>
<meta name='layout' content='home' />

</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>
				<i class="ace-icon fa fa-cogs"></i>  <g:message code="controlPanel.title"/>
			</h1>
		</div>

		<div class="col-sm-12">
			<div class="widget-box widget-color-blue">
				<div class="widget-header">
					<h4 class="widget-title">
						<g:message code="controlPanel.title.header" />
					</h4>
				</div>

				<div class="widget-body">
					<div class="widget-main">
						<div class="row">
							<div class="col-xs-12 col-sm-12 center">

								<g:link controller="concept"
									class="btn  btn-app btn-yellow panelButton-md">
									<i class="ace-icon fa fa-tags bigger-300"></i>
									<g:message code="controlPanel.concept" />
								</g:link>
								<g:link controller="user"
									class="btn  btn-app btn-inverse panelButton-md">
									<i class="ace-icon fa fa-group bigger-300"></i>
									<g:message code="controlPanel.User" />
								</g:link>
	<!--  						<g:link controller="accountType"
									class="btn btn-app btn-success panelButton-md">
									<i class="ace-icon fa fa-money bigger-300"></i>
									<g:message code="controlPanel.account.type" />
								</g:link>-->	
							</div>
							<div class="col-xs-12 col-sm-12 center">
								<g:link controller="facebookSetup"
									class="btn  btn-app btn-primary panelButton-lg">
									<i class="ace-icon fa fa-facebook-square  bigger-300"></i>
									<g:message code="controlPanel.facebook.setup" />
								</g:link>
								<g:link controller="twitterSetup"
									class="btn  btn-app btn-info panelButton-lg">
									<i class="ace-icon fa fa-twitter bigger-300"></i>
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

