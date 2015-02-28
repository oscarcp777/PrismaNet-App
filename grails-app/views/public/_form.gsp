<%@ page import="com.prismanet.Contact"%>
<g:form  action="save">

		<div class="row">
			<div class="col-sm-6 col-md-6">
				<div class="control-group">
					<label for="name" class="control-label"><g:message code="public.index..home.contact.name"/></label>
					<div class="controls">
						<g:textField value="${contact?.name}" class="col-sm-12 col-md-12"  name="name" id="name"/>
					</div>
					<!-- /controls -->
				</div>
				<!-- /control-group -->
			</div>
			<!-- /span6 -->
			<div class="col-sm-6 col-md-6">
				<div class="control-group">
					<label for="email" class="control-label"><g:message code="public.index..home.contact.email"/></label>
					<div class="controls">
						<g:textField value="${contact?.email}" class="col-md-12 col-sm-12" name="email" id="email"/>
					</div>
					<!-- /controls -->
				</div>
				<!-- /control-group -->
			</div>
			<!-- /span6 -->
		</div>
		<!-- /row -->

		<div class="row">
			<div class="col-md-6 col-sm-6">
				<div class="control-group">
					<label for="phone" class="control-label"><g:message code="public.index..home.contact.phone"/></label>
					<div class="controls">
						<g:textField value="${contact?.phone}" class="col-md-12 col-sm-12" name="phone" id="phone"/>
					</div>
					<!-- /controls -->
				</div>
				<!-- /control-group -->
			</div>
			<!-- /span6 -->
			<div class="col-md-6 col-sm-6">
				<div class="control-group">
					<label for="website" class="control-label"><g:message code="public.index..home.contact.web"/>
						:</label>
					<div class="controls">
						<g:textField value="${contact?.website}" class="col-md-12 col-sm-12" type="url"
							name="website" id="website"/>
					</div>
					<!-- /controls -->
				</div>
				<!-- /control-group -->
			</div>
			<!-- /span6 -->
		</div>
		<!-- /row -->

		<div class="row">
			<div class="col-md-12 col-sm-12">
				<div class="control-group">
					<label for="message" class="control-label">
						<g:message code="public.index..home.contact.message"/></label>
					<div class="controls">
						<g:textArea class="col-md-12 col-sm-12" name="message" id="message" rows="4"/>
					</div>
					<!-- /controls -->
				</div>
				<!-- /control-group -->
			</div>
			<!-- /span12 -->
		</div>
		<!-- /row -->

		<button type="submit" class="btn-inverse pull-right">
			 <g:message code="public.index..home.contact.send"/><span><g:message code="public.index..home.contact.message"/></span> <i class="ace-icon fa fa-arrow-circle-right"></i>
		</button>
	</g:form>
