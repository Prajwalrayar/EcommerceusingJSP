<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Admin Login"/>

<%@ include file="../common/header.jsp" %>


<h2>Admin Login</h2>


<c:if test="${not empty error}">

    <p>
        <b>Error:</b> ${error}
    </p>

</c:if>


<form
        method="post"
        action="${pageContext.request.contextPath}/admin/login">


    <table cellpadding="8">

        <tr>

            <td>
                <label for="email">
                    Email
                </label>
            </td>

            <td>

                <input
                        type="email"
                        id="email"
                        name="email"
                        required>

            </td>

        </tr>


        <tr>

            <td>
                <label for="password">
                    Password
                </label>
            </td>

            <td>

                <input
                        type="password"
                        id="password"
                        name="password"
                        required>

            </td>

        </tr>


        <tr>

            <td colspan="2">

                <button type="submit">
                    Login
                </button>

            </td>

        </tr>

    </table>

</form>


<%@ include file="../common/footer.jsp" %>