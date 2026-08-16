<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Seller Profile"/>

<%@ include file="../common/header.jsp" %>


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



<div class="nav">

    <a href="${pageContext.request.contextPath}/admin/sellers">

        Back to Sellers

    </a>

</div>


<%@ include file="../common/footer.jsp" %>