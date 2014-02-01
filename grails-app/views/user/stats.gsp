<html>
<head>
<meta name="layout" content="home" />
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>
				<i class="icon-user"></i> Resultados Usuario: "${user.username}"
			</h1>
		</div>


		<div class="row">
			<div class="col-lg-12">
				<div class="row">
					<div class="col-lg-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-blue">
								<h5>
									<span class="glyphicon glyphicon-stats"></span> Tweets por
									Concepto
								</h5>

								<div class="widget-toolbar">
									<a href="javascript:void(0);" id="refreshCharPie"> <span
										data-action="reload"> <i class="icon-refresh"></i></span>
									</a> <a href="javascript:void(0);" data-action="collapse"> <i
										class="icon-chevron-up"></i></a> <a href="javascript:void(0);"
										data-action="close"> <i class="icon-remove"></i></a>
								</div>
							</div>

							<div class="widget-body">
								<div class="widget-main padding-4">
									<div id="container" style="width: 100%; height: 400px;"></div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
					<div class="col-lg-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-blue">
								<h5>
									<span class="glyphicon glyphicon-stats"></span> Tweets por
									Concepto
								</h5>

								<div class="widget-toolbar">
									<a href="javascript:void(0);" id="refreshCharPie"> <span
										data-action="reload"> <i class="icon-refresh"></i></span>
									</a> <a href="javascript:void(0);" data-action="collapse"> <i
										class="icon-chevron-up"></i></a> <a href="javascript:void(0);"
										data-action="close"> <i class="icon-remove"></i></a>
								</div>
							</div>

							<div class="widget-body">
								<div class="widget-main padding-4">
									<div id="container1" style="width: 100%; height: 400px;"></div>
								</div>
							</div>
						</div>
					</div>


					<div class="col-xs-12 col-sm-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-dark">
								<h5>
									<span class="glyphicon glyphicon-stats"></span> Tweets por Sexo
								</h5>


								<div class="widget-toolbar">
									<a href="#" data-action="reload"> <i class="icon-refresh"></i>
									</a> <a href="#" data-action="collapse"> <i
										class="1 bigger-125 icon-chevron-up"></i>
									</a> <a href="#" data-action="close"> <i class="icon-remove"></i>
									</a>
								</div>

							</div>

							<div class="widget-body">
								<div class="widget-body-inner" style="display: block;">
									<div class="widget-main">


										<div class="row">
											<div class="col-md-6" style="padding-left: 10%;">
												<div class="easy-pie-chart percentage easyPieChart"
													data-percent="62" data-color="#2a91d8">
													<i class="icon-male" style="font-size: 9em; color: #2a91d8"></i>
													<h2
														style="margin-top: -72px; margin-left: 10px;; position: relative;">62%</h2>
												</div>

											</div>
											<div class="col-md-6">
												<div class="easy-pie-chart percentage easyPieChart"
													data-percent="38" data-color="#c6699f">
													<i class="icon-female"
														style="font-size: 9em; color: #c6699f"></i>
													<h2
														style="margin-top: -72px; margin-left: 10px;; position: relative;">38%</h2>
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
		</div>
		<hr>

		<div class="row"></div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			inicializeColorChar();
			activeItemMenuLevel2('chart', 'general', 'Estadisticas > General');
			paintCharGender();
			getTweetCharPie();
			getUserConceptsLineChar('#container1');
			$('#refreshCharPie').click(function() {
				getTweetCharPie();
			});
		});
	</script>
</body>
</html>
