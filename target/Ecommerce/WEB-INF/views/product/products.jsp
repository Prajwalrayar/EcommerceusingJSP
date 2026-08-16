<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Products"/>

<%@ include file="common/header.jsp" %>


<div class="card">

    <h1>Product Management</h1>


    <!-- ===================================================== -->
    <!-- Navigation -->
    <!-- ===================================================== -->

    <div class="nav">

        <a href="${pageContext.request.contextPath}/category/list">
            Categories
        </a>

        <a href="${pageContext.request.contextPath}/admin/sellers">
            Sellers
        </a>

        <a href="${pageContext.request.contextPath}/admin/customers">
            Customers
        </a>

        <a href="${pageContext.request.contextPath}/product/list">
            All Products
        </a>

        <a href="${pageContext.request.contextPath}/product/available">
            Available Products
        </a>

    </div>


    <!-- ===================================================== -->
    <!-- Add Product -->
    <!-- ===================================================== -->

    <div style="margin-bottom:20px;">

        <a
                href="${pageContext.request.contextPath}/product/add"
                class="btn add-btn">

            Add Product

        </a>

    </div>


    <!-- ===================================================== -->
    <!-- Product Table -->
    <!-- ===================================================== -->

    <table>

        <thead>

        <tr>

            <th>Product ID</th>

            <th>Name</th>

            <th>Brand</th>

            <th>Price</th>

            <th>Category</th>

            <th>Seller</th>

            <th>Rating</th>

            <th>Reviews</th>

            <th>Status</th>

            <th>Actions</th>

        </tr>

        </thead>


        <tbody>

        <c:forEach
                var="product"
                items="${products}">

            <tr>

                <td>
                    ${product.productId}
                </td>


                <td>
                    ${product.productName}
                </td>


                <td>
                    ${product.brand}
                </td>


                <td>
                    ₹${product.productPrice}
                </td>


                <td>
                    ${product.category.categoryName}
                </td>


                <td>
                    ${product.seller.shopName}
                </td>


                <td>
                    ${product.rating}
                </td>


                <td>
                    ${product.reviewCount}
                </td>


                <td>
                    ${product.productStatus}
                </td>


                <td>

                    <a
                            href="${pageContext.request.contextPath}/product/view/${product.productId}"
                            class="btn edit-btn">

                        View

                    </a>


                    <a
                            href="${pageContext.request.contextPath}/product/edit/${product.productId}"
                            class="btn edit-btn">

                        Edit

                    </a>


                    <form
                            action="${pageContext.request.contextPath}/product/delete/${product.productId}"
                            method="post"
                            style="display:inline;"
                            onsubmit="return confirm('Delete this product?');">

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


    <c:if test="${empty products}">

        <div class="empty-message">

            No products found.

        </div>

    </c:if>

</div>


<%@ include file="common/footer.jsp" %>