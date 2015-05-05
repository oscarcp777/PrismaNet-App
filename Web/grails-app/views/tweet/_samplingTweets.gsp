<%@ page import="com.prismanet.Tweet"%>
<script type="text/javascript">


 var totalPage=parseInt("${totalPage}");
 
	$(function() {
		
		if(totalPage==0){
			$('#buttons').hide();
		}
		$('#pagination').bootpag({
			total : totalPage,
		    next: 'Siguiente',
		    prev: 'Anterior',
		    maxVisible: 6
		}).on("page", function(event, num) {
			var from,to;
			from = ((num * 10) - 10);
			to =num * 10-1;
			if(totalPage==num){
				to=-1
			}
			if(num==1){
			   from=-1;	
			}
			$.get("../../tweet/samplingTweetsPage", {
				init : from,
				end : to
			}).done(function(data) {
				$("#content").html(data);
			})
		});
		
		$('#btn-next').html('Generar Gráfico <i class="ace-icon fa fa-pie-chart icon-on-right"></i>');
	});
</script>
<div class="col-xs-8 col-md-offset-2" id="content">
	<g:render template="ulListTweets"></g:render>
</div>

<div id="pagination" class="pag-content col-xs-8 col-md-offset-2"></div>