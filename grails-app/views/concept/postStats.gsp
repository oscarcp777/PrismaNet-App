<html>
<head>
<meta name="layout" content="home" />
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'twitter.css')}" type="text/css">
	
</head>
<body>
		
		<g:render template="tabFacebook"  model="['concept':concept,'tabMain':'','tabPosts':'','tabChar':'active','tabSentimental':'']"></g:render>
		
	<div class="page-content">
		
		<div class="row">
			<div class="col-lg-12">
			 <g:render contextPath="../user" template="chart" model="['divPickert':'disable','callback':'disable','titleChar':'user.stats.posts.real.time','div':'postRealTimeChar']"></g:render>
			<g:render contextPath="../user" template="chart" model="['divPickert':'postPickert','callback':'disable','titleChar':'user.stats.posts.date','div':'postsChart']"></g:render>
			</div>
		</div>	
	</div>
	

	<script type="text/javascript">

		function loadChartLineForPostPickert(start, end, rangeSelect) {
		     $('#postPickert span').html(rangeSelect+' - '+start.format('LLLL') + ' - ' + end.format('LLLL'));
   		     var data = {"id":${concept.id}, "div":'#postsChart',"dateFrom":start.format('L HH:mm'),"dateTo":end.format('L HH:mm')};
   		 	 getGroupedPosts(data);
		}

		    
	  $(function() {
		  var id='${concept.id}';
		  loadDatepicker('postPickert',loadChartLineForPostPickert);
		  activeItemMenuLevel2(id,id+'-face','${concept.conceptName}');
		   getPostRealTime(${concept.id}, '#postRealTimeChar','../');
		   var dataPosts = {"id":${concept.id}, "div":'#postsChart',"dateFrom":moment().subtract('days', 29).format('L HH:mm'),"dateTo":moment().format('L HH:mm')};
		   getGroupedPosts(dataPosts);

		});
	</script>
</body>
</html>
