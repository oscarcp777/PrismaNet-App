				<ul class="tweets tweet-group list-unstyled" id="ulTweetsList">
					
					<g:each in="${tweetList}" status="i" var="tweet">
						
						<li class="out">
							<div class="author">
								<img class="avatar" alt=""
									src="${tweet.tweet.author?.profileImage}">

								<div class="clearfix center">
									<div>
										<div class="inline position-relative">
											<a href=" javascript:void(0);" class="btn btn-link dropdown-toggle"
												data-toggle="dropdown"> <i
												class="ace-icon fa fa-plus-circle bigger-120 blue"></i> <g:message code="tweets.list.more.data"/>
											</a>

											
													<div class="clearfix center dropdown-menu dropdown-caret dropdown-lighter" 
													      style="width: 400px;">
														<div class="grid3">
															<span class="line-height-1 bigger-110 "> ${fieldValue(bean: tweet.tweet.author, field: "tweetsCount")} </span> <br>
															<span class="line-height-1 bigger-100 grey">
																<g:message code="tweets.list.tweets"/> </span>
														</div>

														<div class="grid3">

															<span class="line-height-1 bigger-110 "> ${fieldValue(bean: tweet.tweet.author, field: "followers")}  </span> <br>
															<span class="line-height-1 bigger-100 grey">
																<g:message code="tweets.list.followers"/> </span>
														</div>

														<div class="grid3">

															<span class="line-height-1 bigger-110 "> ${fieldValue(bean: tweet.tweet.author, field: "following")}  </span> <br>
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
										href="https://twitter.com/${fieldValue(bean: tweet.tweet.author, field: "accountNameSingle")}"
										target="_blanck" class="name" data-original-title=""> 
										<span class="bigger lighter descrip">${fieldValue(bean: tweet.tweet.author, field: "name")}</span>
										${fieldValue(bean: tweet.tweet.author, field: "accountName")}
									</a>
									 <span class="date-time widget-toolbar no-border"> <g:formatDate
											date="${tweet.tweet.created}" type="datetime"
											style="short" />
									</span>
								</div>

								<p class="body no-margin content">
									${raw(tweet.tweet.contentHtml)}
								</p>
								<div class="hr hr8 hr-double"></div>
								<div class="clearfix ">
									<div class="btn-group">
										<div class="reply-icons pull-right" style="font-size: 2em;"
											id="${tweet.tweet.id}">
											<a href=" javascript:void(0);"  id="${tweet.tweet.id}Pos" 
												class="icon last tooltips" 
												onclick="changeState('${tweet.tweet.id}Pos','${createLink(controller:'tweet', action:'saveOpinion')}','${tweet.tweet.id}','${concept.id}', 'POS');"  
												data-original-title="" 
												title="Positivo"> 
												<i class="ace-icon fa fa-check-square" title="Positivo" ></i>
											</a>
											<a href=" javascript:void(0);"  id="${tweet.tweet.id}Neg" 
												class="icon last tooltips" 
												onclick="changeState('${tweet.tweet.id}Neg', '${createLink(controller:'tweet', action:'saveOpinion')}','${tweet.tweet.id}','${concept.id}','NEG');" 
												data-original-title="" 
												title="Negativo"> 
												<i class="ace-icon fa fa-minus-square" ></i>
											</a>
			     							<a href="javascript:void(0);" id="${tweet.tweet.id}Que" 
			     								class="icon last tooltips" 
			     								onclick="changeState('${tweet.tweet.id}Que', '${createLink(controller:'tweet', action:'saveOpinion')}','${tweet.tweet.id}','${concept.id}','NEU');" 
			     								data-original-title="" 
			     								title="Indefinido"> 
												<i class="ace-icon fa fa-question-circle"></i>
											</a>
										</div>
									</div>
									<div class="grid3 pull-right">
										<div class="reply-icons pull-right">

											<a href="javascript:void(0)" class="icon hide_tweet" data-original-title="">
												${tweet.tweet.retweetCount} <i class="ace-icon fa fa-retweet fs1" title="retweet"></i>
											</a> <a href="javascript:void(0)" class="icon last" data-original-title=""> 
												${tweet.tweet.favoriteCount}<i class="ace-icon fa fa-star fs1" title="favorito"></i>
											</a>
										</div>
									</div>
								</div>
							</div>
						</li>
						<script type="text/javascript">
							activeOpinion('${tweet.tweet.id}','${tweet.value}');
	  					</script>
					</g:each>

				</ul>
