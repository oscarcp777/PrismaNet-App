<table class="table table-hover table-striped">
				<thead>
					<tr>
					   <th> # </th>
					   <th><g:message code="concept.conceptName.label" /></th>
						<th><g:message code="concept.twitterSetup.label"/></th>
						<th><g:message code="concept.facebookSetup.label"/></th>
						<th><g:message code="concept.dateCreated.label"/></th>
						<th><g:message code="concept.status.label"/></th>
						<th >
						<g:message code="label.edit"/>
						</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${conceptList}" status="i" var="concept">
					<tr>
					    <td>${i+1}</td>
						<td>${fieldValue(bean: concept, field: "conceptName")}</td>
					
						<td>${fieldValue(bean: concept, field: "twitterSetup")}</td>
					
						<td>${fieldValue(bean: concept, field: "facebookSetup")}</td>
					
						<td><g:formatDate date="${concept.dateCreated}" format="dd/MM/yyyy HH:mm"/></td>
					    <td>
						 <label class="inline">
							<g:checkBox name="statusConcept-${concept.id}" onclick="changeStatus('${concept.id}','${concept.active}');" checked="${concept.active}" class="ace ace-switch ace-switch-4" />
							<span class="lbl middle tooltips tooltip-info" title="${message(code: 'concept.user.advance.toltip.active')}"></span>
						</label>
					    </td>
					   <td>
					   <div class="hidden-sm hidden-xs action-buttons">
							<a class="blue tooltips tooltip-info" href="#" title='${message(code: 'concept.user.advance.edit')}'>
								<i class="ace-icon fa fa-pencil-square-o bigger-200"></i>
							</a>
						</div>
					   </td>
					</tr>
				</g:each>
				</tbody>
			</table>