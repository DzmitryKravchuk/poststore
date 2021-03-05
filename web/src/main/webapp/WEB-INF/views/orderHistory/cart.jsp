<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h4 class="header">
	<mytaglib:i18n key="table.name.viewOrderHistory" />
</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesOrderHistory}"
					column="id">
					<mytaglib:i18n key="table.column.id" />
				</mytaglib:sort-link></th>
			<th><mytaglib:i18n key="table.column.name" /></th>
			<th><mytaglib:i18n key="table.column.price" /></th>
			<th><mytaglib:i18n key="table.column.quantity" /></th>

			<th><mytaglib:i18n key="table.column.created" /></th>
			<th><mytaglib:i18n key="table.column.updated" /></th>
			<th></th>
		</tr>
		<c:forEach var="orderItem" items="${gridItems}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${orderItem.id}" /></td>
				<td><c:out value="${orderItem.productName}" /></td>
				<td><c:out value="${orderItem.productPrice}" /></td>
				<td><c:out value="${orderItem.quantity}" /></td>

				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${orderItem.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${orderItem.updated}" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<div class="row">
	<div class="col s6"></div>


	<div class="col s3">
		<a class="btn waves-effect waves-light right"
			href="${pagesOrderHistory}"><mytaglib:i18n
				key="table.button.toList" /><i class="material-icons right"></i> </a>
	</div>
	<sec:authorize access="hasRole('manager')">
		<a class="btn waves-effect waves-light right"
			href="${pagesOrderHistory}/<c:out value="${orderProductId}" />/edit"><mytaglib:i18n
				key="table.button.markExecuted" /></a>

	</sec:authorize>
</div>


