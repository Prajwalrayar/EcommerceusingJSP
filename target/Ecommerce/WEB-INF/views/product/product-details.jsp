<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="pageTitle" value="Product Details"/>

<%@ include file="common/header.jsp" %>


<div class="card">

    <h1>Product Details</h1>


    <table>

        <tr>
            <th>Product ID</th>
            <td>${product.productId}</td>
        </tr>

        <tr>
            <th>Name</th>
            <td>${product.productName}</td>
        </tr>

        <tr>
            <th>Brand</th>
            <td>${product.brand}</td>
        </tr>

        <tr>
            <th>Description</th>
            <td>${product.productDescription}</td>
        </tr>

        <tr>
            <th>Price</th>
            <td>₹${product.productPrice}</td>
        </tr>

        <tr>
            <th>Category</th>
            <td>${product.category.categoryName}</td>
        </tr>

        <tr>
            <th>Seller</th>
            <td>${product.seller.shopName}</td>
        </tr>

        <tr>
            <th>Seller Name</th>
            <td>${product.seller.userName}</td>
        </tr>

        <tr>
            <th>Rating</th>
            <td>${product.rating}</td>
        </tr>

        <tr>
            <th>Reviews</th>
            <td>${product.reviewCount}</td>
        </tr>

        <tr>
            <th>Status</th>
            <td>${product.productStatus}</td>
        </tr>

    </table>


    <br>


    <a
            href="${pageContext.request.contextPath}/product/edit/${product.productId}"
            class="btn edit-btn">

        Edit Product

    </a>


    <a
            href="${pageContext.request.contextPath}/product/list"
            class="btn back-btn">

        Back

    </a>

</div>


<%@ include file="common/footer.jsp" %>