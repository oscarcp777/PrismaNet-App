<html>
<head>
	<title>Resultados de la Consulta</title>
<meta name="layout" content="main" />
</head>
<body>
	<h2>Resultados Concepto: "${concept.conceptName}"</h2>
	<h1>Tweets por Sexo</h1>
	<g:each var="item" in="${sexList}">
		<li>Sexo: ${item[0]} -  Valor: ${item[1]}</li>
	</g:each>
	<g:link action='stats' id="${concept.id}">Nueva Busqueda</g:link>
</body>
</html>
