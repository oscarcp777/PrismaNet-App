<html>
<head>
<title>Resultados de la Consulta</title>
<meta name="layout" content="main" />
</head>
<body>
	<h1>Resultados</h1>
	<ul>
		<g:each var="item" in="${categoryList}">
			<li>
				Sexo: ${item[0]} -  Valor: ${item[1]}
			</li>
		</g:each>
	</ul>
	<g:link action='listBySex'>Search Again</g:link>
</body>
</html>
