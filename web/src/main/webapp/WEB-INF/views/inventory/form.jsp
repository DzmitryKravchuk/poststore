<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">
	<mytaglib:i18n key="table.name.editInventory" />
</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesInventory}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<form:input path="version" type="hidden" />
		<form:input path="storeId" type="hidden" />
		<div class="row">
			<div class="input-field col s12">
				<form:select path="productId" disabled="${readonly}">
					<form:options items="${productsChoices}" />
				</form:select>
				<form:errors path="productId" cssClass="red-text" />
				<label for="productId">выбор товара</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="quantity" type="number" disabled="${readonly}" />
				<form:errors path="quantity" cssClass="red-text" />
				<label for="quantity">количество</label>
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
				<a class="btn waves-effect waves-light right"
					href="${pagesInventory}"><mytaglib:i18n
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

