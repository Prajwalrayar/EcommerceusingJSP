<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Sellers - Admin"/>

<%@ include file="../common/header.jsp" %>


<div class="card">

    <h1>Seller Management</h1>


    <div class="nav">

        <a href="${pageContext.request.contextPath}/admin/customers">
            View Customers
        </a>

        <a href="${pageContext.request.contextPath}/admin/profile">
            Admin Profile
        </a>

    </div>


    <table class="data-table">

        <thead>

            <tr>

                <th>Seller ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Shop Name</th>
                <th>Action</th>

            </tr>

        </thead>


        <tbody>

            <c:choose>

                <c:when test="${not empty sellers}">

                    <c:forEach
                            var="seller"
                            items="${sellers}">

                        <tr>

                            <td>
                                ${seller.userId}
                            </td>

                            <td>
                                ${seller.userName}
                            </td>

                            <td>
                                ${seller.userEmail}
                            </td>

                            <td>
                                ${seller.userPhNo}
                            </td>

                            <td>
                                ${seller.shopName}
                            </td>

                            <td class="action-cell">

                                <a
                                    href="${pageContext.request.contextPath}/admin/seller/${seller.userId}"
                                    class="btn btn-primary">

                                    View Profile

                                </a>


                                <form
                                    method="post"
                                    action="${pageContext.request.contextPath}/admin/sellers/delete/${seller.userId}"
                                    style="display:inline;"
                                    onsubmit="return confirm('Are you sure you want to delete this seller?');">

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

                        <td colspan="6">

                            No sellers found.

                        </td>

                    </tr>

                </c:otherwise>

            </c:choose>

        </tbody>

    </table>

</div>


<%@ include file="../common/footer.jsp" %>