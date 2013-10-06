<html>
<head>
<title>Resultados de la Consulta</title>
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
							<div class="widget-header header-color-red">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span> Tweets por
									Fecha
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
										<g:each var="item" in="${dateList}">
											<li>${item[0]} - ${item[1]} tweets</li>
										</g:each>
									</div>


								</div>
							</div>
						</div>
						
						
						
						<div class="widget-box">
							<div class="widget-header header-color-red">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span> Tweets por
									Hora
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
										<g:each var="item" in="${hourList}">
											<li>${item[0]}hs - ${item[1]} tweets</li>
										</g:each>
									</div>


								</div>
							</div>
							
							
							<div class="widget-box">
							<div class="widget-header header-color-red">
								<h5>
									<span class="glyphicon glyphicon-stats"> </span> Tweets por
									Minuto
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
										<g:each var="item" in="${minuteList}">
											<li>${item[0]}hs - ${item[1]} tweets</li>
										</g:each>
									</div>


								</div>
							</div>
						</div>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-xs-12 col-sm-6 widget-container-span ui-sortable">
						<div class="widget-box">
							<div class="widget-header header-color-blue">
								<h5>
									<span class="glyphicon glyphicon-stats"></span> Tweets por Sexo
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
	

										<div class="row">
										<div class="col-md-6" >
										<div class="easy-pie-chart percentage easyPieChart" data-percent="64" data-color="#c6699f" >
												<i class="icon-female" style="font-size:6em;color:#c6699f" ></i>
												<h2 style="margin-top:-72px;margin-left:10px;;position:relative;">64%</h2>
										</div>
	
										</div>
																				<div class="col-md-6" >
										<div class="easy-pie-chart percentage easyPieChart" data-percent="36" data-color="#2a91d8" >
													<i class="icon-male" style="font-size:6em;color:#2a91d8" ></i>
													<h2 style="margin-top:-72px;margin-left:10px;;position:relative;">36%</h2>
										</div>
	
										</div>
								       </div>




									</div>


								</div>
							</div>
							<%-- 										<g:each var="item" in="${sexList}"><li>Sexo: ${item[0]} - Valor: ${item[1]}</li></g:each> --%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<g:link action='stats' conceptName="${concept.conceptName}">Nueva Busqueda</g:link>
	<script type="text/javascript">
	  activeItemMenu('concepts',"${concept.id}", 2);
	  jQuery(function($) {
	  $('.easy-pie-chart.percentage').each(function() {
          var $box = $(this).closest('.infobox');
          var barColor = $(this).data('color') || (!$box.hasClass('infobox-dark') ? $box.css('color') : 'rgba(255,255,255,0.95)');
          var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)' : '#E2E2E2';
          var size = parseInt($(this).data('size')) || 200;
          $(this).easyPieChart({
              barColor: barColor,
              trackColor: trackColor,
              scaleColor: false,
              lineCap: 'butt',
              lineWidth: parseInt(size / 10),
              animate: /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase()) ? false : 1000,
              size: size
          });
      })
	  })
	</script>
</body>
</html>
