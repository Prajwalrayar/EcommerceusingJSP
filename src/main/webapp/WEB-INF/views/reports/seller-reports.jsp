<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Seller Reports"/>

<%@ include file="../common/header.jsp" %>


<!-- =========================================================
     SELLER REPORT HEADER
     ========================================================= -->

<div class="card">

    <h1>My Seller Reports</h1>

    <p>
        View your sales performance, sold products and
        category performance.
    </p>
    
    <a href="${pageContext.request.contextPath}/seller/dashboard"
   class="btn btn-primary">
    Dashboard
</a>

</div>


<!-- =========================================================
     SELLER REPORT FILTERS
     ========================================================= -->

<div class="card">

    <h2>Report Filters</h2>


    <form
            method="get"
            action="${pageContext.request.contextPath}/reports/seller/${sellerId}">


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
                    step="0.01"
                    min="0"
                    name="minPrice"
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
                    step="0.01"
                    min="0"
                    name="maxPrice"
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

            <button type="submit" class="btn btn-primary">
		        Apply Filters
		    </button>


            <a
                    href="${pageContext.request.contextPath}/reports/seller/${sellerId}"
                    class="btn">

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

        <h2>My Sales</h2>


        <table>

            <thead>

            <tr>

                <th>Metric</th>
                <th>Value</th>

            </tr>

            </thead>


            <tbody>

            <tr>

                <td>
                    Total Orders
                </td>

                <td>
                    ${salesReport.totalOrders}
                </td>

            </tr>


            <tr>

                <td>
                    Total Quantity Sold
                </td>

                <td>
                    ${salesReport.totalQuantity}
                </td>

            </tr>


            <tr>

                <td>
                    Total Revenue
                </td>

                <td>
                    ₹${salesReport.totalSales}
                </td>

            </tr>


            <tr>

                <td>
                    Average Order Value
                </td>

                <td>
                    ₹${salesReport.averageOrderValue}
                </td>

            </tr>

            </tbody>

        </table>

    </div>

</c:if>


<!-- =========================================================
     SOLD PRODUCTS
     ========================================================= -->

<div class="card">

    <h2>My Sold Products</h2>


    <c:choose>

        <c:when test="${not empty productReports}">

            <div class="table-container">

                <table>

                    <thead>

                    <tr>

                        <th>Product</th>
                        <th>Category</th>
                        <th>Price</th>
                        <th>Quantity Sold</th>
                        <th>Revenue</th>
                        <th>Rating</th>

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
                                ₹${product.productPrice}
                            </td>

                            <td>
                                ${product.quantitySold}
                            </td>

                            <td>
                                ₹${product.revenue}
                            </td>

                            <td>

                                <c:choose>

                                    <c:when test="${not empty product.averageRating}">
                                        ${product.averageRating}
                                    </c:when>

                                    <c:otherwise>
                                        N/A
                                    </c:otherwise>

                                </c:choose>

                            </td>

                        </tr>

                    </c:forEach>

                    </tbody>

                </table>

            </div>

        </c:when>


        <c:otherwise>

            <p class="empty-message">

                No sold products found for the selected filters.

            </p>

        </c:otherwise>

    </c:choose>

</div>


<!-- =========================================================
     CATEGORY PERFORMANCE
     ========================================================= -->

<div class="card">

    <h2>Category Performance</h2>


    <c:choose>

        <c:when test="${not empty categoryReports}">

            <div class="table-container">

                <table>

                    <thead>

                    <tr>

                        <th>Category</th>
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

        </c:when>


        <c:otherwise>

            <p class="empty-message">

                No category performance data found
                for the selected filters.

            </p>

        </c:otherwise>

    </c:choose>

</div>


<!-- =========================================================
     FOOTER
     ========================================================= -->

<%@ include file="../common/footer.jsp" %>