<%@ page import="com.prismanet.Tweet"%>
<div class="col-xs-8 ">


			<g:render template="ulListTweets"></g:render>

				<div class="col-lg-8" style="text-align: center;">
					<ul class="pagination pagination-sm">
						<li><g:paginate total="${tweetTotal}" id="${concept.id}" params="${params}"  /></li>
					</ul>
				</div>
				
			</div>