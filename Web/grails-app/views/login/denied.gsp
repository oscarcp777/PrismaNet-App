<!DOCTYPE html>
<html lang="es">
<head>
<meta name='layout' content='main' />
<title><g:message code="springSecurity.denied.title" /></title>
<r:require modules="core,ace" />
</head>

<body>
<div class="page-content">
	
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<div class="error-container">
									<div class="well">
										<h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="ace-icon fa fa-sitemap"></i>
												Error
											</span>
											Acceso restringido
										</h1>

										<hr>
										<div class="alert alert-block alert-danger">
										<h3 class="lighter smaller"><g:message code="springSecurity.denied.message" /></h3>
										</div>
										<div>
										
										<hr>
										<div class="space"></div>
										<div class="center">
											<a href="javascript:history.back()" class="btn btn-grey">
												<i class="ace-icon fa fa-arrow-left"></i>
												Volver
											</a>
											<g:link controller='user' action='stats' class="btn btn-primary">
												<i class="ace-icon fa fa-tachometer"></i>
											Ir a pantalla principal
										</g:link>
											
										</div>
								      </div>

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div>
					</div><!-- /.row -->
					</div>
</body>
