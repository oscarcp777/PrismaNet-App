<%@ page import="com.prismanet.Concept" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="home">
	</head>
	<body>
	<div class="page-content">
	<div class="page-header">
			<h1>
			   <i class="ace-icon fa fa-user-plus"></i> <g:message code="concept.user.advance.title"/>
			</h1>
		</div>
		<div class="nav" role="navigation">
			<g:link class="btn btn-white btn-info btn-bold btn-round" action="createAdvance">
				<i class="ace-icon fa fa-cloud-download bigger-120 blue"></i> 
				<g:message code="concept.user.advance.create"/>
				<i class="ace-icon fa fa-arrow-right bigger-120 blue"></i> 
			</g:link>
		</div>
		<hr>
		<div id="table-concept">
			<g:render template="tableConcepts" model="['conceptList':conceptList]"></g:render>
		</div>
			<div class="col-lg-8" style="text-align: center;">
					<ul class="pagination pagination-sm">
						<li><g:paginate total="${conceptTotal}" /></li>
					</ul>
			</div>
		
		</div>
		<script type="text/javascript">
		var msgEnableConcept='${message(code: 'concept.user.advance.enable.msg')}';
		var msgDisableConcept='${message(code: 'concept.user.advance.disable.msg')}';
		function loadTableConcepts(data){
			if(data.error == 'error-save'){
				$('#statusConcept-'+data.id).prop( "checked", data.status=='true' );
				bootbox.alert("Ops! hubo un problema al actualizar su concepto por favor intente otra vez");
			}else{
				$("#table-concept").html(data);
				bootbox.alert("Se actualizo correctamente su concepto");
			}
			
		}
		function changeStatus(id,status){
			bootbox.confirm(status=='true'?msgEnableConcept:msgDisableConcept, function(result) {
				 if(!result){
					 $('#statusConcept-'+id).prop( "checked", status );
				}else{
					var data = {"id":id,'active':status}
					doRequest('changeStatus',data,loadTableConcepts, null, 'GET');
				}
			});
		}
		</script>
	</body>
</html>
