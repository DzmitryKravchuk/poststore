<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>


<h4 class="header">
	<mytaglib:i18n key="table.name.cartOrderProduct" />
</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesOrderProduct}/cart"
					column="product">
					<mytaglib:i18n key="table.column.name" />
				</mytaglib:sort-link></th>
			<th><mytaglib:i18n key="table.column.price" /></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrderProduct}/cart"
					column="quantity">
					<mytaglib:i18n key="table.column.quantity" />
				</mytaglib:sort-link></th>

			<th><mytaglib:sort-link pageUrl="${pagesOrderProduct}/cart"
					column="updated">
					<mytaglib:i18n key="table.column.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="orderItem" items="${gridItems}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${orderItem.productName}" /></td>
				<td><c:out value="${orderItem.productPrice}" /></td>
				<td><c:out value="${orderItem.quantity}" /></td>

				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${orderItem.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesOrderProduct}/${orderItem.id}"><i
						class="material-icons">info</i></a><a class="btn-floating"
					href="${pagesOrderProduct}/${orderItem.id}/item"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesOrderProduct}/${orderItem.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<div class="row">
	<div class="col s6"></div>

	<div class="col s3">
		<c:if test="${!readonly}">
			<a class="btn waves-effect waves-light right" type="submit"
				href="${pagesOrderProduct}/order"><mytaglib:i18n
					key="table.button.save" /></a>
		</c:if>
	</div>
	<div class="col s3">
		<a class="btn waves-effect waves-light right"
			href="${pagesOrderProduct}"><mytaglib:i18n
				key="table.button.toProductList" /><i class="material-icons right"></i>
		</a>
	</div>
</div>
