<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../common/header.jsp" %>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/css/seller-dashboard.css">

<div class="seller-page">

    <div class="page-header">

        <div>
            <h1>Reviews & Ratings</h1>
            <p>Customer feedback for your products</p>
        </div>

        <a href="${pageContext.request.contextPath}/seller/dashboard"
           class="btn secondary-btn">
            Dashboard
        </a>

    </div>


    <div class="review-grid">

        <c:forEach var="review"
                   items="${reviews}">

            <div class="review-card">

                <div class="review-header">

                    <div>

                        <h3>
                            ${review.product.productName}
                        </h3>

                        <p>
                            ${review.customer.userName}
                        </p>

                    </div>

                    <div class="rating">

                        ⭐ ${review.rating}/5

                    </div>

                </div>


                <div class="review-text">

                    ${review.reviewText}

                </div>


                <div class="review-footer">

                    Order:
                    <strong>
                        ${review.order.orderId}
                    </strong>

                    <span>
                        ${review.reviewDate}
                    </span>

                </div>

            </div>

        </c:forEach>


        <c:if test="${empty reviews}">

            <div class="empty-message">

                No reviews have been received yet.

            </div>

        </c:if>

    </div>

</div>

<%@ include file="../common/footer.jsp" %>