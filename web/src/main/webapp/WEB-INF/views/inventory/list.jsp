<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>


<h4 class="header">
	<mytaglib:i18n key="table.name.inventory" />
</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesInventory}" column="id">
					<mytaglib:i18n key="table.column.id" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesInventory}"
					column="product">
					<mytaglib:i18n key="table.column.name" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesInventory}"
					column="store">
					<mytaglib:i18n key="table.column.store" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesInventory}"
					column="quantity">
					<mytaglib:i18n key="table.column.quantity" />
				</mytaglib:sort-link></th>

			<th><mytaglib:sort-link pageUrl="${pagesInventory}"
					column="store">
					<mytaglib:i18n key="table.column.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="inventory" items="${gridItems}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${inventory.id}" /></td>
				<td><c:out value="${inventory.productName}" /></td>
				<td><c:out value="${inventory.storeName}" /></td>
				<td><c:out value="${inventory.quantity}" /></td>

				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${inventory.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesInventory}/${inventory.id}"><i
						class="material-icons">info</i></a> 
						<!--<a class="btn-floating"
					href="${pagesInventory}/${inventory.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesInventory}/${inventory.id}/delete"><i
						class="material-icons">delete</i></a>-->
						</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right"
	href="${pagesInventory}/add"><i class="material-icons">add</i></a>
