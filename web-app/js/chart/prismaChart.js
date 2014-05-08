Highcharts.setOptions({
	lang: {
		months: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',  'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
		weekdays: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Sabado', 'Domingo'],
		shortMonths: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun',  'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic']
	}
});
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

//function inicializeColorChar(){
//	Highcharts.getOptions().colors = Highcharts.map(Highcharts
//			.getOptions().colors, function(color) {
//		return {
//			radialGradient : {cx : 0.5,cy : 0.3,r : 0.7},
//			stops : [
//					[ 0, color ],
//					[1,Highcharts.Color(color).brighten(-0.3).get('rgb') ] // darken
//			]
//		};
//	});
//}

function getTweetCharPie(data){
	doRequest('conceptTweetsJson',data,paintCharPie, null, 'GET');
}

function getTotalFollowers(data){
	doRequest('totalFollowers',data,paintCharPie, null, 'GET');
}
function getSentimentChartPie(id,div){
	var data = {"div":div,"id":id}
	doRequest('../sentimentChartPie',data,paintCharPie, null, 'GET');
}


function getConceptRealTime(data,level){
	doRequest(level+'conceptsRealTime',data,printRealTimeChar, null, 'GET');
}

function getPostRealTime(id,div,level){
	var data = {"div":div,"id":id}
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
function loadTweetCharPie(channel){
	var data = {"div":'#tweetCharPie',"channel":channel}
	getTweetCharPie(data);
}
function loadRealTime(channel){
	var data = {"div":'#realTimeCharUser','channel':channel}
	getConceptRealTime(data,'');
}
function loadRealTimeConcept(id){
	var data = {"div":'#realTimeChar',"id":id}
	getConceptRealTime(data,'../');
}
function loadTotalFollowers(channel){
	var data = {"div":'#totalFollowers',"channel":channel}
	getTotalFollowers(data);
}
function lineaChartUser(start, end,rangeSelect) {
    $('#pickertUser span').html(rangeSelect+' - '+start.format('LLLL') + ' - ' + end.format('LLLL'));
    var data = { "div":'#lineaChartUser',"dateFrom":start.format('L HH:mm'),"dateTo":end.format('L HH:mm')};
    getUserGroupedTweets(data);
}
function loadUserGroupedData(channel){
	loadDatepicker('pickertUser',lineaChartUser);
	var data = {'channel':channel, "div":'#lineaChartUser',"dateFrom":moment().subtract('days', 29).format('L HH:mm'),"dateTo":moment().format('L HH:mm')};
	getUserGroupedTweets(data);
}
function paintCharPie(dataJson) {
	$(dataJson.container)
			.highcharts(
					{
						chart: {
				            type: 'pie',
				            options3d: {
								enabled: true,
				                alpha: 45,
				                beta: 0
				            }
				        },
						title : {
							text : dataJson.title
						},
						tooltip: {
			        	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			            },
			            plotOptions: {
			                pie: {
			                    allowPointSelect: true,
			                    cursor: 'pointer',
			                    depth: 35,
			                    dataLabels: {
			                        enabled: true,
			                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
			                        style: {
			                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
			                        },
			                        connectorColor: 'silver'
			                    },
			                    showInLegend: true
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
            crosshairs: true
//	        shared: true
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