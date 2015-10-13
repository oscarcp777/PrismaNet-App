<html>
<head>
<meta name="layout" content="home" />
</head>
<body>
		<div class="page-header">
			<h1>
				<i class="ace-icon fa fa-calendar-check-o"></i> Reportes
				<small>
								<i class="ace-icon fa fa-angle-double-right"></i> Reportes horarios
								
			</small>
			</h1>
			
		</div>
		<div class="row">
		<div class="col-xs-offset-1 col-xs-11">
		<div class="alert alert-block alert-success">
			<button type="button" class="close" data-dismiss="alert">
				<i class="ace-icon fa fa-times"></i>
			</button>
			<i class="ace-icon fa fa-check green"></i>
			Reporte de los 3 Conceptos con más menciones en Twitter el <g:formatDate date="${dateShow}" format="d MMMM, 'a las' H:mm" /> horas
		</div>
		</div>
<g:render  template="reportPerHour"></g:render>
</div>
	<script type="text/javascript">
	$(function() {
		activeItemMenuLevel1('reports','Reportes');
	});
	</script>
</body>
</html>