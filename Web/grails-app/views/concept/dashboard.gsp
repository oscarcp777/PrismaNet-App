<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="home">
</head>
<body>
		<div class="page-header">
			<h1 id="headerMain">
				<i class="ace-icon glyphicon glyphicon-cloud-download"></i> ${concept.conceptName}
			<small id="headerSmall">
								<i class="ace-icon fa fa-angle-double-right"></i>
								<g:message code="dashboard.concetp.title" />
			</small>
			</h1>
			
		</div>

		<div class="row">
			<div class="col-lg-12">
<div class="alert alert-block alert-warning">

								<i class="ace-icon fa fa-exclamation-triangle fa-2x pull-left fa-border" style="border: solid 0.08em #fcf8e3;color:red;"></i>
								<g:message code='concept.general.edition'/> 
							</div>
				<div class="col-md-6">
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-row">
							<div class="profile-info-name">
								<g:message code="dashborad.concept.init" />
							</div>

							<div class="profile-info-value">
								<span><g:formatDate format="dd/MM/yyyy HH:mm"
										date="${concept.dateCreated}" /></span>
							</div>
						</div>

					</div>
					<h4 class="header blue bolder smaller">
						<g:message code="dashborad.config.twitter" />
					</h4>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-row">
							<div class="profile-info-name">
								<g:message code="dashborad.config.twitter.included.accounts" />
							</div>

							<div class="profile-info-value">
								<span > ${twSetup.includedAccounts}
								</span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">
								<g:message code="dashborad.config.twitter.keywords" />
							</div>

							<div class="profile-info-value">
								<span class="editable editable-click" id="keywords" data-value='${twSetup.keywords}'> ${twSetup.keywords}
								</span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">
								<g:message code="dashborad.config.twitter.neutral.hashtags" />
							</div>

							<div class="profile-info-value">
								<span class="editable editable-click" id="neutralHashtags"> ${twSetup.neutralHashtags}
								</span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">
								<g:message code="dashborad.config.twitter.positive.hashtags" />
							</div>

							<div class="profile-info-value">
								<span class="editable editable-click" id="positiveHashtags">  ${twSetup.positiveHashtags}
								</span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">
								<g:message code="dashborad.config.twitter.negative.hashtags" />
							</div>

							<div class="profile-info-value">
								<span class="editable editable-click" id="negativeHashtags">  ${twSetup.negativeHashtags}
								</span>
							</div>
						</div>
					</div>

					<h4 class="header blue bolder smaller">
						<g:message code="dashborad.config.facebook" />
					</h4>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-row">
							<div class="profile-info-name">
								<g:message code="dashborad.config.facebook.accounts" />
							</div>

							<div class="profile-info-value">
								<span> ${fcSetup?.keywords}
								</span>
							</div>
						</div>
					</div>

				</div>

<!-- 				<div class="col-md-6"> 
					<a href="#" class="btn btn-danger btn-app"> <i
						class="ace-icon fa fa-comments"></i> ${total} <br>
					<g:message code="dashborad.concept.mentions" />
					</a> <a href="#" class="btn btn-primary btn-app"> <i
						class="ace-icon fa fa-twitter bigger-230"></i> ${tweetsTotal} <br>
					<g:message code="dashborad.concept.tweets" />
					</a> <a href="#" class="btn btn-info btn-app"> <i
						class="ace-icon fa fa-facebook bigger-230"></i> ${postsTotal} <br>
					<g:message code="dashborad.concept.post" />
					</a>
				</div>-->
			</div>
			<div class="col-lg-12 ">
				<div class="hr hr32 hr-dotted"></div>
			</div>
		</div>
	<script type="text/javascript">
	        var id='${concept.id}';
	        var idTS='${twSetup.id}'
			activeItemMenuLevel2(id,id+'-dash','${concept.conceptName}');
			var titleKeywords='${message(code: "dashborad.config.twitter.keywords")}';
			var titleNeutral='${message(code: "dashborad.config.twitter.neutral.hashtags")}';
			var titlePositive='${message(code: "dashborad.config.twitter.positive.hashtags")}';
			var titleNegative='${message(code: "dashborad.config.twitter.negative.hashtags")}';
			
			function validateHash(value){
			    	var values=$.trim(value).split(',');
			    	var re = new RegExp('^#[0-9A-Za-z_]+');
			    	for (var i = 0; i < values.length; i++) {
			    		var val=values[i];
			    		 if(val.length > 1 &&  val.length < 4) {
					            return 'Debe ingresar una palabra de mas de 3 letras';
					     }else if(val.length > 1 && !val.match(re)){
					    	 return 'Debe tener un formato de Hashtag (#ejemplo)';
					     }
			    	}
			       
			}
			function validateKeywords(value){
			    $(this).data($(this).attr("id"),$(this).text())
		    	var values=$.trim(value).split(',');
		    	for (var i = 0; i < values.length; i++) {
		    		var val=values[i];
		    		 if(val.length > 1 && val.length < 4) {
				            return 'Debe ingresar una palabra de mas de 3 letras';
				     }
		    	}
		       
		}
			
			function editField(id,title,validateCall){
				$('#'+id).editable({
					type:'text',
					emptytext:'Ingrese valor',
					title: title,
					success: function(response, newValue) {
						bootbox.confirm("Esta seguro cambiar a ' "+newValue+" '?", function(result) {
							if(result){
								var data = {};
								data["id"]=idTS;
								data[id]=newValue;
								doRequest('../../twitterSetup/update',data,null, null, 'POST');
							}else{
								if($('#'+id).data(id)=='undefined'){
									 $('#'+id).text('');
								}else{
								 $('#'+id).text($('#'+id).data(id));
								}
							}
							}); 
				    },
				    validate:validateCall
			});
			}
			$(function() {
				editField('keywords',titleKeywords,validateKeywords);
				editField('neutralHashtags',titleNeutral,validateHash);
				editField('positiveHashtags',titlePositive,validateHash);
				editField('negativeHashtags',titleNegative,validateHash);
			});
	</script>
</body>
</html>