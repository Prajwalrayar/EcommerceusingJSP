<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Seller Payments"/>

<%@ include file="../common/header.jsp" %>


<div class="card">

    <h1>Seller Payments</h1>


    <!-- ================================================= -->
    <!-- Navigation -->
    <!-- ================================================= -->

    <div class="nav">

        <a
                href="${pageContext.request.contextPath}/seller/profile/${sellerId}">

            Seller Profile

        </a>

        <a
                href="${pageContext.request.contextPath}/payment/seller/${sellerId}">

            Payments

        </a>

    </div>


    <!-- ================================================= -->
    <!-- Search -->
    <!-- ================================================= -->

    <form
            action="${pageContext.request.contextPath}/payment/seller/${sellerId}/search"
            method="get"
            style="margin-bottom:20px;">

        <input
                type="text"
                name="keyword"
                value="${keyword}"
                placeholder="Search customer, product or transaction"
                required>

        <button
                type="submit"
                class="btn edit-btn">

            Search

        </button>

        <a
                href="${pageContext.request.contextPath}/payment/seller/${sellerId}"
                class="btn">

            Clear

        </a>

    </form>


    <!-- ================================================= -->
    <!-- Payment Table -->
    <!-- ================================================= -->

    <table>

        <thead>

        <tr>

            <th>Payment ID</th>

            <th>Transaction ID</th>

            <th>Customer</th>

            <th>Order ID</th>

            <th>Product</th>

            <th>Quantity</th>

            <th>Amount</th>

            <th>Method</th>

            <th>Status</th>

            <th>Date</th>

            <th>Action</th>

        </tr>

        </thead>


        <tbody>

        <c:forEach
                var="payment"
                items="${payments}">

            <tr>

                <td>
                    ${payment.paymentId}
                </td>

                <td>
                    ${payment.transactionId}
                </td>

                <td>
                    ${payment.customer.userName}
                </td>

                <td>
                    ${payment.order.orderId}
                </td>

                <td>
                    ${payment.order.product.productName}
                </td>

                <td>
                    ${payment.order.quantity}
                </td>

                <td>
                    ₹${payment.amount}
                </td>

                <td>
                    ${payment.paymentMethod}
                </td>

                <td>
                    ${payment.paymentStatus}
                </td>

                <td>
                    ${payment.paymentDate}
                </td>

                <td>

                    <a
                            href="${pageContext.request.contextPath}/payment/details/${payment.paymentId}"
                            class="btn edit-btn">

                        View

                    </a>

                </td>

            </tr>

        </c:forEach>

        </tbody>

    </table>


    <c:if test="${empty payments}">

        <div class="empty-message">

            No payments found for your products.

        </div>

    </c:if>

</div>


<%@ include file="../common/footer.jsp" %>