<%@ page import="com.prismanet.Tweet"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="home">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'twitter.css')}" type="text/css">
</head>
<body>
<g:javascript src="cloud/d3/d3.js"  />
<g:javascript src="cloud/d3/d3.layout.cloud.js"  />
	<div class="page-content">
		<g:render contextPath="../concept" template="tabTwitter"  model="['concept':concept,'tabMain':'','tabTweets':'active','tabChar':'','tabSentimental':'']"></g:render>
		<div class="row well ">
				<div class="col-xs-12" >
		<div class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title">
							<i class="ace-icon fa  fa-cloud"></i> 
							<g:message code="tweets.list.cloud.more.words"/>
						</h5>

						<div class="widget-toolbar">
							<a href="#" data-action="collapse"> <i
								class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main">
						<div  id="cloudWords" style="padding:50px;margin:50px;"> 
						</div>
						</div>
						</div>
					</div>
		</div>
		
       <g:render template="listTweets" model="['concept':concept,'tweetList':tweetInstanceList,'tweetTotal':tweetInstanceTotal]"></g:render>			

			<div class="col-xs-4">
				<div class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title">
							<i class="ace-icon fa  fa-cloud"></i> 
							<g:message code="tweets.list.cloud.more.words"/>
						</h5>

						<div class="widget-toolbar">
							<a href="#" data-action="collapse"> <i
								class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">
							<table class="table table-bordered table-striped">
								<thead >
									<tr >
										<th style="background-color: white;"><i class="ace-icon fa fa-caret-right blue"></i>
										<g:message code="tweets.list.cloud.words"/>
										
										</th>

										<th style="background-color: white;"><i class="ace-icon fa fa-caret-right blue"></i>
										<g:message code="tweets.list.cloud.appearances"/>
										</th>


									</tr>
								</thead>

								<tbody>
									<g:each in="${relevantWords}" status="i" var="tweet">
										<tr class="blue">
											<td>
												${tweet.text}
											</td><td>
											${tweet.size}</td>
										</tr>
									</g:each>
								</tbody>
							</table>
						</div>
						<!-- /.widget-main -->
					</div>
					<!-- /.widget-body -->
				</div>
				<!-- /.widget-box -->
			</div>
		</div>
		
	</div>
	<script type="text/javascript">
	var word_array='${relevantWordsJson}';
	var jsonObject =word_array.replace(/&quot;/g, '"');
	var jsonObject2 = JSON.parse(jsonObject);
	activeItemMenuLevel2('${concept.id}','${concept.id}-tweet','${concept.conceptName}');
		jQuery(function($) {
			$(".tooltips").tooltip();
			 var fill = d3.scale.category20();

		      d3.layout.cloud().size([400,400])
		           .words(jsonObject2.map(function(d) {
           			 return {text: d.text, size:  10 + Math.random() *50};
          			}))
		          .padding(1)
		          .rotate(function() { return ~~(Math.random() * 2) * 90; })
		          .font("Impact")
		          .fontSize(function(d) { return d.size; })
		          .on("end", draw)
		          .start();

		      function draw(words) {
		        d3.select("#cloudWords").append("svg")
		            .attr("width",400)
		            .attr("height",400)
		          .append("g")
		            .attr("transform", "translate(100,100)")
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
