<script type="text/javascript">
 var dateReport='${dateReport}';
</script>
<div ng-app="prismaApp" >
<div  ng-controller="ReportTweetCtrl as report">
		<sec:ifLoggedIn>
            <div class="col-xs-offset-7 col-sm-5 " >
			<div class="form-inline timePickert">
				<div class="form-group timePickert">
				 <label ><b>Cambiar hora del reporte:</b></label>
				 <uib-timepicker  class="form-control timePickert" ng-change="report.changed()" ng-model="timeReport"  show-meridian="false" hour-step="1" minute-step="60" ></uib-timepicker>
				</div>
				 <button type="submit" class="btn btn-info" ng-click="report.changedTime();">Cambiar</button>
			</div>
			</div>
		</sec:ifLoggedIn>
				  <div class="">
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
					<div class="col-sm-12 pad-0">
						<div class="tabbable">
						<concept-tab></concept-tab>
						</div>
					</div>
					
				
	
</div>
<r:require modules="highcharts,angular" />
</div>