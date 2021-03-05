<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>


<h4 class="header">
	<mytaglib:i18n key="table.name.moneyTransfer" />
</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:i18n key="table.column.payerName" /></th>
			<th><mytaglib:i18n key="table.column.payerAddress" /></th>
			<th><mytaglib:i18n key="table.column.addressee" /></th>
			<th><mytaglib:i18n key="table.column.address" /></th>
			<th><mytaglib:i18n key="table.column.amount" /></th>
			<th><mytaglib:i18n key="table.column.price" /></th>

			<th><mytaglib:sort-link pageUrl="${pagesMoneyTransfer}"
					column="updated">
					<mytaglib:i18n key="table.column.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="moneyTransfer" items="${gridItems}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${moneyTransfer.payerName}" /></td>
				<td><c:out value="${moneyTransfer.payerAdress}" /></td>
				<td><c:out value="${moneyTransfer.beneficiaryName}" /></td>
				<td><c:out value="${moneyTransfer.beneficiaryAdress}" /></td>
				<td><c:out value="${moneyTransfer.amount}" /></td>
				<td><c:out value="${moneyTransfer.transactionPrice}" /></td>

				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${moneyTransfer.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesMoneyTransfer}/${moneyTransfer.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating"
					href="${pagesMoneyTransfer}/${moneyTransfer.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesMoneyTransfer}/${moneyTransfer.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right"
	href="${pagesMoneyTransfer}/add"><i class="material-icons">add</i></a>
