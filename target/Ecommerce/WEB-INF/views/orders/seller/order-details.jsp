<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Order Details"/>

<%@ include file="../common/header.jsp" %>


<div class="card">

    <h1>Order Details</h1>


    <table>

        <tr>
            <th>Order ID</th>
            <td>${order.orderId}</td>
        </tr>

        <tr>
            <th>Customer</th>
            <td>${order.customer.userName}</td>
        </tr>

        <tr>
            <th>Customer Email</th>
            <td>${order.customer.userEmail}</td>
        </tr>

        <tr>
            <th>Product</th>
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
            <th>Order Date</th>
            <td>${order.orderDate}</td>
        </tr>

        <tr>
            <th>Current Status</th>
            <td>${order.orderStatus}</td>
        </tr>

        <tr>
            <th>Delivered Date</th>
            <td>${order.deliveredDate}</td>
        </tr>

    </table>


    <br/>


    <!-- Update Status -->

    <form
            action="${pageContext.request.contextPath}/orders/seller/${sellerId}/status"
            method="post">

        <input
                type="hidden"
                name="orderId"
                value="${order.orderId}"/>


        <label>
            Update Status:
        </label>


        <select name="orderStatus">

            <option value="PENDING_APPROVAL">
                Pending Approval
            </option>

            <option value="APPROVED">
                Approved
            </option>

            <option value="PROCESSING">
                Processing
            </option>

            <option value="SHIPPED">
                Shipped
            </option>

            <option value="DELIVERED">
                Delivered
            </option>

            <option value="CANCELLED">
                Cancelled
            </option>

        </select>


        <button
                type="submit"
                class="btn edit-btn">

            Update Status

        </button>

    </form>

</div>


<%@ include file="../common/footer.jsp" %>