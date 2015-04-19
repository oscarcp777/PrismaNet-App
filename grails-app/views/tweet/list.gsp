<%@ page import="com.prismanet.Tweet"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="home">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'twitter.css')}" type="text/css">
</head>
<body>
	<div class="page-content">
		<g:render contextPath="../concept" template="tabTwitter"  model="['concept':concept,'tabMain':'','tabTweets':'active','tabChar':'','tabSentimental':'']"></g:render>
		<div class="row well ">
				<div class="col-xs-12" >
		<div class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title">
							<i class="ace-icon fa  fa-cloud"></i> 
							<g:message code="tweets.list.cloud.more.words"/> <g:message code="dashborad.concept.tweets"/>
						</h5>

						<div class="widget-toolbar">
							<a href="#" data-action="collapse"> <i
								class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main" style="position: relative;">
						<div  id="cloudWords" style="min-height:400px;"> 
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
							<g:message code="tweets.list.cloud.more.words"/> <g:message code="dashborad.concept.tweets"/>
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

								<tbody id="tableWords">
								<tr><td></td></tr>
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
	activeItemMenuLevel2('${concept.id}','${concept.id}-tweet','${concept.conceptName}');
	
		jQuery(function($) {
			$(".tooltips").tooltip();
 			getWordsCloud('#cloudWords','${concept.id}');
		});
	</script>
</body>
</html>
