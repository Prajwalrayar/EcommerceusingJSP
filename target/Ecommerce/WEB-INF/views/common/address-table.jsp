<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>


<table class="data-table">

    <thead>

        <tr>

            <th>House Number</th>
            <th>Street</th>
            <th>City</th>
            <th>State</th>
            <th>Country</th>
            <th>ZIP Code</th>

            <c:if test="${canEdit}">
                <th>Action</th>
            </c:if>

        </tr>

    </thead>


    <tbody>

        <c:choose>

            <c:when test="${not empty addresses}">

                <c:forEach
                        var="address"
                        items="${addresses}">

                    <tr>

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


                        <c:if test="${canEdit}">

                            <td>

                                <a href="${pageContext.request.contextPath}/address/edit/${address.addressId}"
                                   class="btn btn-primary">

                                    Edit

                                </a>

                            </td>

                        </c:if>

                    </tr>

                </c:forEach>

            </c:when>


            <c:otherwise>

                <tr>

                    <td colspan="${canEdit ? 7 : 6}">

                        No addresses added.

                    </td>

                </tr>

            </c:otherwise>

        </c:choose>

    </tbody>

</table>