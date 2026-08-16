<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Seller Reports"/>

<%@ include file="../common/header.jsp" %>


<!-- =====================================================
     SELLER REPORT FILTERS
     ===================================================== -->

<div class="card">

    <h1>My Seller Reports</h1>


    <form method="get"
          action="${pageContext.request.contextPath}/reports/seller/${sellerId}">


        <!-- CATEGORY -->

        <div class="form-group">

            <label>Category</label>

            <select name="categoryId">

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


        <!-- PRODUCT -->

        <div class="form-group">

            <label>Product</label>

            <input
                    type="text"
                    name="productName"
                    value="${filter.productName}"
                    placeholder="Product name"/>

        </div>


        <!-- MINIMUM PRICE -->

        <div class="form-group">

            <label>Minimum Price</label>

            <input
                    type="number"
                    step="0.01"
                    name="minPrice"
                    value="${filter.minPrice}"
                    placeholder="Minimum price"/>

        </div>


        <!-- MAXIMUM PRICE -->

        <div class="form-group">

            <label>Maximum Price</label>

            <input
                    type="number"
                    step="0.01"
                    name="maxPrice"
                    value="${filter.maxPrice}"
                    placeholder="Maximum price"/>

        </div>


        <!-- FROM DATE -->

        <div class="form-group">

            <label>From Date</label>

            <input
                    type="datetime-local"
                    name="fromDate"
                    value="${filter.fromDate}"/>

        </div>


        <!-- TO DATE -->

        <div class="form-group">

            <label>To Date</label>

            <input
                    type="datetime-local"
                    name="toDate"
                    value="${filter.toDate}"/>

        </div>


        <!-- APPLY FILTER -->

        <button
                type="submit"
                class="btn edit-btn">

            Apply Filters

        </button>

    </form>

</div>


<!-- =====================================================
     SALES SUMMARY
     ===================================================== -->

<div class="card">

    <h2>My Sales</h2>

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

            <th>Total Revenue</th>

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


<!-- =====================================================
     SOLD PRODUCTS
     ===================================================== -->

<div class="card">

    <h2>My Sold Products</h2>

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
                    ${product.averageRating}
                </td>

            </tr>

        </c:forEach>

        </tbody>

    </table>


    <c:if test="${empty productReports}">

        <p class="empty-message">

            No sold products found for the selected filters.

        </p>

    </c:if>

</div>


<!-- =====================================================
     CATEGORY PERFORMANCE
     ===================================================== -->

<div class="card">

    <h2>Category Performance</h2>

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


    <c:if test="${empty categoryReports}">

        <p class="empty-message">

            No category performance data found for the selected filters.

        </p>

    </c:if>

</div>


<%@ include file="../common/footer.jsp" %>