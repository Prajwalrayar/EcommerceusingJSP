<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Edit Customer Profile"/>

<%@ include file="../common/header.jsp" %>


<div class="card">

    <h1>Edit Customer Profile</h1>


    <form
            action="${pageContext.request.contextPath}/customer/profile/update"
            method="post">


        <!-- =====================================================
             CUSTOMER ID
             ===================================================== -->

        <div class="form-group">

            <label>Customer ID</label>

            <input
                    type="text"
                    value="${customer.userId}"
                    readonly>

            <input
                    type="hidden"
                    name="userId"
                    value="${customer.userId}">

        </div>


        <!-- =====================================================
             NAME
             ===================================================== -->

        <div class="form-group">

            <label>Name</label>

            <input
                    type="text"
                    name="userName"
                    value="${customer.userName}"
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
                    value="${customer.userEmail}"
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
                    value="${customer.userPhNo}"
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
                href="${pageContext.request.contextPath}/customer/profile/${customer.userId}"
                class="btn">

            Cancel

        </a>


    </form>

</div>


<%@ include file="../common/footer.jsp" %>