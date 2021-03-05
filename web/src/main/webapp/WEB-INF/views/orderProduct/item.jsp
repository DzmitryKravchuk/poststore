<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<h4 class="header">
	<mytaglib:i18n key="table.name.editOrderItem" />
</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesOrderProduct}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<form:input path="orderProductId" type="hidden" />
		<form:input path="productId" type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:input path="productName" type="text" readonly="true" />
				<form:errors path="productName" cssClass="red-text" />
				<label for="productName">наименование товара</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="productPrice" type="text" readonly="true" />
				<form:errors path="productPrice" cssClass="red-text" />
				<label for="productPrice">цена товара</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="quantity" type="number" disabled="${readonly}" />
				<form:errors path="quantity" cssClass="red-text" />
				<label for="quantity">количество товара</label>
			</div>
		</div>

		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">
						<mytaglib:i18n key="table.button.addToCart" />
					</button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right"
					href="${pagesOrderProduct}"><mytaglib:i18n
						key="table.button.toProductList" /><i
					class="material-icons right"></i> </a>
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

