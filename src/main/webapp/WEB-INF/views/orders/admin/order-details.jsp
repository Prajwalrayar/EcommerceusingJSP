<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="pageTitle" value="Order Details"/>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../../common/header.jsp" %>


<div class="card">

    <h1>Order Details</h1>


    <table>

        <tr>
            <th>Order ID</th>
            <td>${order.orderId}</td>
        </tr>

        <tr>
            <th>Customer ID</th>
            <td>${order.customer.userId}</td>
        </tr>

        <tr>
            <th>Customer Name</th>
            <td>${order.customer.userName}</td>
        </tr>

        <tr>
            <th>Customer Email</th>
            <td>${order.customer.userEmail}</td>
        </tr>

        <tr>
            <th>Product ID</th>
            <td>${order.product.productId}</td>
        </tr>

        <tr>
            <th>Product Name</th>
            <td>${order.product.productName}</td>
        </tr>

        <tr>
            <th>Quantity</th>
            <td>${order.quantity}</td>
        </tr>

        <tr>
            <th>Total Price</th>
            <td>₹${order.totalPrice}</td>
        </tr>

        <tr>
            <th>Status</th>
            <td>${order.orderStatus}</td>
        </tr>

        <tr>
            <th>Order Date</th>
            <td>${order.orderDate}</td>
        </tr>

        <tr>
            <th>Delivered Date</th>
            <td>${order.deliveredDate}</td>
        </tr>

    </table>


    <br/>


    <a
            class="btn edit-btn"
            href="${pageContext.request.contextPath}/orders/admin">

        Back to Orders

    </a>

</div>


<%@ include file="../../common/footer.jsp" %>