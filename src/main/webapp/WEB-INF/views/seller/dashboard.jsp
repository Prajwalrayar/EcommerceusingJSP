<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../common/header.jsp" %>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/css/seller-dashboard.css">


<div class="seller-dashboard">


    <!-- ================================================== -->
    <!-- HEADER -->
    <!-- ================================================== -->

    <div class="seller-header">

        <div>

            <h1>Seller Dashboard</h1>

            <p>
                Welcome,
                <strong>${seller.userName}</strong>
            </p>

        </div>


        <a href="${pageContext.request.contextPath}/logout"
           class="logout-btn">

            Logout

        </a>

    </div>


    <hr>


    <!-- ================================================== -->
    <!-- SYSTEM OVERVIEW -->
    <!-- ================================================== -->

    <h2 class="section-title">
        System Overview
    </h2>


    <div class="stats-grid">


        <div class="stat-card">

            <div class="stat-icon">
                📦
            </div>

            <div>

                <div class="stat-value">
                    ${totalProducts}
                </div>

                <div class="stat-label">
                    Total Products
                </div>

            </div>

        </div>


        <div class="stat-card">

            <div class="stat-icon">
                🛒
            </div>

            <div>

                <div class="stat-value">
                    ${totalOrders}
                </div>

                <div class="stat-label">
                    Total Orders
                </div>

            </div>

        </div>


        <div class="stat-card">

            <div class="stat-icon">
                💰
            </div>

            <div>

                <div class="stat-value">
                    ₹${totalRevenue}
                </div>

                <div class="stat-label">
                    Total Revenue
                </div>

            </div>

        </div>


        <div class="stat-card">

            <div class="stat-icon">
                📊
            </div>

            <div>

                <div class="stat-value">
                    ${totalSoldQuantity}
                </div>

                <div class="stat-label">
                    Sold Quantity
                </div>

            </div>

        </div>


        <div class="stat-card">

            <div class="stat-icon">
                💵
            </div>

            <div>

                <div class="stat-value">
                    ₹${averageRevenue}
                </div>

                <div class="stat-label">
                    Average Revenue
                </div>

            </div>

        </div>


        <div class="stat-card">

            <div class="stat-icon">
                📈
            </div>

            <div>

                <div class="stat-value">
                    ${averageSoldQuantity}
                </div>

                <div class="stat-label">
                    Average Sold Quantity
                </div>

            </div>

        </div>


        <div class="stat-card">

            <div class="stat-icon">
                ⭐
            </div>

            <div>

                <div class="stat-value">
                    ${averageRating}
                </div>

                <div class="stat-label">
                    Average Rating
                </div>

            </div>

        </div>

    </div>


    <!-- ================================================== -->
    <!-- MANAGEMENT -->
    <!-- ================================================== -->

    <div class="management-section">

        <h2 class="section-title">
            Management Operations
        </h2>


        <div class="management-grid">


            <a href="${pageContext.request.contextPath}/seller/profile/${seller.userId}"
               class="management-card">

                <span>👤</span>

                <strong>
                    My Profile
                </strong>

                <small>
                    Manage profile
                </small>

            </a>


            <a href="${pageContext.request.contextPath}/product/list"
               class="management-card">

                <span>📦</span>

                <strong>
                    Products
                </strong>

                <small>
                    Add and manage products
                </small>

            </a>


            <a href="${pageContext.request.contextPath}/inventory/list"
   				class="management-card">

                <span>📊</span>

                <strong>
                    Inventory
                </strong>

                <small>
                    Update stock
                </small>

            </a>


            <a href="${pageContext.request.contextPath}/orders/seller/${seller.userId}"
               class="management-card">

                <span>🛒</span>

                <strong>
                    Orders 
                </strong>

                <small>
                    Manage customer orders
                </small>

            </a>


            <a href="${pageContext.request.contextPath}/seller/reviews/${seller.userId}"
			    class="dashboard-card">
			
			    <div class="card-icon">
			        ⭐
			    </div>
			
			    <h2>Reviews & Ratings</h2>
			
			    <p>
			        Customer feedback
			    </p>
			
			</a>


            <a href="${pageContext.request.contextPath}/seller/customers"
               class="management-card">

                <span>👥</span>

                <strong>
                    Customers
                </strong>

                <small>
                    Your customers
                </small>

            </a>


            <a href="${pageContext.request.contextPath}/reports/seller/${seller.userId}"
               class="management-card">

                <span>📈</span>

                <strong>
                    Sales Report
                </strong>

                <small>
                    Sales analytics
                </small>

            </a>


        </div>

    </div>

</div>


<%@ include file="../common/footer.jsp" %>