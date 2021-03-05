<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>


<h4 class="header">
	<mytaglib:i18n key="table.name.polygraphy" />
</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesPolygraphy}" column="id">
					<mytaglib:i18n key="table.column.id" />
				</mytaglib:sort-link></th>
			<th><mytaglib:i18n key="table.column.copyCount" /></th>
			<th><mytaglib:sort-link pageUrl="${pagesPolygraphy}"
					column="paperDetails">
					<mytaglib:i18n key="table.column.paperName" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPolygraphy}"
					column="price">
					<mytaglib:i18n key="table.column.price" />
				</mytaglib:sort-link></th>

			<th><mytaglib:sort-link pageUrl="${pagesPolygraphy}"
					column="updated">
					<mytaglib:i18n key="table.column.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="polygraphy" items="${gridItems}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${polygraphy.id}" /></td>
				<td><c:out value="${polygraphy.copyCount}" /></td>
				<td><c:out value="${polygraphy.paperDetailsName}" /></td>
				<td><c:out value="${polygraphy.price}" /></td>


				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${polygraphy.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesPolygraphy}/${polygraphy.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating"
					href="${pagesPolygraphy}/${polygraphy.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesPolygraphy}/${polygraphy.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right"
	href="${pagesPolygraphy}/add"><i class="material-icons">add</i></a>
