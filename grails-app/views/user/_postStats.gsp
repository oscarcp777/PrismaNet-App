<script type="text/javascript">
function loadPopover(id,url){
	console.log(url);
	 var contentHtml='<div><i class="fa fa-image fa-4x fa-border"></i><br>No disponible</div>';
	   if(url){
         contentHtml='<div class="thumbnail"><img alt="" src="'+url+'"></div>';
	   }
    	 $('#'+id).popover({
    		 content:contentHtml,
    		 html:true,
    		 placement:'right',
    		 container:'body',
    		 trigger:'hover'
    	 });
    	
}
</script>
<table class="table table-striped table-bordered table-hover table-condensed">
	<thead>
		<tr>
			<th class="center">Vista Previa</th>
			<th>Concepto</th>
			<th>Creado</th>
			<th>Likes</th>
			<th>Comentarios</th>
			<th>Ir al post</th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${statsList}" var="item">
			<tr>
				<td class="center">
				<script type="text/javascript">
				$(function() {
				loadPopover('${item.postId}','${item.picture}');
				});
				</script>
				<a class="blue" href="javascript:void(0);" id="${item.postId}"> 
				<i class="ace-icon fa fa-search-plus fa-2x"></i>
				</a> 
				</td>
				<td>
					${item.faceName}
				</td>
				<td>
					${item.created}
				</td>
				<td>
					${item.totalLikes}
				</td>
				<td>
					${item.totalComments}
				</td>			
				<td>
					<a class="btn btn-app btn-xs btn-primary" href="${item.link}" target="_blanck">
						<i class="ace-icon fa fa-external-link-square bigger-150 icon-only"></i>
					</a>
				</td>

			</tr>
		</g:each>
	</tbody>
</table>

