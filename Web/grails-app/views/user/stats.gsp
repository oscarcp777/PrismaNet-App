<%@page import="com.prismanet.MentionType"%>
<html>
<head>
<meta name="layout" content="home" />
</head>
<body>
<script type="text/javascript">
  var daysDate=7;
</script>
<g:set var="conceptName" value="" />
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
		<g:render template="headerHelp" model="['mainMessage':'dashborad.tab.main.desc']"></g:render>
		<div class="col-lg-12" >
			<sec:access expression="hasRole('ROLE_USER_ADVANCE')">
			   <g:render template="chart" model="['divPickert':'pickertUser','callback':'loadUserGroupedData','titleChar':'user.stats.tweets.date','div':'lineaChartUser','contentHelp':'user.stats.tweets.date.help','icon':'line-chart']"></g:render>	
		      	<script type="text/javascript">
				  var daysDate=1;
				</script>
			</sec:access>
              <g:render template="chart" model="['divPickert':'disable','callback':'loadRealTime','titleChar':'user.stats.tweets.real.time','div':'realTimeCharUser','contentHelp':'user.stats.tweets.real.time.help','icon':'line-chart']"></g:render>
              <g:render template="chart" model="['divPickert':'disable','callback':'loadTotalFollowers','titleChar':'user.stats.tweets.followers','div':'totalFollowers','contentHelp':'user.stats.tweets.followers.help','icon':'pie-chart']"></g:render>
			  <g:render template="chart" model="['divPickert':'pickertTweetConcept','callback':'loadTweetCharPie','titleChar':'user.stats.tweets.concepts','div':'tweetCharPie','contentHelp':'user.stats.tweets.concepts.help','icon':'pie-chart']"></g:render>
			 <sec:ifNotGranted roles="ROLE_USER_ADVANCE">
			   	<g:render template="chart" model="['divPickert':'pickertUser','callback':'loadUserGroupedData','titleChar':'user.stats.tweets.date','div':'lineaChartUser','contentHelp':'user.stats.tweets.date.help','icon':'line-chart']"></g:render>
			  </sec:ifNotGranted>	 
			   <g:render template="chart" model="['divPickert':'pickertMentionAuthor','callback':'loadMentionAuthorData','titleChar':'user.stats.mention.author.stats','div':'mentionAuthor','contentHelp':'user.stats.mentions.author.help','icon':'table']"></g:render>
			  <g:render template="chart" model="['divPickert':'pickertStatsPost','callback':'disable','titleChar':'user.stats.post.stats','div':'postStats','contentHelp':'user.stats.post.stats.help','icon':'table']"></g:render>
			</div>
			<hr>

		</div>
		<script type="text/javascript">
		function loadUserGroupedData(channel){
			changeTitle(channel,'#title-loadUserGroupedData');
			loadDatepicker('pickertUser',lineaChartUser,daysDate==1?setDatesHtmlOne:setDatesHtml);
			var data = {'channel':channel, "div":'#lineaChartUser',"dateFrom":getDateSubtract(daysDate),"dateTo":getDateNowLHH()};
			getUserGroupedTweets(data);
			$('#lineaChartUser').data('channel',channel);
		}
		$(function() {
			$('#liLevel2').hide();
			inicializeColorChar();
			activeItemMenuLevel1('home');
			//real time
			loadRealTime('${MentionType.TWITTER}');
			//Followers y likes por concepto
			loadTotalFollowers('${MentionType.TWITTER}');
			//data por concepto
			loadTweetCharPie('${MentionType.TWITTER}');
			//data entre fechas
			loadUserGroupedData('${MentionType.TWITTER}');
			//data general de meses
			loadMentionAuthorData('${MentionType.TWITTER}');
			//likes y comentarios de posts mas importantes
			loadPostStatsData();
		});

		
	</script>

	
</body>
</html>
