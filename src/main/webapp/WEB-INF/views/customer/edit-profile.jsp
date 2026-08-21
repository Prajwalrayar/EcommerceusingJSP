<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="pageTitle" value="Edit Customer Profile"/>

<%@ include file="../common/header.jsp" %>


<div class="card">

    <h1>Edit Customer Profile</h1>


    <!-- =====================================================
         VALIDATION / BUSINESS ERROR
         ===================================================== -->

    <c:if test="${not empty error}">

        <div class="error-message">

            ${error}

        </div>

    </c:if>


    <form
            action="${pageContext.request.contextPath}/customer/profile/update"
            method="post">


        <!-- =====================================================
             CUSTOMER ID
             ===================================================== -->

        <div class="form-group">

            <label for="userId">
                Customer ID
            </label>

            <input
                    id="userId"
                    type="text"
                    value="${customer.userId}"
                    readonly>

            <!--
                The ID is displayed as read-only.

                It is still submitted as a hidden value because
                CustomerController receives the Customer object.
            -->
            <input
                    type="hidden"
                    name="userId"
                    value="${customer.userId}">

        </div>


        <!-- =====================================================
             NAME
             ===================================================== -->

        <div class="form-group">

            <label for="userName">
                Name
            </label>

            <input
                    id="userName"
                    type="text"
                    name="userName"
                    value="${customer.userName}"
                    required>

        </div>


        <!-- =====================================================
             EMAIL
             ===================================================== -->

        <div class="form-group">

            <label for="userEmail">
                Email
            </label>

            <input
                    id="userEmail"
                    type="email"
                    name="userEmail"
                    value="${customer.userEmail}"
                    required>

        </div>


        <!-- =====================================================
             PHONE
             ===================================================== -->

        <div class="form-group">

            <label for="userPhNo">
                Phone Number
            </label>

            <input
                    id="userPhNo"
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