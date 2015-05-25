<%@ page import="com.prismanet.Tweet"%>
<%@page import="com.prismanet.TweetService.SamplingType"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="home">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'twitter.css')}" type="text/css">
</head>
<body>
		<g:render contextPath="../concept" template="tabTwitter"  model="['concept':concept,'tabMain':'','tabTweets':'active','tabChar':'','tabSentimental':'']"></g:render>
<div class="row bg-well">
<div class="col-lg-12">
   <g:render contextPath="../user" template="headerHelp" model="['mainMessage':'dashborad.tab.tweets.desc']"></g:render>
    <div class="col-lg-9 no-padding">
   <div class="widget-box widget-prisma">
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
						<div  id="cloudWordsTW" style="min-height:350px;"> 
						</div>
						</div>
						</div>
					</div>
	</div>
				<div class="col-xs-3 no-padding" >
				<div class="widget-box widget-prisma">
					<div class="widget-header">
						<div class="widget-toolbar">
							<a href="#" data-action="collapse"> <i
								class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">
							<table class="table table-bordered table-striped table-condensed table-hover">
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
   
    <div class="col-lg-12">
		<div class="widget-box widget-prisma">
			<div class="widget-header">
				<h5 class="widget-title"><i class="fa fa-filter"></i> Filtros Aplicados </h5>

						<div class="widget-toolbar input-group blue-active">
							<div id="pickertListTweets" class="btn btn-info btn-white date-picker">
								<i class="ace-icon fa fa-calendar"></i> <span class="date-range"></span>
								<i class="ace-icon fa fa-chevron-down"></i>
							</div>
						</div>
						<div class="widget-toolbar blue-active" >
						      <div class="btn-group  btn-corner" style="display: none;" id="refresh-sampling">
								<g:link class="btn btn-info active tooltips" controller="concept" action="sampling" id="${concept.id}" 
								   data-original-title="reiniciar el muestreo">
									<i class="ace-icon fa fa-refresh icon-only bigger-150"></i>
								</g:link>
							  </div>
								<div class="btn-group  btn-corner" data-toggle="buttons" id="samplingType">
								  <label  class="btn btn-info active tooltips" 
								  data-original-title="${g.message (code: 'sampling.tweets.random')}">
								    <input type="radio" checked name="options" id="option1" value="${SamplingType.RANDOM}">
								     <i class="ace-icon fa fa-random align-top bigger-125"></i>
								  </label>
								    <label class="btn btn-info tooltips" 
								  data-original-title="${g.message (code: 'sampling.tweets.author')}">
								    <input type="radio" name="options" id="option2" value="${SamplingType.TOP_RELEVANT_AUTHORS}">
								     <i class="ace-icon fa fa-users align-top bigger-125"></i>
								  </label>
								    <label class="btn btn-info tooltips" 
								  data-original-title="${g.message (code: 'sampling.tweets.retweet')}">
								    <input type="radio" name="options" id="option3" value="${SamplingType.TOP_RETWEETS}">
								     <i class="ace-icon fa fa-retweet align-top bigger-125"></i>
								  </label>								  
								  <label class="btn btn-info tooltips " 
								  data-original-title="${g.message (code: 'sampling.tweets.fav')}">
								    <input type="radio" name="options" id="option4" value="${SamplingType.TOP_FAVS}">
								    <i class="ace-icon fa fa-star align-top bigger-125"></i>
								  </label>
								
								</div>
							</div>

			</div>
		<div class="widget-body" >
			<div class="widget-main">
			<div id="divListTweets" class="center-tweets" >
			 <g:render template="listTweets" model="['concept':concept,'tweetList':tweetInstanceList,'tweetTotal':tweetInstanceTotal]"></g:render>
			</div>
		 </div>
		</div>
		</div>
</div>
	</div>	
</div>
	<script type="text/javascript">
	var dateFrom="<g:formatDate format='yyyy-MM-dd HH:mm:ss' date='${dateFrom}' />"
	var dateTo="<g:formatDate format='yyyy-MM-dd HH:mm:ss' date='${dateTo}' />"
	console.log(dateFrom);
	var id='${concept.id}';
	activeItemMenuLevel2(id,'${concept.id}-tweet','${concept.conceptName}');
	var params='${params}';
	
	function loadPickertListTweets(start, end, rangeSelect) {
   	 $('#pickertListTweets span').html(rangeSelect+' - '+start.format('LLLL') + ' - ' + end.format('LLLL'));
   	 var type=$("#samplingType input[type='radio']:checked").val();
	 var data = {"conceptsId":${concept.id},"dateFrom":start.format('L HH:mm'),"dateTo":end.format('L HH:mm'),
	    		      'div':'#divListTweets','samplingType':type};
	}
	
	function setDatesCustom(container){
		$('#'+container+' span').html(moment(dateFrom,"YYYY-MM-DD HH:mm:ss").format('LLL')+ ' - ' + moment(dateTo,"YYYY-MM-DD HH:mm:ss").format('LLL'));	
	}
		jQuery(function($) {
 			getWordsCloud(getParamsCloud(params,'#cloudWordsTW',id));
			 loadDatepicker('pickertListTweets',loadPickertListTweets,setDatesCustom);
		});
	</script>
</body>
</html>
