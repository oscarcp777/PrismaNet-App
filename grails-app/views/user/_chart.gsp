<div class="col-lg-12">
	<div class="widget-box widget-color-blue">
		<div class="widget-header">
			<h5 class="widget-title">
				<span class="glyphicon glyphicon-stats"> </span>
				<g:message code="${titleChar}" />
			</h5>
			<g:if test="${divPickert != 'disable'}">
			<div class="widget-toolbar input-group ">
				<div id="${divPickert}" class="btn btn-primary date-picker">
					<i class="ace-icon fa fa-calendar"></i> <span class="date-range"></span>
					<i class="ace-icon fa fa-chevron-down"></i>
				</div>
			</div>
			</g:if>
			<div class="widget-toolbar">
				<a href="#" data-action="collapse"> <i
					class="1 bigger-125 ace-icon fa fa-chevron-up"></i>
				</a>
			</div>
			<g:if test="${callback != 'disable'}"> 
			<g:render template="chooseChannel"  bean="${callback}"></g:render>
			</g:if>
		</div>

		<div class="widget-body">
				<div class="widget-main">
					<div id="${div}" style=" min-height: 400px; min-width: 310px;margin: 0 auto"></div>
				</div>
			</div>
	</div>
</div>