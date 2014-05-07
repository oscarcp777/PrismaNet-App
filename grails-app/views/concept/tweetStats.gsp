<html>
<head>
<meta name="layout" content="home" />
<link rel="stylesheet" href="${resource(dir: 'css', file: 'twitter.css')}" type="text/css">
	
</head>
<body>
		
		<g:render template="tabTwitter"  model="['concept':concept,'tabMain':'','tabTweets':'','tabChar':'active','tabSentimental':'']"></g:render>
		
	<div class="page-content">
		
		<div class="row">
			<div class="col-lg-12">
				<div class="row">
					<div class="col-xs-12 col-sm-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-blue">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span>
									<g:message code="Tweets en tiempo real"/>
								</h5>

								<div class="widget-toolbar">
									<a href="#" data-action="collapse"> <i
										class="1 bigger-125 fa fa-chevron-up"></i>
									</a>
								</div>


							</div>

							<div class="widget-body">
								<div class="widget-body-inner" style="display: block;">
									<div class="widget-main">
										<div id="realTimeChar" style="height: 400px; min-width: 310px"></div>
									</div>


								</div>
							</div>
						</div>
					</div>

					<div class="col-xs-12 col-sm-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-blue">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span> 
									<g:message code="user.stats.tweets.date"/>
								</h5>
								<div class="widget-toolbar input-group ">
									<div id="tweetPickert" class="btn btn-primary date-picker">
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
										<div id="tweetsChart" style="height: 400px; min-width: 310px"></div>
									</div>


								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-blue">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span>
									<g:message code="concept.stats.scope.date"/>
								</h5>
								<div class="widget-toolbar input-group ">
									<div id="weightPickert" class="btn btn-primary date-picker">
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
										<div id="weightChart" style="height: 400px; min-width: 310px"></div>
									</div>


								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-blue">
								<h5>
									<i class="fa fa-group "></i>
									<g:message code="concept.author.more.followers"/>
								</h5>
								<div class="widget-toolbar input-group ">
									<div id="authorPickert" class="btn btn-primary date-picker">
										<i class="fa fa-calendar"></i> <span class="date-range"></span>
										<i class="fa fa-chevron-down"></i>
									</div>
								</div>
							</div>

							<div class="widget-body">
								<div class="widget-body-inner" style="display: block;">
									<div class="widget-main">
										<div  style="min-width: 310px">
											<div class="profile-users clearfix" id="relevantAuthors">
												
											</div>
    									</div>
									</div>


								</div>
							</div>
						</div>
					</div>
				</div>


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
		  
		  	activeItemMenuLevel2(id,'${concept.id}-tweet');
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
