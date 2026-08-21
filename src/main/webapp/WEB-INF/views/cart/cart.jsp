<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Shopping Cart"/>

<%@ include file="../common/header.jsp" %>


<div class="card">

    <h1>
        ${customer.userName}'s Shopping Cart
    </h1>


    <!-- ===================================================== -->
    <!-- NAVIGATION -->
    <!-- ===================================================== -->

    <div class="nav">

        <a href="${pageContext.request.contextPath}/customer/products">
            Continue Shopping
        </a>

        <a href="${pageContext.request.contextPath}/customer/profile/${customer.userId}">
            Customer Profile
        </a>

    </div>


    <!-- ===================================================== -->
    <!-- CART HAS ITEMS -->
    <!-- ===================================================== -->

    <c:if test="${not empty cartItems}">

        <table>

            <thead>

            <tr>

                <th>Product</th>

                <th>Brand</th>

                <th>Category</th>

                <th>Seller</th>

                <th>Price</th>

                <th>Quantity</th>

                <th>Total</th>

                <th>Action</th>

            </tr>

            </thead>


            <tbody>

            <c:forEach
                    var="cart"
                    items="${cartItems}">

                <tr>

                    <!-- ================================================= -->
                    <!-- PRODUCT -->
                    <!-- ================================================= -->

                    <td>
                        ${cart.product.productName}
                    </td>


                    <!-- ================================================= -->
                    <!-- BRAND -->
                    <!-- ================================================= -->

                    <td>
                        ${cart.product.brand}
                    </td>


                    <!-- ================================================= -->
                    <!-- CATEGORY -->
                    <!-- ================================================= -->

                    <td>
                        ${cart.product.category.categoryName}
                    </td>


                    <!-- ================================================= -->
                    <!-- SELLER -->
                    <!-- Admin-created products have no seller -->
                    <!-- ================================================= -->

                    <td>

                        <c:choose>

                            <c:when test="${not empty cart.product.seller}">

                                ${cart.product.seller.shopName}

                            </c:when>

                            <c:otherwise>

                                Admin

                            </c:otherwise>

                        </c:choose>

                    </td>


                    <!-- ================================================= -->
                    <!-- PRICE -->
                    <!-- ================================================= -->

                    <td>
                        ₹${cart.product.productPrice}
                    </td>


                    <!-- ================================================= -->
                    <!-- QUANTITY -->
                    <!-- ================================================= -->

                    <td>

                        <form
                                action="${pageContext.request.contextPath}/cart/${customer.userId}/update/${cart.cartId}"
                                method="post">

                            <input
                                    type="number"
                                    name="quantity"
                                    value="${cart.quantity}"
                                    min="1"
                                    style="width:70px;"
                            />

                            <button
                                    type="submit"
                                    class="btn edit-btn">

                                Update

                            </button>

                        </form>

                    </td>


                    <!-- ================================================= -->
                    <!-- TOTAL -->
                    <!-- ================================================= -->

                    <td>
                        ₹${cart.totalPrice}
                    </td>


                    <!-- ================================================= -->
                    <!-- REMOVE -->
                    <!-- ================================================= -->

                    <td>

                        <form
                                action="${pageContext.request.contextPath}/cart/${customer.userId}/remove/${cart.cartId}"
                                method="post"
                                onsubmit="return confirm('Remove this product from cart?');">

                            <button
                                    type="submit"
                                    class="btn delete-btn">

                                Remove

                            </button>

                        </form>

                    </td>

                </tr>

            </c:forEach>

            </tbody>

        </table>


        <!-- ===================================================== -->
        <!-- CART ACTIONS -->
        <!-- Clear Cart + Place Order -->
        <!-- ===================================================== -->

        <div class="cart-actions">

            <!-- ================================================= -->
            <!-- CLEAR CART -->
            <!-- ================================================= -->

            <form
                    action="${pageContext.request.contextPath}/cart/${customer.userId}/clear"
                    method="post"
                    onsubmit="return confirm('Clear the entire cart?');">

                <button
                        type="submit"
                        class="btn delete-btn">

                    Clear Cart

                </button>

            </form>


            <!-- ================================================= -->
            <!-- PLACE ORDER -->
            <!-- ================================================= -->

            <a
		        href="${pageContext.request.contextPath}/checkout/${customer.userId}"
		        class="btn edit-btn">
		
		        Place Order
		
		    </a>

        </div>

    </c:if>


    <!-- ===================================================== -->
    <!-- EMPTY CART -->
    <!-- ===================================================== -->

    <c:if test="${empty cartItems}">

        <div class="empty-message">

            Your cart is empty.

        </div>

        <br>

        <a
                href="${pageContext.request.contextPath}/customer/products"
                class="btn add-btn">

            Start Shopping

        </a>

    </c:if>

</div>


<!-- ===================================================== -->
<!-- CART PAGE STYLING -->
<!-- ===================================================== -->

<style>

    .cart-actions {
    display: flex;
    align-items: center;
    gap: 12px;
    margin-top: 25px;
}

.cart-actions form {
    margin: 0;
}

.cart-actions .btn {
    display: inline-flex;
    align-items: center;
    justify-content: center;
    text-decoration: none;
    border: none;
    cursor: pointer;
    padding: 12px 20px;
    border-radius: 6px;
    font-size: 16px;
}

.cart-actions .edit-btn {
    background-color: #0d6efd;
    color: white;
}

.cart-actions .edit-btn:hover {
    background-color: #0b5ed7;
}

.cart-actions .delete-btn {
    background-color: #f1f1f1;
    color: #111;
}

.cart-actions .delete-btn:hover {
    background-color: #ddd;
}
    

</style>


<%@ include file="../common/footer.jsp" %>