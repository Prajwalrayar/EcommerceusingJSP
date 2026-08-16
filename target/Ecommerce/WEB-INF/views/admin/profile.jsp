<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Admin Profile"/>

<%@ include file="common/header.jsp" %>


<div class="card">

    <h1>Admin Profile</h1>


    <!-- Admin ID -->

    <div class="profile-row">

        <div class="profile-label">
            Admin ID
        </div>

        <div class="profile-value">
            ${admin.userId}
        </div>

    </div>


    <!-- Name -->

    <div class="profile-row">

        <div class="profile-label">
            Name
        </div>

        <div class="profile-value">
            ${admin.userName}
        </div>

    </div>


    <!-- Email -->

    <div class="profile-row">

        <div class="profile-label">
            Email
        </div>

        <div class="profile-value">
            ${admin.userEmail}
        </div>

    </div>


    <!-- Phone -->

    <div class="profile-row">

        <div class="profile-label">
            Phone
        </div>

        <div class="profile-value">
            ${admin.userPhNo}
        </div>

    </div>


    <!-- Buttons -->

    <div class="nav">

        <a
                class="btn edit-btn"
                href="${pageContext.request.contextPath}/admin/profile/edit?adminId=${admin.userId}">

            Edit Profile

        </a>


        <a
                class="btn add-btn"
                href="${pageContext.request.contextPath}/admin/customers">

            Customers

        </a>


        <a
                class="btn back-btn"
                href="${pageContext.request.contextPath}/admin/sellers">

            Sellers

        </a>


        <a
                class="btn back-btn"
                href="${pageContext.request.contextPath}/address/list">

            Addresses

        </a>

    </div>

</div>


<%@ include file="common/footer.jsp" %>