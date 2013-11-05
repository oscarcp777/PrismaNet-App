<html>
<head>
	<title>Resultados de la Consulta</title>
<meta name="layout" content="home" />
</head>
<body>
	<h2>Resultados Usuario: "${user.username}"</h2>
	<h1>Tweets por Fecha</h1>
	<g:each var="item" in="${statsList}">
		<li>Concepto: ${item[0]} -  Tweets: ${item[1]} -  Autores: ${item[2]}</li>
	</g:each>
	<g:link action='monthStats' id="${user.id}">Nueva Busqueda</g:link>
	<script type="text/javascript">
   activeItemMenu('chart','mensual', 2,'Estadisticas > Mensual');
</script>
</body>

</html>
