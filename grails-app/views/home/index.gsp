<html>
<head>
<meta name='layout' content='home' />

</head>
<body>

<g:javascript src="cloud/d3/d3.js"  />
<g:javascript src="cloud/d3/d3.layout.cloud.js"  />
		<div class="page-header">
			<h1>
				<i class="ace-icon fa fa-bar-chart-o"></i> <g:message code="dashborad.tab.main"/>
				<small>
								<i class="ace-icon fa fa-angle-double-right"></i>
								<g:message code="user.stats.title"/>
			</small>
			</h1>
			
		</div>
	<div class="container">
	 <div id="example" style="width: 550px; height: 350px;"></div>
			<div class="widget-box widget-color-dark light-border">
				<div class="widget-header">
					<h5 class="widget-title smaller">With Badge</h5>

					<div class="widget-toolbar">
						<span class="badge badge-danger">Alert</span>
					</div>
				</div>

				<div class="widget-body">
					<div class="widget-main padding-6">
						<div class="alert alert-info" id="charCloud"></div>
					</div>
				</div>
			</div>
			<div class="widget-box widget-color-blue light-border">
				<div class="widget-header header-color-grey">
					<h5 class="widget-title bigger lighter">
												<i class="ace-icon fa fa-table"></i>
												Tables &amp; Colors
											</h5>

					<div class="widget-toolbar">

						<a href="#" data-action="reload"> <i class="ace-icon fa fa-refresh"></i>
						</a> <a href="#" data-action="collapse"> <i
							class="ace-icon fa fa-chevron-up"></i>
						</a> <a href="#" data-action="close"> <i class="ace-icon fa fa-remove"></i>
						</a>
					</div>
				</div>

				<div class="widget-body">
					<div class="widget-main padding-4">
						<div class="slimScrollDiv" >
							<div class="slim-scroll" data-height="525" >
								<div class="content">
									<div id="page-body" role="main">

										<div id="controller-list" role="navigation">

											<ul class="list-unstyled">
												<g:each var="c"
													in="${grailsApplication.controllerClasses.sort { it.fullName } }">
													<g:if test="${c.fullName.contains("prismanet")}">
													<li class="controller">
													<i class="ace-icon fa fa-angle-double-right"></i>
													<g:link
															controller="${c.logicalPropertyName}">
															${c.logicalPropertyName}
														</g:link>
													</li>
													</g:if>
												</g:each>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>


	</div>
	</div>
 <script type="text/javascript">
     
     
      $(function() {
     
     
      var fill = d3.scale.category20();

      d3.layout.cloud().size([300, 300])
          .words([
            "Hello", "world", "normally", "you", "want", "more", "words",
            "than", "this"].map(function(d) {
            return {text: d, size: 10 + Math.random() * 90};
          }))
          .padding(5)
          .rotate(function() { return ~~(Math.random() * 2) * 90; })
          .font("Impact")
          .fontSize(function(d) { return d.size; })
          .on("end", draw)
          .start();

      function draw(words) {
        d3.select("#charCloud").append("svg")
            .attr("width",800)
            .attr("height", 300)
          .append("g")
            .attr("transform", "translate(150,150)")
          .selectAll("text")
            .data(words)
          .enter().append("text")
            .style("font-size", function(d) { return d.size + "px"; })
            .style("font-family", "Impact")
            .style("fill", function(d, i) { return fill(i); })
            .attr("text-anchor", "middle")
            .attr("transform", function(d) {
              return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
            })
            .text(function(d) { return d.text; });
      }
      });
    </script>
</body>
</html>

