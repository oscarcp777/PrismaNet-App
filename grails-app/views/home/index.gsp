<html>
<head>
<meta name='layout' content='home' />

</head>
<body>
	<div style="margin-top: 80px;" class="container">

		<div class="col-sm-12 widget-container-span ui-sortable">
			<div class="widget-box">
				<div class="widget-header widget-hea1der-small header-color-grey">
					<h6>Controllers</h6>

					<div class="widget-toolbar">

						<a href="#" data-action="reload"> <i class="fa fa-refresh"></i>
						</a> <a href="#" data-action="collapse"> <i
							class="fa fa-chevron-up"></i>
						</a> <a href="#" data-action="close"> <i class="fa fa-remove"></i>
						</a>
					</div>
				</div>

				<div class="widget-body">
					<div class="widget-main padding-4">
						<div class="slimScrollDiv" >
							<div class="slim-scroll" data-height="525" >
								<div class="content">
									<div id="page-body" role="main">

										<div id="controller-list" role="navigation">

											<ul class="list-unstyled">
												<g:each var="c"
													in="${grailsApplication.controllerClasses.sort { it.fullName } }">
													<g:if test="${c.fullName.contains("prismanet")}">
													<li class="controller">
													<i class="fa fa-angle-double-right"></i>
													<g:link
															controller="${c.logicalPropertyName}">
															${c.logicalPropertyName}
														</g:link>
													</li>
													</g:if>
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

