<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Payment Management"/>

<%@ include file="../common/header.jsp" %>


<div class="card">

    <h1>Payment Management</h1>


    <!-- ================================================= -->
    <!-- Navigation -->
    <!-- ================================================= -->

    <div class="nav">

        <a href="${pageContext.request.contextPath}/admin/customers">
            Customers
        </a>

        <a href="${pageContext.request.contextPath}/admin/sellers">
            Sellers
        </a>

        <a href="${pageContext.request.contextPath}/admin/profile?adminId=ADM001">
            Admin Profile
        </a>

        <a href="${pageContext.request.contextPath}/address/list">
            Address Management
        </a>

        <a href="${pageContext.request.contextPath}/payment/list">
            Payments
        </a>

    </div>


    <!-- ================================================= -->
    <!-- Search -->
    <!-- ================================================= -->

    <form
            action="${pageContext.request.contextPath}/payment/search"
            method="get"
            style="margin-bottom:20px;">

        <input
                type="text"
                name="keyword"
                value="${keyword}"
                placeholder="Search transaction or product"
                required>

        <button
                type="submit"
                class="btn edit-btn">

            Search

        </button>

        <a
                href="${pageContext.request.contextPath}/payment/list"
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

            <th>Amount</th>

            <th>Payment Method</th>

            <th>Status</th>

            <th>Payment Date</th>

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


                    <form
                            action="${pageContext.request.contextPath}/payment/delete/${payment.paymentId}"
                            method="post"
                            style="display:inline;"
                            onsubmit="return confirm('Are you sure you want to delete this payment?');">

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


    <!-- ================================================= -->
    <!-- Empty -->
    <!-- ================================================= -->

    <c:if test="${empty payments}">

        <div class="empty-message">

            No payments found.

        </div>

    </c:if>

</div>


<%@ include file="../common/footer.jsp" %>