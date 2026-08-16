<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Seller Login"/>

<%@ include file="../common/header.jsp" %>


<h2>Seller Login</h2>


<c:if test="${not empty error}">

    <p>
        <b>Error:</b> ${error}
    </p>

</c:if>


<form
        method="post"
        action="${pageContext.request.contextPath}/seller/login">


    <table cellpadding="8">

        <tr>

            <td>
                Email
            </td>

            <td>

                <input
                        type="email"
                        name="email"
                        required>

            </td>

        </tr>


        <tr>

            <td>
                Password
            </td>

            <td>

                <input
                        type="password"
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


<p>

    Don't have a seller account?

    <a href="${pageContext.request.contextPath}/seller/register">
        Register
    </a>

</p>


<%@ include file="../common/footer.jsp" %>