<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Customers - Admin"/>

<%@ include file="../common/header.jsp" %>


<div class="card">

    <h1>Customer Management</h1>


    <div class="nav">

        <a href="${pageContext.request.contextPath}/admin/sellers">
            View Sellers
        </a>

        <a href="${pageContext.request.contextPath}/admin/profile">
            Admin Profile
        </a>

    </div>


    <table class="data-table">

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

            <c:choose>

                <c:when test="${not empty customers}">

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

                            <td class="action-cell">

                                <a
                                    href="${pageContext.request.contextPath}/admin/customer/${customer.userId}"
                                    class="btn btn-primary">

                                    View Profile

                                </a>


                                <form
                                    method="post"
                                    action="${pageContext.request.contextPath}/admin/customers/delete/${customer.userId}"
                                    style="display:inline;"
                                    onsubmit="return confirm('Are you sure you want to delete this customer?');">

                                    <button
                                        type="submit"
                                        class="btn btn-danger">

                                        Delete

                                    </button>

                                </form>

                            </td>

                        </tr>

                    </c:forEach>

                </c:when>


                <c:otherwise>

                    <tr>

                        <td colspan="5">

                            No customers found.

                        </td>

                    </tr>

                </c:otherwise>

            </c:choose>

        </tbody>

    </table>

</div>


<%@ include file="../common/footer.jsp" %>