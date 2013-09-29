<html>
<head>
<title>Resultados de la Consulta</title>
<meta name="layout" content="home" />
</head>
<body>
	<h2>
		Resultados Usuario: "${user.username}"
	</h2>
	<h1>Tweets por Fecha</h1>
	<g:each var="item" in="${dateList}">
		<li>Fecha: ${item[0]} - Concepto: ${item[1]} - Valor: ${item[2]}</li>
	</g:each>
	<h1>Tweets por Sexo</h1>
	<g:each var="item" in="${sexList}">
		<li>Sexo: ${item[0]} - Valor: ${item[1]}</li>
	</g:each>
	<g:link action='stats' id="${user.id}">Nueva Busqueda</g:link>
	<div class="col-sm-5">
		<div class="widget-box">
			<div class="widget-header widget-header-flat widget-header-small">
				<h5>
					<i class="icon-signal"></i> Traffic Sources
				</h5>

				<div class="widget-toolbar no-border">
					<button class="btn btn-minier btn-primary dropdown-toggle"
						data-toggle="dropdown">
						This Week <i class="icon-angle-down icon-on-right bigger-110"></i>
					</button>

					<ul
						class="dropdown-menu pull-right dropdown-125 dropdown-lighter dropdown-caret">
						<li class="active"><a
							href="http://192.69.216.111/themes/preview/ace/#" class="blue">
								<i class="icon-caret-right bigger-110">&nbsp;</i> This Week
						</a></li>

						<li><a href="http://192.69.216.111/themes/preview/ace/#">
								<i class="icon-caret-right bigger-110 invisible">&nbsp;</i> Last
								Week
						</a></li>

						<li><a href="http://192.69.216.111/themes/preview/ace/#">
								<i class="icon-caret-right bigger-110 invisible">&nbsp;</i> This
								Month
						</a></li>

						<li><a href="http://192.69.216.111/themes/preview/ace/#">
								<i class="icon-caret-right bigger-110 invisible">&nbsp;</i> Last
								Month
						</a></li>
					</ul>
				</div>
			</div>

			<div class="widget-body">
				<div class="widget-main">
					<div id="piechart-placeholder"
						style="width: 90%; min-height: 150px; padding: 0px; position: relative;">
						<canvas class="flot-base"
							style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 310px; height: 150px;"
							width="310" height="150"></canvas>
						<canvas class="flot-overlay"
							style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 310px; height: 150px;"
							width="310" height="150"></canvas>
						<div class="legend">
							<div
								style="position: absolute; width: 93px; height: 108px; top: 15px; right: -30px; background-color: rgb(255, 255, 255); opacity: 0.85;"></div>
							<table
								style="position: absolute; top: 15px; right: -30px;; font-size: smaller; color: #545454">
								<tbody>
									<tr>
										<td class="legendColorBox">
											<div style="border: 1px solid null; padding: 1px">
												<div
													style="width: 4px; height: 0; border: 5px solid #68BC31; overflow: hidden"></div>
											</div>
										</td>
										<td class="legendLabel">social networks</td>
									</tr>
									<tr>
										<td class="legendColorBox">
											<div style="border: 1px solid null; padding: 1px">
												<div
													style="width: 4px; height: 0; border: 5px solid #2091CF; overflow: hidden"></div>
											</div>
										</td>
										<td class="legendLabel">search engines</td>
									</tr>
									<tr>
										<td class="legendColorBox">
											<div style="border: 1px solid null; padding: 1px">
												<div
													style="width: 4px; height: 0; border: 5px solid #AF4E96; overflow: hidden"></div>
											</div>
										</td>
										<td class="legendLabel">ad campaigns</td>
									</tr>
									<tr>
										<td class="legendColorBox">
											<div style="border: 1px solid null; padding: 1px">
												<div
													style="width: 4px; height: 0; border: 5px solid #DA5430; overflow: hidden"></div>
											</div>
										</td>
										<td class="legendLabel">direct traffic</td>
									</tr>
									<tr>
										<td class="legendColorBox">
											<div style="border: 1px solid null; padding: 1px">
												<div
													style="width: 4px; height: 0; border: 5px solid #FEE074; overflow: hidden"></div>
											</div>
										</td>
										<td class="legendLabel">other</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>

					<div class="hr hr8 hr-double"></div>

					<div class="clearfix">
						<div class="grid3">
							<span class="grey"> <i
								class="icon-facebook-sign icon-2x blue"></i> &nbsp; likes
							</span>
							<h4 class="bigger pull-right">1,255</h4>
						</div>

						<div class="grid3">
							<span class="grey"> <i
								class="icon-twitter-sign icon-2x purple"></i> &nbsp; tweets
							</span>
							<h4 class="bigger pull-right">941</h4>
						</div>

						<div class="grid3">
							<span class="grey"> <i
								class="icon-pinterest-sign icon-2x red"></i> &nbsp; pins
							</span>
							<h4 class="bigger pull-right">1,050</h4>
						</div>
					</div>
				</div>
				<!-- /widget-main -->
			</div>
			<!-- /widget-body -->
		</div>
		<!-- /widget-box -->
	</div>
</body>
</html>
