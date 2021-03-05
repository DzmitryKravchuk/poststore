<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">
	<mytaglib:i18n key="table.name.editPolygraphy" />
</h4>
<div class="row">
	<form:form class="col s12" method="POST" enctype="multipart/form-data"
		action="${pagesPolygraphy}" modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<form:input path="userAccountId" type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:select path="imageFormat" disabled="${readonly}">
					<form:options items="${imageFormatsChoices}" />
				</form:select>
				<form:errors path="imageFormat" cssClass="red-text" />
				<label for="imageFormat">размер изображения</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="paperDetailsId" disabled="${readonly}">
					<form:options items="${paperDetailsChoices}" />
				</form:select>
				<form:errors path="paperDetailsId" cssClass="red-text" />
				<label for="paperDetailsId">характеристика бумаги</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="copyCount" type="number" disabled="${readonly}" />
				<form:errors path="copyCount" cssClass="red-text" />
				<label for="copyCount">количество копий (тираж), шт.</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<div class="switch">
					<label> печать <form:checkbox path="duplexPrinting"
							disabled="${readonly}" /> <span class="lever"></span>
						duplexPrinting
					</label>
				</div>
				<label class="switch-label">двусторонняя печать</label> <br />
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<div class="switch">
					<label> печать <form:checkbox path="coloured"
							disabled="${readonly}" /> <span class="lever"></span> coloured
					</label>
				</div>
				<label class="switch-label">цветная печать</label> <br />
			</div>
		</div>

		<!-- file upload -->
		<table>
			<tr>
				<td>File to upload:</td>
				<td><input type="file" name="file" /></td>
			</tr>
		</table>

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
					href="${pagesPolygraphy}"><mytaglib:i18n
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

