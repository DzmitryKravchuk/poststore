<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<h4 class="header">
	<mytaglib:i18n key="table.name.editPaperDetails" />
</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesPaperDetails}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<div class="row">
			<div class="input-field col s12">
				<form:input path="name" type="text" disabled="${readonly}" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name">производитель, плотность, и другие
					свойства</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="price" type="text" disabled="${readonly}" />
				<form:errors path="price" cssClass="red-text" />
				<label for="price">стоимость печати за 1 стандартный лист
					формата А4</label>
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
					href="${pagesPaperDetails}"><mytaglib:i18n
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

