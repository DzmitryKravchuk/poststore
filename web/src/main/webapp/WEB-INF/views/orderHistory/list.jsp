<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>


<h4 class="header">
	<mytaglib:i18n key="table.name.orderHistory" />
</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:i18n key="table.column.shippingTo" /></th>
			<th><mytaglib:i18n key="table.column.shippingType" /></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrderHistory}"
					column="cost">
					<mytaglib:i18n key="table.column.cost" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrderHistory}"
					column="status">
					<mytaglib:i18n key="table.column.status" />
				</mytaglib:sort-link></th>

			<th><mytaglib:sort-link pageUrl="${pagesOrderHistory}"
					column="updated">
					<mytaglib:i18n key="table.column.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="orderProduct" items="${gridItems}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${orderProduct.shippingTo}" /></td>
				<td><c:out value="${orderProduct.shippingType}" /></td>
				<td><c:out value="${orderProduct.cost}" /></td>
				<td><c:out value="${orderProduct.status}" /></td>

				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${orderProduct.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesOrderHistory}/${orderProduct.id}"><i
						class="material-icons">info</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<jspFragments:paging />

<div id="piechart" style="width: 900px; height: 500px;"></div>
<script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});
	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {
		var arrWirthHeader = ${jsData};
		var data = google.visualization.arrayToDataTable(arrWirthHeader);

		var options = {
			title : '5 товаров, которые чаще всего заказывали в прошлом месяце'
		}

		var chart = new google.visualization.PieChart(document
				.getElementById('piechart'));

		chart.draw(data, options);
	}
</script>