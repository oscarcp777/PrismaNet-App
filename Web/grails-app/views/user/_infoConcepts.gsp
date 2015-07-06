<div class="col-lg-6">
	<div class="profile-user-info profile-user-info-striped">
	   <g:each in="${listConStats}" status="i" var="conStat">
		<div class="profile-info-row">
			<div class="profile-info-name"> ${conStat[0]} </div>
			<div class="profile-info-value"><span> 
			<g:formatNumber number="${conStat[1]}" type="number" maxFractionDigits="0" />
			</span></div>
		</div>
		</g:each>
		<div class="profile-info-row">
			<div class="profile-info-name"> Total de menciones </div>
			<div class="profile-info-value"><span> 
			<g:formatNumber number="${totalMentions}" type="number" maxFractionDigits="0" />
			</span></div>
		</div>
	</div>
	</div>