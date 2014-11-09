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
				data-target=".navbar-collapse"> <span class="ace-icon fa fa-bars"></span>
			</a>
			<nav id="nav" class="navigation" role="navigation">
				<ul class="unstyled">
					<li class="active" data-section="1"><i class="ace-icon fa fa-home"></i>
						<span><g:message code="public.index.home"/> </span></li>
					<li data-section="2" class=""><i class="ace-icon fa fa-clock-o"></i> <span><g:message code="public.index.real.time"/></span></li>
					<li data-section="3" class=""><i class="ace-icon fa fa-laptop"></i> <span><g:message code="public.index.ideal.for"/></span></li>
					<li data-section="4" class=""><i class="ace-icon fa fa-pencil"></i> <span><g:message code="public.index.features"/></span></li>
					<li data-section="5" class="last"><i class="ace-icon fa fa-envelope"></i>
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
										<span class="ace-icon fa fa-bar"></span> <span class="ace-icon fa fa-bar"></span>
										<span class="ace-icon fa fa-bar"></span>
									</button>

									<div class="brand">
										<a class="logoHolder" title="return to home page" href="#">
											<span class="logoFirst">
											<i class="ace-icon fa fa-filter" style="font-weight:bold;font-size:0.8em;"></i>
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
								<h2> <g:message code="public.index.home.sub.title"/> </h2>
									</div>
								<div class="featurette container" style="margin-left:-60px;">
									<img src="img/prisma_howitworks.png" alt="como trabaja">
									<div class="video">
										<a href="#" 
											class="open-trailer"><i class="ace-icon fa fa-play"></i><span><g:message code="public.index.home.demo"/></span></a>
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
							<h2><g:message code="public.index.home.real.time"/> </h2>
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
							<p><g:message code="public.index.home.real.desc1"/></p>
							<p><g:message code="public.index.home.real.desc2"/></p>
						</div>
						<!-- /span8 -->
					</div>
					<!-- /row -->

					<div class="row content hidden-tablet">
						<div class="col-sm-12 col-md-12 expand">
							<h4></h4>
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
										<h6><g:message code="public.index.home.stats.msj1"/> </h6>
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
										<h6><g:message code="public.index.home.stats.msj2"/> </h6>
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
										<h6><g:message code="public.index.home.stats.msj3"/> </h6>
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
								<h2><g:message code="public.index.home.carousel1.title1"/></h2>
								<h3><g:message code="public.index.home.carousel1.title2"/> </h3>
								<p><g:message code="public.index.home.carousel1.desc"/></p>
							</div>
							<div class="col-md-8">
								<div>
									<img src="img/public/real_time.png" style="height: 340px;">
								</div>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="container featurette">
							<div class="col-md-4">
								<h2><g:message code="public.index.home.carousel2.title1"/></h2>
								<h3><g:message code="public.index.home.carousel2.title2"/> </h3>
								<p><g:message code="public.index.home.carousel2.desc"/></p>

							</div>
							<div class="col-md-8">
								<div>
									<img src="img/public/reputation.png" style="height: 340px;">
								</div>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="container featurette">
							<div class="col-md-4">
								<h2></h2>
								<h3></h3>
								<p></p>
								<h2><g:message code="public.index.home.carousel2.title1"/></h2>
								<h3><g:message code="public.index.home.carousel2.title2"/> </h3>
								<p><g:message code="public.index.home.carousel2.desc"/></p>
									
							</div>
							<div class="col-md-8">
								<div>
									<img src="img/public/cloud_words.png"
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
							<h2><g:message code="public.index.home.features"/></h2>
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
							<p><g:message code="public.index.home.features.desc"/> </p>
						</div>
						<!-- /span8 -->
					</div>
					<!-- /row-fluid -->
					<div class="content row">
						<div class="col-sm-4 col-md-4" id="article-id">
							<ul class="article-tags">
								<li data-blog="blog" class=""><i
									class="ace-icon fa fa-arrow-circle-right"></i><g:message code="public.index.home.features.blog"/></li>
								<li data-blog="dribbble" class=""><i
									class="ace-icon fa fa-arrow-circle-right"></i><g:message code="public.index.home.features.monitor"/></li>
								<li data-blog="instagram" class=""><i
									class="ace-icon fa fa-arrow-circle-right"></i><g:message code="public.index.home.features.important"/></li>
								<li data-blog="github" class="current"><i
									class="ace-icon fa fa-arrow-circle-right"></i><g:message code="public.index.home.features.report"/></li>

							</ul>
						</div>
						<!-- /span4 -->

						<div class="slide col-sm-8 col-md-8" data-blog="blog" style="display: none;">
							<div class="row">
								<div class="expand col-sm-6 col-md-6">
									<h4><g:message code="public.index.home.features.blog.title"/> </h4>
									<p><g:message code="public.index.home.features.blog.title"/>
									</p>
									<div class="pull-right read-more">
										<a href="#" rel="external" target="_blank"><g:message code="public.index.home.features.blog.more"/> <i
											class="ace-icon fa fa-angle-double-right"></i></a>
									</div>
								</div>
								<!-- /span6 -->
								<div class="col-sm-6 col-md-6 visible-lg">
									<h4><g:message code="public.index.home.features.monitor.title"/> </h4>
									<p><g:message code="public.index.home.features.monitor.title"/>									 
									<div class="pull-right read-more">
										<a href="#" rel="external"><g:message code="public.index.home.features.monitor.more"/> <i
											class="ace-icon fa fa-angle-double-right"></i></a>
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
									<h4><g:message code="public.index.home.features.important.title"/></h4>
									<p style="text-align: center">
										<img class="img-responsive" src="img/public/monitor1.png" alt="">
									</p>
									<div class="pull-right read-more">
										<a href="#" rel="external"><g:message code="public.index.home.features.monitor.more"/> <i
											class="ace-icon fa fa-angle-double-right"></i></a>
									</div>
								</div>
								<!-- /span6 -->
								<div class="col-sm-6 col-md-6 visible-lg">
									<h4><g:message code="public.index.home.features.reputacion.title"/></h4>
									<p style="text-align: center">
										<img class="img-responsive"  src="img/public/monitor2.png" alt="">
									</p>
									<div class="pull-right read-more">
										<a href="#" rel="external"><g:message code="public.index.home.features.monitor.more"/>  <i
											class="ace-icon fa fa-angle-double-right"></i></a>
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
									<h4><g:message code="public.index.home.features.monitor.more"/></h4>
									<p style="text-align: center">
										<img class="img-responsive" src="img/features_ff_hl_4.jpg" alt="">
									</p>
									<div class="pull-right read-more">
										<g:message code="public.index.home.features.monitor.more"/> <i class="ace-icon fa fa-angle-double-right"></i>
									</div>
								</div>
								<!-- /span6 -->

								<div class="col-sm-6 col-md-6 visible-lg">
									<h4><g:message code="public.index.home.features.hashtags.title"/> </h4>
									<p style="text-align: center">
										<img class="img-responsive" src="img/public/cloud.jpg" style="width:500px; " alt="">
									</p>
									<div class="pull-right read-more">
										<a href="#" rel="external"><g:message code="public.index.home.features.monitor.more"/> <i
											class="ace-icon fa fa-angle-double-right"></i></a>
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
									<h4><g:message code="public.index.home.features.report.time"/> </h4>
									<p style="text-align: center">
										<img class="img-responsive" src="img/public/betweentDate.png" alt="">
									</p>
									<div class="pull-right read-more">
										<a href="#" rel="external"><g:message code="public.index.home.features.monitor.more"/> <i
											class="ace-icon fa fa-angle-double-right"></i></a>
									</div>
								</div>
								<!-- /span6 -->
								<div class="col-sm-6 col-md-6 visible-lg">
									<h4><g:message code="public.index.home.features.relevant"/> </h4>
									<p style="text-align: center">
										<img class="img-responsive" src="img/public/features.png" alt="">
									</p>
									<div class="pull-right read-more">
										<a href="#" rel="external"><g:message code="public.index.home.features.monitor.more"/> <i
											class="ace-icon fa fa-angle-double-right"></i></a>
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
							<h2><g:message code="public.index.home.contact"/></h2>
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
							<h4 class="intro"><g:message code="public.index..home.contact.tellme"/></h4>
							<p><g:message code="public.index..home.contact.msj"/></p>
							<div class=" alt">
								<h5><g:message code="public.index..home.contact.email"/></h5>
								<span><g:message code="public.index..home.contact.email.prisma"/></span>
							</div>
							<!-- /row -->
							<div class=" alt">
								<h5><g:message code="public.index..home.contact.phone"/></h5>
								<span><g:message code="public.index..home.contact.number"/></span>
							</div>
							<!-- /row -->
							<div class=" alt">
								<h5><g:message code="public.index..home.contact.twitter"/></h5>
								<span><a href="http://www.twitter.com/@Prisma_Net_"
									rel="external"><g:message code="public.index..home.contact.prisma_"/>
									</a></span>
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
								action="">

								<div class="row">
									<div class="col-sm-6 col-md-6">
										<div class="control-group">
											<label for="name" class="control-label"><g:message code="public.index..home.contact.name"/></label>
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
											<label for="email" class="control-label"><g:message code="public.index..home.contact.email"/></label>
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
											<label for="phone" class="control-label"><g:message code="public.index..home.contact.phone"/></label>
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
											<label for="website" class="control-label"><g:message code="public.index..home.contact.web"/>
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
												<g:message code="public.index..home.contact.message"/></label>
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
									 <g:message code="public.index..home.contact.send"/><span><g:message code="public.index..home.contact.message"/></span> <i class="ace-icon fa fa-arrow-circle-right"></i>
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
