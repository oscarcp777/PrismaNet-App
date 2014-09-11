<%@ page import="com.prismanet.Tweet"%>
<script type="text/javascript">
$(function() {
$("#ulTweetsList").quickPagination();
});
</script>
<div class="col-xs-8 col-md-offset-2">
<g:render template="ulListTweets"></g:render>
</div>
