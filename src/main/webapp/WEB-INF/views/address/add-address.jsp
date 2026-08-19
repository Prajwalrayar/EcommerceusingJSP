<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../common/header.jsp" %>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/css/address.css">


<div class="address-page">

    <div class="address-card">

        <h1>Add Address</h1>

        <p class="address-description">
            Add a new address for:
            <strong>${customer.userName}</strong>
        </p>


        <!-- =====================================================
             ADD ADDRESS FORM
             ===================================================== -->

        <form action="${pageContext.request.contextPath}/address/customer/${customerId}/add"
		    method="post">
		
		    <label>House Number</label>
		   		<input
		        	type="text"
		        	name="houseNumber"
		        	required>
		
		    <label>Street</label>
		    <input
		        type="text"
		        name="street"
		        required>
		
		    <label>City</label>
		    <input
		        type="text"
		        name="city"
		        required>
		
		    <label>State</label>
		    <input
		        type="text"
		        name="state"
		        required>
		
		    <label>Country</label>
		    <input
		        type="text"
		        name="country"
		        required>
		
		    <label>ZIP Code</label>
		    <input
		        type="text"
		        name="zipCode"
		        required>
		
		    <button
		        type="submit"
		        class="btn edit-btn">
		        Add Address
		    </button>
		
		</form>


            <!-- =====================================================
                 BUTTONS
                 ===================================================== -->

            <div class="address-actions">

                <button
                        type="submit"
                        class="add-address-btn">

                    Add Address

                </button>


                <a
                        href="${pageContext.request.contextPath}/customer/profile/${customer.userId}"
                        class="back-btn">

                    Back to Customer Profile

                </a>

            </div>

        </form>

    </div>

</div>


<%@ include file="../common/footer.jsp" %>