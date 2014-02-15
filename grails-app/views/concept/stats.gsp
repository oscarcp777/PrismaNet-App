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
							<div class="widget-header header-color-blue">
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
				
<!-- 				<div class="col-xs-12 col-sm-12 widget-container-span ui-sortable"> -->
<!-- 						<div class="widget-box"> -->
<!-- 							<div class="widget-header header-color-green4"> -->
<!-- 								<h5> -->
<!-- 									<span class="glyphicon glyphicon-stats"> </span> Tweets por Dia -->
<!-- 								</h5> -->
<!-- 								<div class="widget-toolbar"> -->
<!-- 													<a href="#" data-action="reload"> -->
<!-- 														<i class=" icon-refresh"></i> -->
<!-- 													</a> -->
<!-- 													<a href="#" data-action="collapse"> -->
<!-- 														<i class="icon-chevron-up"></i> -->
<!-- 													</a> -->
												
<!-- 								</div> -->


<!-- 							</div> -->

<!-- 							<div class="widget-body"> -->
<!-- 								<div class="widget-body-inner" style="display: block;"> -->
<!-- 									<div class="widget-main"> -->
<!-- 										<div id="container1" style="height: 400px; min-width: 310px"></div> -->
<!-- 									</div> -->


<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 				</div>	 -->
					<div class="col-xs-12 col-sm-12 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-blue">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span> Tweets entre fechas
								</h5>
								<div class="widget-toolbar input-group ">
								<div id="pickertConcept" class="btn btn-primary date-picker">
				                        <i class="icon-calendar"></i>
				                        <span class="date-range"></span> 
				                        <i class="icon-chevron-down"></i>
                   					 </div>
								</div>
								<div class="widget-toolbar">
									<a href="#" data-action="collapse"> <i
										class="1 bigger-125 icon-chevron-up"></i>
									</a>
									 
								</div>


							</div>

							<div class="widget-body">
								<div class="widget-body-inner" style="display: block;">
									<div class="widget-main">
										<div id="lineaCharConcept" style="height: 400px; min-width: 310px"></div>
									</div>


								</div>
							</div>
						</div>
				</div>		
<!-- 				<div class="col-xs-12 col-sm-12 widget-container-span ui-sortable"> -->
<!-- 						<div class="widget-box"> -->
<!-- 							<div class="widget-header header-color-green4"> -->
<!-- 								<h5> -->
<!-- 									<span class="glyphicon glyphicon-stats"> </span> Tweets por Minuto -->
<!-- 								</h5> -->

<!-- 								<div class="widget-toolbar"> -->
<!-- 									<a href="#" data-action="collapse"> <i -->
<!-- 										class="1 bigger-125 icon-chevron-up"></i> -->
<!-- 									</a> -->
<!-- 								</div> -->


<!-- 							</div> -->

<!-- 							<div class="widget-body"> -->
<!-- 								<div class="widget-body-inner" style="display: block;"> -->
<!-- 									<div class="widget-main"> -->
<!-- 										<div id="container4" style="height: 400px; min-width: 310px"></div> -->
<!-- 									</div> -->


<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 				</div> -->
									
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">

<%--		$('#pickertConcept').on('apply', function(ev, picker) {--%>
<%--			$('#pickertConcept span').html(rangeSelect+' - '+start.format('LLLL') + ' - ' + end.format('LLLL'));--%>
<%--  		     var data = {"id":${concept.id}, "div":'#lineaCharConcept',"dateFrom":start._d,"dateTo":end._d};--%>
<%--	    	 doRequest('../getGroupedTweets',data,paintCharLine, null, 'GET');--%>
<%--		});--%>
	
		function loadCharLineConcepts(start, end, rangeSelect) {
		     $('#pickertConcept span').html(rangeSelect+' - '+start.format('LLLL') + ' - ' + end.format('LLLL'));
   		     var data = {"id":${concept.id}, "div":'#lineaCharConcept',"dateFrom":start.format('L HH:mm'),"dateTo":end.format('L HH:mm')};
   		 	 getGroupedTweets(data);
		}
    
	  $(function() {
		 
		  loadDatepicker('pickertConcept',loadCharLineConcepts);
		  
		  var id='${concept.id}';
		   activeItemMenuLevel3('concepts',id,id+'-stats','Conceptos > '+"${concept.conceptName}");
		   getConceptRealTime(${concept.id}, '#realTimeChar');
		   var data = {"id":${concept.id}, "div":'#lineaCharConcept',"dateFrom":moment().subtract('days', 29).format('L HH:mm'),"dateTo":moment().format('L HH:mm')};
		   getGroupedTweets(data);
		});
	</script>
</body>
</html>
