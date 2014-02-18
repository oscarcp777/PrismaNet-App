<%@ page import="com.prismanet.User"%>

<div class="tab-content profile-edit-tab-content">
	<div id="edit-basic" class="tab-pane active">
		<h4 class="header blue bolder smaller">
			<g:message code="form.general" />
		</h4>

		<div
			class="form-group fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} ">
			<label class="col-sm-3 control-label no-padding-right" for="username">
				<g:message code="user.username.label" default="Username" />

			</label>
			<div class="col-sm-9">
				<span class="input-icon input-icon-right"> <g:textField
						name="username" maxlength="20" value="${userInstance?.username}" />
				</span>
			</div>
		</div>

		<div
			class="form-group fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} ">
			<label class="col-sm-3 control-label no-padding-right" for="password">
				<g:message code="user.password.label" default="Password" />

			</label>
			<div class="col-sm-9">
				<span class="input-icon input-icon-right"> <g:textField
						name="password" value="${userInstance?.password}" />
				</span>
			</div>
		</div>

		<div
			class="form-group fieldcontain ${hasErrors(bean: userInstance, field: 'firstName', 'error')} ">
			<label class="col-sm-3 control-label no-padding-right"
				for="firstName"> <g:message code="user.firstName.label"
					default="First Name" />

			</label>
			<div class="col-sm-9">
				<span class="input-icon input-icon-right"> <g:textField
						name="firstName" value="${userInstance?.firstName}" />
				</span>
			</div>
		</div>

		<div
			class="form-group fieldcontain ${hasErrors(bean: userInstance, field: 'lastName', 'error')} ">
			<label class="col-sm-3 control-label no-padding-right" for="lastName">
				<g:message code="user.lastName.label" default="Last Name" />

			</label>
			<div class="col-sm-9">
				<span class="input-icon input-icon-right"> <g:textField
						name="lastName" value="${userInstance?.lastName}" />
				</span>
			</div>
		</div>

		<div
			class="form-group fieldcontain ${hasErrors(bean: userInstance, field: 'account', 'error')} ">
			<label class="col-sm-3 control-label no-padding-right" for="account">
				<g:message code="user.account.label" default="Account" />

			</label>
			<div class="col-sm-9">
				<span class="input-icon input-icon-right"> <g:select
						id="account" name="account.id"
						from="${com.prismanet.AccountType.list()}" optionKey="id"
						value="${userInstance?.account?.id}" class="many-to-one"
						noSelection="['null': '']" />
				</span>
			</div>
		</div>

		<div
			class="form-group fieldcontain ${hasErrors(bean: userInstance, field: 'accountExpired', 'error')} ">
			<label class="col-sm-3 control-label no-padding-right"
				for="accountExpired"> <g:message
					code="user.accountExpired.label" default="Account Expired" />

			</label>
			<div class="col-sm-9">
				<span class="input-icon input-icon-right"> <g:checkBox
						name="accountExpired" value="${userInstance?.accountExpired}" />
				</span>
			</div>
		</div>

		<div
			class="form-group fieldcontain ${hasErrors(bean: userInstance, field: 'accountLocked', 'error')} ">
			<label class="col-sm-3 control-label no-padding-right"
				for="accountLocked"> <g:message
					code="user.accountLocked.label" default="Account Locked" />

			</label>
			<div class="col-sm-9">
				<span class="input-icon input-icon-right"> <g:checkBox
						name="accountLocked" value="${userInstance?.accountLocked}" />
				</span>
			</div>
		</div>

		<div
			class="form-group fieldcontain ${hasErrors(bean: userInstance, field: 'concepts', 'error')} ">
			<label class="col-sm-3 control-label no-padding-right" for="concepts">
				<g:message code="user.concepts.label" default="Concepts" />

			</label>
			<div class="col-sm-9">
				
					<ul class="one-to-many">
						<g:each in="${userInstance?.concepts?}" var="c">
							<li><g:link controller="concept" action="show" id="${c.id}" class="btn btn-info btn-sm">
									${c?.encodeAsHTML()}
									<i class="icon-arrow-right icon-on-right bigger-110"></i>
								</g:link></li>
						</g:each>
						<li class="add">
						
						<g:link controller="concept" action="create" class="btn btn-info btn-sm"
								params="['user.id': userInstance?.id]">
								${message(code: 'default.add.label', args: [message(code: 'concept.label', default: 'Concept')])}
							<i class="icon-arrow-right icon-on-right bigger-110"></i>
							</g:link>
						</li>
					</ul>
				
			</div>
		</div>

		<div
			class="form-group fieldcontain ${hasErrors(bean: userInstance, field: 'enabled', 'error')} ">
			<label class="col-sm-3 control-label no-padding-right" for="enabled">
				<g:message code="user.enabled.label" default="Enabled" />

			</label>
			<div class="col-sm-9">
				<span class="input-icon input-icon-right"> <g:checkBox
						name="enabled" value="${userInstance?.enabled}" />
				</span>
			</div>
		</div>

		<div
			class="form-group fieldcontain ${hasErrors(bean: userInstance, field: 'passwordExpired', 'error')} ">
			<label class="col-sm-3 control-label no-padding-right"
				for="passwordExpired"> <g:message
					code="user.passwordExpired.label" default="Password Expired" />

			</label>
			<div class="col-sm-9">
				<span class="input-icon input-icon-right"> <g:checkBox
						name="passwordExpired" value="${userInstance?.passwordExpired}" />
				</span>
			</div>
		</div>

		<div
			class="form-group fieldcontain ${hasErrors(bean: userInstance, field: 'twitterAccounts', 'error')} ">
			<label class="col-sm-3 control-label no-padding-right"
				for="twitterAccounts"> <g:message
					code="user.twitterAccounts.label" default="Twitter Accounts" />

			</label>
			<div class="col-sm-9">
				<span class="input-icon input-icon-right"> 
				<g:select name="twitterAccounts"
						from="${com.prismanet.TwitterAccount.list()}" multiple="multiple"
						optionKey="id" size="5"
						value="${userInstance?.twitterAccounts*.id}" class="many-to-many" />
				</span>
			</div>
		</div>

	</div>
</div>