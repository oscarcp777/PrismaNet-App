<!DOCTYPE html>
<html lang="es">
<head>
<meta name="layout" content="public" />
<r:require modules="ace" />
</head>
<body>
	<div class="page-header " style="margin-top: 20px; text-align: center">
		<h1>Políticos más mencionados en Twitter el <g:formatDate date="${dateShow}" format="d MMMM, 'a las' H:mm" /> horas</h1>
	</div>
	<g:render template="reportPerHour"></g:render>
</body>
</html>