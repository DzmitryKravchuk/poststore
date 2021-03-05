<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<h4 class="header">
	<mytaglib:i18n key="table.name.editMoneyTransfer" />
</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesMoneyTransfer}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<form:input path="userAccountId" type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:input path="payerName" type="text" disabled="${readonly}" />
				<form:errors path="payerName" cssClass="red-text" />
				<label for="payerName">ФИО плательщика</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="payerAdress" type="text" disabled="${readonly}" />
				<form:errors path="payerAdress" cssClass="red-text" />
				<label for="payerAdress">адрес плательщика</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="beneficiaryName" type="text"
					disabled="${readonly}" />
				<form:errors path="beneficiaryName" cssClass="red-text" />
				<label for="beneficiaryName">ФИО получателя</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="beneficiaryAdress" type="text"
					disabled="${readonly}" />
				<form:errors path="beneficiaryAdress" cssClass="red-text" />
				<label for="beneficiaryAdress">адрес получателя</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="amount" type="number" disabled="${readonly}" />
				<form:errors path="amount" cssClass="red-text" />
				<label for="amount">сумма перевода</label>
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
					href="${pagesMoneyTransfer}"><mytaglib:i18n
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

