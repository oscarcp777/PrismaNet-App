<!DOCTYPE html>
<html lang="es">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><g:layoutTitle default="Prisma-Net" /></title>
<link rel="shortcut icon"
	href="${resource(dir: 'images', file: 'favicon.gif')}" type="image/gif">
<link rel="icon" href="${resource(dir: 'images', file: 'favicon.gif')}"
	type="image/gif">

<r:require modules="core,ace" />

<g:layoutHead />
<r:layoutResources />
</head>
<body>

	<div class="navbar-fixed breadcrumbs-fixed skin-1">
		<div class="navbar navbar-default navbar-fixed-top" id="navbar">
			<script type="text/javascript">
				try {
					ace.settings.check('navbar', 'fixed')
				} catch (e) {
				}
			</script>
			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<div class="logo">

						<a class="logoHolder" title="return to home page" href="#"> <span
							class="logoFirst"> <i class="icon-rombo"
								style="font-weight: bold; font-size: 0.9em;"></i> Prisma
						</span>-Net
						</a>
					</div>
					<!-- /.brand -->
					<!-- /.navbar-header -->
				</div>
				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<li><a data-toggle="dropdown" href="#"
							class="dropdown-toggle"> <img class="nav-user-photo"
								src="${resource(dir: 'img', file: 'oscar.png')}"
								alt="Oscar&#39;s Photo"> <span class="user-info"> <small>Bienvenido,</small>
									<sec:username />
							</span> <i class="icon-caret-down"></i>
						</a>

							<ul
								class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li><a href="#"> <i class="icon-cog"></i> Settings
								</a></li>

								<li><a href="#"> <i class="icon-user"></i> Profile
								</a></li>

								<li class="divider"></li>

								<li><sec:ifLoggedIn>

										<g:link controller='logout'>
											<i class="icon-off"></i>Log Out</g:link>
									</sec:ifLoggedIn></li>
							</ul></li>
					</ul>

				</div>
			</div>
		</div>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try {
					ace.settings.check('main-container', 'fixed')
				} catch (e) {
				}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#"> <span
					class="menu-text"></span>
				</a>
				<nav>
					<div class="sidebar sidebar-fixed" id="sidebar">
						<script type="text/javascript">
							try {
								ace.settings.check('sidebar', 'fixed')
							} catch (e) {
							}
						</script>

						<div class="sidebar-shortcuts" id="sidebar-shortcuts">
							<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
								<button class="btn btn-success">
									<i class="icon-signal"></i>
								</button>

								<button class="btn btn-info">
									<i class="icon-pencil"></i>
								</button>

								<button class="btn btn-warning">
									<i class="icon-group"></i>
								</button>

								<button class="btn btn-danger">
									<i class="icon-cogs"></i>
								</button>
							</div>

							<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
								<span class="btn btn-success"></span> <span class="btn btn-info"></span>

								<span class="btn btn-warning"></span> <span
									class="btn btn-danger"></span>
							</div>
						</div>
						<!-- #sidebar-shortcuts -->

						<ul class="nav nav-list">
							<li class="active"><a href="#"> <i
									class="icon-dashboard"></i> <span class="menu-text">Dashboard</span>
							</a></li>
							<li><g:link controller='tweet' action="list">
									<i class="icon-twitter"></i>
									<span class="menu-text">Ver Tweets</span>
								</g:link></li>
							<li><a href="#" class="dropdown-toggle"> <i
									class="icon-bar-chart"></i> <span class="menu-text">Graficos</span>

									<b class="arrow icon-angle-down"></b>
							</a>

								<ul class="submenu">
									<li><a href="#"> <i class="icon-double-angle-right"></i>
											Real Time
									</a></li>

									<li><a href="#"> <i class="icon-double-angle-right"></i>
											jqGrid plugin
									</a></li>
								</ul></li>
							<li><a href="#"> <i class="icon-facebook"></i> <span
									class="menu-text">Facebook</span>
							</a></li>
							<li><a href="#"> <i class="icon-flickr"></i> <span
									class="menu-text">Flickr</span>
							</a></li>
							<li><a href="#"> <i class="icon-book"></i> <span
									class="menu-text">Bloqs</span>
							</a></li>
							<li><a href="#"> <i class="icon-cloud"></i> <span
									class="menu-text">Diarios</span>
							</a></li>
							<li><a href="#"> <i class="icon-rss-sign"></i> <span
									class="menu-text">Otros</span>
							</a></li>
						</ul>
						<!-- /.nav-list -->

						<div class="sidebar-collapse" id="sidebar-collapse">
							<i class="icon-double-angle-left"
								data-icon1="icon-double-angle-left"
								data-icon2="icon-double-angle-right"></i>
						</div>

						<script type="text/javascript">
							try {
								ace.settings.check('sidebar', 'collapsed')
							} catch (e) {
							}
						</script>
					</div>
				</nav>
				<section>
					<div class="main-content">
						<div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
							<script type="text/javascript">
								try {
									ace.settings.check('breadcrumbs', 'fixed')
								} catch (e) {
								}
							</script>

							<ul class="breadcrumb">
								<li><i class="icon-home home-icon"></i> <g:link
										controller="home" action="index">Home</g:link></li>
								<li class="active">Principal</li>
							</ul>
							<!-- .breadcrumb -->

						</div>

						<div class="page-content">
							<div class="row">
								<div class="col-xs-12">
									<!-- PAGE CONTENT BEGINS -->
									<div class="space-32"></div>
									<div class="space-32"></div>
									<g:layoutBody />
									<r:layoutResources />
									<!-- PAGE CONTENT ENDS -->
								</div>
								<!-- /.col -->
							</div>
							<!-- /.row -->
						</div>
						<!-- /.page-content -->
					</div>
					<!-- /.main-content -->
				</section>

			</div>
			<!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up"
				class="btn-scroll-up btn btn-sm btn-inverse"> <i
				class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div>
		<!-- /.main-container -->
	</div>
	<!-- basic scripts -->





	<script type="text/javascript">
		$(function() {
			$('#scroll').slimScroll({
				height : '200px',
				railVisible : true,
				alwaysVisible : true
			});
		});
	</script>


	<script type="text/javascript">
		if ("ontouchend" in document)
			document.write("<script src='js/jquery.mobile.custom.min.js'>"
					+ "<"+"/script>");
	</script>

</body>
</html>
