<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Customer Profile"/>

<%@ include file="../common/header.jsp" %>


<!-- ==========================================================
     CUSTOMER PROFILE
     ========================================================== -->

<div class="card">

    <h1>Customer Profile</h1>


    <!-- ======================================================
         CUSTOMER ID
         ====================================================== -->

    <div class="profile-row">

        <div class="profile-label">
            User ID
        </div>

        <div class="profile-value">
            ${customer.userId}
        </div>

    </div>


    <!-- ======================================================
         CUSTOMER NAME
         ====================================================== -->

    <div class="profile-row">

        <div class="profile-label">
            Name
        </div>

        <div class="profile-value">
            ${customer.userName}
        </div>

    </div>


    <!-- ======================================================
         CUSTOMER EMAIL
         ====================================================== -->

    <div class="profile-row">

        <div class="profile-label">
            Email
        </div>

        <div class="profile-value">
            ${customer.userEmail}
        </div>

    </div>


    <!-- ======================================================
         CUSTOMER PHONE
         ====================================================== -->

    <div class="profile-row">

        <div class="profile-label">
            Phone
        </div>

        <div class="profile-value">
            ${customer.userPhNo}
        </div>

    </div>


    <!-- ======================================================
         WALLET BALANCE
         ====================================================== -->

    <div class="profile-row">

        <div class="profile-label">
            Wallet Balance
        </div>

        <div class="profile-value">
            ₹${customer.walletBalance}
        </div>

    </div>


    <!-- ======================================================
         EDIT PROFILE
         ====================================================== -->

    <div class="actions">

        <a
                class="btn edit-btn"
                href="${pageContext.request.contextPath}/customer/profile/edit/${customer.userId}">

            Edit Profile

        </a>

    </div>

</div>


<!-- ==========================================================
     CUSTOMER ADDRESSES
     ========================================================== -->

<div class="card">

    <h2>Customer Addresses</h2>


    <div class="actions">

        <a
                class="btn btn-primary"
                href="${pageContext.request.contextPath}/customer/${customer.userId}/addresses/add">

            Add / Assign Address

        </a>

    </div>


    <!-- ======================================================
         REUSABLE ADDRESS TABLE
         ====================================================== -->

    <c:set
            var="userType"
            value="customer"/>

    <c:set
            var="userId"
            value="${customer.userId}"/>

    <%@ include file="../common/address-table.jsp" %>

</div>


<!-- ==========================================================
     CUSTOMER NAVIGATION
     ========================================================== -->

<div class="nav">

    <a
            href="${pageContext.request.contextPath}/customer/dashboard">

        Back to Dashboard

    </a>

</div>


<%@ include file="../common/footer.jsp" %>