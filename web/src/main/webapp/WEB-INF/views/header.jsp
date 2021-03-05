<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<header>
	<nav>
		<div class="nav-wrapper container">
			<ul class="left hide-on-med-and-down">

				<!-- Dropdown Trigger -->
				<sec:authorize access="hasAnyRole('customer','admin')">
					<li><a class="dropdown-trigger" href="#!"
						data-target="dropdown2"><mytaglib:i18n
								key="table.name.postServices" /><i class="material-icons right">arrow_drop_down</i></a></li>
				</sec:authorize>

				<!-- Dropdown Trigger -->
				<li><a class="dropdown-trigger" href="#!"
					data-target="dropdown3"><mytaglib:i18n
							key="table.name.postProducts" /><i class="material-icons right">arrow_drop_down</i></a></li>


				<!-- Dropdown Trigger -->
				<sec:authorize access="hasAnyRole('manager','admin')">
					<li><a class="dropdown-trigger" href="#!"
						data-target="dropdown1"><mytaglib:i18n
								key="table.name.settings" /><i class="material-icons right">arrow_drop_down</i></a></li>
				</sec:authorize>

				<sec:authorize access="!isAnonymous()">
					<a class="right" href="${contextPath}/execute_logout"
						title="logout"><i class="material-icons">arrow_forward</i></a>
				</sec:authorize>
				<li><a href="${contextPath}?language=ru">RU</a></li>
				<li><a href="${contextPath}?language=en">EN</a></li>

			</ul>

			<!-- Dropdown Structure -->
			<ul id="dropdown1" class="dropdown-content">

				<li><a href="${pagesProduct}"><mytaglib:i18n
							key="table.name.product" /></a></li>

				<li><a href="${pagesInventory}"><mytaglib:i18n
							key="table.name.inventory" /></a></li>

				<!-- li><a href="${pagesUserAccount}">UserAccounts</a></li -->

				<li><a href="${pagesExpressZone}"><mytaglib:i18n
							key="table.name.expressZone" /></a></li>
				<li><a href="${pagesLetterZone}"><mytaglib:i18n
							key="table.name.letterZone" /></a></li>
				<li><a href="${pagesParcelZone}"><mytaglib:i18n
							key="table.name.parcelZone" /></a></li>
				<li><a href="${pagesCountry}"><mytaglib:i18n
							key="table.name.country" /></a></li>
				<li><a href="${pagesPaperDetails}"><mytaglib:i18n
							key="table.name.paperDetails" /></a></li>
				<!--<li><a href="${pagesStore}"><mytaglib:i18n
							key="table.name.store" /></a></li>  -->
			</ul>

			<ul id="dropdown2" class="dropdown-content">
				<li><a href="${pagesMailing}"><mytaglib:i18n
							key="table.name.mailing" /></a></li>
				<li><a href="${pagesCourier}"><mytaglib:i18n
							key="table.name.courier" /></a></li>
				<li><a href="${pagesMoneyTransfer}"><mytaglib:i18n
							key="table.name.moneyTransfer" /></a></li>
				<li><a href="${pagesPolygraphy}"><mytaglib:i18n
							key="table.name.polygraphy" /></a></li>
			</ul>

			<ul id="dropdown3" class="dropdown-content">
				<li><a href="${pagesOrderProduct}"><mytaglib:i18n
							key="table.name.orderItem" /></a></li>
				<li><a href="${pagesOrderHistory}"><mytaglib:i18n
							key="table.name.orderHistory" /></a></li>

			</ul>

		</div>
	</nav>
</header>