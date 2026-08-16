<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Write Review"/>

<%@ include file="../common/header.jsp" %>


<div class="card">

    <h1>Write Product Review</h1>


    <!-- ===================================================== -->
    <!-- Error Message -->
    <!-- ===================================================== -->

    <c:if test="${not empty error}">

        <div class="error-message">

            ${error}

        </div>

    </c:if>


    <!-- ===================================================== -->
    <!-- Order Information -->
    <!-- ===================================================== -->

    <h2>Order Information</h2>


    <table>

        <tr>

            <th>Order ID</th>

            <td>
                ${order.orderId}
            </td>

        </tr>


        <tr>

            <th>Product</th>

            <td>
                ${order.product.productName}
            </td>

        </tr>


        <tr>

            <th>Brand</th>

            <td>
                ${order.product.brand}
            </td>

        </tr>


        <tr>

            <th>Quantity</th>

            <td>
                ${order.quantity}
            </td>

        </tr>


        <tr>

            <th>Price</th>

            <td>
                ₹${order.product.productPrice}
            </td>

        </tr>


        <tr>

            <th>Total Price</th>

            <td>
                ₹${order.totalPrice}
            </td>

        </tr>


        <tr>

            <th>Order Status</th>

            <td>
                ${order.orderStatus}
            </td>

        </tr>

    </table>


    <br>


    <!-- ===================================================== -->
    <!-- Review Form -->
    <!-- ===================================================== -->

    <h2>Your Review</h2>


    <form
            action="${pageContext.request.contextPath}/customer/review/${order.orderId}"
            method="post">


        <input
                type="hidden"
                name="customerId"
                value="${customerId}"/>


        <!-- Rating -->

        <div class="form-group">

            <label for="rating">

                Rating

            </label>


            <select
                    id="rating"
                    name="rating"
                    required>

                <option value="">
                    Select Rating
                </option>

                <option value="5">
                    5 - Excellent
                </option>

                <option value="4">
                    4 - Very Good
                </option>

                <option value="3">
                    3 - Good
                </option>

                <option value="2">
                    2 - Average
                </option>

                <option value="1">
                    1 - Poor
                </option>

            </select>

        </div>


        <br>


        <!-- Review Text -->

        <div class="form-group">

            <label for="reviewText">

                Your Review

            </label>


            <textarea
                    id="reviewText"
                    name="reviewText"
                    rows="6"
                    required
                    placeholder="Write your experience with this product..."></textarea>

        </div>


        <br>


        <!-- Submit -->

        <button
                type="submit"
                class="btn edit-btn">

            Submit Review

        </button>


        <a
                href="${pageContext.request.contextPath}/customer/orders/${customerId}"
                class="btn">

            Cancel

        </a>

    </form>

</div>


<%@ include file="../common/footer.jsp" %>