<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>${pageTitle}</title>

    <style>

        /* =====================================================
           GLOBAL PAGE
           ===================================================== */

        body {
            font-family: Arial, sans-serif;
            background: #f5f6fa;
            margin: 0;
            padding: 30px;
        }


        /* =====================================================
           MAIN CONTAINER
           ===================================================== */

        .container {
            width: 90%;
            margin: auto;
        }


        /* =====================================================
           CARD
           ===================================================== */

        .card {
            background: white;
            padding: 25px;
            margin-bottom: 25px;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.08);
        }


        /* =====================================================
           HEADINGS
           ===================================================== */

        h1 {
            margin-top: 0;
            margin-bottom: 25px;
        }

        h2 {
            margin-top: 20px;
            margin-bottom: 15px;
        }


        /* =====================================================
           TABLE
           ===================================================== */

        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
        }

        th,
        td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background: #343a40;
            color: white;
        }

        tr:nth-child(even) {
            background: #f8f9fa;
        }


        /* =====================================================
           COMMON BUTTON
           ===================================================== */

        .btn {
            display: inline-block;
            padding: 8px 14px;
            text-decoration: none;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-right: 5px;
            font-size: 14px;
        }


        /* =====================================================
           ADD BUTTON
           ===================================================== */

        .add-btn {
            display: inline-block;
            background: #28a745;
            color: white;
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 5px;
        }

        .add-btn:hover {
            background: #218838;
        }


        /* =====================================================
           VIEW / EDIT BUTTON
           ===================================================== */

        .edit-btn {
            background: #007bff;
            color: white;
        }

        .edit-btn:hover {
            background: #0069d9;
        }


        /* =====================================================
           DELETE / REMOVE BUTTON
           ===================================================== */

        .delete-btn,
        .remove-btn {
            background: #dc3545;
            color: white;
            border: none;
            padding: 7px 12px;
            cursor: pointer;
            border-radius: 4px;
        }

        .delete-btn:hover,
        .remove-btn:hover {
            background: #c82333;
        }


        /* =====================================================
           NAVIGATION
           ===================================================== */

        .nav {
            margin-bottom: 20px;
        }

        .nav a {
            text-decoration: none;
            margin-right: 15px;
            color: #007bff;
        }

        .nav a:hover {
            text-decoration: underline;
        }


        /* =====================================================
           FORM
           ===================================================== */

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: bold;
        }

        input,
        select,
        textarea {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-family: Arial, sans-serif;
        }

        textarea {
            resize: vertical;
        }

        input:focus,
        select:focus,
        textarea:focus {
            outline: none;
            border-color: #007bff;
        }


        /* =====================================================
           ERROR MESSAGE
           ===================================================== */

        .error-message {
            background: #f8d7da;
            color: #721c24;
            padding: 12px;
            border-radius: 5px;
            margin-bottom: 15px;
        }


        /* =====================================================
           SUCCESS MESSAGE
           ===================================================== */

        .success-message {
            background: #d4edda;
            color: #155724;
            padding: 12px;
            border-radius: 5px;
            margin-bottom: 15px;
        }


        /* =====================================================
           WARNING MESSAGE
           ===================================================== */

        .warning-message {
            background: #fff3cd;
            color: #856404;
            padding: 12px;
            border-radius: 5px;
            margin-bottom: 15px;
        }


        /* =====================================================
           INFORMATION MESSAGE
           ===================================================== */

        .info-message {
            background: #d1ecf1;
            color: #0c5460;
            padding: 12px;
            border-radius: 5px;
            margin-bottom: 15px;
        }


        /* =====================================================
           EMPTY MESSAGE
           ===================================================== */

        .empty-message {
            text-align: center;
            padding: 20px;
            color: #666;
        }


        /* =====================================================
           PROFILE INFORMATION
           ===================================================== */

        .profile-row {
            display: flex;
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        .profile-label {
            width: 30%;
            font-weight: bold;
        }

        .profile-value {
            width: 70%;
        }


        /* =====================================================
           ACTION AREA
           ===================================================== */

        .actions {
            margin-top: 20px;
        }


        /* =====================================================
           ADDRESS SECTION
           ===================================================== */

        .address-card {
            background: #f8f9fa;
            border: 1px solid #ddd;
            border-radius: 6px;
            padding: 15px;
            margin-bottom: 15px;
        }


        /* =====================================================
           STAR RATING
           ===================================================== */

        .rating {
            font-size: 20px;
        }


        /* =====================================================
           PRICE
           ===================================================== */

        .price {
            font-weight: bold;
        }


        /* =====================================================
           STATUS
           ===================================================== */

        .status {
            font-weight: bold;
        }


        /* =====================================================
           RESPONSIVE TABLE
           ===================================================== */

        @media (max-width: 768px) {

            .container {
                width: 100%;
            }

            body {
                padding: 15px;
            }

            table {
                font-size: 13px;
            }

            th,
            td {
                padding: 7px;
            }

            .profile-row {
                display: block;
            }

            .profile-label,
            .profile-value {
                width: 100%;
            }

        }

    </style>

</head>

<body>

<div class="container">