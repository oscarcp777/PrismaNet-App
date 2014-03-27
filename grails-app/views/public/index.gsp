<!DOCTYPE html>
<html lang="es">
<head>
<meta name="layout" content="main" />
<r:require modules="indexcss" />
</head>
<body >
<div class="landing">
	<div id="wrapper">
		<div id="sidebar">
			<a class="btn-navbar" data-toggle="collapse"
				data-target=".navbar-collapse"> <span class="fa fa-bars"></span>
			</a>
			<nav id="nav" class="navigation" role="navigation">
				<ul class="unstyled">
					<li class="active" data-section="1"><i class="fa fa-home"></i>
						<span><g:message code="public.index.home"/> </span></li>
					<li data-section="2" class=""><i class="fa fa-clock-o"></i> <span><g:message code="public.index.real.time"/></span></li>
					<li data-section="3" class=""><i class="fa fa-laptop"></i> <span><g:message code="public.index.ideal.for"/></span></li>
					<li data-section="4" class=""><i class="fa fa-pencil"></i> <span><g:message code="public.index.features"/></span></li>
					<li data-section="5" class="last"><i class="fa fa-envelope"></i>
						<span><g:message code="public.index.tellme"/></span></li>
				</ul>
			</nav>
			<!-- /nav -->
		</div>
		<!-- /sidebar -->

		<div id="container">

			<section class="section" id="section1" data-section="1">

				<div class="hd_wrapInner">
					<header class="hd_navbar navbar navbar-default navbar-fixed-top"
						id="header">
						<div class="navbar" style="margin-bottom:7px;">
							<div class="container">
								<div class="navbar-header">
									<button type="button" class="navbar-toggle"
										data-toggle="collapse" data-target=".navbar-collapse">
										<span class="fa fa-bar"></span> <span class="fa fa-bar"></span>
										<span class="fa fa-bar"></span>
									</button>

									<div class="brand">
										<a class="logoHolder" title="return to home page" href="#">
											<span class="logoFirst">
											<i class="fa fa-filter" style="font-weight:bold;font-size:0.8em;"></i>
											<g:message code="general.title.prism"/></span><g:message code="general.title.net"/>
											
										</a>
									</div>

								</div>
								<div class="navbar-collapse collapse">

									<ul class="nav navbar-nav navbar-right">

										<li>
										 <g:link class="btn btn-login"  controller='login' action='auth'><g:message code="public.index.login"/></g:link>
										</li>

									</ul>
								</div>
							</div>
						</div>
					</header>
					<article class="hd_main" role="main">
						<div id="main" class="container">
							<section  class="col-md-10  hd_hero">
                             <div class="col-md-offset-1">
								<h1>
									<span><g:message code="public.index.home.title1"/> </span> <br>
									<g:message code="public.index.home.title2"/> 
								</h1>
								<h2>Prisma-net te ofrece la mejor colecci&oacute;n de
									estad&iacute;sticas sobre la demograf&iacute;a, el
									comportamiento y los intereses de tu audiencia online,
									otorg&aacute;ndote todo lo que necesitas para mantener el
									dominio sobre tu presencia social.</h2>
									</div>
								<div class="featurette container" style="margin-left:-60px;">
									<img src="img/analytics_howitworks.png" alt="como trabaja">
									<div class="video">
										<a href="https://www.youtube.com/embed/s8z3UVn3Xhc?vq=hd720" target="_blank"
											class="open-trailer"><i class="fa fa-play"></i><span>Ver Demo</span></a>
									</div>
								</div>
							</section>
						</div>
					</article>
					<div id="push"></div>
				</div>


				<!-- /container -->
			</section>
			<!-- /section -->

			<section class="section" id="section2" data-section="2">
				<div class="container">
					<div class="row title">
						<div class="col-sm-6 col-md-6">
							<h2>Real-Time</h2>
						</div>
						<!-- /span3 -->
						<div class="col-md-9 hidden-phone">
							<hr>
						</div>
						<!-- /span9 -->
					</div>
					<!-- /row -->

					<div class="row desc">
						<div class="col-sm-8 col-md-8">
							<p>En junio de 2013, 114.5 millones de personas de América Latina visitó un sitio de red social, 
							representando TAN SOLO EL 96% de la población online de la región.</p>
							<p>Las noticias de último minuto que se difunde en las redes sociales tienen mayor alcance en 
							Twitter que en cualquier otra. 
							 El 10% del tráfico del diario se genera en los social media.</p>
						</div>
						<!-- /span8 -->
					</div>
					<!-- /row -->

					<div class="row content hidden-tablet">
						<div class="col-sm-12 col-md-12 expand">
							<h4>Estadisticas</h4>
							<div class="row">
								<div class="col-sm-4 col-md-4">

									<!-- CHART N 1 -->
									<script>
										jQuery(document)
												.ready(
														function($) {
															// Triggering only when it is inside viewport
															jQuery('.knob-4')
																	.waypoint(
																			function() {
																				// Triggering now
																				jQuery(
																						'.knob-4')
																						.knob();
																				// Animating the value
																				if (jQuery(
																						'.knob-4')
																						.val() == 0) {
																					jQuery(
																							{
																								value : 0
																							})
																							.animate(
																									{
																										value : jQuery(
																												'.knob-4')
																												.attr(
																														"rel")
																									},
																									{
																										duration : 5000,
																										easing : 'swing',
																										step : function() {
																											jQuery(
																													'.knob-4')
																													.val(
																															Math
																																	.ceil(this.value))
																													.trigger(
																															'change');
																										}
																									})
																				}
																			},
																			{
																				triggerOnce : true,
																				offset : function() {
																					return $(
																							window)
																							.height()
																							- $(
																									this)
																									.outerHeight();
																				}
																			});
														});
									</script>

									<div class="skill">
										<div style="display: inline; width: 245px; height: 122.5px;">
											<input data-readonly="true" data-fgcolor="#c4aa45"
												data-inputcolor="#333" data-width="245" data-height="122.5"
												class="knob-4" rel="43" value="0" readonly="readonly"
												data-anglearc="180" data-angleoffset="-90" data-max="100"
												style="width: 126px; height: 81px; position: absolute; vertical-align: middle; 
												margin-top: 41px; margin-left: -185px; border: 0px; background-image: none; 
												font-weight: bold; font-style: normal; font-variant: normal; font-size: 61px; 
												line-height: normal; font-family: Arial; text-align: center; color: rgb(51, 51, 51); 
												padding: 0px; -webkit-appearance: none; background-position: initial initial; 
												background-repeat: initial initial;">
										</div>
										<h6>43% de personas usan twitter</h6>
									</div>
									<!-- END CHART N 1 -->
								</div>
								<!-- /span4 -->


								<div class="col-sm-4 col-md-4">
									<!-- CHART N 2 -->
									<script>
										jQuery(document)
												.ready(
														function($) {
															// Triggering only when it is inside viewport
															jQuery('.knob-65')
																	.waypoint(
																			function() {
																				// Triggering now
																				jQuery(
																						'.knob-65')
																						.knob();
																				// Animating the value
																				if (jQuery(
																						'.knob-65')
																						.val() == 0) {
																					jQuery(
																							{
																								value : 0
																							})
																							.animate(
																									{
																										value : jQuery(
																												'.knob-65')
																												.attr(
																														"rel")
																									},
																									{
																										duration : 5000,
																										easing : 'swing',
																										step : function() {
																											jQuery(
																													'.knob-65')
																													.val(
																															Math
																																	.ceil(this.value))
																													.trigger(
																															'change');
																										}
																									})
																				}
																			},
																			{
																				triggerOnce : true,
																				offset : function() {
																					return $(
																							window)
																							.height()
																							- $(
																									this)
																									.outerHeight();
																				}
																			});
														});
									</script>

									<div class="skill">
										<div style="display: inline; width: 245px; height: 122.5px;">
											<input data-readonly="true" data-fgcolor="#f68e51"
												data-inputcolor="#333" data-width="245" data-height="122.5"
												class="knob-65" rel="65" value="0" readonly="readonly"
												data-anglearc="180" data-angleoffset="-90" data-max="100"
												style="width: 126px; height: 81px; position: absolute; vertical-align: middle; margin-top: 41px; margin-left: -185px; border: 0px; background-image: none; font-weight: bold; font-style: normal; font-variant: normal; font-size: 49px; line-height: normal; font-family: Arial; text-align: center; color: rgb(51, 51, 51); padding: 0px; -webkit-appearance: none; background-position: initial initial; background-repeat: initial initial;">
										</div>
										<h6>65% de las personas usan facebook</h6>
									</div>
									<!-- END CHART N 2 -->
								</div>
								<!-- /span4 -->


								<div class="col-sm-4 col-md-4">
									<!-- CHART N 3 -->
									<script>
										jQuery(document)
												.ready(
														function($) {
															// Triggering only when it is inside viewport
															jQuery('.knob-85')
																	.waypoint(
																			function() {
																				// Triggering now
																				jQuery(
																						'.knob-85')
																						.knob();
																				// Animating the value
																				if (jQuery(
																						'.knob-85')
																						.val() == 0) {
																					jQuery(
																							{
																								value : 0
																							})
																							.animate(
																									{
																										value : jQuery(
																												'.knob-85')
																												.attr(
																														"rel")
																									},
																									{
																										duration : 5000,
																										easing : 'swing',
																										step : function() {
																											jQuery(
																													'.knob-85')
																													.val(
																															Math
																																	.ceil(this.value))
																													.trigger(
																															'change');
																										}
																									})
																				}
																			},
																			{
																				triggerOnce : true,
																				offset : function() {
																					return $(
																							window)
																							.height()
																							- $(
																									this)
																									.outerHeight();
																				}
																			});
														});
									</script>

									<div class="skill">
										<div style="display: inline; width: 245px; height: 122.5px;">
											<input data-readonly="true" data-fgcolor="#2c3e50"
												data-inputcolor="#333" data-width="245" data-height="122.5"
												class="knob-85" rel="4000" step="10" value="0" readonly="readonly"
												data-anglearc="180" data-angleoffset="-90" data-max="6000"
												style="width: 126px; height: 81px; position: absolute; vertical-align: middle; margin-top: 41px; margin-left: -185px; border: 0px; background-image: none; font-weight: bold; font-style: normal; font-variant: normal; font-size: 49px; line-height: normal; font-family: Arial; text-align: center; color: rgb(51, 51, 51); padding: 0px; -webkit-appearance: none; background-position: initial initial; background-repeat: initial initial;">
										</div>
										<h6>tweets por segundo</h6>
									</div>
									<!-- END CHART N 3 -->
								</div>
								<!-- /span4 -->

							</div>
							<!-- /row -->
						</div>
						<!-- /span12 -->
					</div>
					<!-- /row -->
				</div>
				<!-- /container -->
			</section>
			<!-- /section -->

			<section class="section" id="section3" data-section="3" style="background: white;">
			<article class="jumbotron" style="background: white;">
			<div id="myCarousel" class="carousel slide">
				<!-- Indicators -->
				<ol class="rslides_tabs carousel-indicators ">
					<li data-target="#myCarousel" data-slide-to="0"
						class="active rslides_here">1</li>
					<li data-target="#myCarousel" data-slide-to="1">2</li>
					<li data-target="#myCarousel" data-slide-to="2">3</li>
				</ol>

				<div class="carousel-inner">
					<div class="item active">
						<div class="container featurette">
							<div class="col-md-4">
								<h2>Ideal para</h2>
								<h3>Monitoreo de redes sociales en tiempo real</h3>
								<p>Prisma-net te trae un tablero de control único y en
									tiempo real, con información de Twitter y Facebook curada
									minuto a minuto sobre los temas y autores a los que deberías
									estar prestándole atención:</p>
							</div>
							<div class="col-md-8">
								<div>
									<img src="img/admin.png" style="height: 340px;">
								</div>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="container featurette">
							<div class="col-md-4">
								<h2>Ideal para</h2>
								<h3>Releva tu reputación en medios sociales</h3>
								<p>Accede a estadísticas pertinentes sobre tus menciones y
									audiencia para comprender cómo se encuentra posicionada tu
									marca en el ámbito de social media, y conecta episodios clave
									con la reacción online de tu audiencia</p>
							</div>
							<div class="col-md-8">
								<div>
									<img src="img/home-work@2x.jpg" style="height: 340px;">
								</div>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="container featurette">
							<div class="col-md-4">
								<h2>Ideal para</h2>
								<h3>Top tweets, top hashtags y nube de palabras</h3>
								<p>Prisma-net te ofrece una herramienta para comparar las
									menciones de tus competidores a la par de las tuyas,
									permitiéndote identificar tendencias y contenidos que también
									son relevantes para tu marca</p>
							</div>
							<div class="col-md-8">
								<div>
									<img src="img/home-merchants-screenshot.jpg"
										style="height: 340px;">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</article>
			</section>
			<!-- /section -->



			<section class="section" id="section4" data-section="4">
				<div class="container">
					<div class="title row">
						<div class="col-sm-4 col-md-4">
							<h2>Features</h2>
						</div>
						<!-- /span4 -->
						<div class="col-sm-8 col-md-8 hidden-sm">
							<hr>
						</div>
						<!-- /span8 -->
					</div>
					<!-- /row-fluid -->
					<div class="desc row">
						<div class="col-sm-8 col-md-8">
							<p>El monitoreo tradicional es cosa del pasado. Consume estadísticas completas y en tiempo real!
							Usa Prisma-net para seguir y cuidar tu presencia online, 
							identificando tendencias en la conversación, detectando y gestionando crisis potenciales,
							 conociendo el contenido que hace reaccionar
							 a tu audiencia y descubriendo quiénes son tus influenciadores.</p>
						</div>
						<!-- /span8 -->
					</div>
					<!-- /row-fluid -->
					<div class="content row">
						<div class="col-sm-4 col-md-4" id="article-id">
							<ul class="article-tags">
								<li data-blog="blog" class=""><i
									class="fa fa-arrow-circle-right"></i>Blog</li>
								<li data-blog="dribbble" class=""><i
									class="fa fa-arrow-circle-right"></i>Monitoreo</li>
								<li data-blog="instagram" class=""><i
									class="fa fa-arrow-circle-right"></i>Features Destacados</li>
								<li data-blog="github" class="current"><i
									class="fa fa-arrow-circle-right"></i>Reportes</li>

							</ul>
						</div>
						<!-- /span4 -->

						<div class="slide col-sm-8 col-md-8" data-blog="blog" style="display: none;">
							<div class="row">
								<div class="expand col-sm-6 col-md-6">
									<h4>Políticos de Argentina en las redes sociales</h4>
									<p>Ayer el diario argentino La Nación publicó un informe sobre la performance de los candidatos a las
									 PASO en las redes sociales elaborado con estadísticas obtenidas por @Tribatics. A continuación reproducimos parte de la información publicada: 
									Ranking por volumen de Tweets Daniel Filmus fue el precandidato a Senador por la Ciudad de Buenos Aires 
									que …</p>
									<div class="pull-right read-more">
										<a href="#" rel="external" target="_blank">Leer más <i
											class="fa fa-angle-double-right"></i></a>
									</div>
								</div>
								<!-- /span6 -->
								<div class="col-sm-6 col-md-6 visible-lg">
									<h4>Twitter y TV. El rating de los #MartinFierro</h4>
									<p>La entrega de los Premios #MartinFierro del pasado lunes 5 de Agosto fue la ceremonia con más
									 audiencia de los últimos 5 años, alcanzando picos de hasta 37.6 puntos de rating (Fuente: IBOPE Media). 
									 Dicho acontecimiento no sólo fue un verdadero éxito televisivo por la gran audiencia que tuvo la 
									 transmisión sino también por el</p>
									<div class="pull-right read-more">
										<a href="#" rel="external">Leer más <i
											class="fa fa-angle-double-right"></i></a>
									</div>
								</div>
								<!-- /span6 -->

							</div>
							</div>
							<!-- /row-fluid -->
						
						

						<div class="slide col-sm-8 col-md-8" data-blog="dribbble"
							style="display: none;">
							<div class="row">
								<div class="expand col-sm-6 col-md-6">
									<h4>Está al tanto de todo lo relevante que está pasando</h4>
									<p style="text-align: center">
										<img class="img-responsive" src="img/features_ff_1.jpg" alt="">
									</p>
									<div class="pull-right read-more">
										<a href="#" rel="external">Leer mas <i
											class="fa fa-angle-double-right"></i></a>
									</div>
								</div>
								<!-- /span6 -->
								<div class="col-sm-6 col-md-6 visible-lg">
									<h4>Releva tu reputación en medios sociales</h4>
									<p style="text-align: center">
										<img class="img-responsive"  src="img/features_ff_2.jpg" alt="">
									</p>
									<div class="pull-right read-more">
										<a href="#" rel="external">Leer mas  <i
											class="fa fa-angle-double-right"></i></a>
									</div>
								</div>
								<!-- /span6 -->
							</div>
							<!-- /row-fluid -->
						</div>
						<!-- /span8 -->

						<div class="slide col-sm-8 col-md-8" data-blog="instagram"
							style="display: none;">
							<div class="row">
								<div class="expand col-sm-6 col-md-6">
									<h4>Género, edad, ubicación y audience score</h4>
									<p style="text-align: center">
										<img class="img-responsive" src="img/features_ff_hl_4.jpg" alt="">
									</p>
									<div class="pull-right read-more">
										Leer más <i class="fa fa-angle-double-right"></i>
									</div>
								</div>
								<!-- /span6 -->

								<div class="col-sm-6 col-md-6 visible-lg">
									<h4>Top tweets, top hashtags y nube de palabras</h4>
									<p style="text-align: center">
										<img class="img-responsive" src="img/features_bz_hl_3.jpg" alt="">
									</p>
									<div class="pull-right read-more">
										<a href="#" rel="external">Leer más <i
											class="fa fa-angle-double-right"></i></a>
									</div>
								</div>
								<!-- /span6 -->
							</div>
							<!-- /row-fluid -->
						</div>
						<!-- /span8 -->

						<div class="slide current col-sm-8 col-md-8" data-blog="github"
							style="display: block;">
							<div class="row">
								<div class="expand col-sm-6 col-md-6">
									<h4>Exportación de reportes para períodos de tiempo</h4>
									<p style="text-align: center">
										<img class="img-responsive" src="img/features_bz_hl_2.jpg" alt="">
									</p>
									<div class="pull-right read-more">
										<a href="#" rel="external">Leer más <i
											class="fa fa-angle-double-right"></i></a>
									</div>
								</div>
								<!-- /span6 -->
								<div class="col-sm-6 col-md-6 visible-lg">
									<h4>Features relevantes</h4>
									<p style="text-align: center">
										<img class="img-responsive" src="img/features_ff_3.jpg" alt="">
									</p>
									<div class="pull-right read-more">
										<a href="#" rel="external">Leer más <i
											class="fa fa-angle-double-right"></i></a>
									</div>
								</div>
								<!-- /span6 -->
							</div>
							<!-- /row-fluid -->
						</div>
						<!-- /span8 -->

					</div>
					<!-- /row-fluid -->
				</div>
				<!-- /container -->
			</section>


			<section class="section" id="section5" data-section="5">
				<div class="container">
					<div class="row title">
						<div class="col-sm-4 col-md-4">
							<h2>Contactenos</h2>
						</div>
						<!-- /span4 -->
						<div class="col-sm-8 col-md-8 hidden-phone alt">
							<hr>
						</div>
						<!-- /span8 -->
					</div>
					<!-- /row -->
					<div class="row content">
						<div class="col-sm-4 col-md-4">
							<h4 class="intro">Hablemos</h4>
							<p>Le mostraremos como funciona nuestro sistema</p>
							<div class=" alt">
								<h5>Email:</h5>
								<span>hello@yourdomain.com</span>
							</div>
							<!-- /row -->
							<div class=" alt">
								<h5>Phone:</h5>
								<span>(+34) 123-4567</span>
							</div>
							<!-- /row -->
							<div class=" alt">
								<h5>Twitter:</h5>
								<span><a href="http://www.twitter.com/basicoh_"
									rel="external">@Basicoh_</a></span>
							</div>
							<!-- /row -->
						</div>
						<!-- /span4 -->

						<div class="col-sm-8 col-md-8">

							<div class="row">
								<div class="col-sm-12 col-md-12"></div>
								<!-- /span12 -->
							</div>
							<!-- /row -->

							<form method="POST"
								action="http://alvarez.is/demo/flat/freelance/index.htm#">

								<div class="row">
									<div class="col-sm-6 col-md-6">
										<div class="control-group">
											<label for="name" class="control-label">Nombre:</label>
											<div class="controls">
												<input class="col-sm-12 col-md-12" type="text"
													name="contact_name" id="name">
											</div>
											<!-- /controls -->
										</div>
										<!-- /control-group -->
									</div>
									<!-- /span6 -->
									<div class="col-sm-6 col-md-6">
										<div class="control-group">
											<label for="email" class="control-label">Email:</label>
											<div class="controls">
												<input class="col-md-12 col-sm-12" type="email"
													name="contact_email" id="email">
											</div>
											<!-- /controls -->
										</div>
										<!-- /control-group -->
									</div>
									<!-- /span6 -->
								</div>
								<!-- /row -->

								<div class="row">
									<div class="col-md-6 col-sm-6">
										<div class="control-group">
											<label for="phone" class="control-label">Teléfono:</label>
											<div class="controls">
												<input class="col-md-12 col-sm-12" type="tel"
													name="contact_phone" id="phone">
											</div>
											<!-- /controls -->
										</div>
										<!-- /control-group -->
									</div>
									<!-- /span6 -->
									<div class="col-md-6 col-sm-6">
										<div class="control-group">
											<label for="website" class="control-label">Tú sitio Web
												:</label>
											<div class="controls">
												<input class="col-md-12 col-sm-12" type="url"
													name="contact_website" id="website">
											</div>
											<!-- /controls -->
										</div>
										<!-- /control-group -->
									</div>
									<!-- /span6 -->
								</div>
								<!-- /row -->

								<div class="row">
									<div class="col-md-12 col-sm-12">
										<div class="control-group">
											<label for="message" class="control-label">
												Mensaje:</label>
											<div class="controls">
												<textarea class="col-md-12 col-sm-12" type="text"
													name="contact_message" id="message" rows="4"></textarea>
											</div>
											<!-- /controls -->
										</div>
										<!-- /control-group -->
									</div>
									<!-- /span12 -->
								</div>
								<!-- /row -->

								<input type="hidden" name="save" value="contact">
								<button type="submit" class="btn-inverse pull-right">
									Envíar <span>Mensaje</span> <i class="fa fa-arrow-circle-right"></i>
								</button>
							</form>
							<!-- /form -->
						</div>
						<!-- /span8 -->

						<div id="footer">
							<div class="row">
								<div class="col-md-12 col-sm-12"></div>
								<!-- /span12 -->
							</div>
							<!-- /row -->
						</div>
						<!-- /footer -->
					</div>
					<!-- /row -->
				</div>
				<!-- /container -->

			</section>
		</div>
		<!-- /container -->
	</div>
	<!-- /wrapper -->
</div>
    <r:require modules="indexjs" />
</body>
</html>
