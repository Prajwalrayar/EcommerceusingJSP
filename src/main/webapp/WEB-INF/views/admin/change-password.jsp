<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Change Password"/>

<%@ include file="../common/header.jsp" %>


<div class="card password-card">

    <h1>Change Password</h1>


    <!-- ERROR -->

    <c:if test="${not empty error}">

        <div class="error-message">
            ${error}
        </div>

    </c:if>


    <!-- SUCCESS -->

    <c:if test="${not empty success}">

        <div class="success-message">
            ${success}
        </div>

    </c:if>


    <form
            method="post"
            action="${pageContext.request.contextPath}/admin/profile/change-password"
            onsubmit="return confirmPasswordChange();">


        <input
                type="hidden"
                name="adminId"
                value="${adminId}">


        <!-- CURRENT PASSWORD -->

        <div class="form-group">

            <label for="currentPassword">
                Current Password
            </label>

            <div class="password-wrapper">

                <input
                        type="password"
                        id="currentPassword"
                        name="currentPassword"
                        required>

                <button
                        type="button"
                        class="password-toggle"
                        onclick="togglePassword('currentPassword', this)"
                        title="Show password">

                    👁

                </button>

            </div>

        </div>


        <!-- NEW PASSWORD -->

        <div class="form-group">

            <label for="newPassword">
                New Password
            </label>

            <div class="password-wrapper">

                <input
                        type="password"
                        id="newPassword"
                        name="newPassword"
                        required>

                <button
                        type="button"
                        class="password-toggle"
                        onclick="togglePassword('newPassword', this)"
                        title="Show password">

                    👁

                </button>

            </div>

        </div>


        <!-- CONFIRM PASSWORD -->

        <div class="form-group">

            <label for="confirmPassword">
                Confirm New Password
            </label>

            <div class="password-wrapper">

                <input
                        type="password"
                        id="confirmPassword"
                        name="confirmPassword"
                        required>

                <button
                        type="button"
                        class="password-toggle"
                        onclick="togglePassword('confirmPassword', this)"
                        title="Show password">

                    👁

                </button>

            </div>

        </div>


        <!-- BUTTONS -->

        <div class="actions">

            <button
                    type="submit"
                    class="btn btn-primary">

                Change Password

            </button>


            <a
                    href="${pageContext.request.contextPath}/admin/profile?adminId=${adminId}"
                    class="btn btn-secondary">

                Cancel

            </a>

        </div>

    </form>

</div>


<style>

    .password-card {

        max-width: 650px;

        margin: 40px auto;

    }


    .password-card h1 {

        margin-bottom: 30px;

    }


    .form-group {

        margin-bottom: 20px;

    }


    .form-group label {

        display: block;

        margin-bottom: 8px;

        font-weight: 600;

    }


    /* Password input + eye button */

    .password-wrapper {

        position: relative;

        width: 100%;

    }


    .password-wrapper input {

        width: 100%;

        padding: 12px 48px 12px 12px;

        box-sizing: border-box;

        border: 1px solid #ced4da;

        border-radius: 6px;

        font-size: 16px;

    }


    .password-toggle {

        position: absolute;

        right: 10px;

        top: 50%;

        transform: translateY(-50%);

        border: none;

        background: transparent;

        cursor: pointer;

        font-size: 18px;

        padding: 5px;

    }


    .password-toggle:hover {

        opacity: 0.7;

    }


    .actions {

        display: flex;

        gap: 15px;

        margin-top: 25px;

    }


    .btn {

        display: inline-block;

        padding: 11px 20px;

        border-radius: 6px;

        border: none;

        text-decoration: none;

        font-size: 15px;

        cursor: pointer;

    }


    .btn-primary {

        background-color: #0d6efd;

        color: white;

    }


    .btn-secondary {

        background-color: #6c757d;

        color: white;

    }


    .error-message {

        margin-bottom: 20px;

        padding: 12px;

        border-radius: 6px;

        background-color: #f8d7da;

        color: #842029;

        border: 1px solid #f5c2c7;

    }


    .success-message {

        margin-bottom: 20px;

        padding: 12px;

        border-radius: 6px;

        background-color: #d1e7dd;

        color: #0f5132;

        border: 1px solid #badbcc;

    }

</style>


<script>

    function togglePassword(inputId, button) {

        const passwordInput =
                document.getElementById(inputId);


        if (passwordInput.type === "password") {

            passwordInput.type = "text";

            button.innerHTML = "🙈";

            button.title = "Hide password";

        } else {

            passwordInput.type = "password";

            button.innerHTML = "👁";

            button.title = "Show password";

        }

    }


    function confirmPasswordChange() {

        return confirm(
            "Are you sure you want to change your password?"
        );

    }

</script>


<%@ include file="../common/footer.jsp" %>