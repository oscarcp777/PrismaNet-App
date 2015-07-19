<%@ page import="com.prismanet.Author"%>
<table class="table table-striped table-bordered table-hover table-condensed">
	<thead>
		<tr>
			<th>Usuario</th>
			<th>Nombre</th>
			<th>Seguidores</th>
			<th>Tweets</th>
			<th>Alcance Potencial</th>
			
		</tr>
	</thead>
	<tbody>
		<g:each in="${authors}" var="curr">
			<tr>
				<td><img class="avatar" style="width: 30px; height: 30px;"
					alt="${curr.author?.accountName}" src="${curr.author?.profileImage}">
				  
				  <a href="https://twitter.com/${curr.author?.accountName}"target="_blanck" class="btn-link" >
					${curr.author?.accountName}
					</a>
				</td>
				<td>
					${curr.author?.name}
				</td>
				<td>
					${curr.author?.followers}
				</td>
				<td>
					${curr.quantity}
				</td>
				<td>
					<g:formatNumber number="${curr.author?.followers*((Integer)curr.quantity)}" type="number" maxFractionDigits="2" />
				</td>
			</tr>
		</g:each>
	</tbody>
</table>