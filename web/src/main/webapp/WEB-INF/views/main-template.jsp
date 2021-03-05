<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"
	scope="request" />
<c:set var="pagesProduct" value="${contextPath}/product" scope="request" />
<!-- c:set var="pagesStore" value="${contextPath}/store" scope="request" / -->
<c:set var="pagesInventory" value="${contextPath}/inventory"
	scope="request" />
<c:set var="pagesUserAccount" value="${contextPath}/userAccount"
	scope="request" />
<c:set var="pagesExpressZone" value="${contextPath}/expressZone"
	scope="request" />
<c:set var="pagesLetterZone" value="${contextPath}/letterZone"
	scope="request" />
<c:set var="pagesParcelZone" value="${contextPath}/parcelZone"
	scope="request" />
<c:set var="pagesCountry" value="${contextPath}/country" scope="request" />
<c:set var="pagesMailing" value="${contextPath}/mailing" scope="request" />
<c:set var="pagesOrderProduct" value="${contextPath}/orderProduct"
	scope="request" />
<c:set var="pagesOrderHistory" value="${contextPath}/orderHistory"
	scope="request" />
<c:set var="pagesCourier" value="${contextPath}/courier" scope="request" />
<c:set var="pagesMoneyTransfer" value="${contextPath}/moneyTransfer"
	scope="request" />
<c:set var="pagesPaperDetails" value="${contextPath}/paperDetails"
	scope="request" />
<c:set var="pagesPolygraphy" value="${contextPath}/polygraphy"
	scope="request" />


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><tiles:insertAttribute name="title" /></title>
<!-- добавили диаграмму -->
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/css/materialize.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>

<link rel="stylesheet" href="${contextPath}/resources/css/custom.css">
<script src="${contextPath}/resources/js/init-materialize-forms.js"></script>
<script src="${contextPath}/resources/js/init-menu.js"></script>
</head>
<body>
	<tiles:insertAttribute name="header" />
	<main>
	<div class="container">
		<tiles:insertAttribute name="body" />
	</div>
	</main>
	<tiles:insertAttribute name="footer" />
</body>
</html>