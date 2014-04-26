<html>
<head>
<meta name="layout" content="home" />
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>
				<i class="fa fa-user"></i> <g:message code="user.stats.title"/>"${user.username}"
			</h1>
		</div>


		<div class="row" >
			<div class="col-lg-12" >
				<div class="row" >
				<div class="col-xs-12 col-sm-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-blue">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span> 
									<g:message code="user.stats.tweets.real.time"/>
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
										<div id="realTimeCharUser" style="height: 400px; min-width: 310px"></div>
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
									<div id="tweetCharPie" style="width: 100%; min-width: 300px;"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-blue">
								<h5>
									<span class="glyphicon glyphicon-stats"></span> 
									<g:message code="user.stats.tweets.followers"/>
								</h5>

								<div class="widget-toolbar">
									<a href="javascript:void(0);" id="refreshCharFollowers"> <span
										data-action="reload"> <i class="fa fa-refresh"></i></span>
									</a> <a href="javascript:void(0);" data-action="collapse"> <i
										class="fa fa-chevron-up"></i></a> <a href="javascript:void(0);"
										data-action="close"> <i class="fa fa-remove"></i></a>
								</div>
							</div>

							<div class="widget-body">
								<div class="widget-main padding-4">
									<div id="totalFollowers" style="width: 100%; min-width: 300px;"></div>
								</div>
							</div>
						</div>
					</div>
					
					
					<div class="col-lg-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-blue">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span> 
									<g:message code="user.stats.tweets.date"/>
								</h5>
								<div class="widget-toolbar input-group ">
								<div id="pickertUser" class="btn btn-primary date-picker">
				                        <i class="fa fa-calendar"></i>
				                        <span class="date-range"></span> 
				                        <i class="fa fa-chevron-down"></i>
                   					 </div>
								</div>
								<div class="widget-toolbar">
									<a href="#" data-action="collapse"> <i
										class="1 bigger-125  fa fa-chevron-up"></i>
									</a>
									 
								</div>
							</div>


							<div class="widget-body">
								<div class="widget-main padding-4">
									<div id=lineaChartUser style="width: 100%; min-width:300px;"></div>
								</div>
							</div>
						</div>
					</div>
					

					

				</div>
			</div>
			<hr>

		</div>
	</div>
		<script type="text/javascript">
		function loadCharTest1(start, end,rangeSelect) {
	        $('#pickertUser span').html(rangeSelect+' - '+start.format('LLLL') + ' - ' + end.format('LLLL'));
	        var data = {"id":${user.id}, "div":'#lineaChartUser',"dateFrom":start.format('L HH:mm'),"dateTo":end.format('L HH:mm')};
	        getUserGroupedTweets(data);
	    }
		$(document).ready(function() {
			inicializeColorChar();
			activeItemMenuLevel1('home');
			getTweetCharPie('#tweetCharPie');
			getTotalFollowers('#totalFollowers');
			
			loadDatepicker('pickertUser',loadCharTest1);
			var data = {"id":${user.id}, "div":'#lineaChartUser',"dateFrom":moment().subtract('days', 29).format('L HH:mm'),"dateTo":moment().format('L HH:mm')};
			getUserGroupedTweets(data);
			getConceptRealTime(${user.id}, '#realTimeCharUser','');
			$('#refreshCharPie').click(function() {
				getTweetCharPie('#tweetCharPie');
			});
		});
	</script>
	
</body>
</html>
