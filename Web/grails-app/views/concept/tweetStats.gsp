<%@ page import="com.prismanet.ConceptService.AuthorOrderType"%>
<html>
<head>
<meta name="layout" content="home" />
<link rel="stylesheet" href="${resource(dir: 'css', file: 'twitter.css')}" type="text/css">
<r:require module="footable" />
</head>
<body>
	<g:set var="conceptName" value="${concept.conceptName}" />
	<g:render template="tabTwitter" model="['concept':concept,'tabMain':'','tabTweets':'','tabChar':'active','tabSentimental':'','tabSampling':'']"></g:render>
  <div class="row bg-well">
			<div class="col-lg-12">
			     <g:render contextPath="../user" template="headerHelp" model="['mainMessage':'dashborad.tab.tweets.desc']"></g:render>
			    <g:render contextPath="../user" template="chart" model="['divPickert':'authorPickert','callback':'disable','titleChar':'concept.author.more.followers','div':'relevantAuthors','contentHelp':'concept.author.more.followers.help','icon':'table']"></g:render>
				<g:render contextPath="../user" template="chart" model="['divPickert':'disable','callback':'disable','titleChar':'user.stats.tweets.real.time','div':'realTimeChar','contentHelp':'user.stats.tweets.real.time.help2','icon':'line-chart']"></g:render>
				<g:render contextPath="../user" template="chart" model="['divPickert':'tweetPickert','callback':'disable','titleChar':'user.stats.tweets.date','div':'tweetsChart','contentHelp':'user.stats.tweets.date.help2','icon':'line-chart']"></g:render>
				<g:render contextPath="../user" template="chart" model="['divPickert':'weightPickert','callback':'disable','titleChar':'concept.stats.scope.date','div':'weightChart','contentHelp':'concept.stats.scope.date.help','icon':'line-chart']"></g:render>
				
			</div>
		</div>


	<script type="text/javascript">

		function loadChartLineForTweetPickert(start, end, rangeSelect) {
		     loadFormatLLL('#tweetPickert',start, end, rangeSelect);
		     var data = {"id":${concept.id}, "div":'#tweetsChart',"dateFrom":start.format('L HH:mm'),"dateTo":end.format('L HH:mm')};
   		 	 getGroupedTweets(data);
		}

		function loadChartLineForWeightPickert(start, end, rangeSelect) {
		     loadFormatLLL('#weightPickert',start, end, rangeSelect);
		     var data = {"id":${concept.id}, "div":'#weightChart',"dateFrom":start.format('L HH:mm'),"dateTo":end.format('L HH:mm')};
  		   	 getGroupedWeight(data);
		}

		function loadAuthorPickert(start, end, rangeSelect) {
		     loadFormatLLL('#authorPickert',start, end, rangeSelect);
		     var data = {"id":${concept.id}, "div":'#relevantAuthors',"dateFrom":start.format('L HH:mm'),"dateTo":end.format('L HH:mm'),"type":$('input[name=filterAuthor]:checked').val(),"cantReg":$("#cantReg").val()};
 		     getRelevantAuthors(data);
		}
		function loadDataAuthor(type){
			var drp = $('#authorPickert').data('daterangepicker');
			  var dataAuthors = {"id":${concept.id}, "div":'#relevantAuthors',"dateFrom":drp.startDate.format('L HH:mm'),"dateTo":drp.endDate.format('L HH:mm'),"type":type,"cantReg":$("#cantReg").val()};
		 	   getRelevantAuthors(dataAuthors);
		  }
	  $(function() {
		  var id='${concept.id}';
		  loadDatepicker('tweetPickert',loadChartLineForTweetPickert,setDatesHtml);
		  loadDatepicker('weightPickert',loadChartLineForWeightPickert,setDatesHtml);
		  loadDatepicker('authorPickert',loadAuthorPickert,setDatesHtml);
		  
		  	activeItemMenuLevel2(id,'${concept.id}-tweet','${concept.conceptName}');
		  	loadRealTimeConcept(${concept.id});
		   
		   var dataTweets = {"id":id, "div":'#tweetsChart',"dateFrom":getDateFromLHH(),"dateTo":getDateNowLHH()};
		   getGroupedTweets(dataTweets);
		   var dataWeight = {"id":id, "div":'#weightChart',"dateFrom":getDateFromLHH(),"dateTo":getDateNowLHH()};
		   getGroupedWeight(dataWeight);
		   var dataAuthors = {"id":id, "div":'#relevantAuthors',"dateFrom":getDateFromLHH(),"dateTo":getDateNowLHH(),"type":'${AuthorOrderType.BY_RELEVANT_AUTHORS}',"cantReg":$("#cantReg").val()};
		   getRelevantAuthors(dataAuthors);

		});
	</script>
</body>
</html>
