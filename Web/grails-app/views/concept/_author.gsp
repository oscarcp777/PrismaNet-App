<%@ page import="com.prismanet.Author"%>
<table class="table table-striped table-bordered table-hover ">
	<thead>
		<tr>
			<th>Usuario</th>
			<th>Nombre</th>
			<th>Seguidores</th>
			<th>Proporcion (Seguidores/Siguiendo)</th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${authors}" var="author">
			<tr>
				<td><img class="avatar" style="width: 30px; height: 30px;"
					alt="${author?.accountName}" src="${author?.profileImage}">
				  ${author?.accountName}
				</td>
				<td>
					${author?.name}
				</td>
				<td>
					${author?.followers}
				</td>
				<td>
					<g:formatNumber number="${author?.ratio}" type="number" maxFractionDigits="2" />
				</td>
			</tr>
		</g:each>
	</tbody>
</table>