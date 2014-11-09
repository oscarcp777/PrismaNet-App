<%@ page import="com.prismanet.sentiment.OpinionValue"%>
<%@ page import="com.prismanet.sentiment.Opinion"%>
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
		<div class="row">
		<g:render contextPath="../user" template="headerHelp" model="['mainMessage':'dashborad.tab.samplings.desc']"></g:render>
		   			<div class="col-lg-12">
						<div class="widget-box widget-color-blue">
							<div class="widget-header">
								<h5 class="widget-title">
								<i class="fa fa-random"></i>
								<g:message code="sampling.tweets.title"/> </h5>

							<div class="widget-toolbar input-group">
							<div id="pickertSampling" class="btn btn-info date-picker">
								<i class="ace-icon fa fa-calendar"></i> <span class="date-range"></span>
								<i class="ace-icon fa fa-chevron-down"></i>
							</div>
								</div>
<!-- 							<div class="widget-toolbar blue-active"> -->
<!-- 							   <div class="btn-group  btn-corner"> -->
<!-- 								<a href="javascripts:void(0);" id="help-samplings" class="btn btn-grey btn-help" >  -->
<!-- 								<i class="ace-icon fa fa-question-circle fa-2x"></i> -->
<!-- 								</a> -->
<!-- 							   </div> -->
<!-- 							</div> -->
							</div>

							<div class="widget-body" >
								<div class="widget-main">
								<div id="divSampling" >
									<div id="fuelux-wizard" data-target="#step-container">
										<ul class="wizard-steps">
											<li data-target="#step1" class="active"><span
												class="step">1</span> <span class="title"><g:message code="sampling.tweets.first.step"/> </span></li>

											<li data-target="#step2" class=""><span
												class="step">2</span> <span class="title"><g:message code="sampling.tweets.second.step"/></span></li>

											<li data-target="#step3" class=""><span
												class="step">3</span> <span class="title"><g:message code="sampling.tweets.three.step"/></span></li>

										</ul>
									</div>

									<hr>

									<div class="step-content pos-rel" id="step-container">
										<div class="step-pane active" id="step1">
										<div class="center" id="samplingStep1">
												<h1 id="titleSampling"> <g:message code="sampling.tweets.zero.step.message"/></h1>

											</div>
										</div>

										<div class="step-pane" id="step2" style="min-height:1700px;">
											<div class="center" id="samplingStep2">
												
											</div>
										</div>

										<div class="step-pane" id="step3">
											<div class="center" id="samplingStep3">
											<div id="charPieOpinion" style=" min-height: 400px; min-width: 310px;margin: 0 auto"></div>
											</div>
										</div>

									</div>

									<hr >
									<div class="wizard-actions" id="buttons">

										<button class="btn btn-info btn-next" data-last="Finish">
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
	<div class="page-content">
		<div class="row">
			<div class="col-lg-12">
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
	    	 $('#pickertSampling span').html(rangeSelect+' - '+start.format('LLLL') + ' - ' + end.format('LLLL'));
		     var data = {"conceptsId":${concept.id},"dateFrom":start.format('L HH:mm'),"dateTo":end.format('L HH:mm'),'div':'#samplingStep1'};
		     getSamplingStep1(data);
		}
		function loadSamplingStep1(data){
			$("#samplingStep1").html(data);
			 $('#buttons').show();
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
		window.onload = function () {
			$('#fuelux-wizard')
			.ace_wizard({})
			.on('change' , function(e, info){
				if(info.step==1){
					var data = {'div':'#samplingStep2'};
					getSamplingStep2(data);
				}
				if(info.step==2){
					loadCharPieOpinion();
				}
			})
			.on('finished', function(e) {
				console.log('fin')
			}).on('stepclick', function(e){
				console.log('paso c');
			});
		}
		 $(function() {
			 $('#buttons').hide();
			 var id='${concept.id}';
			 loadDatepicker('pickertSampling',loadPickertSampling);
			  activeItemMenuLevel2(id,id+'-tweet','${concept.conceptName}');
			  
			  var contentHelp='<g:message code="sampling.tweets.help" />';
		    	 $('#help-samplings').popover({
		    		 content:contentHelp,
		    		 html:true,
		    		 placement:'top',
		    		 title:'¿Qué es esto?',
		    		 container:'body',
		    		 trigger:'hover'
		    	 });
			});
	</script>
</body>
</html>