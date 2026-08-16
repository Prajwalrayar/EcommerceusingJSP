<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Edit Address"/>

<%@ include file="common/header.jsp" %>


<div class="form-container">

    <h1>Edit Address</h1>


    <form
            action="${pageContext.request.contextPath}/address/update"
            method="post">


        <!-- Address ID -->

        <div class="form-group">

            <label for="addressId">
                Address ID
            </label>

            <input
                    type="text"
                    id="addressId"
                    name="addressId"
                    value="${address.addressId}"
                    readonly>

        </div>


        <!-- House Number -->

        <div class="form-group">

            <label for="houseNumber">
                House Number
            </label>

            <input
                    type="text"
                    id="houseNumber"
                    name="houseNumber"
                    value="${address.houseNumber}"
                    required>

        </div>


        <!-- Street -->

        <div class="form-group">

            <label for="street">
                Street
            </label>

            <input
                    type="text"
                    id="street"
                    name="street"
                    value="${address.street}"
                    required>

        </div>


        <!-- City -->

        <div class="form-group">

            <label for="city">
                City
            </label>

            <input
                    type="text"
                    id="city"
                    name="city"
                    value="${address.city}"
                    required>

        </div>


        <!-- State -->

        <div class="form-group">

            <label for="state">
                State
            </label>

            <input
                    type="text"
                    id="state"
                    name="state"
                    value="${address.state}"
                    required>

        </div>


        <!-- Country -->

        <div class="form-group">

            <label for="country">
                Country
            </label>

            <input
                    type="text"
                    id="country"
                    name="country"
                    value="${address.country}"
                    required>

        </div>


        <!-- ZIP Code -->

        <div class="form-group">

            <label for="zipCode">
                ZIP Code
            </label>

            <input
                    type="text"
                    id="zipCode"
                    name="zipCode"
                    value="${address.zipCode}"
                    required>

        </div>


        <!-- Buttons -->

        <button
                type="submit"
                class="btn edit-btn">

            Update Address

        </button>


        <a
                href="${pageContext.request.contextPath}/address/list"
                class="btn back-btn">

            Cancel

        </a>

    </form>

</div>


<%@ include file="common/footer.jsp" %>