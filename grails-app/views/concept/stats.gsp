<html>
<head>
<meta name="layout" content="home" />
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>
				<i class="icon-tag"></i> Resultados Concepto: "${concept.conceptName}"
			</h1>
		</div>



		<div class="row">
			<div class="col-lg-12">
				<div class="row">
				<div class="col-xs-12 col-sm-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-green4">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span> Tweets RealTime
								</h5>

								<div class="widget-toolbar">
									<a href="#" data-action="collapse"> <i
										class="1 bigger-125 icon-chevron-up"></i>
									</a>
								</div>


							</div>

							<div class="widget-body">
								<div class="widget-body-inner" style="display: block;">
									<div class="widget-main">
										<div id="realTimeChar" style="height:400px; min-width: 310px"></div>
									</div>


								</div>
							</div>
						</div>
				</div>
<%--
					<div class="col-xs-12 col-sm-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-green4">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span> Tweets por D&iacute;a
								</h5>

								<div class="widget-toolbar">
									<a href="#" data-action="collapse"> <i
										class="1 bigger-125 icon-chevron-up"></i>
									</a>
								</div>


							</div>

							<div class="widget-body">
								<div class="widget-body-inner" style="display: block;">
									<div class="widget-main">
										<div id="container2" style="height: 400px; min-width: 310px"></div>
									</div>


								</div>
							</div>
						</div>
				</div>		--%>
				
				<div class="col-xs-12 col-sm-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-green4">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span> Tweets por Dia
								</h5>

								<div class="widget-toolbar">
									<a href="#" data-action="collapse"> <i
										class="1 bigger-125 icon-chevron-up"></i>
									</a>
								</div>


							</div>

							<div class="widget-body">
								<div class="widget-body-inner" style="display: block;">
									<div class="widget-main">
										<div id="container1" style="height: 400px; min-width: 310px"></div>
									</div>


								</div>
							</div>
						</div>
				</div>	
					<div class="col-xs-12 col-sm-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-green4">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span> Tweets por Hora
								</h5>

								<div class="widget-toolbar">
									<a href="#" data-action="collapse"> <i
										class="1 bigger-125 icon-chevron-up"></i>
									</a>
								</div>


							</div>

							<div class="widget-body">
								<div class="widget-body-inner" style="display: block;">
									<div class="widget-main">
										<div id="container3" style="height: 400px; min-width: 310px"></div>
									</div>


								</div>
							</div>
						</div>
				</div>		
				<div class="col-xs-12 col-sm-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-green4">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span> Tweets por Minuto
								</h5>

								<div class="widget-toolbar">
									<a href="#" data-action="collapse"> <i
										class="1 bigger-125 icon-chevron-up"></i>
									</a>
								</div>


							</div>

							<div class="widget-body">
								<div class="widget-body-inner" style="display: block;">
									<div class="widget-main">
										<div id="container4" style="height: 400px; min-width: 310px"></div>
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
	 
	  $(function() {
		  var id='${concept.id}';
		  activeItemMenuLevel3('concepts',id,id+'-stats','Conceptos > '+"${concept.conceptName}");
		   getConceptRealTime(${concept.id}, '#realTimeChar');
		   <%-- 	  getConceptCharDay(${concept.id}, '#container2');--%>
		  		  getConceptCharHour(${concept.id},'#container3');
		  		  getConceptCharDate(${concept.id}, '#container1');
				  getConceptCharMinute(${concept.id}, '#container4'); 
		});
	</script>
</body>
</html>
