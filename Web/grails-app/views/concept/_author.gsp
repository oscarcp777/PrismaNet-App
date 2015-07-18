<%@ page import="com.prismanet.Author"%>
<table class="table table-striped table-bordered table-hover ">
	<thead>
		<tr>
			<th>Usuario</th>
			<th>Nombre</th>
			<th>Seguidores</th>
			<th>Proporcion (Seguidores/Siguiendo)</th>
			<th>Tweets</th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${authors}" var="curr">
			<tr>
				<td><img class="avatar" style="width: 30px; height: 30px;"
					alt="${curr.author?.accountName}" src="${curr.author?.profileImage}">
				  ${curr.author?.accountName}
				</td>
				<td>
					${curr.author?.name}
				</td>
				<td>
					${curr.author?.followers}
				</td>
				<td>
					<g:formatNumber number="${curr.author?.ratio}" type="number" maxFractionDigits="2" />
				</td>
				<td>
					${curr.quantity}
				</td>
			</tr>
		</g:each>
	</tbody>
</table>