<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Payment Details"/>

<%@ include file="../common/header.jsp" %>


<div class="card">

    <h1>Payment Details</h1>


    <c:choose>

        <c:when test="${not empty payment}">


            <!-- ================================================= -->
            <!-- Payment Information -->
            <!-- ================================================= -->

            <h2>Payment Information</h2>

            <table>

                <tr>
                    <th>Payment ID</th>
                    <td>${payment.paymentId}</td>
                </tr>

                <tr>
                    <th>Transaction ID</th>
                    <td>${payment.transactionId}</td>
                </tr>

                <tr>
                    <th>Payment Method</th>
                    <td>${payment.paymentMethod}</td>
                </tr>

                <tr>
                    <th>Payment Status</th>
                    <td>${payment.paymentStatus}</td>
                </tr>

                <tr>
                    <th>Amount</th>
                    <td>₹${payment.amount}</td>
                </tr>

                <tr>
                    <th>UPI ID</th>
                    <td>${payment.upiId}</td>
                </tr>

                <tr>
                    <th>Payment Date</th>
                    <td>${payment.paymentDate}</td>
                </tr>

            </table>


            <br>


            <!-- ================================================= -->
            <!-- Customer Information -->
            <!-- ================================================= -->

            <h2>Customer Information</h2>

            <table>

                <tr>
                    <th>Customer ID</th>
                    <td>${payment.customer.userId}</td>
                </tr>

                <tr>
                    <th>Name</th>
                    <td>${payment.customer.userName}</td>
                </tr>

                <tr>
                    <th>Email</th>
                    <td>${payment.customer.userEmail}</td>
                </tr>

                <tr>
                    <th>Phone</th>
                    <td>${payment.customer.userPhNo}</td>
                </tr>

            </table>


            <br>


            <!-- ================================================= -->
            <!-- Order Information -->
            <!-- ================================================= -->

            <h2>Order Information</h2>

            <table>

                <tr>
                    <th>Order ID</th>
                    <td>${payment.order.orderId}</td>
                </tr>

                <tr>
                    <th>Product</th>
                    <td>${payment.order.product.productName}</td>
                </tr>

                <tr>
                    <th>Quantity</th>
                    <td>${payment.order.quantity}</td>
                </tr>

                <tr>
                    <th>Order Total</th>
                    <td>₹${payment.order.totalPrice}</td>
                </tr>

                <tr>
                    <th>Order Status</th>
                    <td>${payment.order.orderStatus}</td>
                </tr>

                <tr>
                    <th>Order Date</th>
                    <td>${payment.order.orderDate}</td>
                </tr>

            </table>


            <br>


            <!-- ================================================= -->
            <!-- Actions -->
            <!-- ================================================= -->

            <a
                    href="${pageContext.request.contextPath}/payment/list"
                    class="btn">

                Back to Payments

            </a>


            <form
                    action="${pageContext.request.contextPath}/payment/delete/${payment.paymentId}"
                    method="post"
                    style="display:inline;"
                    onsubmit="return confirm('Delete this payment?');">

                <button
                        type="submit"
                        class="btn delete-btn">

                    Delete Payment

                </button>

            </form>

        </c:when>


        <c:otherwise>

            <div class="empty-message">

                Payment not found.

            </div>

            <br>

            <a
                    href="${pageContext.request.contextPath}/payment/list"
                    class="btn">

                Back to Payments

            </a>

        </c:otherwise>

    </c:choose>

</div>


<%@ include file="../common/footer.jsp" %>