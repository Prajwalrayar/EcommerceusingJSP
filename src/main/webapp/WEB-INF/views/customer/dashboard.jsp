<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../common/header.jsp" %>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/css/seller-dashboard.css">

<div class="seller-dashboard">

    <!-- HEADER -->

    <div class="seller-header">

        <div>

            <h1>Customer Dashboard</h1>

            <p>
                Welcome,
                <strong>${customer.userName}</strong>
            </p>

        </div>

        <a href="${pageContext.request.contextPath}/logout"
           class="logout-btn">
            Logout
        </a>

    </div>

    <hr>


    <!-- OVERVIEW -->

    <h2 class="section-title">
        Account Overview
    </h2>

    <div class="stats-grid">

    <!-- WALLET BALANCE -->
    <div class="stat-card">

        <div class="stat-icon">
            💰
        </div>

        <div class="stat-content">

            <div class="stat-value">
                ₹ ${walletBalance}
            </div>

            <div class="stat-title">
                Wallet Balance
            </div>

        </div>

    </div>


    <!-- CART ITEMS -->
    <div class="stat-card">

        <div class="stat-icon">
            🛒
        </div>

        <div class="stat-content">

            <div class="stat-value">
                ${cartItemCount}
            </div>

            <div class="stat-title">
                Cart Items
            </div>

        </div>

    </div>


    <!-- TOTAL ORDERS -->
    <div class="stat-card">

        <div class="stat-icon">
            📦
        </div>

        <div class="stat-content">

            <div class="stat-value">
                ${totalOrders}
            </div>

            <div class="stat-title">
                Total Orders
            </div>

        </div>

    </div>


    <!-- TOTAL SPENT -->
    <div class="stat-card">

        <div class="stat-icon">
            💵
        </div>

        <div class="stat-content">

            <div class="stat-value">
                ₹ ${totalSpent}
            </div>

            <div class="stat-title">
                Total Spent
            </div>

        </div>

    </div>

</div>


    <!-- OPERATIONS -->

    <div class="management-section">

        <h2 class="section-title">
            Customer Operations
        </h2>

        <div class="management-grid">


            <a href="${pageContext.request.contextPath}/customer/profile/${customer.userId}"
               class="management-card">

                <span>👤</span>

                <strong>My Profile</strong>

                <small>
                    Manage your profile
                </small>

            </a>


            <a href="${pageContext.request.contextPath}/customer/wallet"
               class="management-card">

                <span>💰</span>

                <strong>Wallet</strong>

                <small>
                    Recharge wallet
                </small>

            </a>


            <a href="${pageContext.request.contextPath}/customer/products"
               class="management-card">

                <div class="operation-icon">
			        🛍️
			    </div>

                <strong>Products</strong>

                <small>
                    Browse and search products
                </small>

            </a>


            <a href="${pageContext.request.contextPath}/cart/${customer.userId}"
			     class="management-card">
			
			        <span>
			            🛒
			        </span>
			
			        <strong>My Cart</strong>
			
			        <small>Manage cart and checkout</small>
		
			</a>


            <a href="${pageContext.request.contextPath}/orders/customer/${customer.userId}"
               class="management-card">

                <span>📦</span>

                <strong>My Orders</strong>

                <small>
                    Track your orders
                </small>

            </a>


            <a href="${pageContext.request.contextPath}/reports/customer/${customer.userId}"
               class="management-card">

                <span>📊</span>

                <strong>My Reports</strong>

                <small>
                    Purchase history
                </small>

            </a>


            <a href="${pageContext.request.contextPath}/customer/addresses"
			   class="management-card">
			
			    <span>📍</span>
			
			    <strong>My Addresses</strong>
			
			    <small>
			        Manage delivery addresses
			    </small>
			
			</a>

        </div>

    </div>

</div>

<%@ include file="../common/footer.jsp" %>