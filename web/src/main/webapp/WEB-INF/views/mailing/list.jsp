<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>


<h4 class="header">
	<mytaglib:i18n key="table.name.mailing" />
</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesMailing}"
					column="mailingType">
					<mytaglib:i18n key="table.column.mailingType" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesMailing}"
					column="country">
					<mytaglib:i18n key="table.column.country" />
				</mytaglib:sort-link></th>
			<th><mytaglib:i18n key="table.column.weight" /></th>
			<th><mytaglib:i18n key="table.column.addressee" /></th>
			<th><mytaglib:i18n key="table.column.address" /></th>
			<th><mytaglib:i18n key="table.column.price" /></th>

			<th><mytaglib:sort-link pageUrl="${pagesMailing}"
					column="updated">
					<mytaglib:i18n key="table.column.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="mailing" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${mailing.mailingType}" /></td>
				<td><c:out value="${mailing.countryName}" /></td>
				<td><c:out value="${mailing.weight}" /></td>
				<td><c:out value="${mailing.addressee}" /></td>
				<td><c:out value="${mailing.address}" /></td>
				<td><c:out value="${mailing.price}" /></td>


				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${mailing.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesMailing}/${mailing.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${pagesMailing}/${mailing.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesMailing}/${mailing.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesMailing}/add"><i
	class="material-icons">add</i></a>
