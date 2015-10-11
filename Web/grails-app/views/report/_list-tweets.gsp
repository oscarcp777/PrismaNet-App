<ul class="tweets tweet-group list-unstyled" id="ulTweetsList">
	<li class="out" ng-repeat="tweet in listTweets">
		<div class="author">
		<img class="avatar" alt="" ng-src="{{tweet.author.profileImage}}">
			<div class="clearfix center">
				<div>
					<div class="inline position-relative">
						<a href=" javascript:void(0);" class="btn btn-link dropdown-toggle" data-toggle="dropdown"
							style="padding: 0px 0px 0px 4px !important; font-size: 12px;">
							<i class="ace-icon fa fa-plus-circle bigger-120 blue"></i> 
							<span id="moreData"><g:message code="tweets.list.more.data" /></span>
						</a>
						<div class="clearfix center dropdown-menu dropdown-caret dropdown-lighter more-data">
							<div class="grid3">
								<span class="line-height-1 bigger-110 "> {{tweet.author.tweetsCount}}
								</span> <br> 
								<span class="line-height-1 bigger-100 grey"> <g:message code="tweets.list.tweets" />
								</span>
							</div>
							<div class="grid3">
								<span class="line-height-1 bigger-110 "> {{tweet.author.followers}}
								</span> <br> 
								<span class="line-height-1 bigger-100 grey"> <g:message code="tweets.list.followings" />
								</span>
							</div>
							<div class="grid3">
								<span class="line-height-1 bigger-110 "> {{tweet.author.following}}
								</span> <br> 
								<span class="line-height-1 bigger-100 grey"> <g:message code="tweets.list.followers" />
								</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="message">
			<span class="arrow"></span>
			<div>
				<a href="https://twitter.com/{{tweet.author.accountNameSingle}}" target="_blanck" class="name btn-link"> 
				<span class="bigger lighter descrip">{{tweet.author.name}}</span>
				</a> 
				<a href="https://twitter.com/{{tweet.author.accountNameSingle}}" target="_blanck" class="name btn-link"> 
					{{tweet.author.accountName}}&nbsp;·&nbsp;
				</a> 
				<a href="https://twitter.com/{{tweet.author.accountNameSingle}}/status/{{tweet.tweetId}}" target="_blanck" class="name btn-link"> 
					{{tweet.created}}
				</a>
			</div>
			<p class="body no-margin content">
				<span ng-bind-html="tweet.content"></span>
			</p>
			<div class="clearfix ">
				<div class="grid3 ">
					<div class="reply-icons">
						<a href="javascript:void(0)" class="icon-mini retweet tooltips" title="retweets"> 
						<span>{{tweet.retweetCount}} </span> <i class="ace-icon fa fa-retweet fs1" title="retweet"></i>
						</a> 
						<a href="javascript:void(0)" class="icon-mini last tooltips" title="Favoritos"> <span>{{tweet.favoriteCount}}
						</span> <i class="ace-icon fa fa-star fs1" title="favorito"></i>
						</a> 
						<a href="https://twitter.com/{{tweet.author.accountNameSingle}}/status/{{tweet.tweetId}}" target="_blanck" class="icon-mini tw tooltips btn btn-link" title="Ver en  Twitter"> 
							<i class="ace-icon fa fa-external-link fs1" title="favorito"></i>
						</a>
					</div>
				</div>
			</div>
		</div>
	</li>
</ul>
