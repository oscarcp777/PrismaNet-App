<div class="page-header">
			<h1>
				<i class="fa fa-facebook"></i>
				<g:message code="dashboard.concetp.facebook.title" args="[concept.conceptName]"/>
			</h1>
		</div>
	<ul class="nav nav-tabs padding-12 tab-color-blue">
		<li class="${tabMain}">
		<a  href="#"> 
		<i class="fa fa-dashboard grey bigger-120"></i> <g:message code="dashborad.tab.main"/>
		</a>
		</li>

		<li class="${tabPosts}">
		<g:link  controller='tweet' action="list" params="[conceptsId:concept.id]" >
			<i class="light-blue fa fa-facebook bigger-120"></i>  <g:message code="dashborad.tab.facebook"/>
		</g:link>
		
		</li>

		<li class="${tabChar}">
		  <g:link  controller="concept" action="stats" id="${conceptsId}" >
				<i class="green fa fa-bar-chart-o bigger-120"></i> 
				<g:message code="dashborad.tab.chart"/>
		</g:link>
		
		
		</li>
		<li class="${tabSentimental}"><a  href="#"> 
		<i class="red fa fa-heart bigger-120"></i> <g:message code="dashborad.tab.sentimental"/>
		</a>
		</li>
		
	</ul>
	<br>