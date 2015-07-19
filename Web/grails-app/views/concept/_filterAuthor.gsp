<%@ page import="com.prismanet.ConceptService.AuthorOrderType"%>
<div class="widget-toolbar blue-active" >
		<div class="btn-group  btn-corner" data-toggle="buttons" id="samplingType">
		  <a href="javascript:void(0);"  class="btn btn-info  active btn-white tooltips tooltip-info" 
		  	 data-original-title="${g.message (code: 'concept.author.more.order.followers')}" onclick="loadDataAuthor('${AuthorOrderType.BY_RELEVANT_AUTHORS}');">
		    <g:radio name="filterAuthor" checked="true"  value="${AuthorOrderType.BY_RELEVANT_AUTHORS}"/>
		     <i class="ace-icon fa fa-users align-top bigger-150"></i>
		  </a>
		  <a href="javascript:void(0);" class="btn btn-info btn-white tooltips tooltip-info" 
		     data-original-title="${g.message (code: 'concept.author.more.order.tweets')}" onclick="loadDataAuthor('${AuthorOrderType.BY_TWEET_QUANTITY}');">
		     <g:radio name="filterAuthor" value="${AuthorOrderType.BY_TWEET_QUANTITY}"/>
		     <i class="ace-icon fa fa-twitter align-top bigger-150" style="margin-right:-12px;"></i>
		      <i class="ace-icon fa fa-twitter align-top bigger-150"></i>
		  </a>
		</div>
</div>
