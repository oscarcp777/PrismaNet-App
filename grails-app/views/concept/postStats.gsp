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
				<div class="row">
					<div class="col-xs-12 col-sm-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-blue">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span> Posts
									RealTime
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
										<div id="postRealTimeChar" style="height: 400px; min-width: 310px"></div>
									</div>


								</div>
							</div>
						</div>
					</div>

					<div class="col-xs-12 col-sm-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-blue">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span> Posts entre
									fechas
								</h5>
								<div class="widget-toolbar input-group ">
									<div id="postPickert" class="btn btn-primary date-picker">
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
										<div id="postsChart" style="height: 400px; min-width: 310px"></div>
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

		function loadChartLineForPostPickert(start, end, rangeSelect) {
		     $('#postPickert span').html(rangeSelect+' - '+start.format('LLLL') + ' - ' + end.format('LLLL'));
   		     var data = {"id":${concept.id}, "div":'#postsChart',"dateFrom":start.format('L HH:mm'),"dateTo":end.format('L HH:mm')};
   		 	 getGroupedPosts(data);
		}

		    
	  $(function() {
		  var id='${concept.id}';
		  loadDatepicker('postPickert',loadChartLineForPostPickert);
		  activeItemMenuLevel2(id,id+'-post');
		   getPostRealTime(${concept.id}, '#postRealTimeChar','../');
		   var dataPosts = {"id":${concept.id}, "div":'#postsChart',"dateFrom":moment().subtract('days', 29).format('L HH:mm'),"dateTo":moment().format('L HH:mm')};
		   getGroupedPosts(dataPosts);

		});
	</script>
</body>
</html>
