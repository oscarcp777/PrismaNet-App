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
							<div class="widget-header header-color-green4">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span> Tweets RealTime
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
										<div id="realTimeChar" style="height: 500px; min-width: 310px"></div>
									</div>


								</div>
							</div>
						</div>
				</div>
					<div class="col-xs-12 col-sm-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-green4">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span> Tweets por Fecha
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
										<div id="container1" style="height: 500px; min-width: 310px"></div>
									</div>


								</div>
							</div>
						</div>
				</div>
					<div class="col-xs-12 col-sm-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-green4">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span> Tweets por D&iacute;a
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
										<div id="container2" style="height: 500px; min-width: 310px"></div>
									</div>


								</div>
							</div>
						</div>
				</div>
									<div class="col-xs-12 col-sm-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-green4">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span> Tweets por Hora
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
										<div id="container3" style="height: 500px; min-width: 310px"></div>
									</div>


								</div>
							</div>
						</div>
				</div>
									<div class="col-xs-12 col-sm-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-green4">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span> Tweets por Minuto
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
										<div id="container4" style="height: 500px; min-width: 310px"></div>
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
		  activeItemMenuLevel3('concepts','${concept.id}','${concept.id}-stats','Conceptos > '+"${concept.conceptName}");
		  $.getJSON('http://localhost:8080/PrismaNet/concept/conceptsMinuteRealTime/'+"${concept.conceptName}", 
			function(data) {
			  printRealTimeChar(data);
		});
<%-- 			$.getJSON('http://localhost:8080/PrismaNet/concept/conceptsDateJson/'+"${concept.conceptName}",  --%>
// 				function(data) {
// 				printCharDate(data);
// 			});
<%-- 			$.getJSON('http://localhost:8080/PrismaNet/concept/conceptsDayJson/'+"${concept.conceptName}",  --%>
// 					function(data) {
// 				printCharDay(data.data,data.cat);
// 				});
<%-- 			$.getJSON('http://localhost:8080/PrismaNet/concept/conceptsHourJson/'+"${concept.conceptName}",  --%>
// 					function(data) {
// 				printCharHora(data);
// 				});
<%-- 			$.getJSON('http://localhost:8080/PrismaNet/concept/conceptsMinuteJson/'+"${concept.conceptName}",  --%>
// 					function(data) {
// 				printCharMinute(data.data,data.cat);
// 				});
		});
	  function printRealTimeChar(dataJson){
	        Highcharts.setOptions({
	            global: {
	                useUTC: false
	            }
	        });
	    
	        var chart;
	        $('#realTimeChar').highcharts({
	            chart: {
	                type: 'spline',
	                animation: Highcharts.svg, // don't animate in old IE
	                marginRight: 10,
	                events: {
	                    load: function() {
	    
	                        // set up the updating of the chart each second
	                        var series = this.series[0];
	                        setInterval(function() {
	                            var x = (new Date()).getTime(), // current time
	                                y = Math.floor((Math.random()*100)+1)
	                            series.addPoint([x, y], true, true);
	                        }, 60000);
	                    }
	                }
	            },
	            title: {
	                text: 'Tweets por minuto'
	            },
	            subtitle: {
	                text: 'Actualización en tiempo Real de la cantidad de Tweets del concepto'
	            },
	            xAxis: {
	            	title: {
	                    text: 'Minutos'
	                },
	                type: 'datetime',
	                tickPixelInterval:60,
	                plotLines: [{
	                    value:0,
	                }]
	            },
	            yAxis: {
	                title: {
	                    text: 'Cantidad de Tweets'
	                },
	                plotLines: [{
	                    value:0,
	                    width: 1,
	                    color: '#808080'
	                }]
	            },
	            tooltip: {
	                formatter: function() {
	                        return '<b> '+this.y+'<b> tweets a las '+ Highcharts.dateFormat('%H:%M', this.x) ;
	                }
	            },
	            legend: {
	                enabled: false
	            },
	            exporting: {
	                enabled: false
	            },
	            plotOptions: {
	                spline: {
	                    lineWidth: 4,
	                    states: {
	                        hover: {
	                            lineWidth: 5
	                        }
	                    },
	                    pointInterval: 60000, // one hour
	                }
	            },
	            series: [{
	                name: 'Tweets por minuto',
	                data: 
	                	(function() {
	                    // generate an array of random data
	                    var data = [],
	                        time = (new Date()).getTime(),
	                        i; count=0; value=0;
	                    for (i = -19; i <= 0; i++) {
	                    	value=dataJson[count];
	                        data.push({
	                            x: time + i * 60000,
	                            y: value
                                });
	                        count++;
	                    }
	                    return data;
	                    
	                })()
	            }
	            ]
	        });
	  }
	  function printCharDay(data,cat){
		  $('#container2').highcharts({
	            chart: {
	                type: 'spline'
	            },
	            title: {
	                text: 'Tweets por dia'
	            },
	            yAxis: {
	                title: {
	                    text: 'Cantidad de tweets'
	                },
	                labels: {
	                    formatter: function() {
	                        return this.value ;
	                    }
	                }
	            },
	            xAxis: {
	                categories:cat
	            },
	            tooltip: {
	                formatter: function() {
	                	 return '<b>'+ this.series.name +'</b> '+ this.y;
	                }
	            },
	            
	            series: [{
	                name: 'Tweets',
	                data: data
	            } ]
	        });
	  }
	  function printCharHora(data){
		  $('#container3').highcharts({
	            chart: {
	                type: 'spline'
	            },
	            title: {
	                text: 'Tweets por hora'
	            },
	            subtitle: {
	                text: 'Cantidad de tweets de las ultimas 24 horas'
	            },
	            xAxis: {
	            	 title: {
		                    text: 'Horas del dia'
		                },
	                labels: {
	                    formatter: function() {
	                        return this.value; // clean, unformatted number for year
	                    }
	                }
	            },
	            yAxis: {
	                title: {
	                    text: 'Cantidad de tweets'
	                },
	                labels: {
	                    formatter: function() {
	                        return this.value ;
	                    }
	                }
	            },
	            tooltip: {
	                formatter: function() {
	                	 return '<b>'+ this.series.name +'</b> '+ this.y;
	                }
	            },
	            
	            series: [{
	                name: 'Tweets',
	                // Define the data points. All series have a dummy year
	                // of 1970/71 in order to be compared on the same x axis. Note
	                // that in JavaScript, months start at 0 for January, 1 for February etc.
	                data: data
	            } ]
	        });
	  }
	  function printCharMinute(data,cat){
		  $('#container4').highcharts('StockChart',{
	            chart: {
	                type: 'spline'
	            },
	            title: {
	                text: 'Tweets por minuto'
	            },
	            subtitle: {
	                text: 'Cantidad de tweets de las ultimas 24 horas'
	            },
	            xAxis: {
	                categories:cat
	            },
	            yAxis: {
	                title: {
	                    text: 'Cantidad de tweets'
	                },
	                tickInterval: 1
	            },
	            tooltip: {
	                formatter: function() {
	                        return '<b>'+ this.series.name +'</b> '+ this.y;
	                }
	            },
	            
	            series: [{
	                name: 'Tweets',
	                // Define the data points. All series have a dummy year
	                // of 1970/71 in order to be compared on the same x axis. Note
	                // that in JavaScript, months start at 0 for January, 1 for February etc.
	                data: data
	            } ]
	        });
	  }
      function printCharDate(data){
    	// Create the chart
			$('#container1').highcharts('StockChart', {
				rangeSelector : {
					selected : 1
				},
				title : {
					text : 'Tweets por fecha'
				},
	            yAxis: {
	                title: {
	                    text: 'Cantidad de tweets'
	                },
	                tickInterval: 1
	            },
				series : [{
					name : 'Tweets',
					data : data,
					type : 'spline',
					threshold : null,
					tooltip : {
						valueDecimals : 0
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
