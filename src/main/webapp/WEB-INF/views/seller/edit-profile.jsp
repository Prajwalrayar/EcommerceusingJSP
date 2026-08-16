<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Edit Seller Profile"/>

<%@ include file="../common/header.jsp" %>


<div class="card">

    <h1>Edit Seller Profile</h1>


    <form
            action="${pageContext.request.contextPath}/seller/profile/update"
            method="post">


        <!-- =====================================================
             SELLER ID
             ===================================================== -->

        <div class="form-group">

            <label>Seller ID</label>

            <input
                    type="text"
                    value="${seller.userId}"
                    readonly>

            <input
                    type="hidden"
                    name="userId"
                    value="${seller.userId}">

        </div>


        <!-- =====================================================
             NAME
             ===================================================== -->

        <div class="form-group">

            <label>Name</label>

            <input
                    type="text"
                    name="userName"
                    value="${seller.userName}"
                    required>

        </div>


        <!-- =====================================================
             EMAIL
             ===================================================== -->

        <div class="form-group">

            <label>Email</label>

            <input
                    type="email"
                    name="userEmail"
                    value="${seller.userEmail}"
                    required>

        </div>


        <!-- =====================================================
             PHONE
             ===================================================== -->

        <div class="form-group">

            <label>Phone Number</label>

            <input
                    type="text"
                    name="userPhNo"
                    value="${seller.userPhNo}"
                    required>

        </div>


        <!-- =====================================================
             SHOP NAME
             ===================================================== -->

        <div class="form-group">

            <label>Shop Name</label>

            <input
                    type="text"
                    name="shopName"
                    value="${seller.shopName}"
                    required>

        </div>


        <!-- =====================================================
             SHOP ADDRESS
             ===================================================== -->

        <div class="form-group">

            <label>Shop Address</label>

            <input
                    type="text"
                    name="shopAddress"
                    value="${seller.shopAddress}"
                    required>

        </div>


        <!-- =====================================================
             ACTIONS
             ===================================================== -->

        <button
                type="submit"
                class="btn edit-btn">

            Update Profile

        </button>


        <a
                href="${pageContext.request.contextPath}/seller/profile/${seller.userId}"
                class="btn">

            Cancel

        </a>


    </form>

</div>


<%@ include file="../common/footer.jsp" %>