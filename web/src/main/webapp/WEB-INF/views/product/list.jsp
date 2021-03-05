<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>


<h4 class="header">
	<mytaglib:i18n key="table.name.product" />
</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesProduct}" column="name">
					<mytaglib:i18n key="table.column.name" />
				</mytaglib:sort-link></th>

			<th><mytaglib:sort-link pageUrl="${pagesProduct}" column="price">
					<mytaglib:i18n key="table.column.price" />
				</mytaglib:sort-link></th>

			<th><mytaglib:sort-link pageUrl="${pagesProduct}"
					column="updated">
					<mytaglib:i18n key="table.column.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="product" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${product.name}" /></td>
				<td><c:out value="${product.price}" /></td>

				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${product.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesProduct}/${product.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${pagesProduct}/${product.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesProduct}/${product.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesProduct}/add"><i
	class="material-icons">add</i></a>
