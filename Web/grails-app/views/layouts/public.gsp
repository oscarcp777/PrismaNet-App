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
<r:require modules="core" />

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
			<div class="navbar-header pull-left">
				<div class="logo">
				<g:render contextPath="../public" template="logo" ></g:render>
				</div>
				<!-- /.brand -->
				<!-- /.navbar-header -->
			</div>
			<div class="navbar-buttons navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">
					<li class="grey">
							<a  class="dropdown-toggle" href="${createLink(uri: '/#section5')}" >
								<i class="ace-icon fa fa-users bigger-125"></i>
								<span class="label label-xlg label-inv-white arrowed">Contactenos</span>
							</a>
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
					<li>
						<a href="${createLink(uri: '/')}" >
						   <i class="ace-icon fa fa-home home-icon"></i>
							<g:message code="public.index.home" />
						</a>
					</li>
					<li><a href="javascripts:void(0);" id="level1"> 
					     Reportes
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

</body>
</html>
