<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Customer Registration"/>

<%@ include file="../common/header.jsp" %>


<h2 align="center">Customer Registration</h2>


<c:if test="${not empty error}">

    <p align="center">
        <b>Error:</b> ${error}
    </p>

</c:if>


<form
        method="post"
        action="${pageContext.request.contextPath}/customer/register">


    <table align="center" cellpadding="8">


        <!-- NAME -->

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


        <!-- EMAIL -->

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


        <!-- PHONE -->

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


        <!-- PASSWORD -->

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


        <!-- ADDRESS -->

        <tr>

            <td colspan="2" align="center">

                <h3>
                    Address (Optional)
                </h3>

            </td>

        </tr>


        <tr>

            <td>
                House Number
            </td>

            <td>

                <input
                        type="text"
                        name="houseNumber">

            </td>

        </tr>


        <tr>

            <td>
                Street
            </td>

            <td>

                <input
                        type="text"
                        name="street">

            </td>

        </tr>


        <tr>

            <td>
                City
            </td>

            <td>

                <input
                        type="text"
                        name="city">

            </td>

        </tr>


        <tr>

            <td>
                State
            </td>

            <td>

                <input
                        type="text"
                        name="state">

            </td>

        </tr>


        <tr>

            <td>
                Country
            </td>

            <td>

                <input
                        type="text"
                        name="country">

            </td>

        </tr>


        <tr>

            <td>
                ZIP Code
            </td>

            <td>

                <input
                        type="text"
                        name="zipCode">

            </td>

        </tr>


        <!-- REGISTER -->

        <tr>

            <td colspan="2" align="center">

                <button type="submit">
                    Register
                </button>

            </td>

        </tr>


    </table>

</form>


<p align="center">

    Already registered?

    <a href="${pageContext.request.contextPath}/customer/login">
        Login
    </a>

</p>


<%@ include file="../common/footer.jsp" %>