<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Customer Profile"/>

<%@ include file="common/header.jsp" %>


<!-- ===================================================== -->
<!-- CUSTOMER PROFILE -->
<!-- ===================================================== -->

<div class="card">

    <h1>Customer Profile</h1>


    <div class="profile-row">

        <div class="profile-label">
            User ID
        </div>

        <div class="profile-value">
            ${customer.userId}
        </div>

    </div>


    <div class="profile-row">

        <div class="profile-label">
            Name
        </div>

        <div class="profile-value">
            ${customer.userName}
        </div>

    </div>


    <div class="profile-row">

        <div class="profile-label">
            Email
        </div>

        <div class="profile-value">
            ${customer.userEmail}
        </div>

    </div>


    <div class="profile-row">

        <div class="profile-label">
            Phone
        </div>

        <div class="profile-value">
            ${customer.userPhNo}
        </div>

    </div>


    <div class="profile-row">

        <div class="profile-label">
            Wallet Balance
        </div>

        <div class="profile-value">
            ₹${customer.walletBalance}
        </div>

    </div>

</div>


<!-- ===================================================== -->
<!-- CUSTOMER ADDRESSES -->
<!-- ===================================================== -->

<div class="card">

    <h2>Customer Addresses</h2>


    <a
            href="${pageContext.request.contextPath}/customer/${customer.userId}/addresses/add"
            class="btn add-btn">

        Add / Assign Address

    </a>


    <br>
    <br>


    <!-- ================================================= -->
    <!-- REUSABLE ADDRESS TABLE -->
    <!-- ================================================= -->

    <c:set var="userType" value="customer"/>
    <c:set var="userId" value="${customer.userId}"/>

    <%@ include file="common/address-table.jsp" %>


</div>


<!-- ===================================================== -->
<!-- NAVIGATION -->
<!-- ===================================================== -->

<div class="nav">

    <a
            href="${pageContext.request.contextPath}/admin/customers">

        Back to Customers

    </a>

</div>


<%@ include file="common/footer.jsp" %>