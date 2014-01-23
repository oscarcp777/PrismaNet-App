function printRealTimeChar(dataJson,container,id){
	        Highcharts.setOptions({
	            global: {
	                useUTC: false
	            }
	        });
	    
	        var chart;
	        $('#'+container).highcharts({
	            chart: {
	                type: 'spline',
	                animation: Highcharts.svg, // don't animate in old IE
	                plotShadow: true,
	                plotBorderWidth: 2,
	                events: {
	                    load: function() {
	    
	                        // set up the updating of the chart each second
	                        var series = this.series[0];
	                        setInterval(function() {
	                             $.getJSON('http://localhost:8080/PrismaNet/concept/conceptsRealTimeForOneMinute/'+id, 
	                            		 	function(data) {
	                            	        for ( var int = 0; int < data.length; int++) {
												var element = data[int];
												series.addPoint(element, true, true);
											}
	                            	 		
	                             });
	                        }, 60000);
	                    }
	                }
	            },
	            title: {
	                text: 'Tweets por minuto'
	            },
	            subtitle: {
	                text: 'Actualizaci\u00f3n en tiempo Real de la cantidad de Tweets'
	            },
	            xAxis: {
	            	gridLineWidth: 1,
	                lineColor: '#1ABC9C',
	                tickColor: '#1ABC9C',
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
	                min:0
	            },
	            tooltip: {
	                formatter: function() {
	                        return '<b> '+this.y+'<b> tweets a las '+ Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) ;
	                },
	                crosshairs: true,
			        shared: true
	            },
	            legend: {
	                enabled: true
	            },
	            exporting: {
	                enabled: true
	            },
	            plotOptions: {
	                spline: {
	                    lineWidth: 4,
	                    states: {
	                        hover: {
	                            lineWidth: 6
	                        }
	                    },
	                    pointInterval: 60000, // one hour
	                }
	            },
	            series: [{
	                name: 'Tweets por minuto',
	                data: dataJson,
			    	marker: {
			    		fillColor: 'white',
			    		lineWidth: 3,
			    		lineColor: Highcharts.getOptions().colors[0]
			    	},
			    	zIndex: 2
	            }
	            ]
	        });
	  }

function getCharPie(){
	$.ajax({
        type: 'GET',
        url: 'conceptTweetsJson',
        success: paintCharPie
    })
}


function paintCharPie(dataJson) {
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
