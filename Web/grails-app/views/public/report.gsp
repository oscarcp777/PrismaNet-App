<!DOCTYPE html>
<html lang="es" >
<head>
<meta name="layout" content="main" />
<r:require modules="ace" />
</head>
<body >
<script type="text/javascript">
 var dateReport='${dateReport}';
</script>
<div ng-app="prismaApp" class="skin-1">
<div  ng-controller="ReportTweetCtrl as report">
  <div class="row" style="background-color: #FAFAFA;">
		<div class="navbar navbar-default navbar-fixed-top" id="navbar" >
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
						<div class="navbar-buttons navbar-header pull-right">
							<ul class="nav ace-nav">
								<li class="grey">
								<a href="${createLink(uri: '/#section5')}" class="btn btn-inverse btn-round" style="line-height: 25px;height: 80%;margin-top: 5px; ">
								   Solicitar Demo
								</a>
								</li>
							</ul>
							
						</div>
					</div>
				</div>
				<div class="container " style="margin-top: 70px; background-color: white;">
				  <div class="">
					<div class="page-header " style="margin-top: 20px;text-align: center">
						<h1>
							Políticos más mencionados en Twitter el {{hourReport | date : "d MMMM, 'a las' H:mm" }} horas
						</h1>
					</div>
					<section class="col-sm-12">
						<highchart id="chart1" config="chartConfig" class="col-sm-12"></highchart>
					</section>
					</div>
				   <div class="col-sm-12">
				   <div class=" hr hr-double hr-dotted hr18"></div>
				   <div data-toggle="buttons" class="btn-group btn-overlap">
				   
						<label class="btn btn-white btn-info btn-bold" ng-class="{'active':report.isSet(key)}"
						ng-click="report.setConcept(key)" ng-repeat='(key, value) in report.mapTweets'>
							<input type="radio" ng-value="key" ng-model="concept">
							<i class=" ace-icon fa  bigger-150 align-middle" 
							ng-class="{'fa-check-square-o':report.isSet(key) ,'fa-square-o':!report.isSet(key)}" ></i>
							{{key}}
						</label>
					</div>
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