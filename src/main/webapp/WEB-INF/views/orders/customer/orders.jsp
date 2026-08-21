<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="My Orders"/>

<%@ include file="../../common/header.jsp" %>


<div class="card">

    <h1>My Orders</h1>


    <div class="nav">
    
	    <a href="${pageContext.request.contextPath}/customer/dashboard">
	        Dashboard
	    </a>

        <a href="${pageContext.request.contextPath}/orders/customer/${customerId}">
            All Orders
        </a>

        <a href="${pageContext.request.contextPath}/orders/customer/${customerId}/cancelable">
            Cancelable Orders
        </a>

        <a href="${pageContext.request.contextPath}/orders/customer/${customerId}/payment-pending">
            Payment Pending
        </a>

    </div>


    <!-- Search -->

    <form
            action="${pageContext.request.contextPath}/orders/customer/${customerId}/search"
            method="get">

        <input
                type="text"
                name="productName"
                value="${productName}"
                placeholder="Search product..."/>

        <button type="submit" class="btn edit-btn">
            Search
        </button>

    </form>

    <br/>


    <table>

        <thead>

        <tr>

            <th>Order ID</th>
            <th>Product</th>
            <th>Quantity</th>
            <th>Total Price</th>
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

                <td>
                    ${order.orderId}
                </td>

                <td>
                    ${order.product.productName}
                </td>

                <td>
                    ${order.quantity}
                </td>

                <td>
                    ₹${order.totalPrice}
                </td>

                <td>
                    ${order.orderStatus}
                </td>

                <td>
                    ${order.orderDate}
                </td>

                <td>

                    <a
                            class="btn edit-btn"
                            href="${pageContext.request.contextPath}/orders/customer/${customerId}/${order.orderId}">

                        View

                    </a>


                    <c:if test="${order.orderStatus != 'CANCELLED'}">

                        <form
                                action="${pageContext.request.contextPath}/orders/customer/${customerId}/cancel/${order.orderId}"
                                method="post"
                                style="display:inline;"
                                onsubmit="return confirm('Cancel this order?');">

                            <button
                                    type="submit"
                                    class="btn delete-btn">

                                Cancel

                            </button>

                        </form>

                    </c:if>

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


<%@ include file="../../common/footer.jsp" %>