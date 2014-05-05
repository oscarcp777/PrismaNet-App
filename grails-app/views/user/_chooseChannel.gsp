<%@ page import="com.prismanet.MentionType"%>
<div class="widget-toolbar blue-active" >
<div class="btn-group  btn-corner" data-toggle="buttons">
  <label class="btn btn-danger tooltips active" onclick="${callback}('${MentionType.ALL}');"
  data-original-title="${g.message (code: 'choose.channel.view.all')}">
    <input type="radio" name="options" id="option3">
     <i class="ace-icon fa fa-comments align-top bigger-125"></i>
  </label>
    <label class="btn btn-primary tooltips" onclick="${callback}('${MentionType.FACEBOOK}');"
  data-original-title="${g.message (code: 'choose.channel.view.facebook')}">
    <input type="radio" name="options" id="option2">
     <i class="ace-icon fa fa-facebook align-top bigger-125"></i>
  </label>
  <label class="btn btn-info tooltips" onclick="${callback}('${MentionType.TWITTER}');"
  data-original-title="${g.message (code: 'choose.channel.view.twitter')}">
    <input type="radio" name="options" id="option1" >
    <i class="ace-icon fa fa-twitter align-top bigger-125"></i>
  </label>

</div>
</div>
<script type="text/javascript">
	  jQuery(function($) {$(".tooltips").tooltip(); });
	</script>