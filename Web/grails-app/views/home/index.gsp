<html>
<head>
<meta name='layout' content='home' />

</head>
<body>

<g:javascript src="cloud/wordcloud2.js"  />
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
	 <div id="cloudWords" style=" height: 400px;"></div>
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
     var json =[{"text":"cfkargentina","size":144031},{"text":"de","size":57250},{"text":"la","size":53283},{"text":"a","size":42155},{"text":"y","size":38975},{"text":"el","size":38254},{"text":"que","size":37182},{"text":"en","size":35078},{"text":"no","size":26716},{"text":"los","size":21594},{"text":"para","size":17683},{"text":"por","size":16577},{"text":"con","size":16258},{"text":"un","size":15183},{"text":"se","size":14427}]
     
      $(function() {
			var list=[['foo', 12], ['bar', 26],['foo', 32], ['bar', 46],['foo', 120], ['bar', 60],['foo', 52], ['bar', 60]];
			var listJson=[];
			var count=0;
			for (var i = 1; i < json.length; i++) { 
			    var h = parseInt(json[i].size/1000,10);
			    console.log(json[i].size);
			    console.log(h);
			    count+=json[i].size;
			    listJson.push([json[i].text, h]);
			}
			console.log('·········')
			console.log(count/json.length);
			WordCloud($('#cloudWords')[0], { list: listJson } );

     
     
      });
    </script>
</body>
</html>

