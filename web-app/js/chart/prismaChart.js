var doRequest = function(url,data,callback, errorHandler, method) {
	      $.ajax({
	              url: url,
	              type: method,
	              data: data,
	              cache: false,
	              beforeSend: function( xhr ) {
	            	  var message='<div class="widget-box-overlay"><div  class="wait white">Aguarde unos instantes por favor...'+
          						   '<br><i class="fa fa-spinner fa-spin fa-4x"></i></div></div>';
	            	   $(data.div).append(message);
	            	  },
	              success: callback,
	              error: errorHandler
	            });
};

function inicializeColorChar(){
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
}

function getTweetCharPie(div,channel){
	var data = {"div":div,"channel":channel}
	doRequest('conceptTweetsJson',data,paintCharPie, null, 'GET');
}

function getTotalFollowers(div){
	var data = {"div":div}
	doRequest('totalFollowers',data,paintCharPie, null, 'GET');
}
function getSentimentChartPie(id, div){
	var data = {"id":id, "div":div}
	doRequest('../sentimentChartPie',data,paintCharPie, null, 'GET');
}


function getConceptRealTime(id, div,level){
	var data = {"id":id, "div":div}
	doRequest(level+'conceptsRealTime',data,printRealTimeChar, null, 'GET');
}

function getPostRealTime(id, div,level){
	var data = {"id":id, "div":div}
	doRequest(level+'postRealTime',data,printRealTimeChar, null, 'GET');
}


function getGroupedTweets(data){
	doRequest('../getGroupedTweets',data,paintCharLine, null, 'GET');
}
function getGroupedWeight(data){
	doRequest('../getGroupedWeight',data,paintCharLine, null, 'GET');
}
function getRelevantAuthors(data){
	doRequest('../getRelevantAuthors',data,loadAuthors, null, 'GET');
}
function getUserGroupedTweets(data){
	doRequest('getGroupedTweets',data,paintCharLine, null, 'GET');
}


function getGroupedPosts(data){
	doRequest('../getGroupedPosts',data,paintCharLine, null, 'GET');
}

function getSentimentalAnalitycs(data){
	doRequest('../sentimentalAnalitycs',data,paintCharLine, null, 'GET');
}


function paintCharPie(dataJson) {
	$(dataJson.container)
			.highcharts(
					{
						chart : {
							 animation: Highcharts.svg, // don't animate in old IE
					            plotShadow: true,
					            plotBorderWidth: 2
						},
						title : {
							text : dataJson.title
						},
						tooltip : {
							pointFormat : '{series.name}: <b>{point.y}</b>'
						},
						plotOptions : {
							pie : {
								allowPointSelect : true,
								showInLegend: true,
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
							name : dataJson.name,
							data : dataJson.data
						} ]
					});

}
function printRealTimeChar(data){
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });

    var chart;
    $(data.container).highcharts({
        chart: {
            type: 'spline',
            animation: Highcharts.svg, // don't animate in old IE
            plotShadow: true,
            plotBorderWidth: 2,
            events: {
                load: function() {

                    // set up the updating of the chart each second
                    var series = this.series;
                    setInterval(function() {
                    	var dataNew = {"id":data.id};
                    	var element;
                    	doRequest(data.ajaxMethodReload,
                    			  dataNew,
                    			 function(data) {
                	        			for ( var int = 0; int < series.length; int++) {
                	        				for ( var i = 0; int < data.length; i++) {
                	        					if(series[int].name == data[i].name){
                                    			 element = data[int].data;
                                    			 series[int].addPoint(element[0], true, true);
                                    			 break;
                                    		 }
                                	       }
                	        				
                	        			}
                    			}
                    			, null, 
                    			'GET');
                       
                    },60000);
                }
            }
        },
        title: {
            text:data.title
        },
        subtitle: {
            text: data.subTitle
        },
        xAxis: {
        	gridLineWidth: 1,
        	title: {
                text:data.titleX
            },
            type: 'datetime',
            labels: {
                overflow: 'justify'
           }
        },
        yAxis: {
            title: {
                text: data.titleY
            }
        },
        tooltip: {
            formatter: function() {
                    return "Click para ver los <b> "+this.y+'</b> tweets de las '+ Highcharts.dateFormat('%H:%M', this.x) ;
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
            series: {
                cursor: 'pointer',
                point: {
                    events: {
                        click: function() {
                        	window.location.href=data.cursorEvent+data.id+"&"+data.dateProp+"="+this.x+'&conceptName='+this.series.name;;
                        }
                    }
                }
            }
        },
        series: data.series
        
    });
}

function paintCharLine(data){
	Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });
	if (data.serieX != null){
	 $(data.container).highcharts({
	        chart: {
	            type: 'spline',
	            animation: Highcharts.svg, // don't animate in old IE
	            plotShadow: true,
	            plotBorderWidth: 2
	        },
	        title: {
	            text:data.title
	        },
	        subtitle: {
	            text: data.subTitle
	        },
	        xAxis: {
	       		categories: data.serieX,
	        	gridLineWidth: 1,
	        	title: {
	                text:data.titleX
	            },
	            type: 'datetime',
                labels: {
                    overflow: 'justify'
                }
	        },
	        yAxis: {
	            title: {
	                text: data.titleY
	            }
	        },
	        tooltip: {
	        	crosshairs: true,
		        shared: false
	        },
	        legend: {
	            enabled: true
	        },
	        exporting: {
	            enabled: true
	        },
	        plotOptions: {
	            series: {
	                cursor: 'pointer',
	                point: {
	                    events: {
	                        click: function() {
	                        	window.location.href=data.cursorEvent+this.x+'&conceptName='+this.series.name;
	                        }
	                    }
	                }
	            }
	        },
	        series: data.series
	    });
	}else{
		 $(data.container).highcharts({
		        chart: {
		            type: 'spline',
		            animation: Highcharts.svg, // don't animate in old IE
		            plotShadow: true,
		            plotBorderWidth: 2
		        },
		        title: {
		            text:data.title
		        },
		        subtitle: {
		            text: data.subTitle
		        },
		        xAxis: {
		        	gridLineWidth: 1,
		        	title: {
		                text:data.titleX
		            },
		            type: 'datetime',
	                labels: {
	                    overflow: 'justify'
	                }
		        },
		        yAxis: {
		            title: {
		                text: data.titleY
		            }
		        },
		        tooltip: {
		            formatter: function() {
	                    return Highcharts.dateFormat('%d/%m/%Y--%H:%M', this.x) + "<br><b>"+ this.series.name + ": </b>" + this.y;
		            },
		            crosshairs: true,
			        shared: false
		        },
		        legend: {
		            enabled: true
		        },
		        exporting: {
		            enabled: true
		        },
		        plotOptions: {
		            series: {
		                cursor: 'pointer',
		                point: {
		                    events: {
		                        click: function() {
		                        	window.location.href=data.cursorEvent+this.x+'&conceptName='+this.series.name;
		                        }
		                    }
		                }
		            }
		        },
		        series: data.series
		    });

	}
}





function loadAuthors(data){
	$("#relevantAuthors").html(data);
}	