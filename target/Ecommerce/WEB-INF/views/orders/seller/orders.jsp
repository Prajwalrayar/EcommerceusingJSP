<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Seller Orders"/>

<%@ include file="../common/header.jsp" %>


<div class="card">

    <h1>Seller Orders</h1>


    <div class="nav">

        <a href="${pageContext.request.contextPath}/orders/seller/${sellerId}">
            All Orders
        </a>

        <a href="${pageContext.request.contextPath}/orders/seller/${sellerId}/pending">
            Pending Approval
        </a>

    </div>


    <!-- Search -->

    <form
            action="${pageContext.request.contextPath}/orders/seller/${sellerId}/search"
            method="get">

        <input
                type="text"
                name="productName"
                value="${productName}"
                placeholder="Search product..."/>

        <button
                type="submit"
                class="btn edit-btn">

            Search

        </button>

    </form>

    <br/>


    <table>

        <thead>

        <tr>

            <th>Order ID</th>
            <th>Customer</th>
            <th>Product</th>
            <th>Quantity</th>
            <th>Total</th>
            <th>Status</th>
            <th>Order Date</th>
            <th>Action</th>

        </tr>

        </thead>


        <tbody>

        <c:forEach
                var="order"
                items="${orders}">

            <tr>

                <td>${order.orderId}</td>

                <td>${order.customer.userName}</td>

                <td>${order.product.productName}</td>

                <td>${order.quantity}</td>

                <td>₹${order.totalPrice}</td>

                <td>${order.orderStatus}</td>

                <td>${order.orderDate}</td>

                <td>

                    <a
                            class="btn edit-btn"
                            href="${pageContext.request.contextPath}/orders/seller/${sellerId}/${order.orderId}">

                        View

                    </a>

                </td>

            </tr>

        </c:forEach>

        </tbody>

    </table>


    <c:if test="${empty orders}">

        <div class="empty-message">
            No orders found.
        </div>

    </c:if>

</div>


<%@ include file="../common/footer.jsp" %>