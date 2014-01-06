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
									<a href="#" data-action="reload"> <i class="icon-refresh"></i>
									</a> <a href="#" data-action="collapse"> <i
										class="icon-chevron-up"></i>
									</a> <a href="#" data-action="close"> <i class="icon-remove"></i>
									</a>
								</div>
							</div>

							<div class="widget-body">
								<div class="widget-main padding-4">
									<div id="container" style="width: 100%; height: 400px;"></div>
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
									</a>
									 <a href="#" data-action="collapse"> <i
									class="1 bigger-125 icon-chevron-up"></i>
								</a>
									<a href="#" data-action="close"> <i class="icon-remove"></i>
									</a>
						   </div>

						</div>

						<div class="widget-body">
							<div class="widget-body-inner" style="display: block;">
								<div class="widget-main">


									<div class="row">
										<div class="col-md-6" style="padding-left:10%;">
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

		<div class="row">

			

			
		</div>
	</div>

	<script type="text/javascript">
		

		$(document).ready(function() {
			activeItemMenuLevel2('chart', 'general', 'Estadisticas > General');
			paintCharGender();
			$.getJSON("http://localhost:8080/PrismaNet/user/conceptTweetsJson",
					function(json) {
				      paintCharPieTweets(json);
					});
						});

		function paintCharPieTweets(dataJson) {
			// Radialize the colors
			Highcharts.getOptions().colors = Highcharts.map(Highcharts
					.getOptions().colors, function(color) {
				return {
					radialGradient : {
						cx : 0.5,
						cy : 0.3,
						r : 0.7
					},
					stops : [
							[ 0, color ],
							[
									1,
									Highcharts.Color(color).brighten(-0.3).get(
											'rgb') ] // darken
					]
				};
			});

			// Build the chart
			$('#container')
					.highcharts(
							{
								chart : {
									plotBackgroundColor : null,
									plotBorderWidth : null,
									plotShadow : false
								},
								title : {
									text : 'Porcentajes de tweets por Concepto'
								},
								tooltip : {
									pointFormat : '{series.name}: <b>{point.y}</b>'
								},
								plotOptions : {
									pie : {
										allowPointSelect : true,
										cursor : 'pointer',
										dataLabels : {
											enabled : true,
											color : '#000000',
											connectorColor : '#000000',
											formatter : function() {
												return '<b>' + this.point.name
														+ '</b>: '
														+ this.percentage.toFixed(2);
														+ ' %';
											}
										}
									}
								},
								series : [ {
									type : 'pie',
									name : 'Tweets',
									data : dataJson
								} ]
							});

		}
		function paintCharGender() {
			$('.easy-pie-chart.percentage')
					.each(
							function() {
								var $box = $(this).closest('.infobox');
								var barColor = $(this).data('color')
										|| (!$box.hasClass('infobox-dark') ? $box
												.css('color')
												: 'rgba(255,255,255,0.95)');
								var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)'
										: '#E2E2E2';
								var size = parseInt($(this).data('size')) || 300;
								$(this)
										.easyPieChart(
												{
													barColor : barColor,
													trackColor : trackColor,
													scaleColor : false,
													lineCap : 'butt',
													lineWidth : parseInt(size / 10),
													animate : /msie\s*(8|7|6)/
															.test(navigator.userAgent
																	.toLowerCase()) ? false
															: 1000,
													size : size
												});
							})
		}
	</script>
</body>
</html>
