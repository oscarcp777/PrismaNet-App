<%@page import="com.prismanet.MentionType"%>
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
								<g:render template="chooseChannel" model="['callback':'loadRealTime']"></g:render>
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
										<a href="javascript:void(0);" data-action="collapse"> <i
										class="fa fa-chevron-up"></i></a> <a href="javascript:void(0);"
										data-action="close"> <i class="fa fa-remove"></i></a>
								</div>
								<g:render template="chooseChannel" model="['callback':'loadTweetCharPie']"></g:render>
							</div>

							<div class="widget-body">
								<div class="widget-main padding-4">
									<div id="tweetCharPie" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
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
								 <a href="javascript:void(0);" data-action="collapse"> <i
										class="fa fa-chevron-up"></i></a> <a href="javascript:void(0);"
										data-action="close"> <i class="fa fa-remove"></i></a>
								</div>
								<g:render template="chooseChannel" model="['callback':'loadTotalFollowers']"></g:render>
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
								<g:render template="chooseChannel" model="['callback':'loadUserGroupedData']"></g:render>
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
		$(document).ready(function() {
			inicializeColorChar();
			activeItemMenuLevel1('home');
			//real time
			loadRealTime('${MentionType.TWITTER}');
			//data por concepto
			loadTweetCharPie('${MentionType.TWITTER}');
			//Followers y likes por concepto
			loadTotalFollowers('${MentionType.TWITTER}');
			//data entre fechas
			loadUserGroupedData('${MentionType.TWITTER}');
		});
	</script>
	
</body>
</html>
