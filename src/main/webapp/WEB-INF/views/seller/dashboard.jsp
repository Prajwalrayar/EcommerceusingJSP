<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>

    <title>Seller Dashboard</title>

</head>

<body>

<h1>Seller Dashboard</h1>

<p>
    Welcome, ${sessionScope.loggedInUser.userName}
</p>

<a href="${pageContext.request.contextPath}/seller/profile/${sessionScope.userId}">
    My Profile
</a>

<br><br>

<a href="${pageContext.request.contextPath}/logout">
    Logout
</a>

</body>

</html>