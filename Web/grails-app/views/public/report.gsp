<!DOCTYPE html>
<html lang="es" >
<head>
<meta name="layout" content="public" />
<r:require modules="ace" />
</head>
<body >
<script type="text/javascript">
 var dateReport='${dateReport}';
</script>
<div ng-app="prismaApp" >
<div  ng-controller="ReportTweetCtrl as report">
 
	
				
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
<r:require modules="highcharts,angular" />
</div>
</body>
</html>