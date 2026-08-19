<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../common/header.jsp" %>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/css/seller-dashboard.css">

<div class="seller-dashboard">

    <div class="dashboard-header">

        <div>
            <h1>Seller Dashboard</h1>

            <p>
                Welcome,
                <strong>${seller.userName}</strong>
            </p>
        </div>

        <a href="${pageContext.request.contextPath}/logout"
           class="btn btn-danger">
            Logout
        </a>

    </div>


    <hr>


    <h2>System Overview</h2>

    <div class="dashboard-grid">

        <div class="dashboard-card">

            <div class="value">
                ${totalProducts}
            </div>

            <div class="label">
                Total Products
            </div>

        </div>


        <div class="dashboard-card">

            <div class="value">
                ₹${totalRevenue}
            </div>

            <div class="label">
                Total Revenue
            </div>

        </div>


        <div class="dashboard-card">

            <div class="value">
                ${totalSoldQuantity}
            </div>

            <div class="label">
                Total Sold Quantity
            </div>

        </div>


        <div class="dashboard-card">

            <div class="value">
                ₹${averageRevenue}
            </div>

            <div class="label">
                Average Revenue
            </div>

        </div>


        <div class="dashboard-card">

            <div class="value">
                ${averageSoldQuantity}
            </div>

            <div class="label">
                Average Sold Quantity
            </div>

        </div>


        <div class="dashboard-card">

            <div class="value">
                ${averageRating}
            </div>

            <div class="label">
                Average Rating
            </div>

        </div>

    </div>


    <div class="management-section">

        <h2>Management Operations</h2>

        <div class="management-grid">

            <a href="${pageContext.request.contextPath}/seller/profile"
               class="management-card">
                My Profile
            </a>

            <a href="${pageContext.request.contextPath}/product/list"
               class="management-card">
                Products
            </a>

            <a href="${pageContext.request.contextPath}/inventory"
               class="management-card">
                Inventory
            </a>

            <a href="${pageContext.request.contextPath}/seller/orders"
               class="management-card">
                Orders ▼
            </a>

            <a href="${pageContext.request.contextPath}/seller/reviews"
               class="management-card">
                Reviews & Ratings
            </a>

            <a href="${pageContext.request.contextPath}/seller/customers"
               class="management-card">
                Customers
            </a>

            <a href="${pageContext.request.contextPath}/seller/sales"
               class="management-card">
                Sales Report
            </a>

        </div>

    </div>

</div>

<%@ include file="../common/footer.jsp" %>