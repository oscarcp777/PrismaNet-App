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
<div class="col-lg-12" id="cloudWordsDiv" >
				<div class="widget-box widget-prisma">
					<div class="widget-header">
						<h5 class="widget-title">
							<i class="ace-icon fa  fa-cloud"></i> 
							<g:message code="tweets.list.cloud.words.cloud"/> <g:message code="dashborad.concept.tweets"/>
						</h5>

						<div class="widget-toolbar">
							<a href="javascript:void(0);" data-action="collapse" id="btn-collapse"> <i
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
		
   
    <div class="col-lg-12 no-padding">
		<div class="widget-box widget-prisma">
			<div class="widget-header">

						<div class="widget-toolbar input-group blue-active">
							<div id="pickertListTweets" class="btn btn-info btn-white date-picker">
								<i class="ace-icon fa fa-calendar"></i> <span class="date-range"></span>
								<i class="ace-icon fa fa-chevron-down"></i>
							</div>
						</div>
						<div class="widget-toolbar blue-active" >
								<div class="btn-group  btn-corner" data-toggle="buttons" id="samplingType">
								  <a href="javascript:void(0);"  class="btn btn-info  active btn-white tooltips" 
								  data-original-title="${g.message (code: 'sampling.tweets.date')}" onclick="gotToList('${SamplingType.DATE_ORDER}');">
								    <g:radio name="${SamplingType.DATE_ORDER}"  value="${SamplingType.DATE_ORDER}"/>
								     <i class="ace-icon fa fa-calendar align-top bigger-150"></i>
								  </a>
								    <a href="javascript:void(0);" class="btn btn-info btn-white tooltips" 
								  data-original-title="${g.message (code: 'sampling.tweets.author')}" onclick="gotToList('${SamplingType.TOP_RELEVANT_AUTHORS}');">
								     <g:radio name="${SamplingType.TOP_RELEVANT_AUTHORS}" value="${SamplingType.TOP_RELEVANT_AUTHORS}"/>
								     <i class="ace-icon fa fa-users align-top bigger-150"></i>
								  </a>
								    <a href="javascript:void(0);"class="btn btn-info btn-white tooltips" 
								  data-original-title="${g.message (code: 'sampling.tweets.retweet')}" onclick="gotToList('${SamplingType.TOP_RETWEETS}');">
								     <g:radio name="${SamplingType.TOP_RETWEETS}" checked="true" value="${SamplingType.TOP_RETWEETS}"/>
								     <i class="ace-icon fa fa-retweet align-top bigger-150"></i>
								  </a>								  
								 <a href="javascript:void(0);" class="btn btn-info btn-white tooltips " 
								  data-original-title="${g.message (code: 'sampling.tweets.fav')}" onclick="gotToList('${SamplingType.TOP_FAVS}');">
								     <g:radio name="${SamplingType.TOP_FAVS}"  value="${SamplingType.TOP_FAVS}"/>
								    <i class="ace-icon fa fa-star align-top bigger-150"></i>
								  </a>
								
								</div>
							</div>
							<div class="widget-toolbar blue-active" >
						      <div class="btn-group  btn-corner">
								<a class="btn btn-primary btn-white tooltips" id="btn-show-cloud" href="javascritp:void(0)" 
								   data-original-title="Ver en Nube de Palabras" onclick="showCloud();">
									<i class="ace-icon fa fa-cloud icon-only bigger-180"></i>
								</a>
							  </div>
							 </div>

			</div>
		<div class="widget-body" >
			<div class="widget-main row">
			<div class="col-lg-8">
			   <div id="divListTweets" class="center-tweets" >
			    <g:render template="listTweets" model="['concept':concept,'tweetList':tweetInstanceList,'tweetTotal':tweetInstanceTotal]"></g:render>
			  </div>
			 </div>
			 <div class="col-lg-4">
			 <div class="table-header">
				<g:message code="tweets.list.cloud.more.words"/> <g:message code="dashborad.concept.tweets"/>
			</div>
				<table class="table table-bordered table-striped table-condensed table-hover">
					<thead >
						<tr >
							<th ><i class="ace-icon fa fa-caret-right blue"></i>
							<g:message code="tweets.list.cloud.words"/>
							
							</th>

							<th ><i class="ace-icon fa fa-caret-right blue"></i>
							<g:message code="tweets.list.cloud.appearances"/>
							</th>


						</tr>
					</thead>

					<tbody id="tableWords">
					<tr><td></td><td></td></tr>
					</tbody>
				</table>
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
	var id='${concept.id}';
	var typeSampling='${type}';
	activeItemMenuLevel2(id,'${concept.id}-tweet','${concept.conceptName}');
	var params='${params}';
	function showCloud(){
		if(!$('#cloudWordsDiv').hasClass('collapsed')) {
			$('#btn-collapse').click();
		}
	}
	function getParamsCloud(params,div,id,dateFrom,dateTo,type){
		var listParams=params.replace('[','{"').replace(']','"}').replace('","').split(',');
		var data={"div":div,"conceptsId":id};
		var jsonNew='';
		for (i = 0; i < listParams.length; i++) { 
			jsonNew +=listParams[i].replace(':','":"');
			if(i!=listParams.length-1){
				jsonNew+='","'
			}
			
		}
		var data=$.parseJSON(jsonNew);
	    data["div"]=div;
	    data["conceptsId"]=id;
	    if(data.dateFrom==null){
	    	 data["dateFrom"]=moment(dateFrom,"YYYY-MM-DD HH:mm:ss").format('L HH:mm');
	    }
	    if(data.dateTo==null){
	    	 data["dateTo"]=moment(dateTo,"YYYY-MM-DD HH:mm:ss").format('L HH:mm');
	    }
	    if(data.type==null){
	    	 data["type"]=typeSampling;
	    }
		return data;
	}
	function gotToList(type){
		var data = {"id":${concept.id},"dateFrom":moment(dateFrom,"YYYY-MM-DD HH:mm:ss").format('L HH:mm'),
				"dateTo":moment(dateTo,"YYYY-MM-DD HH:mm:ss").format('L HH:mm'),'div':'#divListTweets','type':type};
		 document.location.href =encodeURI('list?conceptsId='+data.id+'&dateFrom='+data.dateFrom+'&dateTo='+data.dateTo+'&type='+data.type);
	}
	function loadPickertListTweets(start, end, rangeSelect) {
   	 loadFormatLLL('#pickertListTweets',start, end, rangeSelect);
   	 var type=$("#samplingType input[type='radio']:checked").val();
	 var data = {"id":${concept.id},"dateFrom":start.format('L HH:mm'),"dateTo":end.format('L HH:mm'),'div':'#divListTweets','type':type};
	 document.location.href =encodeURI('list?conceptsId='+data.id+'&dateFrom='+data.dateFrom+'&dateTo='+data.dateTo+'&type='+data.type);
	}
	
	
	function setDatesCustom(container){
		$('#'+container+' span').html(moment(dateFrom,"YYYY-MM-DD HH:mm:ss").format('LLL')+ ' - ' + moment(dateTo,"YYYY-MM-DD HH:mm:ss").format('LLL'));	
	}
	$(function(){
 			getWordsCloud(getParamsCloud(params,'#cloudWordsTW',id,dateFrom,dateTo,typeSampling));
			 loadDatepicker('pickertListTweets',loadPickertListTweets,setDatesCustom);
			console.log(typeSampling);
			$('#'+typeSampling).prop('checked',true);
			$('#'+typeSampling).parent().addClass('active').siblings().removeClass('active');
		});
	</script>
</body>
</html>
