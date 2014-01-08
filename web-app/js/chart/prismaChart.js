function printRealTimeChar(dataJson,container){
	        Highcharts.setOptions({
	            global: {
	                useUTC: false
	            }
	        });
	    
	        var chart;
	        $('#'+container).highcharts({
	            chart: {
	                type: 'line',
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
	                        }, 6000000000);
	                    }
	                }
	            },
	            title: {
	                text: 'Tweets por minuto'
	            },
	            subtitle: {
	                text: 'Actualizaci—n en tiempo Real de la cantidad de Tweets'
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
	                data: dataJson
	            }
	            ]
	        });
	  }