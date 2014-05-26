<html>
<head>
<meta name="layout" content="home" />
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'twitter.css')}" type="text/css">
</head>
<body>
	<g:render template="tabTwitter" model="['concept':concept,'tabMain':'','tabTweets':'','tabChar':'active','tabSentimental':'']"></g:render>

	<div class="page-content">
		<div class="row">
			<div class="col-lg-12">
				<g:render contextPath="../user" template="chart" model="['divPickert':'disable','callback':'disable','titleChar':'user.stats.tweets.real.time','div':'realTimeChar']"></g:render>
				<g:render contextPath="../user" template="chart" model="['divPickert':'tweetPickert','callback':'disable','titleChar':'user.stats.tweets.date','div':'tweetsChart']"></g:render>
				<g:render contextPath="../user" template="chart" model="['divPickert':'weightPickert','callback':'disable','titleChar':'concept.stats.scope.date','div':'weightChart']"></g:render>
				<g:render contextPath="../user" template="chart" model="['divPickert':'authorPickert','callback':'disable','titleChar':'concept.author.more.followers','div':'relevantAuthors']"></g:render>
			</div>
		</div>
	</div>


	<script type="text/javascript">

		function loadChartLineForTweetPickert(start, end, rangeSelect) {
		     $('#tweetPickert span').html(rangeSelect+' - '+start.format('LLLL') + ' - ' + end.format('LLLL'));
   		     var data = {"id":${concept.id}, "div":'#tweetsChart',"dateFrom":start.format('L HH:mm'),"dateTo":end.format('L HH:mm')};
   		 	 getGroupedTweets(data);
		}

		function loadChartLineForWeightPickert(start, end, rangeSelect) {
		     $('#weightPickert span').html(rangeSelect+' - '+start.format('LLLL') + ' - ' + end.format('LLLL'));
  		     var data = {"id":${concept.id}, "div":'#weightChart',"dateFrom":start.format('L HH:mm'),"dateTo":end.format('L HH:mm')};
  		   	 getGroupedWeight(data);
		}

		function loadAuthorPickert(start, end, rangeSelect) {
		     $('#authorPickert span').html(rangeSelect+' - '+start.format('LLLL') + ' - ' + end.format('LLLL'));
 		     var data = {"id":${concept.id}, "div":'#relevantAuthors',"dateFrom":start.format('L HH:mm'),"dateTo":end.format('L HH:mm')};
 		     getRelevantAuthors(data);
		}
    
	  $(function() {
		  var id='${concept.id}';
		  loadDatepicker('tweetPickert',loadChartLineForTweetPickert);
		  loadDatepicker('weightPickert',loadChartLineForWeightPickert);
		  loadDatepicker('authorPickert',loadAuthorPickert);
		  
		  	activeItemMenuLevel2(id,'${concept.id}-tweet','${concept.conceptName}');
		  	loadRealTimeConcept(${concept.id});
		   
		   var dataTweets = {"id":id, "div":'#tweetsChart',"dateFrom":moment().subtract('days', 29).format('L HH:mm'),"dateTo":moment().format('L HH:mm')};
		   getGroupedTweets(dataTweets);
		   var dataWeight = {"id":id, "div":'#weightChart',"dateFrom":moment().subtract('days', 29).format('L HH:mm'),"dateTo":moment().format('L HH:mm')};
		   getGroupedWeight(dataWeight);
		   var dataAuthors = {"id":id, "div":'#relevantAuthors',"dateFrom":moment().subtract('days', 29).format('L HH:mm'),"dateTo":moment().format('L HH:mm')};
		   getRelevantAuthors(dataAuthors);

		});
	</script>
</body>
</html>
