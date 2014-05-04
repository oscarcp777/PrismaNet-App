<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="home">
	</head>
	<body>
	
	
		<div class="page-header">
			<h1>
				<i class="fa fa-cloud-download"></i>
				<g:message code="dashboard.concetp.title"/> ${concept.conceptName}
			</h1>
		</div>
		

<div class="page-content">
	<div class="tab-content no-border padding-24">
		<div id="dashboard" class="tab-pane active">
			<div class="row well">
			<div class="col-xs-4">
				<div class="widget-body box-user-twitter" style="background: white;">
					<div class="widget-header header-color-green4">
						<h4 class="lighter">
							<i class="fa fa-cloud"></i><g:message code="dashborad.concept.title"/> 
						</h4>
						
					</div>
					<div class="widget-main">
						<div class="profile-user-info profile-user-info-striped">
							<div class="profile-info-row">
								<div class="profile-info-name"> <g:message code="dashborad.concept.name"/> </div>

								<div class="profile-info-value">
									<span >${concept.conceptName}</span>
								</div>
							</div>


							<div class="profile-info-row">
								<div class="profile-info-name"><g:message code="dashborad.concept.init"/> </div>

								<div class="profile-info-value">
									<span ><g:formatDate format="dd/MM/yyyy HH:mm" date="${concept.dateCreated}"/></span>
								</div>
							</div>

							<div class="profile-info-row">
								<div class="profile-info-name"> <g:message code="dashborad.concept.end"/> </div>

								<div class="profile-info-value">
									<span ><g:formatDate format="dd/MM/yyyy HH:mm" date="${concept.dateCreated}"/></span>
								</div>
							</div>

						</div>
						
						<div class="hr hr8 hr-double"></div>
						<div class="clearfix center grey">
							<div class="grid3 ">
								<span class="grey"> <i
									class="fa fa-facebook-square fa-2x blue"></i> 
								</span>
								<h4 ><g:message code="dashborad.concept.post"/> </h4>
								<h4 class="bigger">${postsTotal}</h4>
							</div>

							<div class="grid3">
								<span class="grey"> <i
									class="fa fa-twitter-square fa-2x purple"></i> 
								</span>
								<h4 ><g:message code="dashborad.concept.tweets"/></h4>
								<h4 class="bigger">${tweetsTotal}</h4>
							</div>

							<div class="grid3">
								<span class="grey"> <i
									class="fa fa-comment fa-2x red"></i>
								</span>
								<h4 ><g:message code="dashborad.concept.mentions"/> </h4>
								<h4 class="bigger ">${total}</h4>
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
	
		
	</div>
</div>
</div>
<script type="text/javascript">
	activeItemMenuLevel2('${concept.id}','${concept.id}-dash');
	 
	</script>
</body>
</html>