
<%@ page import="com.prismanet.Tweet"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="home">
<g:set var="entityName"
	value="${message(code: 'tweet.label', default: 'Tweet')}" />
<title><g:message code="tweets.title" /></title>
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'twitter.css')}" type="text/css">
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>
				<i class="icon-twitter"></i>
				<g:message code="tweets.title" />
			</h1>
		</div>
		<div class="row well">
			<div class="col-lg-4">
				<div class="widget-body box-user-twitter">
					<div class="widget-main">
						<div class="btn-group" style="width: 100%;">
							<img class="avatar" alt=""
								src="http://pbs.twimg.com/profile_images/2670960637/379fb2e81c5d384d551b304edcfa1242_normal.jpeg">
							<h5 class="bigger">Oscar Caceres Paredes</h5>
							<h5 class="bigger">@oscarcp777</h5>
						</div>
						<div class="hr hr8 hr-double"></div>
						<div class="clearfix center">
							<div class="grid3">
								<span class="line-height-1 bigger-110 "> 12200 </span> <br>
								<span class="line-height-1 bigger-100 grey"> TWEETS </span>
							</div>

							<div class="grid3">

								<span class="line-height-1 bigger-110 "> 2003 </span> <br>
								<span class="line-height-1 bigger-100 grey"> SIGUIENDO </span>
							</div>

							<div class="grid3">

								<span class="line-height-1 bigger-110 "> 12999 </span> <br>
								<span class="line-height-1 bigger-100 grey"> SEGUIDORES </span>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-lg-8">


				<ul class="tweets tweet-group list-unstyled">
					<g:each in="${tweetInstanceList}" status="i" var="tweetInstance">
						<li class="out"><img class="avatar" alt=""
							src="${tweetInstance.tweet.author?.profileImage}">
							<div class="message">
								<span class="arrow"></span>
								<div>

									<a
										href="https://twitter.com/${fieldValue(bean: tweetInstance.tweet.author, field: "accountNameSingle")}"
										target="_blanck" class="name" data-original-title=""> ${fieldValue(bean: tweetInstance.tweet.author, field: "accountNameSingle")}
									</a> <span class="date-time widget-toolbar no-border"> <g:formatDate
											date="${tweetInstance.tweet.created}" type="datetime"
											style="short" />
									</span>
								</div>

								<p class="body no-margin content">
									${tweetInstance.tweet.contentHtml}
								</p>
								<hr>
								<div class="clearfix ">
									<div class="grid3">
										<div class="reply-icons pull-right" style="font-size:2em;" id="${tweetInstance.tweet.id}">
											 <a href="#" id="${tweetInstance.tweet.id}Pos" class="icon last tooltips" onclick="changeState('${tweetInstance.tweet.id}Pos');"  data-original-title="" title="Positivo"> 
												<i class="icon-check-sign" title="Positivo" ></i>
											</a>
											<a href="#"  id="${tweetInstance.tweet.id}Neg" class="icon last tooltips" onclick="changeState('${tweetInstance.tweet.id}Neg');" data-original-title="" title="Negativo"> 
												<i class="icon-minus-sign-alt" ></i>
											</a>
			     							<a href="#" id="${tweetInstance.tweet.id}Que" class="icon last tooltips" onclick="changeState('${tweetInstance.tweet.id}Que');" data-original-title="" title="Indefinido"> 
												<i class="icon-question-sign"></i>
											</a>
										</div>
									</div>
									<div class="grid3 pull-right">
									<div class="reply-icons pull-right">

											<a href="" class="icon hide_tweet" data-original-title="">
												 28 <i class="icon-retweet fs1" title="retweet"></i>
											</a> <a href="" class="icon last" data-original-title="">
												29 <i class="icon-star fs1" title="favorito"></i>
											</a>
										</div>
									</div>
								</div>
							</div></li>
					</g:each>

				</ul>

				<div class="col-lg-8" style="text-align: center;">
					<ul class="pagination pagination-sm">
						<li><g:paginate total="${tweetInstanceTotal}" /></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	  activeItemMenu('tweets',"",1,'Tweets');
	  jQuery(function($) {
		  $(".tooltips").tooltip();
		  });
	</script>
</body>
</html>
