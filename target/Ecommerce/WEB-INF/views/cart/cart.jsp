<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Shopping Cart"/>

<%@ include file="../common/header.jsp" %>


<div class="card">

    <h1>
        ${customer.userName}'s Shopping Cart
    </h1>


    <div class="nav">

        <a href="${pageContext.request.contextPath}/product/list">
            Continue Shopping
        </a>

        <a href="${pageContext.request.contextPath}/customer/profile/${customer.userId}">
            Customer Profile
        </a>

    </div>


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

                    <td>
                        ${cart.product.productName}
                    </td>

                    <td>
                        ${cart.product.brand}
                    </td>

                    <td>
                        ${cart.product.category.categoryName}
                    </td>

                    <td>
                        ${cart.product.seller.shopName}
                    </td>

                    <td>
                        ₹${cart.product.productPrice}
                    </td>

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

                    <td>
                        ₹${cart.totalPrice}
                    </td>

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


        <br>


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

    </c:if>


    <c:if test="${empty cartItems}">

        <div class="empty-message">

            Your cart is empty.

        </div>

        <br>

        <a
                href="${pageContext.request.contextPath}/product/list"
                class="btn add-btn">

            Start Shopping

        </a>

    </c:if>

</div>


<%@ include file="../common/footer.jsp" %>