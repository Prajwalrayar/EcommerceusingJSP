<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageTitle" value="Products" />

<%@ include file="../common/header.jsp" %>

<style>

    .products-container {
        max-width: 1400px;
        margin: 40px auto;
        padding: 0 25px;
    }

    .products-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 30px;
    }

    .products-header h1 {
        margin: 0;
        font-size: 32px;
    }

    .products-header p {
        margin-top: 8px;
        color: #666;
    }

    .back-btn {
        text-decoration: none;
        background: #1976d2;
        color: white;
        padding: 12px 20px;
        border-radius: 6px;
    }

    .filter-card {
        background: #ffffff;
        padding: 25px;
        border-radius: 10px;
        margin-bottom: 30px;
        box-shadow: 0 2px 10px rgba(0,0,0,0.08);
    }

    .filter-grid {
        display: grid;
        grid-template-columns:
            repeat(auto-fit, minmax(180px, 1fr));
        gap: 18px;
        align-items: end;
    }

    .form-group {
        display: flex;
        flex-direction: column;
    }

    .form-group label {
        margin-bottom: 7px;
        font-weight: bold;
    }

    .form-group input,
    .form-group select {
        padding: 11px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 14px;
    }

    .filter-btn {
        background: #1976d2;
        color: white;
        border: none;
        padding: 11px 20px;
        border-radius: 5px;
        cursor: pointer;
        font-size: 15px;
    }

    .clear-btn {
        background: #777;
        color: white;
        padding: 11px 20px;
        border-radius: 5px;
        text-decoration: none;
        display: inline-block;
        text-align: center;
    }

    .product-grid {
        display: grid;
        grid-template-columns:
            repeat(auto-fill, minmax(280px, 1fr));
        gap: 25px;
    }

    .product-card {
        background: white;
        border-radius: 10px;
        padding: 22px;
        box-shadow: 0 2px 10px rgba(0,0,0,0.08);
    }

    .product-card h2 {
        margin-top: 0;
        margin-bottom: 15px;
    }

    .product-info {
        margin: 8px 0;
        color: #555;
    }

    .product-price {
        font-size: 22px;
        font-weight: bold;
        margin: 15px 0;
    }

    .product-status {
        display: inline-block;
        padding: 6px 10px;
        border-radius: 5px;
        background: #e8f5e9;
        color: #2e7d32;
        margin-bottom: 15px;
    }

    .quantity-input {
        width: 70px;
        padding: 9px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    .cart-btn {
        background: #1976d2;
        color: white;
        border: none;
        padding: 10px 16px;
        border-radius: 5px;
        cursor: pointer;
        margin-left: 8px;
    }

    .alert {
        padding: 12px 16px;
        border-radius: 5px;
        margin-bottom: 20px;
    }

    .alert-success {
        background: #e8f5e9;
        color: #2e7d32;
    }

    .alert-danger {
        background: #ffebee;
        color: #c62828;
    }

    .no-products {
        text-align: center;
        padding: 50px;
        background: white;
        border-radius: 10px;
        color: #666;
    }

</style>


<div class="products-container">

    <div class="products-header">

        <div>
            <h1>Products</h1>

            <p>
                Browse and search products
            </p>
        </div>

        <a href="${pageContext.request.contextPath}/customer/dashboard"
           class="back-btn">
            Dashboard
        </a>

    </div>


    <!-- SUCCESS MESSAGE -->

    <c:if test="${not empty success}">

        <div class="alert alert-success">
            ${success}
        </div>

    </c:if>


    <!-- ERROR MESSAGE -->

    <c:if test="${not empty error}">

        <div class="alert alert-danger">
            ${error}
        </div>

    </c:if>


    <!-- FILTER -->

    <div class="filter-card">

        <h2>Search & Filter Products</h2>

        <form method="get"
              action="${pageContext.request.contextPath}/customer/products">

            <div class="filter-grid">

                <div class="form-group">

                    <label for="keyword">
                        Product Name
                    </label>

                    <input
                        type="text"
                        id="keyword"
                        name="keyword"
                        value="${keyword}"
                        placeholder="Search product">

                </div>


                <div class="form-group">

                    <label for="categoryId">
                        Category
                    </label>

                    <select id="categoryId"
                            name="categoryId">

                        <option value="">
                            All Categories
                        </option>

                        <c:forEach
                                var="category"
                                items="${categories}">

                            <option
                                value="${category.categoryId}"
                                ${selectedCategoryId == category.categoryId
                                  ? 'selected' : ''}>

                                ${category.categoryName}

                            </option>

                        </c:forEach>

                    </select>

                </div>


                <div class="form-group">

                    <label for="sellerId">
                        Seller
                    </label>

                    <select id="sellerId"
                            name="sellerId">

                        <option value="">
                            All Sellers
                        </option>

                        <c:forEach
                                var="seller"
                                items="${sellers}">

                            <option
                                value="${seller.userId}"
                                ${selectedSellerId == seller.userId
                                  ? 'selected' : ''}>

                                ${seller.userName}

                            </option>

                        </c:forEach>

                    </select>

                </div>


                <div class="form-group">

                    <label for="minPrice">
                        Min Price
                    </label>

                    <input
                        type="number"
                        id="minPrice"
                        name="minPrice"
                        value="${minPrice}"
                        step="0.01"
                        min="0">

                </div>


                <div class="form-group">

                    <label for="maxPrice">
                        Max Price
                    </label>

                    <input
                        type="number"
                        id="maxPrice"
                        name="maxPrice"
                        value="${maxPrice}"
                        step="0.01"
                        min="0">

                </div>


                <div>

                    <button
                        type="submit"
                        class="filter-btn">

                        Search

                    </button>

                    <a
                        href="${pageContext.request.contextPath}/customer/products"
                        class="clear-btn">

                        Clear

                    </a>

                </div>

            </div>

        </form>

    </div>


    <!-- PRODUCTS -->

    <c:choose>

        <c:when test="${not empty products}">

            <div class="product-grid">

                <c:forEach
                        var="product"
                        items="${products}">

                    <div class="product-card">

                        <h2>
                            ${product.productName}
                        </h2>


                        <div class="product-info">

                            <strong>Brand:</strong>
                            ${product.brand}

                        </div>


                        <div class="product-info">

                            <strong>Category:</strong>

                            ${product.category.categoryName}

                        </div>


                        <div class="product-info">

                            <strong>Seller:</strong>

                            ${product.seller.shopName}

                        </div>


                        <div class="product-info">

                            <strong>Rating:</strong>

                            ${product.rating}

                        </div>


                        <div class="product-price">

                            ₹
                            <fmt:formatNumber
							    value="${product.productPrice}"
							    type="number"
							    minFractionDigits="2"
							    maxFractionDigits="2"/>

                        </div>


                        <div class="product-status">

                            ${product.productStatus}

                        </div>


                        <!-- ADD TO CART -->

                        <form
                            method="post"
                            action="${pageContext.request.contextPath}/customer/cart/add">

                            <input
                                type="hidden"
                                name="productId"
                                value="${product.productId}">


                            <input
                                type="number"
                                name="quantity"
                                value="1"
                                min="1"
                                class="quantity-input">


                            <button
                                type="submit"
                                class="cart-btn">

                                Add to Cart

                            </button>

                        </form>

                    </div>

                </c:forEach>

            </div>

        </c:when>


        <c:otherwise>

            <div class="no-products">

                <h2>No Products Available</h2>

                <p>
                    There are currently no products matching your search.
                </p>

            </div>

        </c:otherwise>

    </c:choose>

</div>

<%@ include file="../common/footer.jsp" %>