<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>


<h4 class="header">
	<mytaglib:i18n key="table.name.courier" />
</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:i18n key="table.column.shippingFrom" /></th>
			<th><mytaglib:i18n key="table.column.shippingTo" /></th>
			<th><mytaglib:i18n key="table.column.addressee" /></th>
			<th><mytaglib:i18n key="table.column.price" /></th>

			<th><mytaglib:sort-link pageUrl="${pagesCourier}"
					column="updated">
					<mytaglib:i18n key="table.column.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="courier" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${courier.shippingFrom}" /></td>
				<td><c:out value="${courier.shippingTo}" /></td>
				<td><c:out value="${courier.addressee}" /></td>
				<td><c:out value="${courier.price}" /></td>


				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${courier.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesCourier}/${courier.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${pagesCourier}/${courier.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesCourier}/${courier.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesCourier}/add"><i
	class="material-icons">add</i></a>
