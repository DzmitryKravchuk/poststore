<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">
	<mytaglib:i18n key="table.name.editMailing" />
</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesMailing}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<form:input path="userAccountId" type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:select path="mailingType" disabled="${readonly}">
					<form:options items="${mailingTypesChoices}" />
				</form:select>
				<form:errors path="mailingType" cssClass="red-text" />
				<label for="mailingType">тип отправления</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="countryId" disabled="${readonly}">
					<form:options items="${countriesChoices}" />
				</form:select>
				<form:errors path="countryId" cssClass="red-text" />
				<label for="countryId">страна назначения</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="weight" type="text" disabled="${readonly}" />
				<form:errors path="weight" cssClass="red-text" />
				<label for="weight">вес отправления, кг.</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="addressee" type="text" disabled="${readonly}" />
				<form:errors path="addressee" cssClass="red-text" />
				<label for="addressee">ФИО получателя</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="address" type="text" disabled="${readonly}" />
				<form:errors path="address" cssClass="red-text" />
				<label for="address">адрес получателя</label>
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
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${pagesMailing}"><mytaglib:i18n
						key="table.button.toList" /><i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>


<c:if test='${param["showAlerts"]}'>
	<!-- checks the URL parameter -->


	<script src="${contextPath}/resources/js/sample-alert-with-params.js"></script>
	<script>
		showMessage('${contextPath}'); // execute function defined somewhere above
	</script>

</c:if>

