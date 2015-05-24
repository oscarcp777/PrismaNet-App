<%@ page import="com.prismanet.Tweet"%>
<div class="col-xs-10 ">


			<g:render template="ulListTweets"></g:render>

				<div class="col-lg-10" style="text-align: center;">
					<ul class="pagination pagination-sm">
						<li><g:paginate total="${tweetTotal}" params="${params}"  /></li>
					</ul>
				</div>
				
			</div>