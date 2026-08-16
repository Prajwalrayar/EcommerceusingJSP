<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Admin Dashboard"/>

<%@ include file="../common/header.jsp" %>


<div class="admin-dashboard">


    <!-- ===================================================== -->
    <!-- HEADER -->
    <!-- ===================================================== -->

    <div class="dashboard-header">

        <div>

            <h1>Admin Dashboard</h1>

            <p>
                Welcome,
                <strong>
                    ${sessionScope.loggedInUser.userName}
                </strong>
            </p>

        </div>


        <a href="${pageContext.request.contextPath}/logout"
           class="logout-btn">

            Logout

        </a>

    </div>



    <!-- ===================================================== -->
    <!-- OVERVIEW -->
    <!-- ===================================================== -->

    <section class="dashboard-section">

        <h2>Overview</h2>


        <div class="summary-grid">


            <!-- CUSTOMERS -->

            <a href="${pageContext.request.contextPath}/admin/customers"
               class="summary-card">

                <div class="summary-icon">
                    👥
                </div>

                <div>

                    <span class="summary-title">
                        Customers
                    </span>

                    <span class="summary-description">
                        Manage registered customers
                    </span>

                </div>

            </a>


            <!-- SELLERS -->

            <a href="${pageContext.request.contextPath}/admin/sellers"
               class="summary-card">

                <div class="summary-icon">
                    🏪
                </div>

                <div>

                    <span class="summary-title">
                        Sellers
                    </span>

                    <span class="summary-description">
                        Manage marketplace sellers
                    </span>

                </div>

            </a>


            <!-- PRODUCTS -->

            <a href="${pageContext.request.contextPath}/admin/products"
               class="summary-card">

                <div class="summary-icon">
                    📦
                </div>

                <div>

                    <span class="summary-title">
                        Products
                    </span>

                    <span class="summary-description">
                        Manage marketplace products
                    </span>

                </div>

            </a>


        </div>

    </section>



    <!-- ===================================================== -->
    <!-- MANAGEMENT -->
    <!-- ===================================================== -->

    <section class="dashboard-section">

        <h2>Management</h2>


        <div class="operation-grid">


            <!-- PRODUCTS -->

            <a href="${pageContext.request.contextPath}/admin/products"
               class="operation-card">

                <div class="operation-icon">
                    📦
                </div>

                <h3>
                    Products
                </h3>

                <p>
                    Add, view and manage products available
                    in the marketplace.
                </p>

                <span class="operation-link">
                    Manage Products →
                </span>

            </a>



            <!-- CATEGORIES -->

            <a href="${pageContext.request.contextPath}/admin/categories"
               class="operation-card">

                <div class="operation-icon">
                    🏷️
                </div>

                <h3>
                    Categories
                </h3>

                <p>
                    Add and manage product categories
                    available to sellers.
                </p>

                <span class="operation-link">
                    Manage Categories →
                </span>

            </a>



            <!-- INVENTORY -->

            <a href="${pageContext.request.contextPath}/admin/inventory"
               class="operation-card">

                <div class="operation-icon">
                    📊
                </div>

                <h3>
                    Inventory
                </h3>

                <p>
                    Monitor stock levels, low-stock products
                    and product availability.
                </p>

                <span class="operation-link">
                    Manage Inventory →
                </span>

            </a>


        </div>

    </section>



    <!-- ===================================================== -->
    <!-- TRANSACTIONS -->
    <!-- ===================================================== -->

    <section class="dashboard-section">

        <h2>Transactions</h2>


        <div class="operation-grid">


            <!-- ORDERS -->

            <a href="${pageContext.request.contextPath}/admin/orders"
               class="operation-card">

                <div class="operation-icon">
                    🛒
                </div>

                <h3>
                    Orders
                </h3>

                <p>
                    View customer orders, purchased items,
                    quantities and order totals.
                </p>

                <span class="operation-link">
                    View Orders →
                </span>

            </a>



            <!-- PAYMENTS -->

            <a href="${pageContext.request.contextPath}/admin/payments"
               class="operation-card">

                <div class="operation-icon">
                    💳
                </div>

                <h3>
                    Payments
                </h3>

                <p>
                    View payment transactions, payment modes
                    and payment status.
                </p>

                <span class="operation-link">
                    View Payments →
                </span>

            </a>


        </div>

    </section>



    <!-- ===================================================== -->
    <!-- ANALYTICS -->
    <!-- ===================================================== -->

    <section class="dashboard-section">

        <h2>Analytics & Reports</h2>


        <div class="analytics-card">


            <div class="analytics-icon">
                📈
            </div>


            <div class="analytics-content">

                <h3>
                    Reports & Analytics
                </h3>

                <p>
                    Analyse sales, products, categories,
                    sellers and customers using the available
                    reporting operations.
                </p>


                <a href="${pageContext.request.contextPath}/admin/reports"
                   class="primary-btn">

                    Open Reports

                </a>

            </div>


        </div>

    </section>



    <!-- ===================================================== -->
    <!-- ACCOUNT -->
    <!-- ===================================================== -->

    <section class="dashboard-section">

        <h2>Account</h2>


        <div class="account-card">


            <div class="account-icon">
                👤
            </div>


            <div class="account-content">

                <h3>
                    Admin Profile
                </h3>

                <p>
                    View your administrator information,
                    update your phone number and change
                    your password.
                </p>


                <a href="${pageContext.request.contextPath}/admin/profile"
                   class="secondary-btn">

                    View Profile

                </a>

            </div>


        </div>

    </section>


</div>



<style>


/* ========================================================= */
/* DASHBOARD */
/* ========================================================= */

.admin-dashboard {

    max-width: 1250px;

    margin: 35px auto;

    padding: 0 30px;

    box-sizing: border-box;

}



/* ========================================================= */
/* HEADER */
/* ========================================================= */

.dashboard-header {

    display: flex;

    justify-content: space-between;

    align-items: center;

    padding-bottom: 25px;

    margin-bottom: 35px;

    border-bottom: 1px solid #dee2e6;

}


.dashboard-header h1 {

    margin: 0;

    font-size: 36px;

    color: #212529;

}


.dashboard-header p {

    margin: 8px 0 0;

    color: #6c757d;

    font-size: 17px;

}


.dashboard-header strong {

    color: #212529;

}



/* ========================================================= */
/* LOGOUT */
/* ========================================================= */

.logout-btn {

    display: inline-block;

    padding: 11px 22px;

    background-color: #dc3545;

    color: white;

    text-decoration: none;

    border-radius: 6px;

    font-weight: 600;

}


.logout-btn:hover {

    background-color: #bb2d3b;

}



/* ========================================================= */
/* SECTION */
/* ========================================================= */

.dashboard-section {

    margin-bottom: 40px;

}


.dashboard-section h2 {

    margin: 0 0 18px;

    font-size: 24px;

    color: #212529;

}



/* ========================================================= */
/* SUMMARY GRID */
/* ========================================================= */

.summary-grid {

    display: grid;

    grid-template-columns:
        repeat(3, 1fr);

    gap: 20px;

}


.summary-card {

    display: flex;

    align-items: center;

    gap: 18px;

    padding: 22px;

    background: white;

    border: 1px solid #e1e5e9;

    border-radius: 10px;

    text-decoration: none;

    color: #212529;

    box-shadow:
        0 3px 10px rgba(0,0,0,0.05);

    transition:
        transform 0.2s ease,
        box-shadow 0.2s ease;

}


.summary-card:hover {

    transform: translateY(-3px);

    box-shadow:
        0 7px 18px rgba(0,0,0,0.09);

}


.summary-icon {

    width: 52px;

    height: 52px;

    display: flex;

    align-items: center;

    justify-content: center;

    background-color: #f1f3f5;

    border-radius: 10px;

    font-size: 27px;

}


.summary-title {

    display: block;

    font-size: 19px;

    font-weight: 700;

}


.summary-description {

    display: block;

    margin-top: 5px;

    color: #6c757d;

    font-size: 14px;

}



/* ========================================================= */
/* OPERATION GRID */
/* ========================================================= */

.operation-grid {

    display: grid;

    grid-template-columns:
        repeat(3, 1fr);

    gap: 20px;

}


.operation-card {

    display: block;

    padding: 25px;

    background-color: white;

    border: 1px solid #e1e5e9;

    border-radius: 10px;

    text-decoration: none;

    color: #212529;

    box-shadow:
        0 3px 10px rgba(0,0,0,0.05);

    transition:
        transform 0.2s ease,
        box-shadow 0.2s ease;

}


.operation-card:hover {

    transform: translateY(-4px);

    box-shadow:
        0 8px 20px rgba(0,0,0,0.10);

}


.operation-icon {

    font-size: 32px;

    margin-bottom: 15px;

}


.operation-card h3 {

    margin: 0 0 10px;

    font-size: 21px;

}


.operation-card p {

    margin: 0 0 18px;

    color: #6c757d;

    line-height: 1.5;

    font-size: 15px;

}


.operation-link {

    color: #0d6efd;

    font-weight: 600;

}



/* ========================================================= */
/* ANALYTICS */
/* ========================================================= */

.analytics-card {

    display: flex;

    align-items: center;

    gap: 25px;

    padding: 30px;

    background-color: white;

    border: 1px solid #e1e5e9;

    border-radius: 10px;

    box-shadow:
        0 3px 10px rgba(0,0,0,0.05);

}


.analytics-icon {

    width: 70px;

    height: 70px;

    display: flex;

    align-items: center;

    justify-content: center;

    background-color: #f1f3f5;

    border-radius: 12px;

    font-size: 38px;

}


.analytics-content h3 {

    margin: 0 0 8px;

    font-size: 23px;

}


.analytics-content p {

    margin: 0 0 18px;

    color: #6c757d;

    line-height: 1.5;

}



/* ========================================================= */
/* BUTTONS */
/* ========================================================= */

.primary-btn,
.secondary-btn {

    display: inline-block;

    padding: 11px 20px;

    border-radius: 6px;

    text-decoration: none;

    font-weight: 600;

}


.primary-btn {

    background-color: #0d6efd;

    color: white;

}


.primary-btn:hover {

    background-color: #0b5ed7;

}


.secondary-btn {

    background-color: #6c757d;

    color: white;

}


.secondary-btn:hover {

    background-color: #5c636a;

}



/* ========================================================= */
/* ACCOUNT */
/* ========================================================= */

.account-card {

    display: flex;

    align-items: center;

    gap: 25px;

    padding: 28px;

    background-color: white;

    border: 1px solid #e1e5e9;

    border-radius: 10px;

    box-shadow:
        0 3px 10px rgba(0,0,0,0.05);

}


.account-icon {

    width: 65px;

    height: 65px;

    display: flex;

    align-items: center;

    justify-content: center;

    background-color: #f1f3f5;

    border-radius: 12px;

    font-size: 35px;

}


.account-content h3 {

    margin: 0 0 8px;

    font-size: 22px;

}


.account-content p {

    margin: 0 0 18px;

    color: #6c757d;

    line-height: 1.5;

}



/* ========================================================= */
/* RESPONSIVE */
/* ========================================================= */

@media (max-width: 950px) {

    .summary-grid {

        grid-template-columns: repeat(2, 1fr);

    }


    .operation-grid {

        grid-template-columns: repeat(2, 1fr);

    }

}


@media (max-width: 600px) {

    .admin-dashboard {

        padding: 0 15px;

        margin: 20px auto;

    }


    .dashboard-header {

        flex-direction: column;

        align-items: flex-start;

        gap: 20px;

    }


    .dashboard-header h1 {

        font-size: 30px;

    }


    .summary-grid,
    .operation-grid {

        grid-template-columns: 1fr;

    }


    .analytics-card,
    .account-card {

        flex-direction: column;

        align-items: flex-start;

    }

}


</style>


<%@ include file="../common/footer.jsp" %>