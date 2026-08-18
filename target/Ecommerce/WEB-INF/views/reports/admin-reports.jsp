<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Admin Reports"/>

<%@ include file="../common/header.jsp" %>


<!-- =========================================================
     PAGE HEADER
     ========================================================= -->

<div class="reports-header">

    <div>

        <h1>Reports & Analytics</h1>

        <p>
            Analyse marketplace sales, products, categories,
            sellers and customers.
        </p>

    </div>

    <a
            href="${pageContext.request.contextPath}/admin/dashboard"
            class="btn back-btn">

        &larr; Dashboard

    </a>

</div>


<!-- =========================================================
     ERROR MESSAGE
     ========================================================= -->

<c:if test="${not empty error}">

    <div
            class="alert alert-error"
            style="padding:12px;
                   background:#fee2e2;
                   color:#991b1b;
                   border-radius:8px;
                   margin-bottom:16px;">

        ${error}

    </div>

</c:if>


<!-- =========================================================
     REPORT FILTER CARD
     ========================================================= -->

<div class="card">

    <h2>Select Report</h2>


    <form
            method="post"
            action="${pageContext.request.contextPath}/admin/reports"
            id="reportForm">


        <!-- =================================================
             REPORT TYPE
             ================================================= -->

        <div class="form-group">

            <label for="reportType">
                Report Type
            </label>

            <select
                    id="reportType"
                    name="reportType"
                    onchange="changeReportType()"
                    required>

                <option
                        value="sales"
                        ${reportType == 'sales' || empty reportType ? 'selected' : ''}>

                    Sales Report

                </option>

                <option
                        value="product"
                        ${reportType == 'product' ? 'selected' : ''}>

                    Product Sales

                </option>

                <option
                        value="category"
                        ${reportType == 'category' ? 'selected' : ''}>

                    Category Sales

                </option>

                <option
                        value="seller"
                        ${reportType == 'seller' ? 'selected' : ''}>

                    Seller Sales

                </option>

                <option
                        value="customer"
                        ${reportType == 'customer' ? 'selected' : ''}>

                    Customer Report

                </option>

                <option
                        value="customerProduct"
                        ${reportType == 'customerProduct' ? 'selected' : ''}>

                    Customer Product Report

                </option>

            </select>

        </div>


        <hr>


        <!-- =================================================
             FILTERS
             ================================================= -->

        <h2>Filters</h2>


        <!-- =================================================
             CATEGORY
             ================================================= -->

        <div class="form-group">

            <label for="categoryId">
                Category
            </label>

            <select
                    id="categoryId"
                    name="categoryId">

                <option value="">
                    All Categories
                </option>

                <c:forEach
                        var="category"
                        items="${categories}">

                    <option
                            value="${category.categoryId}"
                            ${filter.categoryId == category.categoryId ? 'selected' : ''}>

                        ${category.categoryName}

                    </option>

                </c:forEach>

            </select>

        </div>


        <!-- =================================================
             PRODUCT ID
             ================================================= -->

        <div class="form-group">

            <label for="productId">
                Product ID
            </label>

            <input
                    type="text"
                    id="productId"
                    name="productId"
                    value="${filter.productId}"
                    placeholder="Product ID"/>

        </div>


        <!-- =================================================
             PRODUCT NAME
             ================================================= -->

        <div class="form-group">

            <label for="productName">
                Product Name
            </label>

            <input
                    type="text"
                    id="productName"
                    name="productName"
                    value="${filter.productName}"
                    placeholder="Search product"/>

        </div>


        <!-- =================================================
             SELLER FILTER
             ONLY USED FOR SELLER SALES
             ================================================= -->

        <div
                id="sellerFilter"
                class="form-group">

            <label for="sellerSelect">
                Seller
            </label>


            <select
                    id="sellerSelect"
                    name="sellerId"
                    onchange="sellerSelected()">

                <option value="">
                    -- Select Seller --
                </option>


                <c:forEach
                        var="seller"
                        items="${sellers}">

                    <option
                            value="${seller.userId}"
                            ${filter.sellerId == seller.userId ? 'selected' : ''}>

                        ${seller.shopName} - ${seller.userName}

                    </option>

                </c:forEach>

            </select>


            <small style="color:#666;">

                Select a seller by name.
                The corresponding Seller ID will be submitted.

            </small>

        </div>


        <!-- =================================================
             MANUAL SELLER ID
             ================================================= -->

        <div
                id="sellerIdFilter"
                class="form-group">

            <label for="manualSellerId">
                Seller ID
            </label>


            <input
                    type="text"
                    id="manualSellerId"
                    placeholder="Example: SEL1001"
                    value="${filter.sellerId}"
                    oninput="manualSellerIdEntered()"/>


            <small style="color:#666;">

                You may enter Seller ID instead of selecting
                a seller from the dropdown.

            </small>

        </div>


        <!-- =================================================
             MINIMUM PRICE
             ================================================= -->

        <div class="form-group">

            <label for="minPrice">
                Minimum Price
            </label>

            <input
                    type="number"
                    id="minPrice"
                    step="0.01"
                    min="0"
                    name="minPrice"
                    value="${filter.minPrice}"
                    placeholder="0.00"/>

        </div>


        <!-- =================================================
             MAXIMUM PRICE
             ================================================= -->

        <div class="form-group">

            <label for="maxPrice">
                Maximum Price
            </label>

            <input
                    type="number"
                    id="maxPrice"
                    step="0.01"
                    min="0"
                    name="maxPrice"
                    value="${filter.maxPrice}"
                    placeholder="0.00"/>

        </div>


        <!-- =================================================
             MINIMUM QUANTITY
             ================================================= -->

        <div class="form-group">

            <label for="minQuantity">
                Minimum Quantity Sold
            </label>

            <input
                    type="number"
                    id="minQuantity"
                    min="0"
                    name="minQuantity"
                    value="${filter.minQuantity}"
                    placeholder="Minimum quantity"/>

        </div>


        <!-- =================================================
             MAXIMUM QUANTITY
             ================================================= -->

        <div class="form-group">

            <label for="maxQuantity">
                Maximum Quantity Sold
            </label>

            <input
                    type="number"
                    id="maxQuantity"
                    min="0"
                    name="maxQuantity"
                    value="${filter.maxQuantity}"
                    placeholder="Maximum quantity"/>

        </div>


        <!-- =================================================
             ORDER STATUS
             ================================================= -->

        <div class="form-group">

            <label for="orderStatus">
                Order Status
            </label>

            <select
                    id="orderStatus"
                    name="orderStatus">

                <option value="">
                    All Order Statuses
                </option>

                <c:forEach
                        var="status"
                        items="${orderStatuses}">

                    <option
                            value="${status}"
                            ${filter.orderStatus == status ? 'selected' : ''}>

                        ${status}

                    </option>

                </c:forEach>

            </select>

        </div>


        <!-- =================================================
             PAYMENT STATUS
             ================================================= -->

        <div class="form-group">

            <label for="paymentStatus">
                Payment Status
            </label>

            <select
                    id="paymentStatus"
                    name="paymentStatus">

                <option value="">
                    All Payment Statuses
                </option>

                <c:forEach
                        var="status"
                        items="${paymentStatuses}">

                    <option
                            value="${status}"
                            ${filter.paymentStatus == status ? 'selected' : ''}>

                        ${status}

                    </option>

                </c:forEach>

            </select>

        </div>


        <!-- =================================================
             CUSTOMER ID
             ================================================= -->

        <div class="form-group">

            <label for="customerId">
                Customer ID
            </label>

            <input
                    type="text"
                    id="customerId"
                    name="customerId"
                    value="${filter.customerId}"
                    placeholder="Example: CUS1001"/>

        </div>


        <!-- =================================================
             FROM DATE
             ================================================= -->

        <div class="form-group">

            <label for="fromDate">
                From Date
            </label>

            <input
                    type="datetime-local"
                    id="fromDate"
                    name="fromDate"
                    value="${filter.fromDate}"/>

        </div>


        <!-- =================================================
             TO DATE
             ================================================= -->

        <div class="form-group">

            <label for="toDate">
                To Date
            </label>

            <input
                    type="datetime-local"
                    id="toDate"
                    name="toDate"
                    value="${filter.toDate}"/>

        </div>


        <!-- =================================================
             BUTTONS
             ================================================= -->

        <div
                style="margin-top:20px;
                       display:flex;
                       gap:12px;">

            <button
                    type="submit"
                    class="btn add-btn">

                Generate Report

            </button>


            <a
                    href="${pageContext.request.contextPath}/admin/reports"
                    class="btn back-btn">

                Clear

            </a>

        </div>


    </form>

</div>


<!-- =========================================================
     SALES SUMMARY
     ========================================================= -->

<c:if test="${not empty salesReport}">

    <div class="card">

        <h2>Sales Summary</h2>

        <table>

            <tr>

                <th>Total Orders</th>

                <td>
                    ${salesReport.totalOrders}
                </td>

            </tr>


            <tr>

                <th>Total Quantity Sold</th>

                <td>
                    ${salesReport.totalQuantity}
                </td>

            </tr>


            <tr>

                <th>Total Sales</th>

                <td>
                    ₹${salesReport.totalSales}
                </td>

            </tr>


            <tr>

                <th>Average Order Value</th>

                <td>
                    ₹${salesReport.averageOrderValue}
                </td>

            </tr>

        </table>

    </div>

</c:if>


<!-- =========================================================
     PRODUCT SALES REPORT
     ========================================================= -->

<c:if test="${not empty productReports}">

    <div class="card">

        <h2>Product Sales</h2>

        <table>

            <thead>

            <tr>

                <th>Product</th>
                <th>Category</th>
                <th>Seller</th>
                <th>Price</th>
                <th>Quantity Sold</th>
                <th>Revenue</th>
                <th>Rating</th>
                <th>Reviews</th>

            </tr>

            </thead>


            <tbody>

            <c:forEach
                    var="product"
                    items="${productReports}">

                <tr>

                    <td>
                        ${product.productName}
                    </td>

                    <td>
                        ${product.categoryName}
                    </td>

                    <td>
                        ${product.sellerName}
                    </td>

                    <td>
                        ₹${product.productPrice}
                    </td>

                    <td>
                        ${product.quantitySold}
                    </td>

                    <td>
                        ₹${product.revenue}
                    </td>

                    <td>
                        ${product.averageRating}
                    </td>

                    <td>
                        ${product.reviewCount}
                    </td>

                </tr>

            </c:forEach>

            </tbody>

        </table>

    </div>

</c:if>


<!-- =========================================================
     CATEGORY SALES REPORT
     ========================================================= -->

<c:if test="${not empty categoryReports}">

    <div class="card">

        <h2>Category Sales</h2>

        <table>

            <thead>

            <tr>

                <th>Category</th>
                <th>Products</th>
                <th>Quantity Sold</th>
                <th>Revenue</th>

            </tr>

            </thead>


            <tbody>

            <c:forEach
                    var="category"
                    items="${categoryReports}">

                <tr>

                    <td>
                        ${category.categoryName}
                    </td>

                    <td>
                        ${category.productCount}
                    </td>

                    <td>
                        ${category.quantitySold}
                    </td>

                    <td>
                        ₹${category.revenue}
                    </td>

                </tr>

            </c:forEach>

            </tbody>

        </table>

    </div>

</c:if>


<!-- =========================================================
     SELLER PERFORMANCE
     ========================================================= -->

<c:if test="${not empty sellerReports}">

    <div class="card">

        <h2>Seller Performance</h2>

        <table>

            <thead>

            <tr>

                <th>Seller</th>
                <th>Shop</th>
                <th>Orders</th>
                <th>Quantity Sold</th>
                <th>Revenue</th>

            </tr>

            </thead>


            <tbody>

            <c:forEach
                    var="seller"
                    items="${sellerReports}">

                <tr>

                    <td>
                        ${seller.sellerName}
                    </td>

                    <td>
                        ${seller.shopName}
                    </td>

                    <td>
                        ${seller.orderCount}
                    </td>

                    <td>
                        ${seller.quantitySold}
                    </td>

                    <td>
                        ₹${seller.revenue}
                    </td>

                </tr>

            </c:forEach>

            </tbody>

        </table>

    </div>

</c:if>


<!-- =========================================================
     JAVASCRIPT
     ========================================================= -->

<script>

    function changeReportType() {

        var reportType =
            document.getElementById("reportType").value;

        var sellerFilter =
            document.getElementById("sellerFilter");

        var sellerIdFilter =
            document.getElementById("sellerIdFilter");


        /*
         * Seller Name and Seller ID are only relevant
         * for Seller Sales report.
         */

        if (reportType === "seller") {

            sellerFilter.style.display = "block";

            sellerIdFilter.style.display = "block";

        } else {

            sellerFilter.style.display = "none";

            sellerIdFilter.style.display = "none";

        }

    }


    /*
     * Seller selected from dropdown.
     */
    function sellerSelected() {

        var sellerSelect =
            document.getElementById("sellerSelect");

        var manualSellerId =
            document.getElementById("manualSellerId");


        if (sellerSelect.value !== "") {

            manualSellerId.value = "";

        }

    }


    /*
     * Seller ID entered manually.
     */
    function manualSellerIdEntered() {

        var manualSellerId =
            document.getElementById("manualSellerId");

        var sellerSelect =
            document.getElementById("sellerSelect");


        if (manualSellerId.value.trim() !== "") {

            sellerSelect.value = "";

        }

    }


    /*
     * Validate Seller Sales.
     *
     * Either Seller Name OR Seller ID must be provided.
     */
    document.getElementById("reportForm")
        .addEventListener("submit", function(event) {

            var reportType =
                document.getElementById("reportType").value;


            if (reportType === "seller") {

                var sellerSelect =
                    document.getElementById("sellerSelect").value;

                var manualSellerId =
                    document.getElementById("manualSellerId").value.trim();


                if (
                    sellerSelect === "" &&
                    manualSellerId === ""
                ) {

                    alert(
                        "Please select a Seller Name or enter a Seller ID."
                    );

                    event.preventDefault();

                    return false;
                }


                /*
                 * If Seller Name is selected,
                 * put its ID into the actual sellerId field.
                 *
                 * If Seller ID is manually entered,
                 * put that value into sellerId.
                 */

                var sellerIdField =
                    document.getElementById("sellerIdHidden");


                if (sellerSelect !== "") {

                    sellerIdField.value =
                        sellerSelect;

                } else {

                    sellerIdField.value =
                        manualSellerId;

                }

            }

        });


    /*
     * Load correct filter visibility when page opens.
     */
    document.addEventListener(
        "DOMContentLoaded",
        function() {

            changeReportType();

        }
    );

</script>


<!-- =========================================================
     HIDDEN SELLER ID
     ========================================================= -->

<input
        type="hidden"
        id="sellerIdHidden"
        name="sellerId"
        value="${filter.sellerId}"/>


<%@ include file="../common/footer.jsp" %>