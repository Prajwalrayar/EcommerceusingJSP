<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Admin Reports"/>

<%@ include file="../common/header.jsp" %>


<!-- =====================================================
     ADMIN REPORT FILTERS
     ===================================================== -->

<div class="card">

    <h1>Admin Reports</h1>

    <h2>Report Filters</h2>


    <form method="get"
          action="${pageContext.request.contextPath}/reports/admin">


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


        <!-- PRODUCT ID -->

        <div class="form-group">

            <label>Product ID</label>

            <input
                    type="text"
                    name="productId"
                    value="${filter.productId}"
                    placeholder="Product ID"/>

        </div>


        <!-- PRODUCT NAME -->

        <div class="form-group">

            <label>Product Name</label>

            <input
                    type="text"
                    name="productName"
                    value="${filter.productName}"
                    placeholder="Search product"/>

        </div>


        <!-- MINIMUM PRICE -->

        <div class="form-group">

            <label>Minimum Price</label>

            <input
                    type="number"
                    step="0.01"
                    min="0"
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
                    min="0"
                    name="maxPrice"
                    value="${filter.maxPrice}"
                    placeholder="Maximum price"/>

        </div>


        <!-- MINIMUM QUANTITY -->

        <div class="form-group">

            <label>Minimum Quantity Sold</label>

            <input
                    type="number"
                    min="0"
                    name="minQuantity"
                    value="${filter.minQuantity}"
                    placeholder="Minimum quantity"/>

        </div>


        <!-- MAXIMUM QUANTITY -->

        <div class="form-group">

            <label>Maximum Quantity Sold</label>

            <input
                    type="number"
                    min="0"
                    name="maxQuantity"
                    value="${filter.maxQuantity}"
                    placeholder="Maximum quantity"/>

        </div>


        <!-- ORDER STATUS -->

        <div class="form-group">

            <label>Order Status</label>

            <select name="orderStatus">

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


        <!-- PAYMENT STATUS -->

        <div class="form-group">

            <label>Payment Status</label>

            <select name="paymentStatus">

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


        <!-- APPLY -->

        <button
                type="submit"
                class="btn edit-btn">

            Apply Filters

        </button>


        <!-- CLEAR -->

        <a
                href="${pageContext.request.contextPath}/reports/admin"
                class="btn">

            Clear

        </a>

    </form>

</div>


<!-- =====================================================
     SALES SUMMARY
     ===================================================== -->

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


<!-- =====================================================
     PRODUCT SALES REPORT
     ===================================================== -->

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


    <c:if test="${empty productReports}">

        <p class="empty-message">

            No product sales found for the selected filters.

        </p>

    </c:if>

</div>


<!-- =====================================================
     CATEGORY SALES REPORT
     ===================================================== -->

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


    <c:if test="${empty categoryReports}">

        <p class="empty-message">

            No category sales found for the selected filters.

        </p>

    </c:if>

</div>


<!-- =====================================================
     SELLER PERFORMANCE
     ===================================================== -->

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


    <c:if test="${empty sellerReports}">

        <p class="empty-message">

            No seller performance data found for the selected filters.

        </p>

    </c:if>

</div>


<%@ include file="../common/footer.jsp" %>