<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Inventory Details"/>

<%@ include file="../common/header.jsp" %>


<div class="card">

    <h1>Inventory Details</h1>


    <table>

        <tr>

            <th>Inventory ID</th>

            <td>
                ${inventory.inventoryId}
            </td>

        </tr>


        <tr>

            <th>Product ID</th>

            <td>
                ${inventory.product.productId}
            </td>

        </tr>


        <tr>

            <th>Product Name</th>

            <td>
                ${inventory.product.productName}
            </td>

        </tr>


        <tr>

            <th>Brand</th>

            <td>
                ${inventory.product.brand}
            </td>

        </tr>


        <tr>

            <th>Category</th>

            <td>
                ${inventory.product.category.categoryName}
            </td>

        </tr>


        <tr>

            <th>Seller</th>

            <td>
                ${inventory.product.seller.shopName}
            </td>

        </tr>


        <tr>

            <th>Seller Name</th>

            <td>
                ${inventory.product.seller.userName}
            </td>

        </tr>


        <tr>

            <th>Product Price</th>

            <td>
                ₹${inventory.product.productPrice}
            </td>

        </tr>


        <tr>

            <th>Quantity</th>

            <td>
                ${inventory.quantity}
            </td>

        </tr>


        <tr>

            <th>Product Status</th>

            <td>
                ${inventory.product.productStatus}
            </td>

        </tr>

    </table>


    <br>


    <a
            href="${pageContext.request.contextPath}/inventory/edit/${inventory.inventoryId}"
            class="btn edit-btn">

        Edit Inventory

    </a>


    <a
            href="${pageContext.request.contextPath}/inventory/list"
            class="btn back-btn">

        Back

    </a>

</div>


<%@ include file="../common/footer.jsp" %>