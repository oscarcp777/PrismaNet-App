<%@page import="com.prismanet.MentionType"%>
<html>
<head>
<meta name="layout" content="home" />
</head>
<body>
		<div class="page-header">
			<h1>
				<i class="ace-icon fa fa-bar-chart-o"></i> <g:message code="dashborad.tab.main"/>
				<small>
								<i class="ace-icon fa fa-angle-double-right"></i>
								<g:message code="user.stats.title"/>
			</small>
			</h1>
			
		</div>
		<div class="row" >
			<div class="col-lg-12" >
				
              <g:render template="chart" model="['divPickert':'disable','callback':'loadRealTime','titleChar':'user.stats.tweets.real.time','div':'realTimeCharUser']"></g:render>
                <g:render template="chart" model="['divPickert':'disable','callback':'loadTotalFollowers','titleChar':'user.stats.tweets.followers','div':'totalFollowers']"></g:render>
			  <g:render template="chart" model="['divPickert':'pickertTweetConcept','callback':'loadTweetCharPie','titleChar':'user.stats.tweets.concepts','div':'tweetCharPie']"></g:render>
			  <g:render template="chart" model="['divPickert':'pickertUser','callback':'loadUserGroupedData','titleChar':'user.stats.tweets.date','div':'lineaChartUser']"></g:render>
			  <g:render template="chart" model="['divPickert':'pickertStatsMonth','callback':'loadMonthStatsData','titleChar':'user.stats.month.stats','div':'monthStats']"></g:render>
			</div>
			<hr>

		</div>
		<script type="text/javascript">
		$(document).ready(function() {
			$('#liLevel2').hide();
			inicializeColorChar();
			activeItemMenuLevel1('home');
			//real time
			loadRealTime('${MentionType.TWITTER}');
			//data por concepto
			loadTweetCharPie('${MentionType.TWITTER}');
			//Followers y likes por concepto
			loadTotalFollowers('${MentionType.TWITTER}');
			//data entre fechas
			loadUserGroupedData('${MentionType.TWITTER}');
			//data general de meses
			loadMonthStatsData('${MentionType.TWITTER}');
		});

		
	</script>
	
</body>
</html>
