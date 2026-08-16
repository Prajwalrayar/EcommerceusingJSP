<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Category Management"/>

<%@ include file="common/header.jsp" %>


<div class="card">

    <h1>Category Management</h1>


    <!-- ===================================================== -->
    <!-- Navigation -->
    <!-- ===================================================== -->

    <div class="nav">

        <a
                href="${pageContext.request.contextPath}/admin/customers">

            Customers

        </a>


        <a
                href="${pageContext.request.contextPath}/admin/sellers">

            Sellers

        </a>


        <a
                href="${pageContext.request.contextPath}/address/list">

            Addresses

        </a>


        <a
                href="${pageContext.request.contextPath}/category/list">

            Categories

        </a>


        <a
                href="${pageContext.request.contextPath}/product/list">

            Products

        </a>

    </div>


    <!-- ===================================================== -->
    <!-- Add Category -->
    <!-- ===================================================== -->

    <div style="margin-bottom: 20px;">

        <a
                href="${pageContext.request.contextPath}/category/add"
                class="btn add-btn">

            Add Category

        </a>

    </div>


    <!-- ===================================================== -->
    <!-- Category Table -->
    <!-- ===================================================== -->

    <table>

        <thead>

        <tr>

            <th>Category ID</th>

            <th>Category Name</th>

            <th>Description</th>

            <th>Actions</th>

        </tr>

        </thead>


        <tbody>

        <c:forEach
                var="category"
                items="${categories}">

            <tr>

                <td>
                    ${category.categoryId}
                </td>


                <td>
                    ${category.categoryName}
                </td>


                <td>
                    ${category.categoryDescription}
                </td>


                <td>

                    <!-- Edit -->

                    <a
                            href="${pageContext.request.contextPath}/category/edit/${category.categoryId}"
                            class="btn edit-btn">

                        Edit

                    </a>


                    <!-- Delete -->

                    <form
                            action="${pageContext.request.contextPath}/category/delete/${category.categoryId}"
                            method="post"
                            style="display:inline;"
                            onsubmit="return confirm('Are you sure you want to delete this category?');">

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


    <!-- ===================================================== -->
    <!-- Empty Message -->
    <!-- ===================================================== -->

    <c:if test="${empty categories}">

        <div class="empty-message">

            No categories found.

        </div>

    </c:if>

</div>


<%@ include file="common/footer.jsp" %>