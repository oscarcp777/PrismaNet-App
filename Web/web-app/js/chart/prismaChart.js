Highcharts.setOptions({
	lang: {
		months: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',  'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
		weekdays: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Sabado', 'Domingo'],
		shortMonths: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun',  'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
		downloadJPEG:'Descargar JPEG',
		downloadPDF:'Descargar PDF',
		downloadPNG:'Descargar PNG',
		downloadSVG:'Descargar SVG',
		printChart:'Imprimir Gr\u00e1fico',
		contextButtonTitle:'Men\u00fa Imprimir'
	}
});
var doRequest = function(url,data,callback, errorHandler, method) {
	      $.ajax({
	              url: url,
	              type: method,
	              div:data.div,
	              data: data,
	              cache: false,
	              beforeSend: function( xhr ) {
	            	  var message='<div class="widget-box-overlay" style="top:43px; min-height:90%;right:15px; left:15px;"><div class="white" style="margin-top:100px;">Aguarde unos instantes por favor...'+
          						   '<br><i class="loading-icon ace-icon fa fa-cog fa-spin fa-4x white"></i></div></div>';
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
			radialGradient : {cx : 0.5,cy : 0.3,r : 0.7},
			stops : [
					[ 0, color ],
					[1,Highcharts.Color(color).brighten(-0.3).get('rgb') ] // darken
			]
		};
	});
}
function setDataMenu(context,state){
	var data = {"state":state}
	doRequest(context+'/home/setDataMenu',data,null, null, 'GET');
}
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
	doRequest('../getRelevantAuthors',data,loadHtmlTable, null, 'GET');
}
function getUserGroupedTweets(data){
	doRequest('getGroupedTweets',data,paintCharLine, null, 'GET');
}

function loadMentionAuthorData(channel){
	loadDatepicker('pickertMentionAuthor',loadMentionAuthorPickert,setDatesHtml);
	var data = {"channel":channel, "div":'#mentionAuthor', "dateFrom":getDateFromLHH(),"dateTo":getDateNowLHH()};
	getMentionAuthor(data);
	$('#mentionAuthor').data('channel',channel);
}

function loadPostStatsData(){
	loadDatepicker('pickertStatsPost',loadStatsPostPickert,setDatesHtml);
	var data = {"div":'#postStats', "dateFrom":getDateFromLHH(),"dateTo":getDateNowLHH()};
	getPostStats(data);
}


function getMentionAuthor(data){
	doRequest('mentionAuthor',data,loadHtmlTable, null, 'GET');
}

function getPostStats(data){
	doRequest('postsStats',data,loadHtmlTable, null, 'GET');
}

function loadMentionAuthorPickert(start, end, rangeSelect) {
    loadFormatLLL('#pickertStatsMonth',start, end, rangeSelect);
    var data = {"div":'#mentionAuthor',"dateFrom":start.format('L HH:mm'),"dateTo":end.format('L HH:mm')};
    getMentionAuthor(data);
}

function loadStatsPostPickert(start, end, rangeSelect) {
    loadFormatLLL('#pickertStatsPost',start, end, rangeSelect);
    var data = {"div":'#postStats',"dateFrom":start.format('L HH:mm'),"dateTo":end.format('L HH:mm')};
    getPostStats(data);
}


function printWordCloud(data){
	if(data.json.length>0){
		var htmlTable;
		for (i = 0; i < data.mapWords.length; i++) { 
			htmlTable +='<tr><td>'+data.mapWords[i].text + "</td><td id="+data.mapWords[i].text+'TB'+">"+data.mapWords[i].size+'</tr></td>';
		}
		$('#tableWords').html(htmlTable);
		$(data.div).empty();
		WordCloud($(data.div)[0], { list: data.json } );
		 $('#btn-collapse').click();
	}
}
function getWordsCloud(data){
	doRequest('wordsCloud',data,printWordCloud, null, 'GET');
}
function getGroupedPosts(data){
	doRequest('../getGroupedPosts',data,paintCharLine, null, 'GET');
}
function getPostMoreLikes(data){
	doRequest('../getPostMoreLikes',data,loadHtmlTable, null, 'GET');
}

function getSentimentalAnalitycs(data){
	doRequest('../sentimentalAnalitycs',data,paintCharLine, null, 'GET');
}
function loadTweetCharPie(channel){
	changeTitle(channel,'#title-loadTweetCharPie');
	loadDatepicker('pickertTweetConcept',loadTweetCharPiePickert,setDatesHtml);
	var data = {'channel':channel, "div":'#tweetCharPie',"dateFrom":getDateFromLHH(),"dateTo":getDateNowLHH()};
	getTweetCharPie(data);
	$('#tweetCharPie').data('channel',channel);
}
function loadTweetCharPiePickert(start, end,rangeSelect) {
    loadFormatLLL('#pickertTweetConcept',start, end, rangeSelect);
    var data = { 'channel':$('#tweetCharPie').data('channel'),"div":'#tweetCharPie',"dateFrom":start.format('L HH:mm'),"dateTo":end.format('L HH:mm')};
    getTweetCharPie(data);
}
function changeTitle(channel,idTitle){
	if(channel=='TWITTER'){
		$(idTitle).text($(idTitle).text().replace('Comentarios','Tweets'));
		$(idTitle).text($(idTitle).text().replace('Menciones','Tweets'));
	}else if(channel=='FACEBOOK'){
		$(idTitle).text($(idTitle).text().replace('Tweets','Comentarios'));
		$(idTitle).text($(idTitle).text().replace('Menciones','Comentarios'));
	}else if(channel=='ALL'){
		$(idTitle).text($(idTitle).text().replace('Tweets','Menciones'));
		$(idTitle).text($(idTitle).text().replace('Comentarios','Menciones'));
	}

}
function loadRealTime(channel){
	var data = {"div":'#realTimeCharUser','channel':channel};
	changeTitle(channel,'#title-loadRealTime');
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
    loadFormatLLL('#pickertUser',start, end, rangeSelect);
    var data = { 'channel':$('#lineaChartUser').data('channel'),"div":'#lineaChartUser',"dateFrom":start.format('L HH:mm'),"dateTo":end.format('L HH:mm')};
    getUserGroupedTweets(data);
}


function paintCharPie(dataJson) {
	if(dataJson.data.length==0){
		$(dataJson.container+'-up').trigger("click");
	}
	$(dataJson.container)
			.highcharts(
					   {
						chart: {
			                plotBackgroundColor: null,
			                plotBorderWidth: null,
			                plotShadow: true
			            },
						title : {
							text : dataJson.title
						},
						tooltip: {
			        	    pointFormat: '{series.name}: <b>{point.y}</b>'
			            },
			            plotOptions: {
			                pie: {
			                    allowPointSelect: true,
			                    cursor: 'pointer',
			                    dataLabels: {
			                        enabled: true,
			                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
			                        style: {
			                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
			                        }
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
                    	var dataNew = {"channel":data.channel,"id":data.id};
                    	var element;
                    	doRequest(data.ajaxMethodReload,
                    			  dataNew,
                    			 function(data) {
                	        			for ( var j = 0; j < series.length; j++) {
                	        				for ( var i = 0; i < data.length; i++) {
                	        					if(series[j].name == data[i].name){
                                    			 element = data[i].data;
                                    			 series[j].addPoint(element[0], true, true);
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
	if(data.series.length==0){
		$(data.container+'-up').trigger("click");
	}
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
	                        	window.location.href=data.cursorEvent+this.options.url+'&conceptName='+this.series.name;
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


function loadHtmlTable(data) {
	if($.trim($(data).find("tbody").html())==''){
		$(this.div+'-up').trigger("click");
	}
	$(this.div).html(data);
}

	
function getParamsCloud(params,div,id){
	var listParams=params.replace(/:/g,',').replace(/ /g,'').replace('[','').replace(']','').split(',');
	var data={"div":div,"conceptsId":id};
	for (i = 0; i < listParams.length-1; i++) { 
		if(listParams[i]!='id'){
		   data[listParams[i]]=listParams[i+1]
		}
		i++;
	}
	return data
}



