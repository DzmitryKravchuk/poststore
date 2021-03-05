<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<h4 class="header">
	<mytaglib:i18n key="table.name.editCourier" />
</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesCourier}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<form:input path="userAccountId" type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:input path="shippingFrom" type="text" disabled="${readonly}" />
				<form:errors path="shippingFrom" cssClass="red-text" />
				<label for="shippingFrom">вызов курьера (куда)</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="shippingTo" type="text" disabled="${readonly}" />
				<form:errors path="shippingTo" cssClass="red-text" />
				<label for="shippingTo">адрес доставки</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="addressee" type="text" disabled="${readonly}" />
				<form:errors path="addressee" cssClass="red-text" />
				<label for="addressee">получатель</label>
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
				<a class="btn waves-effect waves-light right" href="${pagesCourier}"><mytaglib:i18n
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

