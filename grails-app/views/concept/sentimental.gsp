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
				<g:render contextPath="../user" template="chart" model="['divPickert':'sentimentalPickert','callback':'disable','titleChar':'user.stats.tweets.date','div':'sentimentalChart']"></g:render>
				<g:render contextPath="../user" template="chart" model="['divPickert':'disable','callback':'disable','titleChar':'user.stats.tweets.date','div':'sentimentChartPie']"></g:render>
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
			  activeItemMenuLevel2(id,id+'-tweet','${concept.conceptName}');
			   
			  var dataSentiment = {"id":id, "div":'#sentimentalChart',"dateFrom":moment().subtract('days', 29).format('L HH:mm'),"dateTo":moment().format('L HH:mm')};
			  getSentimentalAnalitycs(dataSentiment);
			  getSentimentChartPie(id, '#sentimentChartPie');
			
			});
	</script>
</body>
</html>