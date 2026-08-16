<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Edit Admin Profile"/>

<%@ include file="../common/header.jsp" %>


<div class="card">

    <h1>Edit Admin Profile</h1>


    <!-- ===================================================== -->
    <!-- ERROR MESSAGE -->
    <!-- ===================================================== -->

    <c:if test="${not empty error}">

        <div class="alert alert-danger">
            ${error}
        </div>

    </c:if>


    <form id="adminProfileForm"
          method="post"
          action="${pageContext.request.contextPath}/admin/profile/update">


        <!-- ================================================= -->
        <!-- ADMIN ID -->
        <!-- READ ONLY -->
        <!-- ================================================= -->

        <div class="form-group">

            <label for="userId">
                Admin ID
            </label>

            <input type="text"
                   id="userId"
                   value="${admin.userId}"
                   readonly
                   class="readonly-field">

            <!-- Actual value submitted to Spring -->
            <input type="hidden"
                   name="userId"
                   value="${admin.userId}">

        </div>


        <!-- ================================================= -->
        <!-- NAME -->
        <!-- READ ONLY -->
        <!-- ================================================= -->

        <div class="form-group">

            <label for="userName">
                Name
            </label>

            <input type="text"
                   id="userName"
                   value="${admin.userName}"
                   readonly
                   class="readonly-field">

            <input type="hidden"
                   name="userName"
                   value="${admin.userName}">

        </div>


        <!-- ================================================= -->
        <!-- EMAIL -->
        <!-- READ ONLY -->
        <!-- ================================================= -->

        <div class="form-group">

            <label for="userEmail">
                Email
            </label>

            <input type="email"
                   id="userEmail"
                   value="${admin.userEmail}"
                   readonly
                   class="readonly-field">

            <input type="hidden"
                   name="userEmail"
                   value="${admin.userEmail}">

        </div>


        <!-- ================================================= -->
        <!-- PHONE -->
        <!-- EDITABLE -->
        <!-- ================================================= -->

        <div class="form-group">

            <label for="userPhNo">
                Phone Number
            </label>

            <input type="text"
                   id="userPhNo"
                   name="userPhNo"
                   value="${admin.userPhNo}"
                   maxlength="10"
                   pattern="[6-9][0-9]{9}"
                   required>

            <small class="field-hint">
                Enter a valid 10-digit Indian mobile number.
            </small>

        </div>


        <!-- ================================================= -->
        <!-- ACTIONS -->
        <!-- ================================================= -->

        <div class="actions">

            <button type="submit"
                    id="updateButton"
                    class="btn btn-primary"
                    disabled>

                Update

            </button>


            <a href="${pageContext.request.contextPath}/admin/profile?adminId=${admin.userId}"
               class="btn btn-secondary">

                Cancel

            </a>

        </div>

    </form>

</div>


<style>

    /* ========================================= */
    /* FORM GROUP */
    /* ========================================= */

    .form-group {

        margin-bottom: 24px;

    }


    .form-group label {

        display: block;

        font-weight: 600;

        font-size: 18px;

        margin-bottom: 8px;

    }


    .form-group input {

        width: 100%;

        box-sizing: border-box;

        padding: 13px 15px;

        font-size: 16px;

        border: 1px solid #ccc;

        border-radius: 6px;

    }


    /* ========================================= */
    /* READ ONLY FIELDS */
    /* ========================================= */

    .readonly-field {

        background-color: #f1f3f5;

        color: #6c757d;

        border-color: #d6d9dc !important;

        cursor: not-allowed;

    }


    /* ========================================= */
    /* EDITABLE PHONE FIELD */
    /* ========================================= */

    #userPhNo {

        background-color: #ffffff;

        color: #212529;

    }


    #userPhNo:focus {

        outline: none;

        border-color: #0d6efd;

        box-shadow: 0 0 0 3px rgba(13, 110, 253, 0.15);

    }


    /* ========================================= */
    /* HINT */
    /* ========================================= */

    .field-hint {

        display: block;

        margin-top: 6px;

        color: #6c757d;

        font-size: 13px;

    }


    /* ========================================= */
    /* BUTTONS */
    /* ========================================= */

    .actions {

        display: flex;

        gap: 15px;

        margin-top: 30px;

        align-items: center;

    }


    .btn {

        display: inline-block;

        padding: 11px 22px;

        border-radius: 6px;

        border: none;

        font-size: 16px;

        text-decoration: none;

        cursor: pointer;

    }


    .btn-primary {

        background-color: #0d6efd;

        color: white;

    }


    .btn-primary:disabled {

        background-color: #adb5bd;

        cursor: not-allowed;

        opacity: 0.7;

    }


    .btn-secondary {

        background-color: #6c757d;

        color: white;

    }


    .btn-secondary:hover {

        background-color: #5c636a;

    }


    /* ========================================= */
    /* ERROR */
    /* ========================================= */

    .alert-danger {

        background-color: #f8d7da;

        color: #842029;

        border: 1px solid #f5c2c7;

        padding: 12px 15px;

        border-radius: 6px;

        margin-bottom: 25px;

    }

</style>


<script>

    /*
     * Original phone number when page loads.
     */
    const originalPhone =
        document.getElementById("userPhNo").value;


    const phoneInput =
        document.getElementById("userPhNo");


    const updateButton =
        document.getElementById("updateButton");


    const form =
        document.getElementById("adminProfileForm");


    /*
     * Enable Update button ONLY when
     * the phone number has actually changed.
     */
    phoneInput.addEventListener("input", function () {

        const currentPhone =
            phoneInput.value.trim();


        if (currentPhone !== originalPhone) {

            updateButton.disabled = false;

        } else {

            updateButton.disabled = true;

        }

    });


    /*
     * Confirmation before changing phone number.
     */
    form.addEventListener("submit", function (event) {

        const currentPhone =
            phoneInput.value.trim();


        /*
         * Safety check.
         *
         * If nothing changed, don't submit.
         */
        if (currentPhone === originalPhone) {

            event.preventDefault();

            updateButton.disabled = true;

            return;

        }


        /*
         * Ask user for confirmation.
         */
        const confirmed =
            confirm(
                "Are you sure you want to change your phone number?"
            );


        /*
         * User clicked NO.
         */
        if (!confirmed) {

            event.preventDefault();


            /*
             * Restore original phone number.
             */
            phoneInput.value =
                originalPhone;


            /*
             * Disable Update again.
             */
            updateButton.disabled = true;

        }

        /*
         * User clicked YES.
         *
         * Form submission continues normally.
         */

    });

</script>


<%@ include file="../common/footer.jsp" %>