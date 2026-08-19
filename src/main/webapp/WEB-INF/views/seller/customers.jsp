<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../common/header.jsp" %>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/css/seller-dashboard.css">

<div class="seller-page">

    <div class="page-header">

        <div>
            <h1>My Customers</h1>
            <p>Customers who ordered your products</p>
        </div>

        <a href="${pageContext.request.contextPath}/seller/dashboard"
           class="btn secondary-btn">
            Dashboard
        </a>

    </div>


    <div class="table-card">

        <table>

            <thead>

            <tr>
                <th>Customer ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
            </tr>

            </thead>


            <tbody>

            <c:forEach var="customer"
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

                </tr>

            </c:forEach>


            <c:if test="${empty customers}">

                <tr>

                    <td colspan="4"
                        class="empty-message">

                        No customers found.

                    </td>

                </tr>

            </c:if>

            </tbody>

        </table>

    </div>

</div>

<%@ include file="../common/footer.jsp" %>