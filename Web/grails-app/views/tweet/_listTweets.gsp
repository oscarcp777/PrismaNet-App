<%@ page import="com.prismanet.Tweet"%>

	<g:render template="ulListTweets"></g:render>

	<div  style="text-align: center;">
		<ul class="pagination pagination-sm">
			<li><g:paginate total="${tweetTotal}" params="${params}"  /></li>
		</ul>
	</div>
