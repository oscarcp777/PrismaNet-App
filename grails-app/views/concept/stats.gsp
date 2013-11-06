<html>
<head>
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
										<div id="container" style="height: 500px; min-width: 310px"></div>
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
	 
	  $(function() {
		  activeItemMenu('concepts',"${concept.id}", 2,'Conceptos > '+"${concept.conceptName}");
			$.getJSON('http://localhost:8080/PrismaNet/concept/conceptsJson/'+"${concept.conceptName}", 
				function(data) {
				printCharDate(data);
				
			});
		});
      function printCharDate(data){
    	// Create the chart
			$('#container').highcharts('StockChart', {
				rangeSelector : {
					selected : 1
				},
				title : {
					text : 'Tweets por fecha'
				},
				series : [{
					name : 'Tweets',
					data : data,
					type : 'area',
					threshold : null,
					tooltip : {
						valueDecimals : 2
					},
					fillColor : {
						linearGradient : {
							x1: 0, 
							y1: 0, 
							x2: 0, 
							y2: 1
						},
						stops : [[0, Highcharts.getOptions().colors[0]], [1, 'rgba(0,0,0,0)']]
					}
				}]
			});
      }
	</script>
</body>
</html>
