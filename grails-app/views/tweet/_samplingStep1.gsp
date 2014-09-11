
<div class="widget-box transparent" id="samplingInit">
	<div class="widget-header widget-header-large">
		<h3 class="widget-title grey lighter">
			<i class="ace-icon fa fa-codepen green"></i> <g:message code="sampling.tweets.first.step.title"/>
		</h3>

		<div class="widget-toolbar no-border invoice-info">
			<span class="invoice-info-label"><g:message code="sampling.tweets.first.step.from"/></span> <span class="red">${dateFrom}</span>
	    <br> <span class="invoice-info-label"><g:message code="sampling.tweets.first.step.to"/></span> <span class="blue">${dateTo}</span>
		</div>

		<div class="widget-toolbar hidden-480">
			<a href="#"> <i class="ace-icon fa fa-calendar fa-lg "></i>
			</a>
		</div>
	</div>

	<div class="widget-body">
		<div class="widget-main padding-24">

			<div class="row">
				<div class="col-sm-2"></div>
				<div class="col-sm-8">
					<div class="row">
						<div
							class="col-xs-11 label label-lg label-info arrowed-in arrowed-right">
							<b><g:message code="sampling.tweets.first.step.demos"/>
							</b> <label id="demos">${demos} </label> Tweets
						</div>
					</div>
					<hr>
					<div class="row">
						<div
							class="col-xs-11 label label-lg label-primary arrowed-in arrowed-right">
							<b><g:message code="sampling.tweets.first.step.sampling"/> </b> <label id="sampling">${sampling}</label> Tweets
						</div>

					</div>
				</div>
				<!-- /.col -->

			</div>

		</div>
	</div>
</div>