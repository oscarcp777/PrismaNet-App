<!DOCTYPE html>
<html lang="es" >
<head>
<meta name="layout" content="main" />
<r:require modules="ace" />
</head>
<body >
<div ng-app="prismaApp" class="skin-1">
<div  ng-controller="ReportTweetCtrl as report">
  <div class="row" style="background-color: #FAFAFA;">
		<div class="navbar navbar-default navbar-fixed-top" id="navbar" style="min-height:65px;">
				<script type="text/javascript">
						try {
						ace.settings.check('navbar', 'fixed')
					} catch (e) {
					}
				</script>
					<div class="navbar-container" id="navbar-container">
						<div class="navbar-header pull-left">
							<div class="logo">
								<a class="logoHolder" href="javascript:void(0);"> <span
									class="logoFirst"><i class="ace-icon fa fa-filter"></i>Prisma</span>-Net
								</a>
							</div>
						</div>
					</div>
				</div>
				<div class="container " style="margin-top: 70px; background-color: white;">
				  <div class="">
					<div class="page-header " style="margin-top: 20px;">
						<h1>
							Cantidad de menciones en Twitter <i class="fa fa-angle-double-right"></i><small> En este grafico podes observar los candidatos</small>
						</h1>
					</div>
					<section class="col-sm-12">
						<highchart id="chart1" config="chartConfig" class="col-sm-12"></highchart>
					</section>
					</div>
				   <div class="col-sm-12">
					<div class=" hr hr-double hr-dotted hr18"></div>
					</div>
					<div class="col-sm-12">
						<div class="tabbable">
							<concept-tab></concept-tab>
						</div>
					</div>
					
				</div>
	</div>
</div>
<r:require modules="highcharts,angular" />
</div>
</body>
</html>