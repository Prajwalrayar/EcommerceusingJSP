<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<table>

    <thead>

    <tr>

        <th>Address ID</th>
        <th>House Number</th>
        <th>Street</th>
        <th>City</th>
        <th>State</th>
        <th>Country</th>
        <th>ZIP Code</th>
        <th>Action</th>

    </tr>

    </thead>


    <tbody>

    <c:forEach
            var="address"
            items="${addresses}">

        <tr>

            <td>
                ${address.addressId}
            </td>

            <td>
                ${address.houseNumber}
            </td>

            <td>
                ${address.street}
            </td>

            <td>
                ${address.city}
            </td>

            <td>
                ${address.state}
            </td>

            <td>
                ${address.country}
            </td>

            <td>
                ${address.zipCode}
            </td>

            <td>

                <form
                        action="${pageContext.request.contextPath}/${userType}/${userId}/addresses/remove/${address.addressId}"
                        method="post"
                        style="display:inline;"
                        onsubmit="return confirm('Remove this address?');">

                    <button
                            type="submit"
                            class="btn remove-btn">

                        Remove

                    </button>

                </form>

            </td>

        </tr>

    </c:forEach>

    </tbody>

</table>


<c:if test="${empty addresses}">

    <div class="empty-message">

        No addresses assigned.

    </div>

</c:if>