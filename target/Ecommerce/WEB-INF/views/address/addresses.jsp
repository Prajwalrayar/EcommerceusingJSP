<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Address Management"/>

<%@ include file="common/header.jsp" %>


<div class="card">

    <h1>Address Management</h1>


    <!-- Navigation -->

    <div class="nav">

        <a
                href="${pageContext.request.contextPath}/admin/customers">

            Customers

        </a>


        <a
                href="${pageContext.request.contextPath}/admin/sellers">

            Sellers

        </a>


        <a
                href="${pageContext.request.contextPath}/admin/profile?adminId=ADM001">

            Admin Profile

        </a>

    </div>


    <!-- Add Address -->

    <a
            href="${pageContext.request.contextPath}/address/add"
            class="btn add-btn">

        Add Address

    </a>


    <br>
    <br>


    <!-- Address Table -->

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
            <th>Actions</th>

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

                    <!-- Edit -->

                    <a
                            href="${pageContext.request.contextPath}/address/edit/${address.addressId}"
                            class="btn edit-btn">

                        Edit

                    </a>


                    <!-- Delete -->

                    <form
                            action="${pageContext.request.contextPath}/address/delete/${address.addressId}"
                            method="post"
                            style="display:inline;"
                            onsubmit="return confirm('Delete this address?');">

                        <button
                                type="submit"
                                class="btn delete-btn">

                            Delete

                        </button>

                    </form>

                </td>

            </tr>

        </c:forEach>

        </tbody>

    </table>


    <c:if test="${empty addresses}">

        <div class="empty-message">

            No addresses found.

        </div>

    </c:if>

</div>


<%@ include file="common/footer.jsp" %>