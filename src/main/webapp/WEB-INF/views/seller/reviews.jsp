<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle"
       value="Reviews & Ratings"/>

<%@ include file="../common/header.jsp" %>


<div class="container">

    <h1>Reviews & Ratings</h1>

    <p>
        Customer feedback for your products
    </p>


    <!-- ===================================================== -->
    <!-- NO REVIEWS -->
    <!-- ===================================================== -->

    <c:if test="${empty reviews}">

        <div class="card">

            <h2>No Reviews Yet</h2>

            <p>
                Customers have not reviewed your products yet.
            </p>

        </div>

    </c:if>


    <!-- ===================================================== -->
    <!-- REVIEWS -->
    <!-- ===================================================== -->

    <c:if test="${not empty reviews}">

        <table>

            <thead>

                <tr>

                    <th>Review ID</th>

                    <th>Product</th>

                    <th>Customer</th>

                    <th>Rating</th>

                    <th>Review</th>

                    <th>Review Date</th>

                </tr>

            </thead>


            <tbody>

                <c:forEach
                        var="review"
                        items="${reviews}">

                    <tr>

                        <!-- Review ID -->

                        <td>
                            ${review.reviewId}
                        </td>


                        <!-- Product -->

                        <td>

                            <strong>
                                ${review.product.productName}
                            </strong>

                            <br>

                            ${review.product.brand}

                        </td>


                        <!-- Customer -->

                        <td>

                            ${review.customer.userName}

                            <br>

                            <small>
                                ${review.customer.userEmail}
                            </small>

                        </td>


                        <!-- Rating -->

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

                            <br>

                            (${review.rating}/5)

                        </td>


                        <!-- Review -->

                        <td>

                            ${review.reviewText}

                        </td>


                        <!-- Date -->

                        <td>

                            ${review.reviewDate}

                        </td>

                    </tr>

                </c:forEach>

            </tbody>

        </table>

    </c:if>


    <br>


    <a
            href="${pageContext.request.contextPath}/seller/dashboard"
            class="btn">

        Back to Dashboard

    </a>

</div>


<%@ include file="../common/footer.jsp" %>