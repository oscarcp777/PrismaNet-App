<html>
<head>
<title>Resultados de la Consulta</title>
<meta name="layout" content="home" />
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>
				<i class="icon-tag"></i> Resultados Concepto: "${concept.conceptName}"
			</h1>
		</div>



		<div class="row">
			<div class="col-lg-12">
				<div class="row">
					<div class="col-xs-12 col-sm-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-red">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span> Tweets por
									Fecha
								</h5>

								<div class="widget-toolbar">
									<a href="#" data-action="collapse"> <i
										class="1 bigger-125 icon-chevron-up"></i>
									</a>
								</div>


							</div>

							<div class="widget-body">
								<div class="widget-body-inner" style="display: block;">
									<div class="widget-main">
										<div id="container" style="width:100%; height:400px;"></div>
									</div>


								</div>
							</div>
						</div>
						
						
						
						<div class="widget-box">
							<div class="widget-header header-color-red">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span> Tweets por
									Hora
								</h5>

								<div class="widget-toolbar">
									<a href="#" data-action="collapse"> <i
										class="1 bigger-125 icon-chevron-up"></i>
									</a>
								</div>


							</div>

							<div class="widget-body">
								<div class="widget-body-inner" style="display: block;">
									<div class="widget-main">
										<g:each var="item" in="${hourList}">
											<li>${item[0]}hs - ${item[1]} tweets</li>
										</g:each>
									</div>


								</div>
							</div>
							
							
							<div class="widget-box">
							<div class="widget-header header-color-red">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span> Tweets por
									Minuto
								</h5>

								<div class="widget-toolbar">
									<a href="#" data-action="collapse"> <i
										class="1 bigger-125 icon-chevron-up"></i>
									</a>
								</div>


							</div>

							<div class="widget-body">
								<div class="widget-body-inner" style="display: block;">
									<div class="widget-main">
										<g:each var="item" in="${minuteList}">
											<li>${item[0]}hs - ${item[1]} tweets</li>
										</g:each>
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

	<script type="text/javascript">
	  activeItemMenu('concepts',"${concept.id}", 2,'Conceptos > '+"${concept.conceptName}");
	  $(function () {
	    	
	    	// Radialize the colors
			Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function(color) {
			    return {
			        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
			        stops: [
			            [0, color],
			            [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
			        ]
			    };
			});
			
			// Build the chart
	        $('#container').highcharts({
	            chart: {
	                plotBackgroundColor: null,
	                plotBorderWidth: null,
	                plotShadow: false
	            },
	            title: {
	                text: 'Browser market shares at a specific website, 2010'
	            },
	            tooltip: {
	        	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	            },
	            plotOptions: {
	                pie: {
	                    allowPointSelect: true,
	                    cursor: 'pointer',
	                    dataLabels: {
	                        enabled: true,
	                        color: '#000000',
	                        connectorColor: '#000000',
	                        formatter: function() {
	                            return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
	                        }
	                    }
	                }
	            },
	            series: [{
	                type: 'pie',
	                name: 'Browser share',
	                data: [
	                    ['Firefox',   45.0],
	                    ['IE',       26.8],
	                    {
	                        name: 'Chrome',
	                        y: 12.8,
	                        sliced: true,
	                        selected: true
	                    },
	                    ['Safari',    8.5],
	                    ['Opera',     6.2],
	                    ['Others',   0.7]
	                ]
	            }]
	        });
	    });
	</script>
</body>
</html>
