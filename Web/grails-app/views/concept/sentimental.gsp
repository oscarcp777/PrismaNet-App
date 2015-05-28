<%@ page import="com.prismanet.sentiment.OpinionValue"%>
<%@ page import="com.prismanet.sentiment.Opinion"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="home">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'twitter.css')}" type="text/css">
</head>
<body>
<g:set var="conceptName" value="${concept.conceptName}" />
	<g:render template="tabTwitter"  model="['concept':concept,'tabMain':'','tabTweets':'','tabChar':'','tabSentimental':'active','tabSampling':'']"></g:render>

	  <div class="row bg-well">
		<div class="col-lg-12">
		<g:render contextPath="../user" template="headerHelp" model="['mainMessage':'dashborad.tab.sentimental.desc']"></g:render>
				<g:render contextPath="../user" template="chart" model="['divPickert':'sentimentalPickert','callback':'disable','titleChar':'dashborad.tab.sentimental.line','div':'sentimentalChart','contentHelp':'dashborad.tab.sentimental.line.help','icon':'line-chart']"></g:render>
				<g:render contextPath="../user" template="chart" model="['divPickert':'disable','callback':'disable','titleChar':'dashborad.tab.sentimental.pie','div':'sentimentChartPie','contentHelp':'dashborad.tab.sentimental.pie.help','icon':'pie-chart']"></g:render>
		</div>
	</div>
	<script type="text/javascript">
		function loadChartLineForSentimentalPickert(start, end, rangeSelect) {
	    	 loadFormatLLL('#sentimentalPickert',start, end, rangeSelect);
	    	 var data = {"id":${concept.id}, "div":'#sentimentalChart',"dateFrom":start.format('L HH:mm'),"dateTo":end.format('L HH:mm')};
		     getSentimentalAnalitycs(data);
		}

		 $(function() {
			 var id='${concept.id}';
			  loadDatepicker('sentimentalPickert',loadChartLineForSentimentalPickert,setDatesHtml);
			  activeItemMenuLevel2(id,id+'-tweet','${concept.conceptName}');
			   
			  var dataSentiment = {"id":id, "div":'#sentimentalChart',"dateFrom":getDateFromLHH(),"dateTo":getDateNowLHH()};
			  getSentimentalAnalitycs(dataSentiment);
			  getSentimentChartPie(id, '#sentimentChartPie');
			
			});
	</script>
</body>
</html>