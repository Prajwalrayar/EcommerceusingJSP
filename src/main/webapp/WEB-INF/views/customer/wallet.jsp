<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../common/header.jsp" %>


<style>

    .wallet-container {
        max-width: 1000px;
        margin: 50px auto;
        padding: 0 20px;
    }

    .wallet-title {
        font-size: 32px;
        font-weight: 700;
        color: #172033;
        margin-bottom: 30px;
    }

    .wallet-card {
        background: #ffffff;
        border-radius: 18px;
        padding: 40px;
        box-shadow: 0 8px 25px rgba(0, 0, 0, 0.08);
        border: 1px solid #e5e7eb;
    }

    .wallet-icon {
        font-size: 55px;
        margin-bottom: 15px;
    }

    .wallet-label {
        font-size: 17px;
        color: #64748b;
        margin-bottom: 10px;
    }

    .wallet-balance {
        font-size: 42px;
        font-weight: 700;
        color: #2563eb;
        margin-bottom: 30px;
    }

    .wallet-info {
        display: grid;
        grid-template-columns: repeat(2, 1fr);
        gap: 20px;
        margin-top: 30px;
    }

    .info-box {
        background: #f8fafc;
        border-radius: 12px;
        padding: 20px;
        border: 1px solid #e2e8f0;
    }

    .info-title {
        font-size: 14px;
        color: #64748b;
        margin-bottom: 8px;
    }

    .info-value {
        font-size: 18px;
        font-weight: 600;
        color: #172033;
    }

    /* =====================================================
       RECHARGE SECTION
       ===================================================== */

    .recharge-section {
        margin-top: 35px;
        padding: 30px;
        background: #f8fafc;
        border: 1px solid #e2e8f0;
        border-radius: 14px;
    }

    .recharge-title {
        font-size: 22px;
        font-weight: 700;
        color: #172033;
        margin-bottom: 20px;
    }

    .form-group {
        margin-bottom: 20px;
    }

    .form-group label {
        display: block;
        font-weight: 600;
        color: #334155;
        margin-bottom: 8px;
    }

    .form-group input,
    .form-group select {
        width: 100%;
        max-width: 500px;
        padding: 12px 14px;
        border: 1px solid #cbd5e1;
        border-radius: 8px;
        font-size: 16px;
        box-sizing: border-box;
    }

    .form-group input:focus,
    .form-group select:focus {
        outline: none;
        border-color: #2563eb;
    }

    .btn {
        display: inline-block;
        padding: 12px 24px;
        border-radius: 8px;
        text-decoration: none;
        font-weight: 600;
        font-size: 15px;
        cursor: pointer;
        border: none;
    }

    .btn-primary {
        background: #2563eb;
        color: white;
    }

    .btn-primary:hover {
        background: #1d4ed8;
        color: white;
    }

    .btn-secondary {
        background: #e2e8f0;
        color: #172033;
    }

    .btn-secondary:hover {
        background: #cbd5e1;
        color: #172033;
    }

    .wallet-note {
        margin-top: 30px;
        padding: 18px;
        background: #eff6ff;
        border-left: 4px solid #2563eb;
        border-radius: 8px;
        color: #334155;
        line-height: 1.6;
    }

    .error-message {
        margin-bottom: 20px;
        padding: 14px;
        background: #fee2e2;
        border-left: 4px solid #dc2626;
        border-radius: 8px;
        color: #991b1b;
    }

    .success-message {
        margin-bottom: 20px;
        padding: 14px;
        background: #dcfce7;
        border-left: 4px solid #16a34a;
        border-radius: 8px;
        color: #166534;
    }

    .wallet-actions {
        margin-top: 30px;
        display: flex;
        gap: 15px;
        flex-wrap: wrap;
    }

    @media (max-width: 700px) {

        .wallet-container {
            margin: 30px auto;
        }

        .wallet-card {
            padding: 25px;
        }

        .wallet-title {
            font-size: 26px;
        }

        .wallet-balance {
            font-size: 34px;
        }

        .wallet-info {
            grid-template-columns: 1fr;
        }

    }

</style>


<div class="wallet-container">


    <!-- =====================================================
         PAGE TITLE
         ===================================================== -->

    <div class="wallet-title">
        My Wallet
    </div>


    <div class="wallet-card">


        <!-- =================================================
             ERROR MESSAGE
             ================================================= -->

        <% if (request.getAttribute("error") != null) { %>

            <div class="error-message">

                <strong>Error:</strong>

                <%= request.getAttribute("error") %>

            </div>

        <% } %>


        <!-- =================================================
             SUCCESS MESSAGE
             ================================================= -->

        <% if (request.getAttribute("success") != null) { %>

            <div class="success-message">

                <strong>Success:</strong>

                <%= request.getAttribute("success") %>

            </div>

        <% } %>


        <!-- =================================================
             WALLET ICON
             ================================================= -->

        <div class="wallet-icon">
            💰
        </div>


        <!-- =================================================
             BALANCE
             ================================================= -->

        <div class="wallet-label">
            Available Wallet Balance
        </div>

        <div class="wallet-balance">
            ₹ ${customer.walletBalance}
        </div>


        <!-- =================================================
             CUSTOMER INFORMATION
             ================================================= -->

        <div class="wallet-info">


            <div class="info-box">

                <div class="info-title">
                    Customer
                </div>

                <div class="info-value">
                    ${customer.userName}
                </div>

            </div>


            <div class="info-box">

                <div class="info-title">
                    Customer ID
                </div>

                <div class="info-value">
                    ${customer.userId}
                </div>

            </div>


        </div>


        <!-- =====================================================
     RECHARGE WALLET
     ===================================================== -->

<div class="recharge-section">

    <div class="recharge-title">
        Recharge Wallet
    </div>


    <form
            method="post"
            action="${pageContext.request.contextPath}/customer/wallet/recharge"
            onsubmit="return validateRechargeForm();">


        <!-- ==============================================
             RECHARGE AMOUNT
             ============================================== -->

        <div class="form-group">

            <label for="amount">
                Recharge Amount
            </label>

            <input
                    type="number"
                    id="amount"
                    name="amount"
                    min="1"
                    step="0.01"
                    placeholder="Enter amount"
                    required>

        </div>


        <!-- ==============================================
             PAYMENT METHOD
             ============================================== -->

        <div class="form-group">

            <label for="paymentMethod">
                Payment Method
            </label>

            <select
                    id="paymentMethod"
                    name="paymentMethod"
                    onchange="showPaymentFields()"
                    required>

                <option value="">
                    -- Select Payment Method --
                </option>

                <option value="UPI">
                    UPI
                </option>

                <option value="DEBIT_CARD">
                    Debit Card
                </option>

            </select>

        </div>


        <!-- ==============================================
             UPI DETAILS
             ============================================== -->

        <div
                id="upiSection"
                style="display:none;">

            <div class="form-group">

                <label for="upiId">
                    UPI ID
                </label>

                <input
                        type="text"
                        id="upiId"
                        name="upiId"
                        placeholder="Example: username@upi">

                <small>
                    Example:
                    username@upi
                    or
                    9876543210@upi
                </small>

            </div>

        </div>


        <!-- ==============================================
             DEBIT CARD DEMO SECTION
             ============================================== -->

        <div
                id="debitCardSection"
                style="display:none;">

            <div class="demo-warning">

                <strong>
                    Debit Card Demo
                </strong>

                <br>

                Debit Card recharge is currently unavailable.
                These fields are for demonstration only.
                Actual card validation will be implemented later.

            </div>


            <div class="form-group">

                <label for="cardNumber">
                    Card Number
                </label>

                <input
                        type="text"
                        id="cardNumber"
                        name="cardNumber"
                        placeholder="XXXX XXXX XXXX XXXX"
                        maxlength="19">

            </div>


            <div class="card-row">

                <div class="form-group">

                    <label for="expiryDate">
                        Expiry Date
                    </label>

                    <input
                            type="text"
                            id="expiryDate"
                            name="expiryDate"
                            placeholder="MM/YY"
                            maxlength="5">

                </div>


                <div class="form-group">

                    <label for="cvv">
                        CVV
                    </label>

                    <input
                            type="password"
                            id="cvv"
                            name="cvv"
                            placeholder="XXX"
                            maxlength="3">

                </div>

            </div>

        </div>


        <!-- ==============================================
             RECHARGE BUTTON
             ============================================== -->

        <button
                type="submit"
                class="btn btn-primary">

            Recharge Wallet

        </button>

    </form>

</div>


        <!-- =================================================
             WALLET INFORMATION
             ================================================= -->

        <div class="wallet-note">

            <strong>Wallet Information</strong>

            <br>

            Add money to your wallet using the available
            payment methods. Your wallet balance can then
            be used during checkout.

        </div>


        <!-- =================================================
             NAVIGATION
             ================================================= -->

        <div class="wallet-actions">

            <a
                    href="${pageContext.request.contextPath}/customer/dashboard"
                    class="btn btn-secondary">

                ← Back to Dashboard

            </a>


            <a
                    href="${pageContext.request.contextPath}/customer/cart"
                    class="btn btn-primary">

                Go to Cart

            </a>

        </div>


    </div>

</div>

<script>

    function showPaymentFields() {

        const paymentMethod =
                document.getElementById("paymentMethod").value;

        const upiSection =
                document.getElementById("upiSection");

        const debitCardSection =
                document.getElementById("debitCardSection");

        const upiId =
                document.getElementById("upiId");


        upiSection.style.display = "none";

        debitCardSection.style.display = "none";

        upiId.required = false;


        if (paymentMethod === "UPI") {

            upiSection.style.display = "block";

            upiId.required = true;

        }


        if (paymentMethod === "DEBIT_CARD") {

            debitCardSection.style.display = "block";

        }
    }


    function validateRechargeForm() {

        const amount =
                parseFloat(
                    document.getElementById("amount").value
                );


        const paymentMethod =
                document.getElementById("paymentMethod").value;


        if (isNaN(amount) || amount <= 0) {

            alert(
                "Recharge amount must be greater than zero."
            );

            return false;
        }


        if (paymentMethod === "") {

            alert(
                "Please select a payment method."
            );

            return false;
        }


        /*
         * Debit card is intentionally disabled.
         *
         * We don't allow the form to proceed.
         */
        if (paymentMethod === "DEBIT_CARD") {

            alert(
                "Debit Card recharge is currently unavailable. "
                + "It will be implemented after card validation is added."
            );

            return false;
        }


        if (paymentMethod === "UPI") {

            const upiId =
                    document.getElementById("upiId").value.trim();


            if (upiId === "") {

                alert(
                    "UPI ID cannot be empty."
                );

                return false;
            }
        }


        return true;
    }

</script>

<%@ include file="../common/footer.jsp" %>


<%@ include file="../common/footer.jsp" %>