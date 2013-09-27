<html>
<head>
<meta name='layout' content='home' />
<title>Welcome to Grails</title>

</head>
<body>
	<div style="margin-top: 80px;" class="container">

		<div class="col-sm-12 widget-container-span ui-sortable">
			<div class="widget-box">
				<div class="widget-header widget-hea1der-small header-color-grey">
					<h6>Controllers</h6>

					<div class="widget-toolbar">

						<a href="#" data-action="reload"> <i class="icon-refresh"></i>
						</a> <a href="#" data-action="collapse"> <i
							class="icon-chevron-up"></i>
						</a> <a href="#" data-action="close"> <i class="icon-remove"></i>
						</a>
					</div>
				</div>

				<div class="widget-body">
					<div class="widget-main padding-4">
						<div class="slimScrollDiv" id="scroll">
							<div class="slim-scroll" data-height="125" >
								<div class="content">
									<div id="page-body" role="main">

										<div id="controller-list" role="navigation">

											<ul class="list-unstyled">
												<g:each var="c"
													in="${grailsApplication.controllerClasses.sort { it.fullName } }">
													<li class="controller">
													<i class="icon-double-angle-right"></i>
													<g:link
															controller="${c.logicalPropertyName}">
															${c.fullName}
														</g:link></li>
												</g:each>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>



	</div>
</body>
</html>

