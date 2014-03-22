<%@ page import="com.prismanet.Concept" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><g:layoutTitle default="Prisma-Net" /></title>
<link rel="shortcut icon" href="${resource(dir: 'img', file: 'favicon.png')}" type="image/png">
<link rel="icon" href="${resource(dir: 'img', file: 'favicon.png')}" type="image/png">

<r:require modules="core,ace,chartPie,highcharts,datepicker" />

<g:layoutHead />
<r:layoutResources />
</head>
<body>
<g:set var="scService" bean="springSecurityService"/>
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
							class="logoFirst"> <i class="fa fa-filter"
								style="font-weight: bold; font-size: 0.9em;"></i> 
								<g:message code="general.title.prism"/></span><g:message code="general.title.net"/>
						</a>
					</div>
					<!-- /.brand -->
					<!-- /.navbar-header -->
				</div>
				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<li > <a class="dropdown-toggle" href="javascript:void(0);">  
							    <span id="datetime">
							    </span>
							    </a>
						</li>
						<li><a data-toggle="dropdown" href="#"
							class="dropdown-toggle"> <img class="nav-user-photo"
								src="${resource(dir: 'img', file: 'oscar.png')}"
								alt="Oscar&#39;s Photo"> <span class="user-info"> <small>Bienvenido,</small>
									<sec:username />
							</span> <i class="fa fa-caret-down"></i>
						</a>

							<ul
								class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li><a href="#"> <i class="fa fa-cog"></i> Settings
								</a></li>

								<li><a href="#"> <i class="fa fa-user"></i> Profile
								</a></li>

								<li class="divider"></li>

								<li><sec:ifLoggedIn>

										<g:link controller='logout'>
											<i class="fa fa-off"></i>Log Out</g:link>
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
									<i class="fa fa-signal"></i>
								</button>

								<button class="btn btn-info">
									<i class="fa fa-pencil"></i>
								</button>

								<button class="btn btn-warning">
									<i class="fa fa-group"></i>
								</button>

								<button class="btn btn-danger">
									<i class="fa fa-cogs"></i>
								</button>
							</div>

							<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
								<span class="btn btn-success"></span> <span class="btn btn-info"></span>

								<span class="btn btn-warning"></span> <span
									class="btn btn-danger"></span>
							</div>
						</div>
						<!-- #sidebar-shortcuts -->

						<ul class="nav nav-list" id="menuIzquierdo">
							<li class="active" id="Dashboard">
							<g:link	controller="home" action="index"> 
								<i class="fa fa-dashboard"></i> 
								<span class="menu-text" >Dashboard</span>
							   </g:link>
							</li>
							<li id="chart">
							   <a href="#" class="dropdown-toggle"> <i
									class="fa fa-bar-chart-o"></i> <span class="menu-text">Estadisticas</span>

									<b class="arrow fa fa-angle-down"></b>
							</a>

								<ul class="submenu">
									<li id="general"><g:link controller='user' action="stats" >
									<i class="fa fa-angle-double-right"></i>
											General
									</g:link> 
									</li>

									<li id="mensual"><g:link controller='user' action="monthStats" ><i class="fa fa-angle-double-right"></i>
											Mensual
									</g:link> 
									</li>
								</ul>
							</li>
							<li id="concepts">
							<a href="#" class="dropdown-toggle"> 
							  <i class="fa fa-tags"></i> <span class="menu-text">Conceptos</span>
									<b class="arrow fa fa-angle-down"></b>
							</a>
								<ul class="submenu">
								<g:each in="${scService?.currentUser?.concepts?.sort{it.conceptName}}" status="i" var="conceptInstance">
									<li id="${conceptInstance.id}">
									<a href="#" class="dropdown-toggle">
									<i class="fa fa-angle-double-right"></i>
									${fieldValue(bean: conceptInstance, field: "conceptName")}
									<b class="arrow  fa fa fa-angle-down"></b>
									</a>
									<ul class="submenu">
									    <li id="${conceptInstance.id}-tweet">
									    	<g:link controller='tweet' action="list" params="[conceptsId:conceptInstance.id]" >
											<i class="fa fa-twitter"></i>
											<span class="menu-text">Tweets</span>
											</g:link>
									    </li>
									    <li id="${conceptInstance.id}-stats">
									    <g:link controller="concept" action="stats" id="${conceptInstance.id}" >
											<span class="glyphicon glyphicon-stats"> </span>
											<span class="menu-text">Graficos</span>
										</g:link>
									    </li>
									</ul>
									</li>
									</g:each>
								</ul>
							</li>							
							<li id="facebook"><a href="#"> <i class="fa fa-facebook"></i> <span
									class="menu-text">Facebook</span>
							</a></li>
							<li id="Flickr"><a href="#"> <i class="fa fa-flickr"></i> <span
									class="menu-text">Flickr</span>
							</a></li>
							<li id="Bloqs"><a href="#"> <i class="fa fa-book"></i> <span
									class="menu-text">Bloqs</span>
							</a></li>
							<li id="Diarios"><a href="#"> <i class="fa fa-cloud"></i> <span
									class="menu-text">Diarios</span>
							</a></li>
							<li id="Otros"><a href="#"> <i class="fa fa-rss-square"></i> <span
									class="menu-text">Otros</span>
							</a></li>
							
							<sec:access expression="hasRole('ROLE_ADMIN')">
							    <li id="admin">
								<g:link controller='home' action='controlPanel'>
								<i class="fa fa-cogs"></i> <span class="menu-text"><g:message code="home.menu.admin"/></span>
								</g:link>
								</li>
							</sec:access>
							
								
							
						</ul>
						<!-- /.nav-list -->

						<div class="sidebar-collapse" id="sidebar-collapse">
							<i class="fa fa-angle-double-left"
								data-icon1="fa fa-dangle-double-left"
								data-icon2="fa fa-angle-double-left"></i>
						</div>

						<script type="text/javascript">
							try {
								ace.settings.check('sidebar', 'collapsed')
							} catch (e) {
							}
						</script>
					</div>
					<div class="main-content">
						<div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
							<script type="text/javascript">
								try {
									ace.settings.check('breadcrumbs', 'fixed')
								} catch (e) {
								}
							</script>

							<ul class="breadcrumb">
								<li><i class="fa fa-home home-icon"></i> <g:link
										controller="home" action="index">Home</g:link></li>
								<li class="active" ><small id="nameItem" style="font-size: 13px;">Dashboard</small></li>
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

			</div>
			<!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up"
				class="btn-scroll-up btn btn-sm btn-inverse"> <i
				class="fa fa-double-angle-up icon-only bigger-110"></i>
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
