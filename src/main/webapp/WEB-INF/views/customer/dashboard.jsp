<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>

    <title>Customer Dashboard</title>

</head>

<body>

<h1>Customer Dashboard</h1>

<p>
    Welcome, ${sessionScope.loggedInUser.userName}
</p>

<a href="${pageContext.request.contextPath}/customer/profile/${sessionScope.userId}">
    My Profile
</a>

<br><br>

<a href="${pageContext.request.contextPath}/logout">
    Logout
</a>

</body>

</html>