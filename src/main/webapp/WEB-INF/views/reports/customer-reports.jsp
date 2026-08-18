<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="My Reports"/>

<%@ include file="../common/header.jsp" %>


<!-- =========================================================
     CUSTOMER REPORT HEADER
     ========================================================= -->

<div class="card">

    <h1>My Purchase Reports</h1>

    <p>
        View your purchase history, spending summary and
        purchased products.
    </p>

</div>


<!-- =========================================================
     FILTERS
     ========================================================= -->

<div class="card">

    <h2>Report Filters</h2>


    <form
            method="get"
            action="${pageContext.request.contextPath}/reports/customer/${customerId}">


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
             PRODUCT
             ================================================= -->

        <div class="form-group">

            <label for="productName">
                Product
            </label>

            <input
                    type="text"
                    id="productName"
                    name="productName"
                    value="${filter.productName}"
                    placeholder="Enter product name"/>

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
                    name="minPrice"
                    step="0.01"
                    min="0"
                    value="${filter.minPrice}"
                    placeholder="Minimum price"/>

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
                    name="maxPrice"
                    step="0.01"
                    min="0"
                    value="${filter.maxPrice}"
                    placeholder="Maximum price"/>

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

        <div class="form-actions">

            <button
                    type="submit"
                    class="btn edit-btn">

                Apply Filters

            </button>


            <a
                    href="${pageContext.request.contextPath}/reports/customer/${customerId}"
                    class="btn">

                Clear

            </a>

        </div>

    </form>

</div>


<!-- =========================================================
     CUSTOMER SUMMARY
     ========================================================= -->

<c:if test="${not empty customerReport}">

    <div class="card">

        <h2>Purchase Summary</h2>


        <table>

            <thead>

            <tr>

                <th>Metric</th>
                <th>Value</th>

            </tr>

            </thead>


            <tbody>

            <!-- CUSTOMER -->

            <tr>

                <td>
                    Customer
                </td>

                <td>
                    ${customerReport.customerName}
                </td>

            </tr>


            <!-- TOTAL ORDERS -->

            <tr>

                <td>
                    Total Orders
                </td>

                <td>
                    ${customerReport.orderCount}
                </td>

            </tr>


            <!-- PRODUCTS PURCHASED -->

            <tr>

                <td>
                    Products Purchased
                </td>

                <td>
                    ${customerReport.quantityPurchased}
                </td>

            </tr>


            <!-- TOTAL SPENT -->

            <tr>

                <td>
                    Total Spent
                </td>

                <td>
                    ₹${customerReport.totalSpent}
                </td>

            </tr>


            <!-- AVERAGE ORDER VALUE -->

            <tr>

                <td>
                    Average Order Value
                </td>

                <td>
                    ₹${customerReport.averageOrderValue}
                </td>

            </tr>

            </tbody>

        </table>

    </div>

</c:if>


<!-- =========================================================
     PURCHASED PRODUCTS
     ========================================================= -->

<div class="card">

    <h2>Purchased Products</h2>


    <c:choose>

        <c:when test="${not empty productReports}">

            <div class="table-container">

                <table>

                    <thead>

                    <tr>

                        <th>Product</th>
                        <th>Category</th>
                        <th>Seller</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Amount</th>

                    </tr>

                    </thead>


                    <tbody>

                    <c:forEach
                            var="product"
                            items="${productReports}">

                        <tr>

                            <!-- PRODUCT -->

                            <td>
                                ${product.productName}
                            </td>


                            <!-- CATEGORY -->

                            <td>
                                ${product.categoryName}
                            </td>


                            <!-- SELLER -->

                            <td>
                                ${product.sellerName}
                            </td>


                            <!-- PRICE -->

                            <td>
                                ₹${product.productPrice}
                            </td>


                            <!-- QUANTITY -->

                            <td>
                                ${product.quantitySold}
                            </td>


                            <!-- AMOUNT -->

                            <td>
                                ₹${product.revenue}
                            </td>

                        </tr>

                    </c:forEach>

                    </tbody>

                </table>

            </div>

        </c:when>


        <c:otherwise>

            <p class="empty-message">

                No purchased products found for the selected filters.

            </p>

        </c:otherwise>

    </c:choose>

</div>


<!-- =========================================================
     FOOTER
     ========================================================= -->

<%@ include file="../common/footer.jsp" %>