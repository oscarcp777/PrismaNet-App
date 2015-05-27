<%@ page import="com.prismanet.MentionType"%>
<script type="text/javascript">
  $('#loadTotalFollowers').hide();
  $(function($) {$(".tooltips").tooltip(); });
</script>
<div class="widget-toolbar blue-active" >
<div class="btn-group  btn-corner" data-toggle="buttons">
  <a href="javascript:void(0);" id="${callback}" class="btn btn-danger btn-white tooltips" onclick="${callback}('${MentionType.ALL}');"
  data-original-title="${g.message (code: 'choose.channel.view.all')}">
    <input type="radio" name="options" id="option3">
     <i class="ace-icon fa fa-comments align-top bigger-180"></i>
  </a>
    <a href="javascript:void(0);" class="btn btn-primary btn-white tooltips" onclick="${callback}('${MentionType.FACEBOOK}');"
  data-original-title="${g.message (code: 'choose.channel.view.facebook')}">
    <input type="radio" name="options" id="option2">
     <i class="ace-icon fa fa-facebook-official align-top bigger-180"></i>
  </a>
  <a href="javascript:void(0);" class="btn btn-info btn-white tooltips active" onclick="${callback}('${MentionType.TWITTER}');"
  data-original-title="${g.message (code: 'choose.channel.view.twitter')}">
    <input type="radio" name="options" id="option1" >
    <i class="ace-icon fa fa-twitter-square align-top bigger-180"></i>
  </a>

</div>
</div>
