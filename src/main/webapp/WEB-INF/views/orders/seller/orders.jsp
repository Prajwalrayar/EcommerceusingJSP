<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../../common/header.jsp" %>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/css/seller-dashboard.css">


<div class="seller-page">

    <!-- ================================================== -->
    <!-- PAGE HEADER -->
    <!-- ================================================== -->

    <div class="page-header">

        <div>

            <h1>Orders</h1>

            <p>
                Manage orders placed for your products
            </p>

        </div>


        <!-- DASHBOARD BUTTON -->

        <a href="${pageContext.request.contextPath}/seller/dashboard"
           class="btn secondary-btn">

            Dashboard

        </a>

    </div>


    <!-- ================================================== -->
    <!-- SEARCH AND FILTER -->
    <!-- ================================================== -->

    <div class="filter-panel">


        <!-- ================================================== -->
        <!-- SEARCH ORDER -->
        <!-- ================================================== -->

        <form method="get"
              action="${pageContext.request.contextPath}/orders/seller/${sellerId}/search-order">

            <input type="text"
                   name="orderId"
                   value="${orderId}"
                   placeholder="Search Order Number"
                   maxlength="20"
                   required>

            <button type="submit"
                    class="btn primary-btn">

                Search

            </button>

        </form>


        <!-- ================================================== -->
        <!-- STATUS FILTER -->
        <!-- ================================================== -->

        <form method="get"
              action="${pageContext.request.contextPath}/orders/seller/${sellerId}/status">

            <select name="status">

                <!-- ALL ORDERS -->

                <option value=""
                    <c:if test="${empty selectedStatus}">
                        selected
                    </c:if>>

                    All Statuses

                </option>


                <!-- ORDER STATUSES -->

                <c:forEach var="status"
                           items="${orderStatuses}">

                    <option value="${status}"
                        <c:if test="${selectedStatus eq status}">
                            selected
                        </c:if>>

                        ${status}

                    </option>

                </c:forEach>

            </select>


            <button type="submit"
                    class="btn primary-btn">

                Filter

            </button>

        </form>

    </div>


    <!-- ================================================== -->
    <!-- ORDERS TABLE -->
    <!-- ================================================== -->

    <div class="table-card">

        <table>

            <thead>

            <tr>

                <th>
                    Order Number
                </th>

                <th>
                    Customer
                </th>

                <th>
                    Product
                </th>

                <th>
                    Quantity
                </th>

                <th>
                    Amount
                </th>

                <th>
                    Status
                </th>

                <th>
                    Tracking
                </th>

                <th>
                    Update
                </th>

            </tr>

            </thead>


            <tbody>


            <!-- ================================================== -->
            <!-- DISPLAY ORDERS -->
            <!-- ================================================== -->

            <c:forEach var="order"
                       items="${orders}">

                <tr>


                    <!-- ORDER NUMBER -->

                    <td>

                        <strong>
                            ${order.orderId}
                        </strong>

                    </td>


                    <!-- CUSTOMER -->

                    <td>

                        ${order.customer.userName}

                    </td>


                    <!-- PRODUCT -->

                    <td>

                        ${order.product.productName}

                    </td>


                    <!-- QUANTITY -->

                    <td>

                        ${order.quantity}

                    </td>


                    <!-- AMOUNT -->

                    <td>

                        ₹${order.totalPrice}

                    </td>


                    <!-- STATUS -->

                    <td>

                        <span class="status-badge">

                            ${order.orderStatus}

                        </span>

                    </td>


                    <!-- TRACKING -->

                    <td>

                        <c:choose>

                            <c:when test="${not empty order.trackingNumber}">

                                <strong>
                                    ${order.trackingNumber}
                                </strong>

                            </c:when>


                            <c:otherwise>

                                <span class="muted">

                                    Not generated

                                </span>

                            </c:otherwise>

                        </c:choose>

                    </td>


                    <!-- ================================================== -->
                    <!-- UPDATE ORDER STATUS -->
                    <!-- ================================================== -->

                    <td>

                        <form method="post"
                              action="${pageContext.request.contextPath}/orders/seller/${sellerId}/status">


                            <!-- ORDER ID -->

                            <input type="hidden"
                                   name="orderId"
                                   value="${order.orderId}">


                            <select name="orderStatus"
                                    required>

                                <option value="">

                                    Select Status

                                </option>


                                <!-- ================================================== -->
                                <!-- PENDING APPROVAL -->
                                <!-- ================================================== -->

                                <c:if test="${order.orderStatus eq 'PENDING_APPROVAL'}">

                                    <option value="CONFIRMED">

                                        Approve

                                    </option>

                                    <option value="CANCELLED">

                                        Cancel

                                    </option>

                                    <option value="REJECTED">

                                        Reject

                                    </option>

                                </c:if>


                                <!-- ================================================== -->
                                <!-- CONFIRMED -->
                                <!-- ================================================== -->

                                <c:if test="${order.orderStatus eq 'CONFIRMED'}">

                                    <option value="SHIPPED">

                                        Shipped

                                    </option>

                                    <option value="CANCELLED">

                                        Cancel

                                    </option>

                                </c:if>


                                <!-- ================================================== -->
                                <!-- SHIPPED -->
                                <!-- ================================================== -->

                                <c:if test="${order.orderStatus eq 'SHIPPED'}">

                                    <option value="IN_TRANSIT">

                                        In Transit

                                    </option>

                                </c:if>


                                <!-- ================================================== -->
                                <!-- IN TRANSIT -->
                                <!-- ================================================== -->

                                <c:if test="${order.orderStatus eq 'IN_TRANSIT'}">

                                    <option value="OUT_FOR_DELIVERY">

                                        Out For Delivery

                                    </option>

                                </c:if>


                                <!-- ================================================== -->
                                <!-- OUT FOR DELIVERY -->
                                <!-- ================================================== -->

                                <c:if test="${order.orderStatus eq 'OUT_FOR_DELIVERY'}">

                                    <option value="DELIVERED">

                                        Delivered

                                    </option>

                                </c:if>

                            </select>


                            <button type="submit"
                                    class="btn small-btn">

                                Update

                            </button>

                        </form>

                    </td>

                </tr>

            </c:forEach>


            <!-- ================================================== -->
            <!-- NO ORDERS -->
            <!-- ================================================== -->

            <c:if test="${empty orders}">

                <tr>

                    <td colspan="8"
                        class="empty-message">

                        No orders found.

                    </td>

                </tr>

            </c:if>


            </tbody>

        </table>

    </div>

</div>


<%@ include file="../../common/footer.jsp" %>