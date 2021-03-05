<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<h4 class="header">
	<mytaglib:i18n key="table.name.orderProduct" />
</h4>
<div class="row">
	<form:form class="col s12" method="POST"
		action="${pagesOrderProduct}/order" modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<form:input path="userAccountId" type="hidden" />
		<form:input path="status" type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:input path="shippingTo" type="text" disabled="${readonly}" />
				<form:errors path="shippingTo" cssClass="red-text" />
				<label for="shippingTo">адрес доставки</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:select path="shippingType" disabled="${readonly}">
					<form:options items="${shippingTypesChoices}" />
				</form:select>
				<form:errors path="shippingType" cssClass="red-text" />
				<label for="shippingType">способ доставки</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="cost" type="text" readonly="true" />
				<form:errors path="cost" cssClass="red-text" />
				<label for="cost">стоимость заказа</label>
			</div>
		</div>

		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit"><mytaglib:i18n
				key="table.button.markSubmited" /></button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right"
					href="${pagesOrderProduct}/cart"><mytaglib:i18n
				key="table.button.toProductCart" /><i
					class="material-icons right"></i>
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

