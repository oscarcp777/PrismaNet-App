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
												data-toggle="dropdown" style="padding:0px 0px  0px 4px!important; font-size:12px;"> <i
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
																<g:message code="tweets.list.followings"/> </span>
														</div>

														<div class="grid3">

															<span class="line-height-1 bigger-110 "> ${fieldValue(bean: tweet.tweet.author, field: "following")}  </span> <br>
															<span class="line-height-1 bigger-100 grey">
																<g:message code="tweets.list.followers"/> </span>
														</div>
													</div>
										</div>
									</div>
								</div>
							</div>
							<div class="message">
								<span class="arrow"></span>
								<div>

									<a href="https://twitter.com/${fieldValue(bean: tweet.tweet.author, field: "accountNameSingle")}"
										target="_blanck" class="name btn-link" > 
										<span class="bigger lighter descrip">${fieldValue(bean: tweet.tweet.author, field: "name")}</span>
									</a>
									<a href="https://twitter.com/${fieldValue(bean: tweet.tweet.author, field: "accountNameSingle")}"
										target="_blanck" class="name btn-link" > 
										${fieldValue(bean: tweet.tweet.author, field: "accountName")} &nbsp;·&nbsp;
									</a>
									<a href="https://twitter.com/${fieldValue(bean: tweet.tweet.author, field: "accountNameSingle")}/status/${tweet.tweet.tweetId.toString()}"
										target="_blanck" class="name btn-link" > 
										<g:formatDate date="${tweet.tweet.created}" type="datetime" style="LONG" timeStyle="SHORT" />
									</a>
								</div>

								<p class="body no-margin content">
									${raw(tweet.tweet.contentHtml)}
								</p>
								<div class="clearfix ">
									<div class="grid3 ">
										<div class="reply-icons">

											<a href="javascript:void(0)" class="icon-mini retweet tooltips" title="retweets">
												<span>${tweet.tweet.retweetCount}</span>  <i class="ace-icon fa fa-retweet fs1" title="retweet"></i>
											</a> 
											<a href="javascript:void(0)" class="icon-mini last tooltips" title="Favoritos"> 
												<span>${tweet.tweet.favoriteCount}</span> <i class="ace-icon fa fa-star fs1" title="favorito"></i>
											</a>
											<a href="https://twitter.com/${fieldValue(bean: tweet.tweet.author, field: "accountNameSingle")}/status/${tweet.tweet.tweetId.toString()}"
												target="_blanck" class="icon-mini tw tooltips btn btn-link" title="Ver en  Twitter"> 
												 <i class="ace-icon fa fa-external-link fs1" title="favorito"></i>
											</a>											
										</div>
									</div>
									<div class="btn-group pull-right">
										<div class="reply-icons pull-right" id="${tweet.tweet.id}">
											<a href=" javascript:void(0);"  id="${tweet.tweet.id}Pos" 
												class="icon pos tooltips" 
												onclick="changeState('${tweet.tweet.id}Pos','${createLink(controller:'tweet', action:'saveOpinion')}','${tweet.tweet.id}','${concept.id}', 'POS');"  
												 
												title="Positivo"> 
												<i class="ace-icon fa fa-check-square" title="Positivo" ></i>
											</a>
											<a href=" javascript:void(0);"  id="${tweet.tweet.id}Neg" 
												class="icon neg tooltips" 
												onclick="changeState('${tweet.tweet.id}Neg', '${createLink(controller:'tweet', action:'saveOpinion')}','${tweet.tweet.id}','${concept.id}','NEG');" 
												 
												title="Negativo"> 
												<i class="ace-icon fa fa-minus-square" ></i>
											</a>
			     							<a href="javascript:void(0);" id="${tweet.tweet.id}Que" 
			     								class="icon neu tooltips" 
			     								onclick="changeState('${tweet.tweet.id}Que', '${createLink(controller:'tweet', action:'saveOpinion')}','${tweet.tweet.id}','${concept.id}','NEU');" 
			     								 
			     								title="Indefinido"> 
												<i class="ace-icon fa fa-question-circle"></i>
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
