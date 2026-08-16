<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Assign Customer Address"/>

<%@ include file="common/header.jsp" %>


<div class="form-container">

    <h1>
        Assign Address
    </h1>


    <p>

        Assign an address to:

        <strong>
            ${customer.userName}
        </strong>

    </p>


    <form
            action="${pageContext.request.contextPath}/customer/${customer.userId}/addresses/add"
            method="post">


        <div class="form-group">

            <label for="addressId">
                Select Address
            </label>


            <select
                    id="addressId"
                    name="addressId"
                    required>

                <option value="">
                    -- Select Address --
                </option>


                <c:forEach
                        var="address"
                        items="${addresses}">

                    <option
                            value="${address.addressId}">

                        ${address.addressId}
                        -
                        ${address.houseNumber},
                        ${address.street},
                        ${address.city},
                        ${address.state},
                        ${address.country}
                        -
                        ${address.zipCode}

                    </option>

                </c:forEach>

            </select>

        </div>


        <c:if test="${empty addresses}">

            <div class="empty-message">

                No addresses are available to assign.

            </div>

        </c:if>


        <button
                type="submit"
                class="btn add-btn">

            Assign Address

        </button>


        <a
                href="${pageContext.request.contextPath}/customer/profile/${customer.userId}"
                class="btn back-btn">

            Back to Customer Profile

        </a>

    </form>

</div>


<%@ include file="common/footer.jsp" %>