<table class="table table-hover table-striped table-condensed">
				<thead>
					<tr>
					   <th> # </th>
					   <th><g:message code="concept.conceptName.label" /></th>
						<th><g:message code="concept.lang.label"/></th>
						<th><g:message code="concept.dateCreated.label"/></th>
						<th><g:message code="concept.status.label"/></th>
						<th >
						<g:message code="label.edit"/>
						</th>
						<th >
						<g:message code="label.show"/>
						</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${conceptList}" status="i" var="concept">
					<tr>
					    <td>${i+1}</td>
						<td>${fieldValue(bean: concept, field: "conceptName")}</td>
						<td>
					     <g:if test="${concept.lang}">
					        ${fieldValue(bean: concept, field: "lang")}
					     </g:if>
					      <g:if test="${!concept.lang}">
					       ${message(code: 'concept.user.advance.lenguage.all')}
					     </g:if>
						</td>
					
					
						<td><g:formatDate date="${concept.dateCreated}" format="dd/MM/yyyy HH:mm"/></td>
					    <td>
						 <label class="inline">
							<g:checkBox name="statusConcept-${concept.id}" onclick="changeStatus('${concept.id}','${concept.active}');" checked="${concept.active}" class="ace ace-switch ace-switch-4" />
							<span class="lbl middle tooltips tooltip-info" title="${message(code: 'concept.user.advance.toltip.active')}"></span>
						</label>
					    </td>
					   <td>
					   <div class="hidden-sm hidden-xs action-buttons">
							<g:link class="blue tooltips tooltip-info" action="editAdvance" title='${message(code: 'concept.user.advance.edit')}'
							id="${concept.id}">
								<i class="ace-icon fa fa-pencil-square-o bigger-200"></i>
							</g:link>
						</div>
					   </td>
					    <td>
					   <div class="hidden-sm hidden-xs action-buttons">
							<g:link class="blue tooltips tooltip-info" action="showAdvance" title='${message(code: 'concept.user.advance.show')}'
							id="${concept.id}">
								<i class="ace-icon fa fa-search-plus bigger-200"></i>
							</g:link>
						</div>
					   </td>					   
					</tr>
				</g:each>
				</tbody>
			</table>