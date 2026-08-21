<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Assign Customer Address"/>

<%@ include file="../common/header.jsp" %>



<div class="form-container">

    <h1>
        Add Address
    </h1>


    <p>Add a new address for: <strong>${customer.userName}</strong></p>


    <form
            action="${pageContext.request.contextPath}/customer/${customer.userId}/addresses/add"
            method="post">


        <div>
		    <label>House Number</label>
		    <input type="text" name="houseNumber" required>
		</div>
		
		<div>
		    <label>Street</label>
		    <input type="text" name="street" required>
		</div>
		
		<div>
		    <label>City</label>
		    <input type="text" name="city" required>
		</div>
		
		<div>
		    <label>State</label>
		    <input type="text" name="state" required>
		</div>
		
		<div>
		    <label>Country</label>
		    <input type="text" name="country" required>
		</div>
		
		<div>
		    <label>ZIP Code</label>
		    <input type="text" name="zipCode" required>
		</div>


        <c:if test="${empty addresses}">

            <div class="empty-message">

                No addresses are available to assign.

            </div>

        </c:if>


        <button
                type="submit"
                class="btn add-btn">

            Add Address

        </button>


        <a
                href="${pageContext.request.contextPath}/customer/profile/${customer.userId}"
                class="btn back-btn">

            Back to Customer Profile

        </a>

    </form>

</div>


<%@ include file="../common/footer.jsp" %>