<%@ page import="com.prismanet.Author"%>
<table class="table table-striped table-bordered table-hover table-condensed">
	<thead>
		<tr>
			<th>Usuario</th>
			<th>Nombre</th>
			<th>Seguidores</th>
			<th>Tweets</th>
			
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
				
			</tr>
		</g:each>
	</tbody>
</table>