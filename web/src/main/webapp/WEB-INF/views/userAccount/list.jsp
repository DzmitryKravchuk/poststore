<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<h4 class="header">userAccounts</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesUserAccount}" column="id">id</mytaglib:sort-link></th>
			<th>eMail</th>
			<th>password</th>
			
			
			<th>created</th>
			<th>updated</th>
			<th></th>
		</tr>
		<c:forEach var="userAccount" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${userAccount.id}" /></td>
				<td><c:out value="${userAccount.eMail}" /></td>
				<td><c:out value="${userAccount.password}" /></td>
				
				
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${userAccount.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${userAccount.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesUserAccount}/${userAccount.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${pagesUserAccount}/${userAccount.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesUserAccount}/${userAccount.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesUserAccount}/add"><i
	class="material-icons">add</i></a>
