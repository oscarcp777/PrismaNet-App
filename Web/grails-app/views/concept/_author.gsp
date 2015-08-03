<%@ page import="com.prismanet.Author"%>
<script type="text/javascript">
        $(function () {
			$('#authorRelevants').footable();
        });
</script>
<table id="authorRelevants" class="table table-striped table-bordered table-hover table-condensed" data-page-navigation=".paginate">
	<thead>
		<tr>
			<th>Usuario</th>
			<th>Nombre</th>
			<th>Seguidores</th>
			<th>Tweets</th>
			<th>Ver tweets</th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${authors}" var="curr">
			<tr>
				<td><img class="avatar" style="width: 30px; height: 30px;"
					alt="${curr.accountName}" src="${curr.profileImage}">
				  
				  <a href="https://twitter.com/${curr?.accountName}"target="_blanck" class="btn-link" >
					${curr.accountName}
					</a>
				</td>
				<td>
					${curr.name}
				</td>
				<td>
					${curr.followers}
				</td>
				<td>
					${curr.quantity}
				</td>
				<td>
				<g:link controller="tweet" action="list" class="btn btn-sm btn-white btn-info btn-round"
					 params="[conceptsId:conceptId,authorId:curr.id,dateTo:dateTo,dateFrom:dateFrom]">
					<i class="ace-icon fa fa-external-link bigger-140 blue icon-only"></i>
				</g:link>
				</td>
			</tr>
		</g:each>
	</tbody>
	<tfoot class="hide-if-no-paging">
		<tr>
			<td colspan="5">
				<div class="paginate text-center"></div>
			</td>
		</tr>
	</tfoot>
</table>