<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Edit Admin Profile"/>

<%@ include file="../common/header.jsp" %>


<div class="card">

    <h1>Edit Admin Profile</h1>


    <form
            action="${pageContext.request.contextPath}/admin/profile/update"
            method="post">


        <!-- =====================================================
             ADMIN ID
             ===================================================== -->

        <div class="form-group">

            <label>Admin ID</label>

            <input
                    type="text"
                    value="${admin.userId}"
                    readonly>

            <!-- ID must still be submitted -->

            <input
                    type="hidden"
                    name="userId"
                    value="${admin.userId}">

        </div>


        <!-- =====================================================
             NAME
             ===================================================== -->

        <div class="form-group">

            <label>Name</label>

            <input
                    type="text"
                    value="${admin.userName}"
                    readonly>

        </div>


        <!-- =====================================================
             EMAIL
             ===================================================== -->

        <div class="form-group">

            <label>Email</label>

            <input
                    type="email"
                    value="${admin.userEmail}"
                    readonly>

        </div>


        <!-- =====================================================
             PHONE
             ADMIN IS ALLOWED TO EDIT THIS
             ===================================================== -->

        <div class="form-group">

            <label>Phone Number</label>

            <input
                    type="text"
                    name="userPhNo"
                    value="${admin.userPhNo}"
                    required>

        </div>


        <!-- =====================================================
             ACTIONS
             ===================================================== -->

        <button
                type="submit"
                class="btn edit-btn">

            Update Phone

        </button>


        <a
                href="${pageContext.request.contextPath}/admin/profile?adminId=${admin.userId}"
                class="btn">

            Cancel

        </a>


    </form>

</div>


<%@ include file="../common/footer.jsp" %>