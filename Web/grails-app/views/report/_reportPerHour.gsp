<script type="text/javascript">
 var dateReport='${dateReport}';
</script>
<div ng-app="prismaApp" >
<div  ng-controller="ReportTweetCtrl as report">
		<sec:ifLoggedIn>
            <div class="col-xs-offset-2 col-sm-10 " >
            <div class="form-inline">
				 <label style="margin-right: 10px;"><b> Cambiar hora del reporte: </b></label>
				<div class="input-group">
					 <input type="text" class="form-control date-picker col-xs-2" uib-datepicker-popup="dd-MMMM-yyyy" ng-model="dt" 
					 is-open="status.opened" max-date="maxDate" close-text="Cerrar" current-text="Hoy"   clear-text="Limpiar"/>
					<span class="input-group-btn">
					<button class="btn btn-sm btn-info" type="button" ng-click="report.open($event)">
						<i class="ace-icon fa fa-calendar bigger-110"></i>
					</button>
				</span>
				</div>
				<uib-timepicker  class="form-control timePickert" ng-change="report.changed()" ng-model="timeReport"  show-meridian="false" hour-step="1" minute-step="60" ></uib-timepicker>
				<button type="button" class="btn btn-info btn-sm" ng-click="report.changedTime();">
					<i class="ace-icon fa fa-key bigger-110"></i>Cambiar
				</button>
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