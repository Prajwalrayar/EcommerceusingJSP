<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Reports & Analytics"/>

<%@ include file="../common/header.jsp" %>


<div class="reports-container">


    <!-- ===================================================== -->
    <!-- HEADER -->
    <!-- ===================================================== -->

    <div class="reports-header">

        <div>

            <h1>
                Reports & Analytics
            </h1>

            <p>
                Analyse marketplace sales, products,
                categories, sellers and customers.
            </p>

        </div>


        <a href="${pageContext.request.contextPath}/admin/dashboard"
           class="back-btn">

            ← Dashboard

        </a>

    </div>



    <!-- ===================================================== -->
    <!-- ERROR -->
    <!-- ===================================================== -->

    <c:if test="${not empty error}">

        <div class="alert alert-danger">

            ${error}

        </div>

    </c:if>



    <!-- ===================================================== -->
    <!-- REPORT SELECTION -->
    <!-- ===================================================== -->

    <div class="report-selector card">


        <h2>
            Select Report
        </h2>


        <form method="post"
              action="${pageContext.request.contextPath}/admin/reports/generate"
              id="reportForm">


            <div class="form-group">

                <label for="reportType">
                    Report Type
                </label>


                <select id="reportType"
                        name="reportType"
                        required>

                    <option value="">
                        -- Select Report --
                    </option>


                    <option value="sales"
                        ${reportType == 'sales' ? 'selected' : ''}>

                        Sales Summary

                    </option>


                    <option value="products"
                        ${reportType == 'products' ? 'selected' : ''}>

                        Product Sales

                    </option>


                    <option value="categories"
                        ${reportType == 'categories' ? 'selected' : ''}>

                        Category Sales

                    </option>


                    <option value="sellers"
                        ${reportType == 'sellers' ? 'selected' : ''}>

                        Seller Sales

                    </option>


                    <option value="customers"
                        ${reportType == 'customers' ? 'selected' : ''}>

                        Customer Report

                    </option>


                    <option value="customerProducts"
                        ${reportType == 'customerProducts' ? 'selected' : ''}>

                        Customer Product Sales

                    </option>

                </select>

            </div>



            <!-- ================================================= -->
            <!-- FILTERS -->
            <!-- ================================================= -->

            <div class="filters-section">


                <h2>
                    Filters
                </h2>


                <div class="filter-grid">


                    <!-- CUSTOMER ID -->

                    <div class="form-group">

                        <label>
                            Customer ID
                        </label>

                        <input type="text"
                               name="customerId"
                               value="${filter.customerId}"
                               placeholder="Example: CUS1001">

                    </div>



                    <!-- SELLER ID -->

                    <div class="form-group">

                        <label>
                            Seller ID
                        </label>

                        <input type="text"
                               name="sellerId"
                               value="${filter.sellerId}"
                               placeholder="Example: SEL1001">

                    </div>



                    <!-- CATEGORY ID -->

                    <div class="form-group">

                        <label>
                            Category ID
                        </label>

                        <input type="text"
                               name="categoryId"
                               value="${filter.categoryId}"
                               placeholder="Category ID">

                    </div>



                    <!-- PRODUCT ID -->

                    <div class="form-group">

                        <label>
                            Product ID
                        </label>

                        <input type="text"
                               name="productId"
                               value="${filter.productId}"
                               placeholder="Product ID">

                    </div>



                    <!-- PRODUCT NAME -->

                    <div class="form-group">

                        <label>
                            Product Name
                        </label>

                        <input type="text"
                               name="productName"
                               value="${filter.productName}"
                               placeholder="Product name">

                    </div>



                    <!-- MINIMUM PRICE -->

                    <div class="form-group">

                        <label>
                            Minimum Price
                        </label>

                        <input type="number"
                               step="0.01"
                               name="minPrice"
                               value="${filter.minPrice}"
                               placeholder="0.00">

                    </div>



                    <!-- MAXIMUM PRICE -->

                    <div class="form-group">

                        <label>
                            Maximum Price
                        </label>

                        <input type="number"
                               step="0.01"
                               name="maxPrice"
                               value="${filter.maxPrice}"
                               placeholder="0.00">

                    </div>



                    <!-- MINIMUM QUANTITY -->

                    <div class="form-group">

                        <label>
                            Minimum Quantity
                        </label>

                        <input type="number"
                               name="minQuantity"
                               value="${filter.minQuantity}"
                               placeholder="0">

                    </div>



                    <!-- MAXIMUM QUANTITY -->

                    <div class="form-group">

                        <label>
                            Maximum Quantity
                        </label>

                        <input type="number"
                               name="maxQuantity"
                               value="${filter.maxQuantity}"
                               placeholder="0">

                    </div>



                    <!-- ORDER STATUS -->

                    <div class="form-group">

                        <label>
                            Order Status
                        </label>

                        <input type="text"
                               name="orderStatus"
                               value="${filter.orderStatus}"
                               placeholder="Example: COMPLETED">

                    </div>



                    <!-- PAYMENT STATUS -->

                    <div class="form-group">

                        <label>
                            Payment Status
                        </label>

                        <input type="text"
                               name="paymentStatus"
                               value="${filter.paymentStatus}"
                               placeholder="Example: SUCCESS">

                    </div>



                    <!-- FROM DATE -->

                    <div class="form-group">

                        <label>
                            From Date
                        </label>

                        <input type="datetime-local"
                               name="fromDate"
                               value="${filter.fromDate}">

                    </div>



                    <!-- TO DATE -->

                    <div class="form-group">

                        <label>
                            To Date
                        </label>

                        <input type="datetime-local"
                               name="toDate"
                               value="${filter.toDate}">

                    </div>


                </div>


                <!-- ================================================= -->
                <!-- ACTIONS -->
                <!-- ================================================= -->

                <div class="report-actions">

                    <button type="submit"
                            class="generate-btn">

                        Generate Report

                    </button>


                    <a href="${pageContext.request.contextPath}/admin/reports"
                       class="clear-btn">

                        Clear

                    </a>

                </div>


            </div>

        </form>

    </div>



    <!-- ===================================================== -->
    <!-- SALES REPORT -->
    <!-- ===================================================== -->

    <c:if test="${reportType == 'sales'}">

        <div class="result-card">

            <h2>
                Sales Summary
            </h2>


            <div class="result-placeholder">

                <p>
                    Sales report generated successfully.
                </p>

                <p>
                    Your <strong>SalesReport</strong> object
                    has been returned by ReportService.
                </p>

            </div>

        </div>

    </c:if>



    <!-- ===================================================== -->
    <!-- PRODUCT REPORT -->
    <!-- ===================================================== -->

    <c:if test="${reportType == 'products'}">

        <div class="result-card">

            <h2>
                Product Sales Report
            </h2>


            <c:choose>

                <c:when test="${not empty productReport}">

                    <table class="report-table">

                        <thead>

                            <tr>

                                <th>
                                    Product
                                </th>

                                <th>
                                    Sales Data
                                </th>

                            </tr>

                        </thead>


                        <tbody>

                            <c:forEach
                                    var="report"
                                    items="${productReport}">

                                <tr>

                                    <td>
                                        ${report.productName}
                                    </td>

                                    <td>
                                        ${report}
                                    </td>

                                </tr>

                            </c:forEach>

                        </tbody>

                    </table>

                </c:when>


                <c:otherwise>

                    <div class="no-results">

                        No product sales data found.

                    </div>

                </c:otherwise>

            </c:choose>

        </div>

    </c:if>



    <!-- ===================================================== -->
    <!-- CATEGORY REPORT -->
    <!-- ===================================================== -->

    <c:if test="${reportType == 'categories'}">

        <div class="result-card">

            <h2>
                Category Sales Report
            </h2>


            <c:choose>

                <c:when test="${not empty categoryReport}">

                    <table class="report-table">

                        <thead>

                            <tr>

                                <th>
                                    Category
                                </th>

                                <th>
                                    Report
                                </th>

                            </tr>

                        </thead>


                        <tbody>

                            <c:forEach
                                    var="report"
                                    items="${categoryReport}">

                                <tr>

                                    <td>
                                        ${report.categoryName}
                                    </td>

                                    <td>
                                        ${report}
                                    </td>

                                </tr>

                            </c:forEach>

                        </tbody>

                    </table>

                </c:when>


                <c:otherwise>

                    <div class="no-results">

                        No category sales data found.

                    </div>

                </c:otherwise>

            </c:choose>

        </div>

    </c:if>



    <!-- ===================================================== -->
    <!-- SELLER REPORT -->
    <!-- ===================================================== -->

    <c:if test="${reportType == 'sellers'}">

        <div class="result-card">

            <h2>
                Seller Sales Report
            </h2>


            <c:choose>

                <c:when test="${not empty sellerReport}">

                    <table class="report-table">

                        <thead>

                            <tr>

                                <th>
                                    Seller
                                </th>

                                <th>
                                    Report
                                </th>

                            </tr>

                        </thead>


                        <tbody>

                            <c:forEach
                                    var="report"
                                    items="${sellerReport}">

                                <tr>

                                    <td>
                                        ${report.sellerId}
                                    </td>

                                    <td>
                                        ${report}
                                    </td>

                                </tr>

                            </c:forEach>

                        </tbody>

                    </table>

                </c:when>


                <c:otherwise>

                    <div class="no-results">

                        No seller sales data found.

                    </div>

                </c:otherwise>

            </c:choose>

        </div>

    </c:if>



    <!-- ===================================================== -->
    <!-- CUSTOMER REPORT -->
    <!-- ===================================================== -->

    <c:if test="${reportType == 'customers'}">

        <div class="result-card">

            <h2>
                Customer Report
            </h2>


            <c:choose>

                <c:when test="${not empty customerReport}">

                    <div class="result-placeholder">

                        Customer report generated successfully.

                    </div>

                </c:when>


                <c:otherwise>

                    <div class="no-results">

                        No customer data found.

                    </div>

                </c:otherwise>

            </c:choose>

        </div>

    </c:if>



    <!-- ===================================================== -->
    <!-- CUSTOMER PRODUCT REPORT -->
    <!-- ===================================================== -->

    <c:if test="${reportType == 'customerProducts'}">

        <div class="result-card">

            <h2>
                Customer Product Report
            </h2>


            <c:choose>

                <c:when test="${not empty customerProductReport}">

                    <table class="report-table">

                        <thead>

                            <tr>

                                <th>
                                    Product
                                </th>

                                <th>
                                    Report
                                </th>

                            </tr>

                        </thead>


                        <tbody>

                            <c:forEach
                                    var="report"
                                    items="${customerProductReport}">

                                <tr>

                                    <td>
                                        ${report.productName}
                                    </td>

                                    <td>
                                        ${report}
                                    </td>

                                </tr>

                            </c:forEach>

                        </tbody>

                    </table>

                </c:when>


                <c:otherwise>

                    <div class="no-results">

                        No customer product data found.

                    </div>

                </c:otherwise>

            </c:choose>

        </div>

    </c:if>


</div>



<style>


/* ========================================================= */
/* CONTAINER */
/* ========================================================= */

.reports-container {

    max-width: 1250px;

    margin: 35px auto;

    padding: 0 30px;

}



/* ========================================================= */
/* HEADER */
/* ========================================================= */

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

}



/* ========================================================= */
/* CARD */
/* ========================================================= */

.card,
.result-card {

    background: white;

    border: 1px solid #e1e5e9;

    border-radius: 10px;

    padding: 30px;

    margin-bottom: 30px;

    box-shadow:
        0 3px 10px rgba(0,0,0,0.05);

}


.card h2,
.result-card h2 {

    margin-top: 0;

}



/* ========================================================= */
/* FORM */
/* ========================================================= */

.form-group {

    margin-bottom: 20px;

}


.form-group label {

    display: block;

    margin-bottom: 7px;

    font-weight: 600;

}


.form-group input,
.form-group select {

    width: 100%;

    box-sizing: border-box;

    padding: 12px 13px;

    border: 1px solid #ced4da;

    border-radius: 6px;

    font-size: 15px;

}


.form-group input:focus,
.form-group select:focus {

    outline: none;

    border-color: #0d6efd;

    box-shadow:
        0 0 0 3px rgba(13,110,253,0.12);

}



/* ========================================================= */
/* FILTERS */
/* ========================================================= */

.filters-section {

    margin-top: 30px;

    padding-top: 25px;

    border-top: 1px solid #dee2e6;

}


.filter-grid {

    display: grid;

    grid-template-columns:
        repeat(3, 1fr);

    column-gap: 25px;

}



/* ========================================================= */
/* BUTTONS */
/* ========================================================= */

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



/* ========================================================= */
/* REPORT TABLE */
/* ========================================================= */

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

}


.report-table td {

    padding: 13px;

    border-bottom: 1px solid #dee2e6;

}


.report-table tr:hover {

    background-color: #f8f9fa;

}



/* ========================================================= */
/* NO RESULTS */
/* ========================================================= */

.no-results {

    padding: 25px;

    text-align: center;

    background-color: #f8f9fa;

    color: #6c757d;

    border-radius: 6px;

}


.result-placeholder {

    padding: 25px;

    background-color: #f8f9fa;

    border-radius: 6px;

}



/* ========================================================= */
/* ERROR */
/* ========================================================= */

.alert-danger {

    padding: 14px 18px;

    margin-bottom: 25px;

    background-color: #f8d7da;

    color: #842029;

    border: 1px solid #f5c2c7;

    border-radius: 6px;

}



/* ========================================================= */
/* RESPONSIVE */
/* ========================================================= */

@media (max-width: 900px) {

    .filter-grid {

        grid-template-columns:
            repeat(2, 1fr);

    }

}


@media (max-width: 600px) {

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

}

</style>


<%@ include file="../common/footer.jsp" %>