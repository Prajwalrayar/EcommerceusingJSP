<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Seller Registration"/>

<%@ include file="../common/header.jsp" %>


<h2>Seller Registration</h2>


<c:if test="${not empty error}">

    <p>
        <b>Error:</b> ${error}
    </p>

</c:if>


<form
        method="post"
        action="${pageContext.request.contextPath}/seller/register">


    <table cellpadding="8">


        <tr>

            <td>
                Name
            </td>

            <td>

                <input
                        type="text"
                        name="userName"
                        required>

            </td>

        </tr>


        <tr>

            <td>
                Email
            </td>

            <td>

                <input
                        type="email"
                        name="userEmail"
                        required>

            </td>

        </tr>


        <tr>

            <td>
                Phone Number
            </td>

            <td>

                <input
                        type="text"
                        name="userPhNo"
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
                        name="userPassword"
                        required>

            </td>

        </tr>


        <tr>

            <td colspan="2">

                <h3>
                    Shop Information
                </h3>

            </td>

        </tr>


        <tr>

            <td>
                Shop Name
            </td>

            <td>

                <input
                        type="text"
                        name="shopName"
                        required>

            </td>

        </tr>


        <tr>

            <td>
                Shop Address
            </td>

            <td>

                <textarea
                        name="shopAddress"
                        rows="4"
                        cols="30"
                        required></textarea>

            </td>

        </tr>


        <tr>

            <td colspan="2">

                <button type="submit">
                    Register
                </button>

            </td>

        </tr>


    </table>

</form>


<p>

    Already registered?

    <a href="${pageContext.request.contextPath}/seller/login">
        Login
    </a>

</p>


<%@ include file="../common/footer.jsp" %>