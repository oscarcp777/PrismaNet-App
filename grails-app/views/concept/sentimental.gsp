<%@ page import="com.prismanet.sentiment.OpinionValue"%>
<%@ page import="com.prismanet.sentiment.Opinion"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="home">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'twitter.css')}" type="text/css">
</head>
<body>
	
	<g:render template="tabTwitter"  model="['concept':concept,'tabMain':'','tabTweets':'','tabChar':'','tabSentimental':'active']"></g:render>

	<div class="page-content">
		
		<div class="row">
			<div class="col-lg-12">
				<div class="row">
					<div class="col-xs-12 col-sm-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-blue">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span> 
									<g:message code="user.stats.tweets.date"/>
								</h5>
								<div class="widget-toolbar input-group ">
									<div id="sentimentalPickert" class="btn btn-primary date-picker">
										<i class="fa fa-calendar"></i> <span class="date-range"></span>
										<i class="fa fa-chevron-down"></i>
									</div>
								</div>
								<div class="widget-toolbar">
									<a href="#" data-action="collapse"> <i
										class="1 bigger-125 fa fa-chevron-up"></i>
									</a>

								</div>


							</div>

							<div class="widget-body">
								<div class="widget-body-inner" style="display: block;">
									<div class="widget-main">
										<div id="sentimentalChart" style="height: 400px; min-width: 310px"></div>
									</div>


								</div>
							</div>
						</div>
					</div>
					</div>
					<div class="col-lg-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-blue">
								<h5>
									<span class="glyphicon glyphicon-stats"></span> 
									<g:message code="user.stats.tweets.concepts"/>
								</h5>

								<div class="widget-toolbar">
									<a href="javascript:void(0);" id="refreshCharPie"> <span
										data-action="reload"> <i class="fa fa-refresh"></i></span>
									</a> <a href="javascript:void(0);" data-action="collapse"> <i
										class="fa fa-chevron-up"></i></a> <a href="javascript:void(0);"
										data-action="close"> <i class="fa fa-remove"></i></a>
								</div>
							</div>

							<div class="widget-body">
								<div class="widget-main padding-4">
									<div id="sentimentChartPie" style="width: 100%; min-width: 300px;"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
		</div>
	</div>
	<script type="text/javascript">
		function loadChartLineForSentimentalPickert(start, end, rangeSelect) {
	    	 $('#sentimentalPickert span').html(rangeSelect+' - '+start.format('LLLL') + ' - ' + end.format('LLLL'));
		     var data = {"id":${concept.id}, "div":'#sentimentalChart',"dateFrom":start.format('L HH:mm'),"dateTo":end.format('L HH:mm')};
		     getSentimentalAnalitycs(data);
		}

		 $(function() {
			 var id='${concept.id}';
			  loadDatepicker('sentimentalPickert',loadChartLineForSentimentalPickert);
			  activeItemMenuLevel2(id,id+'-sentiment');
			   
			  var dataSentiment = {"id":id, "div":'#sentimentalChart',"dateFrom":moment().subtract('days', 29).format('L HH:mm'),"dateTo":moment().format('L HH:mm')};
			  getSentimentalAnalitycs(dataSentiment);

			  getSentimentChartPie(id, '#sentimentChartPie');
			
			});
	</script>
</body>
</html>