<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="home">
</head>
<body>


	<div class="page-header">
		<h1>
			<i class="fa fa-cloud-download"></i>
			<g:message code="dashboard.concetp.title" />
			${concept.conceptName}
		</h1>
	</div>


	<div class="page-content">
		<div class="row">
			<div class="col-lg-12">

				<div class="col-md-6">
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-row">
							<div class="profile-info-name">
								<g:message code="dashborad.concept.init" />
							</div>

							<div class="profile-info-value">
								<span><g:formatDate format="dd/MM/yyyy HH:mm"
										date="${concept.dateCreated}" /></span>
							</div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">
								<g:message code="dashborad.concept.end" />
							</div>

							<div class="profile-info-value">
								<span><g:formatDate format="dd/MM/yyyy HH:mm"
										date="${concept.lastUpdated}" /></span>
							</div>
						</div>
					</div>
					<h4 class="header blue bolder smaller">
						<g:message code="dashborad.config.twitter" />
					</h4>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-row">
							<div class="profile-info-name">
								<g:message code="dashborad.config.twitter.included.accounts" />
							</div>

							<div class="profile-info-value">
								<span> ${twSetup.includedAccounts}
								</span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">
								<g:message code="dashborad.config.twitter.excluded.accounts" />
							</div>

							<div class="profile-info-value">
								<span> ${twSetup.excludedAccounts}
								</span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">
								<g:message code="dashborad.config.twitter.keywords" />
							</div>

							<div class="profile-info-value">
								<span> ${twSetup.keywords}
								</span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">
								<g:message code="dashborad.config.twitter.neutral.hashtags" />
							</div>

							<div class="profile-info-value">
								<span> ${twSetup.neutralHashtags}
								</span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">
								<g:message code="dashborad.config.twitter.positive.hashtags" />
							</div>

							<div class="profile-info-value">
								<span> ${twSetup.positiveHashtags}
								</span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">
								<g:message code="dashborad.config.twitter.negative.hashtags" />
							</div>

							<div class="profile-info-value">
								<span> ${twSetup.negativeHashtags}
								</span>
							</div>
						</div>
					</div>

					<h4 class="header blue bolder smaller">
						<g:message code="dashborad.config.facebook" />
					</h4>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-row">
							<div class="profile-info-name">
								<g:message code="dashborad.config.twitter.keywords" />
							</div>

							<div class="profile-info-value">
								<span> ${fcSetup?.keywords}
								</span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">
								<g:message code="dashborad.config.facebook.accounts" />
							</div>

							<div class="profile-info-value">
								<span> ${fcSetup?.accounts}
								</span>
							</div>
						</div>
					</div>

				</div>

				<div class="col-md-6">
					<a href="#" class="btn btn-danger btn-app"> <i
						class="ace-icon fa fa-comments"></i> ${total} <br>
					<g:message code="dashborad.concept.mentions" />
					</a> <a href="#" class="btn btn-primary btn-app"> <i
						class="ace-icon fa fa-twitter bigger-230"></i> ${tweetsTotal} <br>
					<g:message code="dashborad.concept.tweets" />
					</a> <a href="#" class="btn btn-info btn-app"> <i
						class="ace-icon fa fa-facebook bigger-230"></i> ${postsTotal} <br>
					<g:message code="dashborad.concept.post" />
					</a>
				</div>
			</div>
			<div class="col-lg-12 ">
				<div class="hr hr32 hr-dotted"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
			activeItemMenuLevel2('${concept.id}','${concept.id}-dash');
	 
	</script>
</body>
</html>