
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
		<div class="row">
			<div class="col-lg-12">
				<ul class="tweets tweet-group list-unstyled">
					<g:each in="${tweetInstanceList}" status="i" var="tweetInstance">
						<li class="out"><img class="avatar" alt=""
							src="${tweetInstance.author?.profileImage}">
							<div class="message">
								<span class="up-arrow"></span> <span class="arrow"></span>
								<div>
									<a href="" class="name" data-original-title=""> ${fieldValue(bean: tweetInstance, field: "author")}
									</a> <span class="date-time"><g:formatDate
											date="${tweetInstance.created}" /></span>
								</div>
								<p class="body no-margin">
									${fieldValue(bean: tweetInstance, field: "content")}
								</p>
								<hr>
								<div class="reply-icons pull-right">
								  <a href="" class="icon"
										data-original-title=""> <i class="icon-reply fs1"
										title="retweet"></i>
									</a>
									<a href="" class="icon hide_tweet selected"
										data-original-title=""> <i class="icon-retweet fs1"
										title="retweet"></i>
									</a> <a href="" class="icon last" data-original-title=""> <i
										class="icon-star fs1" title="favorito"></i>
									</a>
								</div>
								<div class="clearfix"></div>
							</div></li>
					</g:each>

				</ul>
			</div>
			<div class="col-lg-12" style="text-align: center;">
				<ul class="pagination pagination-sm">
					<li><g:paginate total="${tweetInstanceTotal}" /></li>
				</ul>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	  activeItemMenu('tweets',"",1,'Tweets');
	</script>
</body>
</html>
