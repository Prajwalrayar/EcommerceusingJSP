<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Products - Admin"/>

<%@ include file="../common/header.jsp" %>


<div class="card">

    <h1>Product Management</h1>




    <!-- ===================================================== -->
    <!-- ADD PRODUCT -->
    <!-- ===================================================== -->

    <div style="margin-bottom: 25px;">
    
    <a href="${pageContext.request.contextPath}/admin/dashboard"
        	class="btn btn-primary" style="color: white;">
            Dashboard
        </a>

        <a href="${pageContext.request.contextPath}/product/add"
           class="btn btn-primary">

            Add Product

        </a>
        
        <a href="${pageContext.request.contextPath}/admin/inventory"  class="btn btn-primary">
            Inventory
        </a>
        

    </div>


    <!-- ===================================================== -->
    <!-- PRODUCT TABLE -->
    <!-- ===================================================== -->

    <table class="data-table">

        <thead>

            <tr>

                <th>Product ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Category</th>
                <th>Seller</th>
                <th>Status</th>
                <th>Action</th>

            </tr>

        </thead>


        <tbody>

            <c:choose>

                <c:when test="${not empty products}">

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
                                ${product.productDescription}
                            </td>


                            <td>
                                ₹${product.productPrice}
                            </td>


                            <td>
                                ${product.category.categoryName}
                            </td>


                            <td>
                                ${product.seller.userName}
                            </td>


                            <td>
                                ${product.productStatus}
                            </td>


                            <td class="action-cell">


                                <!-- EDIT -->

                                <a href="${pageContext.request.contextPath}/product/edit/${product.productId}"
                                   class="btn btn-primary">

                                    Edit

                                </a>


                                <!-- DELETE -->

                                <form
                                        method="post"
                                        action="${pageContext.request.contextPath}/admin/products/delete/${product.productId}"
                                        style="display:inline;"
                                        onsubmit="return confirm('Are you sure you want to delete this product?');">

                                    <button
                                            type="submit"
                                            class="btn btn-danger">

                                        Delete

                                    </button>

                                </form>


                            </td>

                        </tr>

                    </c:forEach>

                </c:when>


                <c:otherwise>

                    <tr>

                        <td colspan="8">

                            No products found.

                        </td>

                    </tr>

                </c:otherwise>

            </c:choose>

        </tbody>

    </table>

</div>


<%@ include file="../common/footer.jsp" %>