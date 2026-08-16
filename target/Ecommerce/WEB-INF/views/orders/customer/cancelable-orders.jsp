<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Cancelable Orders"/>

<%@ include file="../common/header.jsp" %>


<div class="card">

    <h1>Cancelable Orders</h1>


    <table>

        <thead>

        <tr>

            <th>Order ID</th>
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

                <td>${order.product.productName}</td>

                <td>${order.quantity}</td>

                <td>₹${order.totalPrice}</td>

                <td>${order.orderStatus}</td>

                <td>

                    <form
                            action="${pageContext.request.contextPath}/orders/customer/${customerId}/cancel/${order.orderId}"
                            method="post"
                            onsubmit="return confirm('Cancel this order?');">

                        <button
                                type="submit"
                                class="btn delete-btn">

                            Cancel

                        </button>

                    </form>

                </td>

            </tr>

        </c:forEach>

        </tbody>

    </table>


    <c:if test="${empty orders}">

        <div class="empty-message">
            No cancelable orders.
        </div>

    </c:if>

</div>


<%@ include file="../common/footer.jsp" %>