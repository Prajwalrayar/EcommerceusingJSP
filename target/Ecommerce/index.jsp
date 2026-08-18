<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>E-Commerce Marketplace</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background-color: #f5f5f5;
        }

        .header {
            background-color: #343a40;
            color: white;
            padding: 18px 40px;
        }

        .header h2 {
            margin: 0;
        }

        .content {
            width: 90%;
            margin: 40px auto;
        }

        .welcome {
            background-color: white;
            padding: 25px;
            border: 1px solid #ddd;
            margin-bottom: 25px;
        }

        .welcome h1 {
            margin-top: 0;
        }

        .login-section {
            background-color: white;
            padding: 25px;
            border: 1px solid #ddd;
        }

        .login-section h2 {
            margin-top: 0;
        }

        .login-links {
            margin-top: 20px;
        }

        .login-links a {
            display: inline-block;
            padding: 10px 18px;
            margin-right: 10px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }

        .login-links a:hover {
            background-color: #0056b3;
        }

    </style>

</head>

<body>

<div class="header">

    <h2>E-Commerce Marketplace</h2>

</div>


<div class="content">


    <div class="welcome">

        <h1>Welcome</h1>

        <p>
            Welcome to the E-Commerce Marketplace.
        </p>

        <p>
            Please select an option below to continue.
        </p>

    </div>


    <div class="login-section">

        <h2>Login</h2>

        <div class="login-links">

            <a href="${pageContext.request.contextPath}/admin/login">
                Admin Login
            </a>

            <a href="${pageContext.request.contextPath}/seller/login">
                Seller Login
            </a>

            <a href="${pageContext.request.contextPath}/customer/login">
                Customer Login
            </a>

        </div>

    </div>


</div>

</body>

</html>