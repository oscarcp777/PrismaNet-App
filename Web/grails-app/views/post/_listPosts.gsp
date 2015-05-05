<%@ page import="com.prismanet.Tweet"%>
<div class="col-xs-8 " >

<div class="post-well">
				<ul class="tweets tweet-group list-unstyled post">
					
					<g:each in="${postList}" status="i" var="post">
						
						<li class="out">
							<div class="author">
								<img class="avatar" alt="autor" src="${post.tweet.author.profileImage==null?resource(dir: 'img/icon', file: 'user-face.jpeg'):post.tweet.author.profileImage}">

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
															<span class="line-height-1 bigger-110 "> 100 </span> <br>
															<span class="line-height-1 bigger-100 grey">
																Likes</span>
														</div>

														<div class="grid3">

															<span class="line-height-1 bigger-110 "> 10  </span> <br>
															<span class="line-height-1 bigger-100 grey">
																Post </span>
														</div>

														<div class="grid3">

															<span class="line-height-1 bigger-110 "> 20 </span> <br>
															<span class="line-height-1 bigger-100 grey">
																Comentarios </span>
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
										href="https://www.facebook.com/${post.tweet.author.facebookAuthorId}"
										target="_blanck" class="name" data-original-title=""> 
										<span class="bigger lighter descrip">${fieldValue(bean: post.tweet.author, field: "name")}</span>
										${fieldValue(bean: post.tweet.author, field: "accountName")}
									</a>

								</div>

								<p class="body no-margin content">
									${raw(post.tweet.content)}
								</p>
								<p >
									 <i class="ace-icon fa fa-hand-o-right blue"> 22 </i>  <i class="date-time"><g:formatDate  date="${post.tweet.created}" format="dd 'de' MMMM 'de' yyyy 'a las(s)' h:mm" /></i>
								</p>
								<div class="hr hr8 hr-dotted hr-face"></div>
								<div class="clearfix">

									<div class=" pull-right">
										<div class="reply-icons pull-right">
									<div class="btn-group">
										<div class="reply-icons pull-right" style="font-size: 2em;"
											id="${post.tweet.id}">
											<a href=" javascript:void(0);"  id="${post.tweet.id}Pos" 
												class="icon last tooltips" 
												onclick="changeState('${post.tweet.id}Pos','${createLink(controller:'tweet', action:'saveOpinion')}','${post.tweet.id}','${concept.id}', 'POS');"  
												data-original-title="" 
												title="Positivo"> 
												<i class="ace-icon fa fa-check-square" title="Positivo" ></i>
											</a>
											<a href=" javascript:void(0);"  id="${post.tweet.id}Neg" 
												class="icon last tooltips" 
												onclick="changeState('${post.tweet.id}Neg', '${createLink(controller:'tweet', action:'saveOpinion')}','${post.tweet.id}','${concept.id}','NEG');" 
												data-original-title="" 
												title="Negativo"> 
												<i class="ace-icon fa fa-minus-square" ></i>
											</a>
			     							<a href="javascript:void(0);" id="${post.tweet.id}Que" 
			     								class="icon last tooltips" 
			     								onclick="changeState('${post.tweet.id}Que', '${createLink(controller:'tweet', action:'saveOpinion')}','${post.tweet.id}','${concept.id}','NEU');" 
			     								data-original-title="" 
			     								title="Indefinido"> 
												<i class="ace-icon fa fa-question-circle"></i>
											</a>
										</div>
									</div>
										</div>
									</div>
								</div>
							</div>
						</li>
						<script type="text/javascript">
							activeOpinion('${post.tweet.id}','${post.value}');
	  					</script>
					</g:each>

				</ul>

				<div class="col-lg-8" style="text-align: center;">
					<ul class="pagination pagination-sm">
						<li><g:paginate total="${postTotal}" params="${params}"  /></li>
					</ul>
				</div>
				
			</div>
</div>