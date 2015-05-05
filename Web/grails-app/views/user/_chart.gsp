
<div class="col-lg-12">
	<div class="widget-box widget-color-blue">
		<div class="widget-header">
			<h5 class="widget-title">
				<i class="ace-icon  fa-lg fa fa-${icon}"> </i>
				<label id="title-${callback}"> <g:message code="${titleChar}" /></label>
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
			<div class="widget-toolbar blue-active">
			   <div class="btn-group  btn-corner">
				<a href="javascript:void(0);" id="help-${div}" class="btn btn-grey btn-help" > 
				<i class="ace-icon fa fa-question-circle fa-2x"></i>
				</a>
			   </div>
			</div>
		</div>

		<div class="widget-body">
				<div class="widget-main">
					<div id="${div}" style=" min-height: 400px; min-width: 310px;margin: 0 auto"></div>
				</div>
			</div>
	</div>
</div>
<script type="text/javascript">

    $(function() {
        var contentHelp='<g:message code="${contentHelp}" args="[conceptName]"/>';
    	 $('#help-${div}').popover({
    		 content:contentHelp,
    		 html:true,
    		 placement:'bottom',
    		 container:'body',
    		 trigger:'hover'
    	 });
    	});

</script>