<%@ page import="com.prismanet.Tweet"%>
<div class="col-xs-8 ">


				<ul class="tweets tweet-group list-unstyled">
					
					<g:each in="${tweetInstanceList}" status="i" var="tweetInstance">
						
						<li class="out">
							<div class="author">
								<img class="avatar" alt=""
									src="${tweetInstance.tweet.author?.profileImage}">

								<div class="clearfix center">
									<div>
										<div class="inline position-relative">
											<a href=" javascript:void(0);" class="btn btn-link dropdown-toggle"
												data-toggle="dropdown"> <i
												class="fa fa-plus-circle bigger-120 blue"></i> <g:message code="tweets.list.more.data"/>
											</a>

											
													<div class="clearfix center dropdown-menu dropdown-caret dropdown-lighter" 
													      style="width: 400px;">
														<div class="grid3">
															<span class="line-height-1 bigger-110 "> ${fieldValue(bean: tweetInstance.tweet.author, field: "tweetsCount")} </span> <br>
															<span class="line-height-1 bigger-100 grey">
																<g:message code="tweets.list.tweets"/> </span>
														</div>

														<div class="grid3">

															<span class="line-height-1 bigger-110 "> ${fieldValue(bean: tweetInstance.tweet.author, field: "followers")}  </span> <br>
															<span class="line-height-1 bigger-100 grey">
																<g:message code="tweets.list.followers"/> </span>
														</div>

														<div class="grid3">

															<span class="line-height-1 bigger-110 "> ${fieldValue(bean: tweetInstance.tweet.author, field: "following")}  </span> <br>
															<span class="line-height-1 bigger-100 grey">
																<g:message code="tweets.list.followings"/> </span>
														</div>
													</div>
										</div>
									</div>
								</div>
							</div>
							<div class="message">
								<span class="arrow"></span>
								<div>

									<a
										href="https://twitter.com/${fieldValue(bean: tweetInstance.tweet.author, field: "accountNameSingle")}"
										target="_blanck" class="name" data-original-title=""> 
										<span class="bigger lighter descrip">${fieldValue(bean: tweetInstance.tweet.author, field: "name")}</span>
										${fieldValue(bean: tweetInstance.tweet.author, field: "accountName")}
									</a>
									 <span class="date-time widget-toolbar no-border"> <g:formatDate
											date="${tweetInstance.tweet.created}" type="datetime"
											style="short" />
									</span>
								</div>

								<p class="body no-margin content">
									${raw(tweetInstance.tweet.contentHtml)}
								</p>
								<div class="hr hr8 hr-double"></div>
								<div class="clearfix ">
									<div class="btn-group">
										<div class="reply-icons pull-right" style="font-size: 2em;"
											id="${tweetInstance.tweet.id}">
											<a href=" javascript:void(0);"  id="${tweetInstance.tweet.id}Pos" 
												class="icon last tooltips" 
												onclick="changeState('${tweetInstance.tweet.id}Pos','${createLink(controller:'tweet', action:'saveOpinion')}','${tweetInstance.tweet.id}','${concept.id}', 'POS');"  
												data-original-title="" 
												title="Positivo"> 
												<i class="fa fa-check-square" title="Positivo" ></i>
											</a>
											<a href=" javascript:void(0);"  id="${tweetInstance.tweet.id}Neg" 
												class="icon last tooltips" 
												onclick="changeState('${tweetInstance.tweet.id}Neg', '${createLink(controller:'tweet', action:'saveOpinion')}','${tweetInstance.tweet.id}','${concept.id}','NEG');" 
												data-original-title="" 
												title="Negativo"> 
												<i class="fa fa-minus-square" ></i>
											</a>
			     							<a href="javascript:void(0);" id="${tweetInstance.tweet.id}Que" 
			     								class="icon last tooltips" 
			     								onclick="changeState('${tweetInstance.tweet.id}Que', '${createLink(controller:'tweet', action:'saveOpinion')}','${tweetInstance.tweet.id}','${concept.id}','NEU');" 
			     								data-original-title="" 
			     								title="Indefinido"> 
												<i class="fa fa-question-circle"></i>
											</a>
										</div>
									</div>
									<div class="grid3 pull-right">
										<div class="reply-icons pull-right">

											<a href="" class="icon hide_tweet" data-original-title="">
												${tweetInstance.tweet.retweetCount} <i class="fa fa-retweet fs1" title="retweet"></i>
											</a> <a href="" class="icon last" data-original-title=""> 
												${tweetInstance.tweet.favoriteCount}<i class="fa fa-star fs1" title="favorito"></i>
											</a>
										</div>
									</div>
								</div>
							</div>
						</li>
						<script type="text/javascript">
							activeOpinion('${tweetInstance.tweet.id}','${tweetInstance.value}');
	  					</script>
					</g:each>

				</ul>

				<div class="col-lg-8" style="text-align: center;">
					<ul class="pagination pagination-sm">
						<li><g:paginate total="${tweetInstanceTotal}" id="${concept.id}" params="${params}"  /></li>
					</ul>
				</div>
				
			</div>