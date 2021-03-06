<html>
<head>
<meta name="layout" content="home" />
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'twitter.css')}" type="text/css">
	
</head>
<body>
		<g:set var="conceptName" value="${concept.conceptName}" />
		<g:render template="tabFacebook"  model="['concept':concept,'tabMain':'','tabPosts':'','tabChar':'active','tabSentimental':'']"></g:render>
		
		
  <div class="row bg-well">
			<div class="col-lg-12">
		<g:render contextPath="../user" template="headerHelp" model="['mainMessage':'dashborad.tab.posts.desc']"></g:render>
			 <g:render contextPath="../user" template="chart" model="['divPickert':'disable','callback':'disable','titleChar':'user.stats.posts.real.time','div':'postRealTimeChar','contentHelp':'user.stats.posts.real.time.help','icon':'line-chart']"></g:render>
			 <g:render contextPath="../user" template="chart" model="['divPickert':'postPickert','callback':'disable','titleChar':'user.stats.posts.date','div':'postsChart','contentHelp':'user.stats.posts.date.help','icon':'line-chart']"></g:render>
			 <g:render contextPath="../user" template="chart" model="['divPickert':'pickertPostMoreLikes','callback':'disable','titleChar':'user.stats.post.stats','div':'postMoreLikes','contentHelp':'user.stats.post.stats.help','icon':'table']"></g:render>
			</div>
</div>	
	

	<script type="text/javascript">

		function loadChartLineForPostPickert(start, end, rangeSelect) {
		     loadFormatLLL('#postPickert',start, end, rangeSelect);
		     var data = {"id":${concept.id}, "div":'#postsChart',"dateFrom":start.format('L HH:mm'),"dateTo":end.format('L HH:mm')};
   		 	 getGroupedPosts(data);
		}
		function loadPostMoreLikesPickert(start, end, rangeSelect) {
		     loadFormatLLL('#pickertPostMoreLikes',start, end, rangeSelect);
		     var data = {"id":${concept.id}, "div":'#postMoreLikes',"dateFrom":start.format('L HH:mm'),"dateTo":end.format('L HH:mm')};
  		 	 getPostMoreLikes(data);
		}

		    
	  $(function() {
		  var id='${concept.id}';
		  loadDatepicker('postPickert',loadChartLineForPostPickert,setDatesHtml);
		  loadDatepicker('pickertPostMoreLikes',loadPostMoreLikesPickert,setDatesHtml);
		  
		  activeItemMenuLevel2(id,id+'-face','${concept.conceptName}');
		  
		   getPostRealTime(${concept.id}, '#postRealTimeChar','../');
		   
		   var dataPosts = {"id":${concept.id}, "div":'#postsChart',"dateFrom":getDateFromLHH(),"dateTo":getDateNowLHH()};
		   getGroupedPosts(dataPosts);
		   
		   var data = {"id":${concept.id}, "div":'#postMoreLikes',"dateFrom":getDateFromLHH(),"dateTo":getDateNowLHH()};
  	       getPostMoreLikes(data);

		});
	</script>
</body>
</html>
