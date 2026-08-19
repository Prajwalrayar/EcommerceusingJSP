<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Checkout"/>

<%@ include file="../common/header.jsp" %>


<div class="card checkout-container">

    <h1>Checkout</h1>


    <!-- ERROR MESSAGE -->

    <c:if test="${not empty error}">
        <div class="error-message">
            ${error}
        </div>
    </c:if>


    <!-- WALLET INSUFFICIENT MESSAGE -->

    <c:if test="${walletInsufficient}">

        <div class="wallet-error">

            <h3>Insufficient Wallet Balance</h3>

            <p>
                Order Amount:
                <strong>
                    ₹${orderAmount}
                </strong>
            </p>

            <p>
                Wallet Balance:
                <strong>
                    ₹${walletBalance}
                </strong>
            </p>

            <p>
                Remaining Amount:
                <strong>
                    ₹${remainingAmount}
                </strong>
            </p>

            <p>
                Your wallet does not have enough balance
                to complete this order.
            </p>

            <div class="wallet-actions">

               <a href="${pageContext.request.contextPath}/customer/wallet"
				  class="btn recharge-btn">
				
				    Add ₹${remainingAmount} to Wallet
				
				</a>

                <a
                    href="${pageContext.request.contextPath}/checkout/${customer.userId}"
                    class="btn change-payment-btn">

                    Choose Another Payment

                </a>

            </div>

        </div>

    </c:if>


    <!-- DELIVERY ADDRESS -->

    <h2>Delivery Address</h2>


    <c:choose>

        <c:when test="${not empty addresses}">

            <div class="address-list">

                <c:forEach
                    var="address"
                    items="${addresses}">

                    <label class="address-option">

                        <input
                            type="radio"
                            name="addressId"
                            value="${address.addressId}"
                            form="checkoutForm"
                            required
                        />

                        <span class="address-details">

                            <strong>
                                ${address.houseNumber},
                                ${address.street}
                            </strong>

                            <br>

                            ${address.city},
                            ${address.state},
                            ${address.country}
                            -
                            ${address.zipCode}

                        </span>

                    </label>

                </c:forEach>

            </div>


            <div class="add-address-section">

                <a
                    href="${pageContext.request.contextPath}/address/customer/${customer.userId}/add"
                    class="add-address-link">

                    + Add New Address

                </a>

            </div>

        </c:when>


        <c:otherwise>

            <div class="no-address-message">

                <p>
                    You do not have a delivery address.
                </p>

                <a
                    href="${pageContext.request.contextPath}/address/customer/${customer.userId}/add"
                    class="btn add-btn">

                    + Add Address

                </a>

            </div>

        </c:otherwise>

    </c:choose>


    <!-- PAYMENT -->

    <h2>Payment Method</h2>


    <form
        id="checkoutForm"
        action="${pageContext.request.contextPath}/checkout/${customer.userId}/confirm"
        method="post">


        <div class="payment-options">

            <label class="payment-option">

                <input
                    type="radio"
                    name="paymentMethod"
                    value="UPI"
                    required
                    onclick="showUpi()"
                />

                UPI

            </label>


            <label class="payment-option">

                <input
                    type="radio"
                    name="paymentMethod"
                    value="WALLET"
                    onclick="hideUpi()"
                />

                Wallet

            </label>


            <label class="payment-option">

                <input
                    type="radio"
                    name="paymentMethod"
                    value="CASH_ON_DELIVERY"
                    onclick="hideUpi()"
                />

                Cash on Delivery

            </label>

        </div>


        <!-- UPI -->

        <div
            id="upiSection"
            class="upi-section">

            <label for="upiId">
                UPI ID
            </label>

            <input
                type="text"
                name="upiId"
                id="upiId"
                placeholder="example@upi"
            />

        </div>


        <!-- ORDER SUMMARY -->

        <h2>Order Summary</h2>


        <table class="checkout-table">

            <thead>

                <tr>

                    <th>Product</th>

                    <th>Quantity</th>

                    <th>Price</th>

                    <th>Total</th>

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
                            ${cart.quantity}
                        </td>

                        <td>
                            ₹${cart.product.productPrice}
                        </td>

                        <td>
                            ₹${cart.totalPrice}
                        </td>

                    </tr>

                </c:forEach>

            </tbody>

        </table>


        <!-- TOTAL -->

        <div class="checkout-total">

            <strong>
                Grand Total:
            </strong>

            <strong>
                ₹${totalAmount}
            </strong>

        </div>


        <!-- ACTIONS -->

        <div class="checkout-actions">

            <a
                href="${pageContext.request.contextPath}/cart/${customer.userId}"
                class="btn back-btn">

                Back to Cart

            </a>


            <button
                type="submit"
                class="btn confirm-btn">

                Confirm &amp; Pay

            </button>

        </div>


    </form>

</div>


<style>

.checkout-container {
    max-width: 1100px;
    margin: 30px auto;
    padding: 35px;
}

.checkout-container h1 {
    margin-bottom: 35px;
}

.checkout-container h2 {
    margin-top: 30px;
    margin-bottom: 18px;
}


/* ERROR */

.error-message {
    padding: 18px;
    margin-bottom: 20px;
    background-color: #ffe5e5;
    border: 1px solid #ffaaaa;
    border-radius: 7px;
    color: #8b0000;
    font-size: 16px;
}


/* WALLET ERROR */

.wallet-error {
    padding: 22px;
    margin-bottom: 25px;
    background-color: #fff3cd;
    border: 1px solid #ffc107;
    border-radius: 8px;
}

.wallet-error h3 {
    margin-top: 0;
    margin-bottom: 15px;
    color: #856404;
}

.wallet-error p {
    margin: 8px 0;
}

.wallet-actions {
    display: flex;
    gap: 15px;
    margin-top: 20px;
}


/* ADDRESS */

.address-list {
    display: flex;
    flex-direction: column;
    gap: 10px;
}

.address-option {
    display: flex;
    align-items: flex-start;
    gap: 15px;
    padding: 18px;
    border: 1px solid #ddd;
    border-radius: 8px;
    cursor: pointer;
    transition: 0.2s;
}

.address-option:hover {
    background-color: #f8f9fa;
    border-color: #0d6efd;
}

.address-option input {
    margin-top: 5px;
    width: 18px;
    height: 18px;
}

.address-details {
    line-height: 1.6;
}

.add-address-section {
    margin-top: 15px;
}

.add-address-link {
    color: #0d6efd;
    text-decoration: none;
    font-size: 17px;
}

.add-address-link:hover {
    text-decoration: underline;
}


/* NO ADDRESS */

.no-address-message {
    padding: 20px;
    background-color: #f8f9fa;
    border: 1px solid #ddd;
    border-radius: 8px;
}


/* PAYMENT */

.payment-options {
    display: flex;
    align-items: center;
    gap: 35px;
    margin: 15px 0 20px 0;
}

.payment-option {
    display: flex;
    align-items: center;
    gap: 8px;
    cursor: pointer;
    font-size: 17px;
}

.payment-option input {
    width: 18px;
    height: 18px;
}


/* UPI */

.upi-section {
    display: none;
    margin-top: 15px;
    margin-bottom: 25px;
}

.upi-section label {
    display: block;
    margin-bottom: 8px;
    font-weight: bold;
}

.upi-section input {
    width: 320px;
    padding: 10px;
    border: 1px solid #999;
    border-radius: 6px;
    font-size: 15px;
}


/* TABLE */

.checkout-table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 15px;
}

.checkout-table th {
    background-color: #f5f5f5;
    font-weight: bold;
}

.checkout-table th,
.checkout-table td {
    border: 1px solid #ddd;
    padding: 15px;
    text-align: left;
}


/* TOTAL */

.checkout-total {
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 21px;
    margin-top: 25px;
    padding: 20px;
    border-top: 2px solid #ddd;
}


/* ACTIONS */

.checkout-actions {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-top: 25px;
}


/* BUTTON */

.btn {
    display: inline-block;
    padding: 12px 22px;
    border-radius: 6px;
    font-size: 16px;
    text-decoration: none;
    border: none;
    cursor: pointer;
}


/* BACK */

.back-btn {
    background-color: #eeeeee;
    color: #111;
}

.back-btn:hover {
    background-color: #dddddd;
}


/* CONFIRM */

.confirm-btn {
    background-color: #0d6efd;
    color: white;
}

.confirm-btn:hover {
    background-color: #0b5ed7;
}


/* ADD ADDRESS */

.add-btn {
    background-color: #0d6efd;
    color: white;
}

.add-btn:hover {
    background-color: #0b5ed7;
}


/* WALLET BUTTON */

.recharge-btn {
    background-color: #198754;
    color: white;
}

.recharge-btn:hover {
    background-color: #157347;
}


/* CHANGE PAYMENT */

.change-payment-btn {
    background-color: #6c757d;
    color: white;
}

.change-payment-btn:hover {
    background-color: #5c636a;
}

</style>


<script>

function showUpi() {

    var upiSection =
        document.getElementById("upiSection");

    var upiId =
        document.getElementById("upiId");

    upiSection.style.display = "block";

    upiId.required = true;
}


function hideUpi() {

    var upiSection =
        document.getElementById("upiSection");

    var upiId =
        document.getElementById("upiId");

    upiSection.style.display = "none";

    upiId.required = false;

    upiId.value = "";
}

</script>


<%@ include file="../common/footer.jsp" %>