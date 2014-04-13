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
								alt="Oscar&#39;s Photo"> <span class="user-info"> <small><g:message code="home.user.wellcome"/> </small>
									<sec:username />
							</span> <i class="fa fa-caret-down"></i>
						</a>

							<ul
								class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li><a href="#"> <i class="fa fa-cog"></i> <g:message code="home.user.settings"/>
								</a></li>

								<li><a href="#"> <i class="fa fa-user"></i> <g:message code="home.user.profile"/>
								</a></li>

								<li class="divider"></li>

								<li><sec:ifLoggedIn>

										<g:link controller='logout'>
											<i class="fa fa-off"></i><g:message code="home.user.logout"/> </g:link>
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
                         <div class="nav-container">
						<ul class="nav nav-list" id="menuIzquierdo">
							<li class="active" id="home">
							<g:link	controller="user" action="stats"> 
								<i class="fa fa-home"></i> 
								<span class="menu-text" ><g:message code="home.menu.begin"/> </span>
							   </g:link>
							</li>
								<g:each in="${session.concepts}" status="i" var="conceptInstance">
									<li id="${conceptInstance.id}">
									<a href="#" class="dropdown-toggle">
									<i class="fa fa-cloud-download"></i>
									<span class="menu-text" >${fieldValue(bean: conceptInstance, field: "conceptName")}</span>
									
									<b class="arrow  fa fa fa-angle-down"></b>
									</a>
									<ul class="submenu">
									
									<li id="${conceptInstance.id}-dash">
									    	<g:link controller='concept' action="dashboard" id="${conceptInstance.id}"  >
											<span class="menu-text"><g:message code="home.menu.concept.dashboard"/></span>
											</g:link>
									    </li>
									    <li id="${conceptInstance.id}-tweet">
									    	<g:link controller='tweet' action="list" params="[conceptsId:conceptInstance.id]" >
											<span class="menu-text"><g:message code="home.menu.concept.twitter"/></span>
											</g:link>
									    </li>
									    <li id="${conceptInstance.id}-face">
									    <g:link controller="facebook" action="list" id="${conceptInstance.id}" >
											<span class="menu-text"><g:message code="home.menu.concept.facebook"/></span>
										</g:link>
									    </li>
									</ul>
									</li>
									</g:each>
							<sec:access expression="hasRole('ROLE_ADMIN')">
							    <li id="admin">
								<g:link controller='home' action='controlPanel'>
								<i class="fa fa-cogs"></i> <span class="menu-text"><g:message code="home.menu.admin"/></span>
								</g:link>
								</li>
							</sec:access>
						</ul>
						</div>
						<!-- /.nav-list -->

						<div class="sidebar-collapse" id="sidebar-collapse">
							<i class="fa fa-angle-double-left"
								data-icon1="fa fa-angle-double-left"
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
				class="fa fa-double-angle-up bigger-110"></i>
			</a>
		</div>
		<!-- /.main-container -->
	</div>
	<!-- basic scripts -->

</body>
</html>
