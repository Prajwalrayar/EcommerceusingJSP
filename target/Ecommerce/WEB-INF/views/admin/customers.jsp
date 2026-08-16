<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Customers - Admin"/>

<%@ include file="common/header.jsp" %>


<div class="card">

    <h1>Customer Management</h1>


    <!-- Navigation -->

    <div class="nav">

        <a
                href="${pageContext.request.contextPath}/admin/sellers">

            View Sellers

        </a>


        <a
                href="${pageContext.request.contextPath}/admin/profile?adminId=ADM001">

            Admin Profile

        </a>


        <a
                href="${pageContext.request.contextPath}/address/list">

            Address Management

        </a>


    </div>


    <!-- Customer Table -->

    <table>

        <thead>

        <tr>

            <th>User ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Action</th>

        </tr>

        </thead>


        <tbody>

        <c:forEach
                var="customer"
                items="${customers}">

            <tr>

                <td>
                    ${customer.userId}
                </td>

                <td>
                    ${customer.userName}
                </td>

                <td>
                    ${customer.userEmail}
                </td>

                <td>
                    ${customer.userPhNo}
                </td>

                <td>

                    <!-- View Profile -->

                    <a
                            href="${pageContext.request.contextPath}/customer/profile/${customer.userId}"
                            class="btn edit-btn">

                        View Profile

                    </a>


                    <!-- Delete -->

                    <form
                            action="${pageContext.request.contextPath}/admin/customers/delete/${customer.userId}"
                            method="post"
                            style="display:inline;"
                            onsubmit="return confirm('Are you sure you want to delete this customer?');">

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


    <c:if test="${empty customers}">

        <div class="empty-message">

            No customers found.

        </div>

    </c:if>

</div>


<%@ include file="common/footer.jsp" %>