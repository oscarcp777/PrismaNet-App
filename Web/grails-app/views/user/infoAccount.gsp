<%@ page import="com.prismanet.MonthlyConceptStats" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="home">
	</head>
	<body>
	
	<div class="page-content">
	<div class="page-header">
		<h1>
		<i class="ace-icon fa  fa-info-circle"></i>
			<g:message code="user.info.account.title" />
		</h1>
	</div>
	<br>
	<div class="col-lg-8">
	<div class="alert alert-info">
	<button type="button" class="close" data-dismiss="alert">
			<i class="ace-icon fa fa-times"></i>
		</button>
		<h4>Importante!</h4>
        <ul class="fa-ul">
          <li>
            <i class="fa fa-info-circle fa-lg fa-li"></i>
           La Información mostrada corresponde hasta la fecha del 
		<b><g:formatDate date="${infoDate}" type="datetime" style="LONG" timeStyle="MEDIUM"/></b> 
          </li>
        </ul>
      </div>
     </div>
    
 <div class="form-horizontal col-sm-12">
  <div class="form-group">
	<label class="col-sm-3 control-label" for="form-field-1"> Seleccione el periodo a mostrar</label>
	<div class="col-sm-3">
		<g:select class="form-control" name="info-period" from="${listPeriod}" 
		optionKey="periodDesc" optionValue="periodDate"/>
	</div>
</div>
</div>    
     
     <div id="infoConcepts">
	<g:render template="infoConcepts"></g:render>
	</div>
	</div>
<script type="text/javascript">
function loadInfoPeriod(data) {
	$('#infoConcepts').html(data);
}
	$(function() {
	$('#info-period').change(function() {
	   var data = {"period":$(this).val()};
		doRequest('infoConcepts',data,loadInfoPeriod, null, 'GET');
	});
	});
</script>
	</body>
</html>
