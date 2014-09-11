		<div class="page-header">
			<h1 id="headerMain">
				<i class="ace-icon glyphicon glyphicon-cloud-download"></i> ${concept.conceptName}
				
			<small id="headerSmall">
			<i class="ace-icon fa fa-angle-double-right"></i>
				
				<g:message code="dashboard.concetp.twitter.title" args="[concept.conceptName]"/>
			</small>
			</h1>
			
		</div>
	<ul class="nav nav-tabs padding-12 tab-color-blue">
<%-- 		<li class="${tabMain}"> --%>
<!-- 		<a  href="#">  -->
<%-- 		<i class="ace-icon fa fa-dashboard grey bigger-120"></i> <g:message code="dashborad.tab.main"/> --%>
<!-- 		</a> -->
<!-- 		</li> -->

		<li class="${tabTweets}">
		<g:link  controller='tweet' action="list" params="[conceptsId:concept.id]" >
			<i class="light-blue fa fa-twitter bigger-120"></i> <g:message code="dashborad.tab.tweets"/>
		</g:link>
		
		</li>

		<li class="${tabChar}">
		  	<g:link  controller="concept" action="tweetStats" id="${concept.id}" >
				<i class="green fa fa-bar-chart-o bigger-120"></i> 
				<g:message code="dashborad.tab.chart"/>
			</g:link>
		</li>
		
		<li class="${tabSentimental}">
			<g:link  controller="concept" action="sentimental" id="${concept.id}" >
				<i class="red fa fa-heart bigger-120"></i> 
				<g:message code="dashborad.tab.sentimental"/>
			</g:link>
		</li>
		<li class="${tabSampling}">
			<g:link  controller="concept" action="sampling" id="${concept.id}" >
				<i class="blue glyphicon glyphicon-stats bigger-120"></i> 
				<g:message code="dashborad.tab.sampling"/>
			</g:link>
		</li>
	</ul>
	<br>

		