<%@ page import="com.prismanet.Author"%>
<table class="table table-striped table-bordered table-hover table-condensed">
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
				<g:link controller="tweet" action="list" class="btn btn-white btn-info btn-round"
					 params="[conceptsId:conceptId,authorId:curr.id,dateTo:dateTo,dateFrom:dateFrom]">
					<i class="ace-icon fa fa-external-link bigger-160 blue"></i>
				</g:link>
				</td>
			</tr>
		</g:each>
	</tbody>
</table>