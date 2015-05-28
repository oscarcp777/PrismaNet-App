<%@ page import="com.prismanet.sentiment.OpinionValue"%>
<%@ page import="com.prismanet.sentiment.Opinion"%>
<%@page import="com.prismanet.TweetService.SamplingType"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="home">
<r:require modules="paginate"/>
<link rel="stylesheet" href="${resource(dir: 'css', file: 'twitter.css')}" type="text/css">
</head>
<body>
<g:set var="conceptName" value="${concept.conceptName}" />
	<g:render template="tabTwitter"  model="['concept':concept,'tabMain':'','tabTweets':'','tabChar':'','tabSentimental':'','tabSampling':'active']"></g:render>
	  <div class="row bg-well">
		<div class="col-lg-12">
		 <g:render contextPath="../user" template="headerHelp" model="['mainMessage':'dashborad.tab.samplings.desc']"></g:render>
		<div class="col-lg-12">
				<div class="widget-box widget-prisma">
					<div class="widget-header">
						<h5 class="widget-title"><i class="fa fa-random"></i> <g:message code="sampling.tweets.title"/> </h5>
						<div class="widget-toolbar">

							<a href="javascript:void(0);" data-action="collapse"> <i
								class="1 bigger-125 ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
						<div class="widget-toolbar input-group blue-active">
							<div id="pickertSampling" class="btn btn-white btn-info date-picker">
								<i class="ace-icon fa fa-calendar"></i> <span class="date-range"></span>
								<i class="ace-icon fa fa-chevron-down"></i>
							</div>
						</div>
						<div class="widget-toolbar blue-active" >
						      <div class="btn-group  btn-corner" style="display: none;" id="refresh-sampling">
								<g:link class="btn btn-info active tooltips" controller="concept" action="sampling" id="${concept.id}" 
								   data-original-title="reiniciar el muestreo">
									<i class="ace-icon fa fa-refresh icon-only bigger-180"></i>
								</g:link>
							  </div>
								<div class="btn-group  btn-corner" data-toggle="buttons" id="samplingType">
								  <label  class="btn btn-info btn-white active tooltips" 
								  data-original-title="${g.message (code: 'sampling.tweets.random')}">
								    <input type="radio" checked name="options" id="option1" value="${SamplingType.RANDOM}">
								     <i class="ace-icon fa fa-random align-top bigger-150"></i>
								  </label>
								    <label class="btn btn-info btn-white tooltips" 
								  data-original-title="${g.message (code: 'sampling.tweets.author')}">
								    <input type="radio" name="options" id="option2" value="${SamplingType.TOP_RELEVANT_AUTHORS}">
								     <i class="ace-icon fa fa-users align-top bigger-150"></i>
								  </label>
								    <label class="btn btn-info btn-white tooltips" 
								  data-original-title="${g.message (code: 'sampling.tweets.retweet')}">
								    <input type="radio" name="options" id="option3" value="${SamplingType.TOP_RETWEETS}">
								     <i class="ace-icon fa fa-retweet align-top bigger-150"></i>
								  </label>								  
								  <label class="btn btn-info btn-white tooltips " 
								  data-original-title="${g.message (code: 'sampling.tweets.fav')}">
								    <input type="radio" name="options" id="option4" value="${SamplingType.TOP_FAVS}">
								    <i class="ace-icon fa fa-star align-top bigger-150"></i>
								  </label>
								
								</div>
							</div>
					 </div>

							<div class="widget-body" >
								<div class="widget-main">
								<div id="divSampling" >
									<div id="fuelux-wizard-container" >
									<div>
										<ul class="steps">
											<li data-step="1" class="active">
											<span class="step">1</span> 
											<span class="title"><g:message code="sampling.tweets.first.step"/> </span></li>

											<li data-step="2" class=""><span
												class="step">2</span> <span class="title"><g:message code="sampling.tweets.second.step"/></span></li>

											<li data-step="3" class=""><span
												class="step">3</span> <span class="title"><g:message code="sampling.tweets.three.step"/></span></li>

										</ul>
									</div>

									<hr>

									<div class="step-content pos-rel" id="step-container">
										<div class="step-pane active" data-step="1" id="step1">
										<div class="center" id="samplingStep1">
												<h1 id="titleSampling"> <g:message code="sampling.tweets.zero.step.message"/></h1>

											</div>
										</div>

										<div class="step-pane" data-step="2" id="step2" style="display: inline-block;min-height:inherit;">
											<div class="center" id="samplingStep2" >
												
											</div>
										</div>

										<div class="step-pane" data-step="3" id="step3">
											<div class="center" id="samplingStep3">
											<div id="charPieOpinion" style=" min-height: 400px; min-width: 310px;margin: 0 auto"></div>
											</div>
										</div>

									</div>
								</div>
									<hr >
									<div class="wizard-actions" id="buttons">

										<button class="btn btn-info btn-next" data-last="Finish" id="btn-next">
											<g:message code="sampling.tweets.first.next"/> <i class="ace-icon fa fa-arrow-right icon-on-right"></i>
										</button>
									</div>
								</div>
							</div>
								<!-- /.widget-main -->
							</div>
							<!-- /.widget-body -->
						</div>
         </div>
		
					</div>
		</div>
	
		
	
	<script type="text/javascript">
		function loadCharPieOpinion(){
			var data = {"div":'#charPieOpinion'}
			getDataCharPieOpinion(data);
		}
		function getDataCharPieOpinion(data){
			doRequest('../../tweet/samplingStep3',data,paintCharPie, null, 'GET');
		}
		function loadPickertSampling(start, end, rangeSelect) {
	    	 loadFormatLLL('#pickertSampling',start, end, rangeSelect);
	    	 var type=$("#samplingType input[type='radio']:checked").val();
		     var data = {"conceptsId":${concept.id},"dateFrom":start.format('L HH:mm'),"dateTo":end.format('L HH:mm'),
		    		      'div':'#samplingStep1','samplingType':type};
		     getSamplingStep1(data);
		}
		function loadSamplingStep1(data){
			 $("#samplingStep1").html(data);
		}
		function getSamplingStep1(data){
			doRequest('../../tweet/samplingStep1',data,loadSamplingStep1, null, 'GET');
		}
		function loadSamplingStep2(data){
			$("#samplingStep2").html(data);
		}
		function getSamplingStep2(data){
			doRequest('../../tweet/samplingStep2',data,loadSamplingStep2, null, 'GET');
		}
		jQuery(function($) {
			$('#fuelux-wizard-container')
			.ace_wizard({})
			.on('actionclicked.fu.wizard' , function(e, info){
				if(info.step==1){
					
					var data = {'div':'#samplingStep2'};
					getSamplingStep2(data);
					$('#samplingStep2').css({ 'min-height':'200px'});
					$('#samplingType').hide();
					$('#pickertSampling').hide();
					$('#refresh-sampling').show();
					$('#step1').empty();
					
				}
				if(info.step==2){
					$('#step2').empty();
					loadCharPieOpinion();
					 $('#buttons').hide();
				}
			})
			.on('finished.fu.wizard', function(e) {
			}).on('stepclick.fu.wizard', function(e){
			});
		
		});
		 $(function() {
			 $('#buttons').hide();
			 var id='${concept.id}';
			 loadDatepicker('pickertSampling',loadPickertSampling,setDatesHtml);
			  activeItemMenuLevel2(id,id+'-tweet','${concept.conceptName}');

			});
	</script>
</body>
</html>