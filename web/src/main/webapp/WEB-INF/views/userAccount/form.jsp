<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">
	<mytaglib:i18n key="table.name.editUserAccount" />
</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesUserAccount}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<form:input path="userRole" type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:input path="eMail" type="text" disabled="${readonly}" />
				<form:errors path="eMail" cssClass="red-text" />
				<label for="eMail">адрес электронной почты</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="password" type="text" disabled="${readonly}" />
				<form:errors path="password" cssClass="red-text" />
				<label for="password">пароль</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field  col s12">
				<form:textarea path="details.name" disabled="${readonly}" />
				<form:errors path="details.name" cssClass="red-text" />
				<label for="details.name">ФИО</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field  col s12">
				<form:textarea path="details.adress" disabled="${readonly}" />
				<form:errors path="details.adress" cssClass="red-text" />
				<label for="details.adress">адрес</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field  col s12">
				<form:textarea path="details.phone" disabled="${readonly}" />
				<form:errors path="details.phone" cssClass="red-text" />
				<label for="details.phone">номер телефона</label>
			</div>
		</div>

		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">
						<mytaglib:i18n key="table.button.save" />
					</button>
				</c:if>
			</div>
			<!--div class="col s3">
				<a class="btn waves-effect waves-light right"
					href="${pagesUserAccount}"><mytaglib:i18n
						key="table.button.toList" /><i class="material-icons right"></i>
				</a>
			</div-->
		</div>
	</form:form>
</div>

