<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">
	<mytaglib:i18n key="table.name.editCountry" />
</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesCountry}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<div class="row">
			<div class="input-field col s12">
				<form:input path="name" type="text" disabled="${readonly}" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name">название страны</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:select path="expressZoneId" disabled="${readonly}">
					<form:options items="${expressZonesChoices}" />
				</form:select>
				<form:errors path="expressZoneId" cssClass="red-text" />
				<label for="expressZoneId">тарифная зона Экспресс</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:select path="letterZoneId" disabled="${readonly}">
					<form:options items="${letterZonesChoices}" />
				</form:select>
				<form:errors path="letterZoneId" cssClass="red-text" />
				<label for="letterZoneId">тарифная зона Письма</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:select path="parcelZoneId" disabled="${readonly}">
					<form:options items="${parcelZonesChoices}" />
				</form:select>
				<form:errors path="parcelZoneId" cssClass="red-text" />
				<label for="parcelZoneId">тарифная зона Посылки</label>
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
				<a class="btn waves-effect waves-light right" href="${pagesCountry}"><mytaglib:i18n
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

