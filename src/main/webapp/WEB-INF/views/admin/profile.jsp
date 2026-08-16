<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Admin Profile"/>

<%@ include file="../common/header.jsp" %>


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


    <!-- Main Actions -->

    <div class="actions">

        <a href="${pageContext.request.contextPath}/admin/profile/edit"
           class="btn btn-primary">
            Edit Profile
        </a>

 		<a href="${pageContext.request.contextPath}/admin/profile/change-password?adminId=${admin.userId}"
           class="btn btn-warning">
        	Change Password
    	</a>
        <a href="${pageContext.request.contextPath}/admin/dashboard"
           class="btn btn-secondary">
            Back to Dashboard
        </a>

    </div>


    <!-- Admin Navigation -->

    <div class="nav">

        <a href="${pageContext.request.contextPath}/admin/customers"
           class="btn btn-secondary">
            Customers
        </a>

        <a href="${pageContext.request.contextPath}/admin/sellers"
           class="btn btn-secondary">
            Sellers
        </a>

    </div>

</div>


<%@ include file="../common/footer.jsp" %>