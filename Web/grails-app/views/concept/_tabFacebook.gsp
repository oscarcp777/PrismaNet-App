			<div class="page-header ph-tab">
			<h1 id="headerMain">
				<i class="ace-icon glyphicon glyphicon-cloud-download"></i> ${concept.conceptName}
				
			<small id="headerSmall">
			<i class="ace-icon fa fa-angle-double-right"></i>
				
				<g:message code="dashboard.concetp.facebook.title" args="[concept.conceptName]"/>
			</small>
			</h1>
			
		</div>
	<ul class="nav nav-tabs padding-12 tab-color-blue">
<%--		<li class="${tabMain}">--%>
<%--		<a  href="#"> --%>
<%--		<i class="ace-icon fa fa-dashboard grey bigger-120"></i> <g:message code="dashborad.tab.main"/>--%>
<%--		</a>--%>
<%--		</li>--%>

		<li class="${tabPosts}">
		<g:link  controller='post' action="list" params="[conceptsId:concept.id]" >
			<i class="light-blue fa fa-facebook bigger-120"></i>  <g:message code="dashborad.tab.facebook"/>
		</g:link>
		
		</li>

		<li class="${tabChar}">
		  <g:link  controller="concept" action="postStats" id="${concept.id}" >
				<i class="green fa fa-bar-chart-o bigger-120"></i> 
				<g:message code="dashborad.tab.chart"/>
		</g:link>
		
		
		</li>
<%--		<li class="${tabSentimental}"><a  href="#"> --%>
<%--		<i class="red fa fa-heart bigger-120"></i> <g:message code="dashborad.tab.sentimental"/>--%>
<%--		</a>--%>
<%--		</li>--%>
		
	</ul>
