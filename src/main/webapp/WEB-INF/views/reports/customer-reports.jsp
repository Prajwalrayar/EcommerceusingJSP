<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="My Reports"/>

<%@ include file="../common/header.jsp" %>


<!-- =====================================================
     FILTERS
     ===================================================== -->

<div class="card">

    <h1>My Purchase Reports</h1>


    <form method="get"
          action="${pageContext.request.contextPath}/reports/customer/${customerId}">


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
                    value="${filter.minPrice}"/>

        </div>


        <!-- MAXIMUM PRICE -->

        <div class="form-group">

            <label>Maximum Price</label>

            <input
                    type="number"
                    step="0.01"
                    name="maxPrice"
                    value="${filter.maxPrice}"/>

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

    </form>

</div>


<!-- =====================================================
     CUSTOMER SUMMARY
     ===================================================== -->

<div class="card">

    <h2>Purchase Summary</h2>

    <table>

        <tr>

            <th>Customer</th>

            <td>
                ${customerReport.customerName}
            </td>

        </tr>


        <tr>

            <th>Total Orders</th>

            <td>
                ${customerReport.orderCount}
            </td>

        </tr>


        <tr>

            <th>Products Purchased</th>

            <td>
                ${customerReport.quantityPurchased}
            </td>

        </tr>


        <tr>

            <th>Total Spent</th>

            <td>
                ₹${customerReport.totalSpent}
            </td>

        </tr>


        <tr>

            <th>Average Order Value</th>

            <td>
                ₹${customerReport.averageOrderValue}
            </td>

        </tr>

    </table>

</div>


<!-- =====================================================
     PURCHASED PRODUCTS
     ===================================================== -->

<div class="card">

    <h2>Purchased Products</h2>

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

            </tr>

        </c:forEach>

        </tbody>

    </table>


    <c:if test="${empty productReports}">

        <p class="empty-message">
            No purchased products found for the selected filters.
        </p>

    </c:if>

</div>


<%@ include file="../common/footer.jsp" %>