<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<c:set var="pageTitle" value="Reports & Analytics" />


<%@ include file="../common/header.jsp"%>


<div class="reports-container">


	<!-- =====================================================
         PAGE HEADER
         ===================================================== -->

	<div class="reports-header">

		<div>

			<h1>Reports & Analytics</h1>

			<p>Analyse marketplace sales, products, categories, sellers and
				customers.</p>

		</div>


		<a href="${pageContext.request.contextPath}/admin/dashboard"
			class="back-btn"> ← Dashboard </a>

	</div>



	<!-- =====================================================
         ERROR MESSAGE
         ===================================================== -->

	<c:if test="${not empty error}">

		<div class="alert alert-danger">${error}</div>

	</c:if>



	<!-- =====================================================
         REPORT SELECTION
         ===================================================== -->

	<div class="report-selector card">

		<h2>Select Report</h2>


		<!-- IMPORTANT:
             Controller uses @GetMapping("/admin")
             Therefore this form must use GET
             and /reports/admin
        -->

		<form method="get"
			action="${pageContext.request.contextPath}/reports/admin"
			id="reportForm">


			<!-- =================================================
                 REPORT TYPE
                 ================================================= -->

			<div class="form-group">

				<label for="reportType"> Report Type </label> <select
					id="reportType" name="reportType" required>

					<option value="">-- Select Report --</option>


					<option value="sales" ${reportType == 'sales' ? 'selected' : ''}>

						Sales Summary</option>


					<option value="product"
						${reportType == 'product' ? 'selected' : ''}>Product
						Sales</option>


					<option value="category"
						${reportType == 'category' ? 'selected' : ''}>Category
						Sales</option>


					<option value="seller" ${reportType == 'seller' ? 'selected' : ''}>

						Seller Sales</option>


					<option value="customer"
						${reportType == 'customer' ? 'selected' : ''}>Customer
						Report</option>


					<option value="customerProduct"
						${reportType == 'customerProduct' ? 'selected' : ''}>

						Customer Product Sales</option>

				</select>

			</div>



			<!-- =================================================
                 FILTERS
                 ================================================= -->

			<c:if test="${reportType == 'seller'}">

				<div class="filter-grid">

					<!-- SELLER -->

					<div class="form-group">

						<label for="sellerId"> Seller </label> <select id="sellerId"
							name="sellerId" required>

							<option value="">-- Select Seller --</option>

							<c:forEach var="seller" items="${sellers}">

								<option value="${seller.userId}"
									${filter.sellerId == seller.userId
                              ? 'selected'
                              : ''}>

									${seller.userName} (${seller.userId})</option>

							</c:forEach>

						</select>

					</div>
			</c:if>



			<!-- =================================================
                         CUSTOMER FILTER
                         ================================================= -->

			<c:if
				test="${reportType == 'customer' ||
                                 reportType == 'customerProduct'}">

				<div class="form-group">

					<label for="customerId"> Customer </label> <select id="customerId"
						name="customerId">

						<option value="">-- All Customers --</option>


						<c:forEach var="customer" items="${customers}">

							<option value="${customer.customerId}"
								${filter.customerId == customer.customerId ? 'selected' : ''}>

								${customer.customerName} (${customer.customerId})</option>

						</c:forEach>

					</select>

				</div>

			</c:if>



			<!-- =================================================
                         CATEGORY FILTER
                         ================================================= -->

			<c:if
				test="${reportType == 'product' ||
                                 reportType == 'category' ||
                                 reportType == 'customerProduct'}">

				<div class="form-group">

					<label for="categoryId"> Category </label> <select id="categoryId"
						name="categoryId">

						<option value="">-- All Categories --</option>


						<c:forEach var="category" items="${categories}">

							<option value="${category.categoryId}"
								${filter.categoryId == category.categoryId ? 'selected' : ''}>

								${category.categoryName}</option>

						</c:forEach>

					</select>

				</div>

			</c:if>



			<!-- =================================================
                         PRODUCT ID
                         ================================================= -->

			<c:if
				test="${reportType == 'product' ||
                                 reportType == 'customerProduct'}">

				<div class="form-group">

					<label for="productId"> Product ID </label> <input type="text"
						id="productId" name="productId" value="${filter.productId}"
						placeholder="Product ID" />

				</div>

			</c:if>



			<!-- =================================================
                         PRODUCT NAME
                         ================================================= -->

			<c:if
				test="${reportType == 'product' ||
                                 reportType == 'customerProduct'}">

				<div class="form-group">

					<label for="productName"> Product Name </label> <input type="text"
						id="productName" name="productName" value="${filter.productName}"
						placeholder="Product name" />

				</div>

			</c:if>



			<!-- =================================================
                         MINIMUM PRICE
                         NOT FOR SELLER REPORT
                         ================================================= -->

			<c:if test="${reportType != 'seller'}">

				<div class="form-group">

					<label for="minPrice"> Minimum Price </label> <input type="number"
						id="minPrice" name="minPrice" step="0.01"
						value="${filter.minPrice}" placeholder="0.00" />

				</div>

			</c:if>



			<!-- =================================================
                         MAXIMUM PRICE
                         NOT FOR SELLER REPORT
                         ================================================= -->

			<c:if test="${reportType != 'seller'}">

				<div class="form-group">

					<label for="maxPrice"> Maximum Price </label> <input type="number"
						id="maxPrice" name="maxPrice" step="0.01"
						value="${filter.maxPrice}" placeholder="0.00" />

				</div>

			</c:if>



			<!-- =================================================
                         MINIMUM QUANTITY
                         ================================================= -->

			<c:if
				test="${reportType == 'product' ||
                                 reportType == 'category' ||
                                 reportType == 'customerProduct'}">

				<div class="form-group">

					<label for="minQuantity"> Minimum Quantity </label> <input
						type="number" id="minQuantity" name="minQuantity"
						value="${filter.minQuantity}" placeholder="0" />

				</div>

			</c:if>



			<!-- =================================================
                         MAXIMUM QUANTITY
                         ================================================= -->

			<c:if
				test="${reportType == 'product' ||
                                 reportType == 'category' ||
                                 reportType == 'customerProduct'}">

				<div class="form-group">

					<label for="maxQuantity"> Maximum Quantity </label> <input
						type="number" id="maxQuantity" name="maxQuantity"
						value="${filter.maxQuantity}" placeholder="0" />

				</div>

			</c:if>



			<!-- =================================================
                         ORDER STATUS
                         ================================================= -->

			<c:if
				test="${reportType == 'sales' ||
                                 reportType == 'customer'}">

				<div class="form-group">

					<label for="orderStatus"> Order Status </label> <input type="text"
						id="orderStatus" name="orderStatus" value="${filter.orderStatus}"
						placeholder="Example: COMPLETED" />

				</div>

			</c:if>



			<!-- =================================================
                         PAYMENT STATUS
                         ================================================= -->

			<c:if
				test="${reportType == 'sales' ||
                                 reportType == 'customer'}">

				<div class="form-group">

					<label for="paymentStatus"> Payment Status </label> <input
						type="text" id="paymentStatus" name="paymentStatus"
						value="${filter.paymentStatus}" placeholder="Example: SUCCESS" />

				</div>

			</c:if>



			<!-- =================================================
                         FROM DATE
                         ================================================= -->

			<div class="form-group">

				<label for="fromDate"> From Date </label> <input
					type="datetime-local" id="fromDate" name="fromDate"
					value="${filter.fromDate}" />


				<c:if test="${reportType == 'seller'}">

					<small class="date-help"> Optional </small>

				</c:if>

			</div>



			<!-- =================================================
                         TO DATE
                         ================================================= -->

			<div class="form-group">

				<label for="toDate"> To Date </label> <input type="datetime-local"
					id="toDate" name="toDate" value="${filter.toDate}" />


				<c:if test="${reportType == 'seller'}">

					<small class="date-help"> Optional — defaults to today </small>

				</c:if>

			</div>
	</div>



	<!-- =================================================
                     BUTTONS
                     ================================================= -->

	<div class="report-actions">


		<button type="submit" class="generate-btn">Generate Report</button>


		<a href="${pageContext.request.contextPath}/reports/admin"
			class="clear-btn"> Clear </a>


	</div>


</div>


</form>

</div>



<!-- =====================================================
         SALES REPORT
         ===================================================== -->

<c:if test="${reportType == 'sales'}">

	<div class="result-card">

		<h2>Sales Summary</h2>


		<c:choose>

			<c:when test="${not empty salesReport}">

				<table class="report-table">

					<tbody>

						<tr>

							<th>Total Orders</th>

							<td>${salesReport.totalOrders}</td>

						</tr>


						<tr>

							<th>Total Quantity</th>

							<td>${salesReport.totalQuantity}</td>

						</tr>


						<tr>

							<th>Total Sales</th>

							<td>₹${salesReport.totalSales}</td>

						</tr>


						<tr>

							<th>Average Order Value</th>

							<td>₹${salesReport.averageOrderValue}</td>

						</tr>

					</tbody>

				</table>

			</c:when>


			<c:otherwise>

				<div class="no-results">No sales data found.</div>

			</c:otherwise>

		</c:choose>

	</div>

</c:if>



<!-- =====================================================
         PRODUCT SALES REPORT
         ===================================================== -->

<c:if test="${reportType == 'product'}">

	<div class="result-card">

		<h2>Product Sales Report</h2>


		<c:choose>

			<c:when test="${not empty productSalesReport}">

				<div class="table-wrapper">

					<table class="report-table">

						<thead>

							<tr>

								<th>Product</th>
								<th>Category</th>
								<th>Seller</th>
								<th>Price</th>
								<th>Quantity Sold</th>
								<th>Revenue</th>
								<th>Average Rating</th>

							</tr>

						</thead>


						<tbody>

							<c:forEach var="report" items="${productSalesReport}">

								<tr>

									<td>${report.productName}</td>

									<td>${report.categoryName}</td>

									<td>${report.sellerName}</td>

									<td>₹<fmt:formatNumber value="${report.productPrice}"
											minFractionDigits="2" maxFractionDigits="2" />
									</td>

									<td>${report.quantitySold}</td>

									<td>₹<fmt:formatNumber value="${report.revenue}"
											minFractionDigits="2" maxFractionDigits="2" />
									</td>

									<td>${report.averageRating}</td>

								</tr>

							</c:forEach>

						</tbody>

					</table>

				</div>

			</c:when>


			<c:otherwise>

				<div class="no-results">No product sales data found.</div>

			</c:otherwise>

		</c:choose>

	</div>

</c:if>



<!-- =====================================================
         CATEGORY SALES REPORT
         ===================================================== -->

<c:if test="${reportType == 'category'}">

	<div class="result-card">

		<h2>Category Sales Report</h2>


		<c:choose>

			<c:when test="${not empty categorySalesReport}">

				<div class="table-wrapper">

					<table class="report-table">

						<thead>

							<tr>

								<th>Category</th>
								<th>Quantity Sold</th>
								<th>Revenue</th>

							</tr>

						</thead>


						<tbody>

							<c:forEach var="report" items="${categorySalesReport}">

								<tr>

									<td>${report.categoryName}</td>

									<td>${report.quantitySold}</td>

									<td>₹<fmt:formatNumber value="${report.revenue}"
											minFractionDigits="2" maxFractionDigits="2" />
									</td>

								</tr>

							</c:forEach>

						</tbody>

					</table>

				</div>

			</c:when>


			<c:otherwise>

				<div class="no-results">No category sales data found.</div>

			</c:otherwise>

		</c:choose>

	</div>

</c:if>



<!-- =====================================================
         SELLER SALES REPORT
         SELLER FOCUSED
         ===================================================== -->

<c:if test="${reportType == 'seller'}">

	<div class="result-card">

		<h2>Seller Sales Report</h2>


		<c:choose>

			<c:when test="${not empty sellerSalesReport}">

				<!-- =========================================
                     SELLER SUMMARY
                     ========================================= -->

				<c:forEach var="seller" items="${sellerSalesReport}">

					<div class="seller-summary">

						<div class="seller-summary-box">

							<span> Seller </span> <strong> ${seller.sellerName} </strong> <small>
								${seller.sellerId} </small>

						</div>


						<div class="seller-summary-box">

							<span> Total Orders </span> <strong>
								${seller.orderCount} </strong>

						</div>


						<div class="seller-summary-box">

							<span> Total Quantity Sold </span> <strong>
								${seller.quantitySold} </strong>

						</div>


						<div class="seller-summary-box">

							<span> Total Sales </span> <strong> ₹${seller.revenue} </strong>

						</div>


						<div class="seller-summary-box">

							<span> Average Selling Price </span> <strong> <c:choose>

									<c:when test="${seller.quantitySold > 0}">

                                        ₹${seller.revenue /
                                           seller.quantitySold}

                                    </c:when>

									<c:otherwise>

                                        ₹0.00

                                    </c:otherwise>

								</c:choose>

							</strong>

						</div>

					</div>

				</c:forEach>


				<!-- =========================================
                     PRODUCTS SOLD
                     ========================================= -->

				<h3>Products Sold</h3>


				<c:choose>

					<c:when test="${not empty productSalesReport}">

						<div class="table-wrapper">

							<table class="report-table">

								<thead>

									<tr>

										<th>Product</th>

										<th>Category</th>

										<th>Price</th>

										<th>Quantity Sold</th>

										<th>Total Revenue</th>

										<th>Average Rating</th>

									</tr>

								</thead>


								<tbody>

									<c:forEach var="product" items="${productSalesReport}">

										<tr>

											<td>${product.productName}</td>

											<td>${product.categoryName}</td>

											<td>₹${product.productPrice}</td>

											<td>${product.quantitySold}</td>

											<td>₹${product.revenue}</td>

											<td>${product.averageRating}</td>

										</tr>

									</c:forEach>

								</tbody>

							</table>

						</div>

					</c:when>


					<c:otherwise>

						<div class="no-results">No products were sold by this seller
							for the selected date range.</div>

					</c:otherwise>

				</c:choose>

			</c:when>


			<c:otherwise>

				<div class="no-results">No seller sales found for the selected
					seller and date range.</div>

			</c:otherwise>

		</c:choose>

	</div>

</c:if>



<!-- =====================================================
         CUSTOMER REPORT
         ===================================================== -->

<c:if test="${reportType == 'customer'}">

	<div class="result-card">

		<h2>Customer Report</h2>


		<c:choose>

			<c:when test="${not empty customerReport}">

				<table class="report-table">

					<tbody>

						<tr>

							<th>Total Customers</th>

							<td>${customerReport.totalCustomers}</td>

						</tr>


						<tr>

							<th>Total Orders</th>

							<td>${customerReport.totalOrders}</td>

						</tr>


						<tr>

							<th>Total Quantity</th>

							<td>${customerReport.totalQuantity}</td>

						</tr>


						<tr>

							<th>Total Spent</th>

							<td>₹${customerReport.totalSpent}</td>

						</tr>

					</tbody>

				</table>

			</c:when>


			<c:otherwise>

				<div class="no-results">No customer data found.</div>

			</c:otherwise>

		</c:choose>

	</div>

</c:if>



<!-- =====================================================
         CUSTOMER PRODUCT REPORT
         ===================================================== -->

<c:if test="${reportType == 'customerProduct'}">

	<div class="result-card">

		<h2>Customer Product Report</h2>


		<c:choose>

			<c:when test="${not empty customerProductReport}">

				<div class="table-wrapper">

					<table class="report-table">

						<thead>

							<tr>

								<th>Product</th>

								<th>Category</th>

								<th>Quantity Sold</th>

								<th>Revenue</th>

							</tr>

						</thead>


						<tbody>

							<c:forEach var="report" items="${customerProductReport}">

								<tr>

									<td>${report.productName}</td>

									<td>${report.categoryName}</td>

									<td>${report.quantitySold}</td>

									<td>₹<fmt:formatNumber value="${report.revenue}"
											minFractionDigits="2" maxFractionDigits="2" />
									</td>

								</tr>

							</c:forEach>

						</tbody>

					</table>

				</div>

			</c:when>


			<c:otherwise>

				<div class="no-results">No customer product data found.</div>

			</c:otherwise>

		</c:choose>

	</div>

</c:if>


</div>



<!-- =========================================================
     PAGE CSS
     ========================================================= -->

<style>

/* =========================================================
   CONTAINER
   ========================================================= */
.reports-container {
	max-width: 1250px;
	margin: 35px auto;
	padding: 0 30px;
}

/* =========================================================
   HEADER
   ========================================================= */
.reports-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 30px;
	padding-bottom: 20px;
	border-bottom: 1px solid #dee2e6;
}

.reports-header h1 {
	margin: 0;
	font-size: 34px;
	color: #212529;
}

.reports-header p {
	margin-top: 8px;
	color: #6c757d;
}

.back-btn {
	padding: 10px 18px;
	background-color: #6c757d;
	color: white;
	text-decoration: none;
	border-radius: 6px;
	font-weight: 600;
}

.back-btn:hover {
	background-color: #5c636a;
}

/* =========================================================
   CARD
   ========================================================= */
.card, .result-card {
	background: white;
	border: 1px solid #e1e5e9;
	border-radius: 10px;
	padding: 30px;
	margin-bottom: 30px;
	box-shadow: 0 3px 10px rgba(0, 0, 0, 0.05);
}

.card h2, .result-card h2 {
	margin-top: 0;
}

/* =========================================================
   FORM
   ========================================================= */
.form-group {
	margin-bottom: 20px;
}

.form-group label {
	display: block;
	margin-bottom: 7px;
	font-weight: 600;
}

.form-group input, .form-group select {
	width: 100%;
	box-sizing: border-box;
	padding: 12px 13px;
	border: 1px solid #ced4da;
	border-radius: 6px;
	font-size: 15px;
	background: white;
}

.form-group input:focus, .form-group select:focus {
	outline: none;
	border-color: #0d6efd;
	box-shadow: 0 0 0 3px rgba(13, 110, 253, 0.12);
}

.date-help {
	display: block;
	margin-top: 5px;
	color: #6c757d;
	font-size: 12px;
}

/* =========================================================
   FILTERS
   ========================================================= */
.filters-section {
	margin-top: 30px;
	padding-top: 25px;
	border-top: 1px solid #dee2e6;
}

.filter-grid {
	display: grid;
	grid-template-columns: repeat(3, 1fr);
	column-gap: 25px;
	row-gap: 5px;
}

/* =========================================================
   BUTTONS
   ========================================================= */
.report-actions {
	display: flex;
	gap: 12px;
	margin-top: 15px;
}

.generate-btn {
	border: none;
	padding: 12px 22px;
	background-color: #0d6efd;
	color: white;
	border-radius: 6px;
	font-size: 15px;
	font-weight: 600;
	cursor: pointer;
}

.generate-btn:hover {
	background-color: #0b5ed7;
}

.clear-btn {
	padding: 12px 22px;
	background-color: #6c757d;
	color: white;
	border-radius: 6px;
	text-decoration: none;
	font-weight: 600;
}

.clear-btn:hover {
	background-color: #5c636a;
}

/* =========================================================
   TABLE
   ========================================================= */
.table-wrapper {
	width: 100%;
	overflow-x: auto;
}

.report-table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

.report-table th {
	background-color: #343a40;
	color: white;
	padding: 13px;
	text-align: left;
	white-space: nowrap;
}

.report-table td {
	padding: 13px;
	border-bottom: 1px solid #dee2e6;
}

.report-table tbody tr:hover {
	background-color: #f8f9fa;
}

/* =========================================================
   SELLER SUMMARY
   ========================================================= */
.seller-report-card {
	margin-top: 30px;
}

.seller-summary {
	display: grid;
	grid-template-columns: repeat(5, 1fr);
	gap: 18px;
	margin-bottom: 30px;
}

.seller-summary-box {
	border: 1px solid #dee2e6;
	border-radius: 8px;
	padding: 20px;
	text-align: center;
	background-color: #f8f9fa;
}

.summary-title {
	display: block;
	color: #6c757d;
	font-size: 14px;
	margin-bottom: 10px;
	font-weight: 600;
}

.seller-summary-box strong {
	display: block;
	font-size: 22px;
	color: #212529;
}

/* =========================================================
   SELLER PRODUCTS
   ========================================================= */
.seller-products-section {
	margin-top: 30px;
}

.seller-products-section h3 {
	margin-bottom: 15px;
}

.seller-products-table {
	margin-top: 0;
}

/* =========================================================
   NO RESULTS
   ========================================================= */
.no-results {
	padding: 25px;
	text-align: center;
	background-color: #f8f9fa;
	color: #6c757d;
	border-radius: 6px;
	margin-top: 20px;
}

/* =========================================================
   ERROR
   ========================================================= */
.alert-danger {
	padding: 14px 18px;
	margin-bottom: 25px;
	background-color: #f8d7da;
	color: #842029;
	border: 1px solid #f5c2c7;
	border-radius: 6px;
}

/* =========================================================
   RESPONSIVE
   ========================================================= */
@media ( max-width : 1000px) {
	.seller-summary {
		grid-template-columns: repeat(2, 1fr);
	}
}

@media ( max-width : 900px) {
	.filter-grid {
		grid-template-columns: repeat(2, 1fr);
	}
}

@media ( max-width : 600px) {
	.reports-container {
		padding: 0 15px;
	}
	.reports-header {
		flex-direction: column;
		align-items: flex-start;
		gap: 15px;
	}
	.filter-grid {
		grid-template-columns: 1fr;
	}
	.seller-summary {
		grid-template-columns: 1fr;
	}
	.report-actions {
		flex-direction: column;
	}
	.generate-btn, .clear-btn {
		text-align: center;
	}
}
</style>



<!-- =========================================================
     SELLER DATE DEFAULT
     ========================================================= -->

<script>

document.addEventListener("DOMContentLoaded", function () {

    var reportType = document.getElementById("reportType");

    var sellerId = document.getElementById("sellerId");

    var customerId = document.getElementById("customerId");

    var categoryId = document.getElementById("categoryId");

    var productId = document.getElementById("productId");

    var productName = document.getElementById("productName");

    var minPrice = document.getElementById("minPrice");

    var maxPrice = document.getElementById("maxPrice");

    var minQuantity = document.getElementById("minQuantity");

    var maxQuantity = document.getElementById("maxQuantity");

    var orderStatus = document.getElementById("orderStatus");

    var paymentStatus = document.getElementById("paymentStatus");

    var fromDate = document.getElementById("fromDate");

    var toDate = document.getElementById("toDate");


    // ==========================================================
    // GET PARENT FORM GROUP
    // ==========================================================

    function getFormGroup(element) {

        if (!element) {
            return null;
        }

        return element.closest(".form-group");
    }


    // ==========================================================
    // SHOW / HIDE FIELD
    // ==========================================================

    function showField(element, required) {

        if (!element) {
            return;
        }

        var group = getFormGroup(element);

        if (group) {
            group.style.display = "";
        }

        element.disabled = false;

        if (required) {
            element.setAttribute("required", "required");
        } else {
            element.removeAttribute("required");
        }
    }


    function hideField(element) {

        if (!element) {
            return;
        }

        var group = getFormGroup(element);

        if (group) {
            group.style.display = "none";
        }

        element.removeAttribute("required");

        element.disabled = true;
    }


    // ==========================================================
    // UPDATE FILTERS WHEN REPORT TYPE CHANGES
    // ==========================================================

    function updateReportFields() {

        var type = reportType ? reportType.value : "";


        // ------------------------------------------------------
        // SELLER
        // Seller Sales / Product Sales
        // ------------------------------------------------------

        if (type === "seller") {

            showField(sellerId, true);

        } else if (type === "product") {

            showField(sellerId, false);

        } else {

            hideField(sellerId);

        }


        // ------------------------------------------------------
        // CUSTOMER
        // Customer Report / Customer Product Sales
        // ------------------------------------------------------

        if (type === "customer"
                || type === "customerProduct") {

            showField(customerId, false);

        } else {

            hideField(customerId);

        }


        // ------------------------------------------------------
        // CATEGORY
        // Product / Category / Customer Product
        // ------------------------------------------------------

        if (type === "product"
                || type === "category"
                || type === "customerProduct") {

            showField(categoryId, false);

        } else {

            hideField(categoryId);

        }


        // ------------------------------------------------------
        // PRODUCT ID
        // Product / Customer Product
        // ------------------------------------------------------

        if (type === "product"
                || type === "customerProduct") {

            showField(productId, false);

        } else {

            hideField(productId);

        }


        // ------------------------------------------------------
        // PRODUCT NAME
        // Product / Customer Product
        // ------------------------------------------------------

        if (type === "product"
                || type === "customerProduct") {

            showField(productName, false);

        } else {

            hideField(productName);

        }


        // ------------------------------------------------------
        // MIN PRICE
        // Everything except Seller Sales
        // ------------------------------------------------------

        if (type !== ""
                && type !== "seller") {

            showField(minPrice, false);

        } else {

            hideField(minPrice);

        }


        // ------------------------------------------------------
        // MAX PRICE
        // Everything except Seller Sales
        // ------------------------------------------------------

        if (type !== ""
                && type !== "seller") {

            showField(maxPrice, false);

        } else {

            hideField(maxPrice);

        }


        // ------------------------------------------------------
        // MIN QUANTITY
        // Product / Category / Customer Product
        // ------------------------------------------------------

        if (type === "product"
                || type === "category"
                || type === "customerProduct") {

            showField(minQuantity, false);

        } else {

            hideField(minQuantity);

        }


        // ------------------------------------------------------
        // MAX QUANTITY
        // Product / Category / Customer Product
        // ------------------------------------------------------

        if (type === "product"
                || type === "category"
                || type === "customerProduct") {

            showField(maxQuantity, false);

        } else {

            hideField(maxQuantity);

        }


        // ------------------------------------------------------
        // ORDER STATUS
        // Sales / Customer
        // ------------------------------------------------------

        if (type === "sales"
                || type === "customer") {

            showField(orderStatus, false);

        } else {

            hideField(orderStatus);

        }


        // ------------------------------------------------------
        // PAYMENT STATUS
        // Sales / Customer
        // ------------------------------------------------------

        if (type === "sales"
                || type === "customer") {

            showField(paymentStatus, false);

        } else {

            hideField(paymentStatus);

        }


        // ------------------------------------------------------
        // DATE FIELDS
        // ------------------------------------------------------

        if (fromDate) {
            fromDate.disabled = false;
        }

        if (toDate) {
            toDate.disabled = false;
        }


        // ------------------------------------------------------
        // SELLER SALES DATE DEFAULT
        // ------------------------------------------------------

        if (type === "seller"
                && toDate
                && !toDate.value) {

            var now = new Date();

            var year = now.getFullYear();

            var month = String(
                now.getMonth() + 1
            ).padStart(2, "0");

            var day = String(
                now.getDate()
            ).padStart(2, "0");

            toDate.value =
                    year
                    + "-"
                    + month
                    + "-"
                    + day
                    + "T23:59";

        }

    }


    // ==========================================================
    // REPORT TYPE CHANGE
    // ==========================================================

    if (reportType) {

        reportType.addEventListener(
                "change",
                function () {

                    updateReportFields();

                }
        );

    }


    // ==========================================================
    // INITIAL LOAD
    // ==========================================================

    updateReportFields();

});

</script>


<%@ include file="../common/footer.jsp"%>