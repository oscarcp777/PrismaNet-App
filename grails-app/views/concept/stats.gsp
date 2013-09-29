<html>
<head>
	<title>Resultados de la Consulta</title>
<meta name="layout" content="home" />
</head>
<body>
	<h2>Resultados Concepto: "${concept.conceptName}"</h2>
	<h1>Tweets por Fecha</h1>
	<g:each var="item" in="${dateList}">
		<li>Fecha: ${item[0]} -  Valor: ${item[1]}</li>
	</g:each>
	<h1>Tweets por Sexo</h1>
	<g:each var="item" in="${sexList}">
		<li>Sexo: ${item[0]} -  Valor: ${item[1]}</li>
	</g:each>
	<g:link action='stats' conceptName="${concept.conceptName}">Nueva Busqueda</g:link>
	<script type="text/javascript">
	  activeItemMenu('concepts',"${concept.id}",2);
	</script>
</body>
</html>
