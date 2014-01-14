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