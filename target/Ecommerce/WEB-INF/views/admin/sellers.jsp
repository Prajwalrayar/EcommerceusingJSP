<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Sellers - Admin"/>

<%@ include file="common/header.jsp" %>


<div class="card">

    <h1>Seller Management</h1>


    <!-- Navigation -->

    <div class="nav">

        <a
                href="${pageContext.request.contextPath}/admin/customers">

            View Customers

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


    <!-- Seller Table -->

    <table>

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

                <td>

                    <!-- View Profile -->

                    <a
                            href="${pageContext.request.contextPath}/seller/profile/${seller.userId}"
                            class="btn edit-btn">

                        View Profile

                    </a>


                    <!-- Delete -->

                    <form
                            action="${pageContext.request.contextPath}/admin/sellers/delete/${seller.userId}"
                            method="post"
                            style="display:inline;"
                            onsubmit="return confirm('Are you sure you want to delete this seller?');">

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


    <c:if test="${empty sellers}">

        <div class="empty-message">

            No sellers found.

        </div>

    </c:if>

</div>


<%@ include file="common/footer.jsp" %>