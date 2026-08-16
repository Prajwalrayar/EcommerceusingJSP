<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Seller Profile"/>

<%@ include file="../common/header.jsp" %>


<!-- ===================================================== -->
<!-- SELLER PROFILE -->
<!-- ===================================================== -->

<div class="card">

    <h1>Seller Profile</h1>


    <div class="profile-row">

        <div class="profile-label">
            Seller ID
        </div>

        <div class="profile-value">
            ${seller.userId}
        </div>

    </div>


    <div class="profile-row">

        <div class="profile-label">
            Name
        </div>

        <div class="profile-value">
            ${seller.userName}
        </div>

    </div>


    <div class="profile-row">

        <div class="profile-label">
            Email
        </div>

        <div class="profile-value">
            ${seller.userEmail}
        </div>

    </div>


    <div class="profile-row">

        <div class="profile-label">
            Phone
        </div>

        <div class="profile-value">
            ${seller.userPhNo}
        </div>

    </div>


    <div class="profile-row">

        <div class="profile-label">
            Shop Name
        </div>

        <div class="profile-value">
            ${seller.shopName}
        </div>

    </div>


    <div class="profile-row">

        <div class="profile-label">
            Shop Address
        </div>

        <div class="profile-value">
            ${seller.shopAddress}
        </div>

    </div>

</div>


<!-- ===================================================== -->
<!-- SELLER ADDRESSES -->
<!-- ===================================================== -->

<div class="card">

    <h2>Seller Addresses</h2>


    <a
            href="${pageContext.request.contextPath}/seller/${seller.userId}/addresses/add"
            class="btn add-btn">

        Add / Assign Address

    </a>


    <br>
    <br>


    <!-- Reusable Address Table -->

    <c:set
            var="userType"
            value="seller"/>

    <c:set
            var="userId"
            value="${seller.userId}"/>


    <%@ include file="../common/address-table.jsp" %>

</div>


<!-- ===================================================== -->
<!-- NAVIGATION -->
<!-- ===================================================== -->

<div class="nav">

    <a
            href="${pageContext.request.contextPath}/admin/sellers">

        Back to Sellers

    </a>

</div>


<%@ include file="../common/footer.jsp" %>