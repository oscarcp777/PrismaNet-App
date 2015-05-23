
	<div class="col-lg-12">
		<div class="widget-box transparent collapsed">
			<div class="widget-header" style="margin:0 30px 0 30px;">
				<div class="widget-toolbar no-border">
				<a href="javascript:void(0);" data-action="collapse" id="switchHelp">
				  <label class="ace-icon ">
						<g:checkBox name="switchCheck" checked="false" class="ace ace-switch ace-switch-5"  />
						<span class="lbl middle" ></span>
					</label>
				</a>

				</div>
				<div class="widget-toolbar">
					<span class="lbl middle padding-4">Ver Ayuda</span>
				</div>
			</div>


		   <div class="widget-body" >
				<blockquote>
					<div class="alert alert-info fade in">
						<i class="fa fa-info-circle fa-lg fa-border"></i>
						<g:message code="${mainMessage}" args="[conceptName]"/>
						<br>
						<g:message code="dashborad.tab.main.desc.help" />
						<i class="fa fa-question-circle fa-lg fa-border"></i>
					</div>
				</blockquote>
		   </div>
		</div>
</div>
<script type="text/javascript">
	$( "#switchHelp" ).click(function() {
		$("#switchCheck").prop( "checked", !($("#switchCheck").is(':checked')) );
	});
</script>