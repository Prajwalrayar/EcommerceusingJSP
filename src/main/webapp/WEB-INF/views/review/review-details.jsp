<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Review Details"/>

<%@ include file="../common/header.jsp" %>


<div class="card">

    <h1>Review Details</h1>


    <!-- ===================================================== -->
    <!-- Review Information -->
    <!-- ===================================================== -->

    <table>

        <tr>

            <th>Review ID</th>

            <td>
                ${review.reviewId}
            </td>

        </tr>


        <tr>

            <th>Order ID</th>

            <td>
                ${review.order.orderId}
            </td>

        </tr>


        <tr>

            <th>Product</th>

            <td>
                ${review.product.productName}
            </td>

        </tr>


        <tr>

            <th>Brand</th>

            <td>
                ${review.product.brand}
            </td>

        </tr>


        <tr>

            <th>Customer</th>

            <td>
                ${review.customer.userName}
            </td>

        </tr>


        <tr>

            <th>Rating</th>

            <td>

                <c:choose>

                    <c:when test="${review.rating == 5}">
                        ⭐⭐⭐⭐⭐
                    </c:when>

                    <c:when test="${review.rating == 4}">
                        ⭐⭐⭐⭐
                    </c:when>

                    <c:when test="${review.rating == 3}">
                        ⭐⭐⭐
                    </c:when>

                    <c:when test="${review.rating == 2}">
                        ⭐⭐
                    </c:when>

                    <c:otherwise>
                        ⭐
                    </c:otherwise>

                </c:choose>

                (${review.rating}/5)

            </td>

        </tr>


        <tr>

            <th>Review</th>

            <td>
                ${review.reviewText}
            </td>

        </tr>


        <tr>

            <th>Review Date</th>

            <td>
                ${review.reviewDate}
            </td>

        </tr>

    </table>


    <br>


    <a
            href="${pageContext.request.contextPath}/customer/orders/${review.customer.userId}"
            class="btn">

        Back to Orders

    </a>

</div>


<%@ include file="../common/footer.jsp" %>