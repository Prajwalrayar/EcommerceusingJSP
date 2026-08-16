<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Inventory"/>

<%@ include file="../common/header.jsp" %>


<div class="card">

    <h1>Inventory Management</h1>


    <!-- ===================================================== -->
    <!-- Navigation -->
    <!-- ===================================================== -->

    <div class="nav">

        <a href="${pageContext.request.contextPath}/product/list">
            Products
        </a>

        <a href="${pageContext.request.contextPath}/inventory/list">
            All Inventory
        </a>

        <a href="${pageContext.request.contextPath}/category/list">
            Categories
        </a>

        <a href="${pageContext.request.contextPath}/admin/sellers">
            Sellers
        </a>

    </div>


    <!-- ===================================================== -->
    <!-- Add Inventory -->
    <!-- ===================================================== -->

    <div style="margin-bottom:20px;">

        <a
                href="${pageContext.request.contextPath}/inventory/add"
                class="btn add-btn">

            Add Inventory

        </a>

    </div>


    <!-- ===================================================== -->
    <!-- Inventory Table -->
    <!-- ===================================================== -->

    <table>

        <thead>

        <tr>

            <th>Inventory ID</th>

            <th>Product ID</th>

            <th>Product</th>

            <th>Brand</th>

            <th>Category</th>

            <th>Seller</th>

            <th>Quantity</th>

            <th>Status</th>

            <th>Actions</th>

        </tr>

        </thead>


        <tbody>

        <c:forEach
                var="inventory"
                items="${inventoryList}">

            <tr>

                <td>
                    ${inventory.inventoryId}
                </td>


                <td>
                    ${inventory.product.productId}
                </td>


                <td>
                    ${inventory.product.productName}
                </td>


                <td>
                    ${inventory.product.brand}
                </td>


                <td>
                    ${inventory.product.category.categoryName}
                </td>


                <td>
                    ${inventory.product.seller.shopName}
                </td>


                <td>

                    ${inventory.quantity}

                </td>


                <td>

                    ${inventory.product.productStatus}

                </td>


                <td>

                    <a
                            href="${pageContext.request.contextPath}/inventory/view/${inventory.inventoryId}"
                            class="btn edit-btn">

                        View

                    </a>


                    <a
                            href="${pageContext.request.contextPath}/inventory/edit/${inventory.inventoryId}"
                            class="btn edit-btn">

                        Edit

                    </a>


                    <form
                            action="${pageContext.request.contextPath}/inventory/delete/${inventory.inventoryId}"
                            method="post"
                            style="display:inline;"
                            onsubmit="return confirm('Delete this inventory record?');">

                        <button
                                type="submit"
                                class="btn delete-btn">

                            Delete

                        </button>

                    </form>

                </td>

            </tr>

        </c:forEach>

        </tbody>

    </table>


    <c:if test="${empty inventoryList}">

        <div class="empty-message">

            No inventory records found.

        </div>

    </c:if>

</div>


<%@ include file="../common/footer.jsp" %>