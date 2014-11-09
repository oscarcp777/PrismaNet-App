<table class="table table-striped table-bordered table-hover table-condensed">
	<thead>
		<tr>
			<th>Concepto</th>
			<th>Creado</th>
			<th>Nombre</th>
			<th>Likes</th>
			<th>Comentarios</th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${statsList}" var="item">
			<tr>
				<td>
					${item.conceptName}
				</td>
				<td>
					<a href=${item.link}>${item.created}</a>
				</td>
				<td>
					${item.name}
				</td>
				<td>
					${item.totalLikes}
				</td>
				<td>
					${item.totalComments}
				</td>
			</tr>
		</g:each>
	</tbody>
</table>

