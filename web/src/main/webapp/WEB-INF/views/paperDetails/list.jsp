<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>


<h4 class="header">
	<mytaglib:i18n key="table.name.paperDetails" />
</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesPaperDetails}"
					column="id">
					<mytaglib:i18n key="table.column.id" />
				</mytaglib:sort-link></th>
			<th><mytaglib:i18n key="table.column.name" /></th>
			<th><mytaglib:i18n key="table.column.price4paper" /></th>

			<th><mytaglib:i18n key="table.column.created" /></th>
			<th><mytaglib:i18n key="table.column.updated" /></th>
			<th></th>
		</tr>
		<c:forEach var="paperDetails" items="${gridItems}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${paperDetails.id}" /></td>
				<td><c:out value="${paperDetails.name}" /></td>
				<td><c:out value="${paperDetails.price}" /></td>

				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${paperDetails.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${paperDetails.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesPaperDetails}/${paperDetails.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating"
					href="${pagesPaperDetails}/${paperDetails.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesPaperDetails}/${paperDetails.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right"
	href="${pagesPaperDetails}/add"><i class="material-icons">add</i></a>
