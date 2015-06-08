<%@ page import="com.prismanet.Concept"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

<title><g:layoutTitle default="${grailsApplication.config.app.name}" /></title>
<link rel="shortcut icon" href="${resource(dir: 'img', file: 'favicon.png')}" type="image/png">
<link rel="icon" href="${resource(dir: 'img', file: 'favicon.png')}" type="image/png">
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300">
<r:require modules="core,highcharts,datepicker,editable,ace" />

<g:layoutHead />
<r:layoutResources />

</head>
<body class="skin-1">

	<div class="navbar navbar-default navbar-fixed-top" id="navbar">
		<script type="text/javascript">
				try {
					ace.settings.check('navbar', 'fixed')
				} catch (e) {
				}
			</script>
		<div class="navbar-container" id="navbar-container">
			<button type="button" class="navbar-toggle menu-toggler pull-left"
				id="menu-toggler">
				<span class="sr-only">Menu</span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<div class="navbar-header pull-left">
				<div class="logo">
				<g:render contextPath="../public" template="logo" ></g:render>
				</div>
				<!-- /.brand -->
				<!-- /.navbar-header -->
			</div>
			<div class="navbar-buttons navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">
					<li><a class="date-time" href="javascript:void(0);"><span id="datetime"> </span></a>
					</li>
					<li>
						<a data-toggle="dropdown" href="javascript:void(0);" class="dropdown-toggle" aria-expanded="false">
							<span class="user-info"> <small>
							<g:message code="home.user.wellcome" /> </small> <sec:username />
						</span> <i class="ace-icon fa fa-caret-down"></i>
						</a>
						<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
						   <sec:access expression="hasRole('ROLE_USER_ADVANCE')">
							<li ><g:link controller='concept' action='listAdvance'>
									<i class="ace-icon fa fa-cog"></i>
									<g:message code="home.user.settings" />
								</g:link>
							</li>
						  </sec:access>
							<li >
								<g:link controller='user' action='infoAccount'>
									<i class="ace-icon fa fa-user"></i> 
									<g:message code="home.user.profile" />
								</g:link>
							</li>						  
							<li ><g:link controller='user' action='changePassword'>
								<i class="ace-icon fa fa-key"></i> 
								<g:message code="home.user.change.key" />
								</g:link>
							</li>
							<li class="divider"></li>

							<li><sec:ifLoggedIn>

									<g:link controller='logout'>
										<i class="ace-icon fa fa-power-off"></i>
										<g:message code="home.user.logout" />
									</g:link>
								</sec:ifLoggedIn></li>
						</ul>
					</li>
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


		<div class="sidebar responsive sidebar-fixed" id="sidebar">
			<script type="text/javascript">
							try {
								ace.settings.check('sidebar', 'fixed')
							} catch (e) {
							}
						</script>
			<div style="position: relative;">
				<div class="nav-wrap" style="">
					<div style="position: relative; top: 0px; transition: top 0.2s; -webkit-transition: top 0.2s;">
							<div class="sidebar-shortcuts" id="sidebar-shortcuts">
								<div class="sidebar-shortcuts-large"
									id="sidebar-shortcuts-large">
									<button class="btn btn-info"
										onclick="alert('aqui va los datos de twitter');">
										<i class="ace-icon fa fa-twitter  bigger-125"></i>
									</button>

									<button class="btn btn-primary">
										<i class="ace-icon fa fa-facebook bigger-125"></i>
									</button>

									<button class="btn btn-danger">
										<i class="ace-icon fa fa-comments bigger-125"></i>
									</button>

									<button class="btn btn-success">
										<i class="ace-icon fa fa-cogs"></i>
									</button>
								</div>

								<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
									<span class="btn btn-success"></span> 
									<span class="btn btn-info"></span> 
									<span class="btn btn-warning"></span>
									<span class="btn btn-danger"></span>
								</div>
						   </div>
							<ul class="nav nav-list" id="menuIzquierdo">
								<li class="" id="home">
								<g:link controller="user"
										action="stats">
										<i class="menu-icon fa fa-tachometer"></i>
										<span class="menu-text"><g:message
												code="home.menu.begin" /> </span>
									</g:link> <b class="arrow"></b>
								</li>
								
							<g:if test="${session.user.concepts}">
								<g:each in="${session.concepts.sort{it.id}}" status="i"
									var="conceptInstance">
									<li id="${conceptInstance.id}" class="hsub">
									<a href="#"
										class="dropdown-toggle"> <i
											class="menu-icon glyphicon glyphicon-cloud-download"></i> <span
											class="menu-text">
												${fieldValue(bean: conceptInstance, field: "conceptName")}
										</span> <b class="arrow fa fa-angle-down"></b>
									</a> <b class="arrow"></b>

										<ul class="submenu">

											<li id="${conceptInstance.id}-dash"><g:link
													controller='concept' action="dashboard"
													id="${conceptInstance.id}">
													<i class="menu-icon fa fa-caret-right"></i>
													<g:message code="home.menu.concept.dashboard" />
												</g:link> <b class="arrow"></b></li>
											<li id="${conceptInstance.id}-tweet"><g:link
													controller="tweet" action="list"
													 params="[conceptsId:conceptInstance.id]">
													<i class="menu-icon fa fa-caret-right"></i>
													<g:message code="home.menu.concept.twitter" />
												</g:link> <b class="arrow"></b></li>
											<li id="${conceptInstance.id}-face"><g:link
													controller="concept" action="postStats"
													id="${conceptInstance.id}">
													<i class="menu-icon fa fa-caret-right"></i>
													<g:message code="home.menu.concept.facebook" />
												</g:link> <b class="arrow"></b></li>
										</ul></li>
								</g:each>
								</g:if>
								<sec:access expression="hasRole('ROLE_ADMIN')">
									<li id="admin"><g:link controller='home'
											action='controlPanel'>
											<i class="menu-icon fa fa-cogs"></i>
											<g:message code="home.menu.admin" />
										</g:link> <b class="arrow"></b></li>
								</sec:access>
							</ul>
						</div>
					</div>
					<div class="ace-scroll nav-scroll">
						<div class="scroll-track" style="display: none; height: 544px;">
							<div class="scroll-bar"
								style="height: 510px; top: 0px; transition: top 0.13s; -webkit-transition: top 0.13s;">
							</div>
						</div>
						<div class="scroll-content" style="">
							<div style=""></div>
						</div>
					</div>
				</div>
				<!-- /.nav-list -->
				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse"
					style="z-index: 1;">
					<i class="ace-icon fa fa-angle-double-left"
						data-icon1="ace-icon fa fa-angle-double-left"
						data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>

				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
				</script>
			</div>
			<!-- /.nav-list -->
			<div class="main-content">
				<div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
					<script type="text/javascript">
								try {
									ace.settings.check('breadcrumbs', 'fixed')
								} catch (e) {
								}
							</script>

				<ul class="breadcrumb">
					<li><g:link controller="home" action="index">
							<i class="ace-icon fa fa-home home-icon"></i>
							<g:message code="public.index.home" />
						</g:link>
					</li>
					<li><a href="javascripts:void(0);" id="level1"> <g:message
								code="dashborad.tab.main" />
						</a>
					</li>
					<li class="active" id="liLevel2"> <small
							id="level2"> 
						</small>
						
					</li>
				</ul>
				<!-- .breadcrumb -->

				</div>

				<div class="page-content">
					
							<g:layoutBody />
							<r:layoutResources />
							<!-- PAGE CONTENT ENDS -->
						</div>
						<!-- /.col -->
					
				<!-- /.page-content -->
			</div>
			<!-- /.main-content -->
			<div class="footer">
				<div class="footer-inner">
					<div class="footer-content">
						<span class="bigger-120"> 
						<span class="bolder logoMini">
						${raw(grailsApplication.config.app.logo)}
						</span> 
					Derechos reservados © 2013-2015
						</span> &nbsp; &nbsp; <span class="action-buttons"> <a href="#">
								<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
						</a> <a href="#"> <i
								class="ace-icon fa fa-facebook-square text-primary bigger-150"></i>
						</a> <a href="#"> <i
								class="ace-icon fa fa-rss-square orange bigger-150"></i>
						</a>
						</span>
					</div>
				</div>
			</div>

			<a href="#" id="btn-scroll-up"
				class="btn-scroll-up btn btn-sm btn-inverse"> <i
				class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
			</div><!-- /.main-container -->
			<!-- 	</div> -->
			<!-- basic scripts -->
	<script type="text/javascript">
	
			function changeStateMenu(state){
				var context='${request.contextPath}';
				setDataMenu(context,state);
			}
			$(document).ready(function() {
				$(".tooltips").tooltip();
				$(".help").popover();
				bootbox.setDefaults({
					  locale: "es"
					});
			var menuState='${session.menu}';
			if (menuState == "collapsed") {
				$("#sidebar-collapse").click();
			}
		});
	</script>
</body>
</html>
