<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Pending Approval Orders"/>

<%@ include file="../common/header.jsp" %>


<div class="card">

    <h1>Pending Approval Orders</h1>


    <table>

        <thead>

        <tr>

            <th>Order ID</th>
            <th>Customer</th>
            <th>Product</th>
            <th>Quantity</th>
            <th>Total</th>
            <th>Status</th>
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

                <td>

                    <a
                            class="btn edit-btn"
                            href="${pageContext.request.contextPath}/orders/seller/${sellerId}/${order.orderId}">

                        Review

                    </a>

                </td>

            </tr>

        </c:forEach>

        </tbody>

    </table>


    <c:if test="${empty orders}">

        <div class="empty-message">
            No pending approval orders.
        </div>

    </c:if>

</div>


<%@ include file="../common/footer.jsp" %>