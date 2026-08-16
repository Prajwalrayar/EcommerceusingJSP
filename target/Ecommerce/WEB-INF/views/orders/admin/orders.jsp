<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Order Management"/>

<%@ include file="../common/header.jsp" %>


<div class="card">

    <h1>Order Management</h1>


    <!-- Search -->

    <form
            action="${pageContext.request.contextPath}/orders/admin/search"
            method="get">

        <input
                type="text"
                name="keyword"
                value="${keyword}"
                placeholder="Search customer, product or order ID..."/>

        <button
                type="submit"
                class="btn edit-btn">

            Search

        </button>

    </form>


    <br/>


    <!-- Status Filter -->

    <form
            action="${pageContext.request.contextPath}/orders/admin/status"
            method="get">

        <label>
            Filter by Status:
        </label>


        <select name="status">

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

            <option value="PAYMENT_PENDING">
                Payment Pending
            </option>

        </select>


        <button
                type="submit"
                class="btn edit-btn">

            Filter

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
                            href="${pageContext.request.contextPath}/orders/admin/${order.orderId}">

                        View

                    </a>


                    <form
                            action="${pageContext.request.contextPath}/orders/admin/delete/${order.orderId}"
                            method="post"
                            style="display:inline;"
                            onsubmit="return confirm('Delete this order?');">

                        <button
                                type="submit"
                                class="btn delete-btn">

                            Delete

                        </button>

                    </form>

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