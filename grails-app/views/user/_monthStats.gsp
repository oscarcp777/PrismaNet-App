<table class="table table-striped table-bordered table-hover table-condensed">
	<thead>
		<tr>
			<th>Concepto</th>
			<th>Tweets</th>
			<th>Autores</th>
			<th> Menciones por Autor</th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${statsList}" var="item">
			<tr>
	
				<td>
					${item[0]}
				</td>
				<td>
					${item[1]}
				</td>
				<td>
					${item[2]}
				</td>
				<td>
					${item[1]/item[2]}
				</td>
			</tr>
		</g:each>
	</tbody>
</table>

