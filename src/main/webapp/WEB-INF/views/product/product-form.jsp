<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>


<!-- ===================================================== -->
<!-- DETERMINE ADD OR EDIT -->
<!-- ===================================================== -->

<c:choose>

    <c:when test="${empty product.productId}">

        <c:set var="pageTitle"
               value="Add Product"/>

        <c:set var="formTitle"
               value="Add Product"/>

        <c:set var="formAction"
               value="/product/add"/>

    </c:when>


    <c:otherwise>

        <c:set var="pageTitle"
               value="Edit Product"/>

        <c:set var="formTitle"
               value="Edit Product"/>

        <c:set var="formAction"
               value="/product/edit"/>

    </c:otherwise>

</c:choose>


<%@ include file="../common/header.jsp" %>


<div class="form-container">

    <h1>
        ${formTitle}
    </h1>


    <!-- ===================================================== -->
    <!-- PRODUCT FORM -->
    <!-- ===================================================== -->

    <form
            action="${pageContext.request.contextPath}${formAction}"
            method="post">


        <!-- ================================================= -->
        <!-- PRODUCT ID -->
        <!-- ================================================= -->
        <!--
             Product ID is NOT shown to the user.

             For ADD:
             product.productId is empty, so nothing useful
             is submitted.

             For EDIT:
             existing productId is submitted as a hidden field
             so the controller knows which product to update.
        -->

        <c:if test="${not empty product.productId}">

            <input
                    type="hidden"
                    name="productId"
                    value="${product.productId}"
            />

        </c:if>


        <!-- ================================================= -->
        <!-- PRODUCT NAME -->
        <!-- ================================================= -->

        <div class="form-group">

            <label for="productName">
                Product Name
            </label>

            <input
                    type="text"
                    id="productName"
                    name="productName"
                    value="${product.productName}"
                    required
                    maxlength="150"
            />

        </div>


        <!-- ================================================= -->
        <!-- BRAND -->
        <!-- ================================================= -->

        <div class="form-group">

            <label for="brand">
                Brand
            </label>

            <input
                    type="text"
                    id="brand"
                    name="brand"
                    value="${product.brand}"
                    required
                    maxlength="100"
            />

        </div>


        <!-- ================================================= -->
        <!-- DESCRIPTION -->
        <!-- ================================================= -->

        <div class="form-group">

            <label for="productDescription">
                Description
            </label>

            <textarea
                    id="productDescription"
                    name="productDescription"
                    rows="5"
                    minlength="6"
                    required>${product.productDescription}</textarea>

        </div>


        <!-- ================================================= -->
        <!-- PRICE -->
        <!-- ================================================= -->

        <div class="form-group">

            <label for="productPrice">
                Price
            </label>

            <input
                    type="number"
                    id="productPrice"
                    name="productPrice"
                    value="${product.productPrice}"
                    min="100"
                    step="1"
                    required
            />

        </div>


        <!-- ================================================= -->
        <!-- CATEGORY -->
        <!-- ================================================= -->

        <div class="form-group">

            <label for="categoryId">
                Category
            </label>

            <select
                    id="categoryId"
                    name="category.categoryId"
                    required>

                <option value="">
                    -- Select Category --
                </option>


                <c:forEach
                        var="category"
                        items="${categories}">

                    <option
                            value="${category.categoryId}"
                            <c:if test="${not empty product.category
                                    and product.category.categoryId eq category.categoryId}">
                                selected
                            </c:if>>

                        ${category.categoryName}

                    </option>

                </c:forEach>

            </select>

        </div>


        <!-- ================================================= -->
        <!-- QUANTITY -->
        <!-- ================================================= -->

        <div class="form-group">

            <label for="initialStock">
                Quantity
            </label>

            <input
                    type="number"
                    id="initialStock"
                    name="initialStock"
                    value="${initialStock}"
                    min="1"
                    step="1"
                    required
            />

        </div>


        <!-- ================================================= -->
        <!-- BUTTONS -->
        <!-- ================================================= -->

        <button
                type="submit"
                class="btn add-btn">

            Save Product

        </button>


        <a
                href="${pageContext.request.contextPath}/product/list"
                class="btn back-btn">

            Cancel

        </a>

    </form>

</div>


<%@ include file="../common/footer.jsp" %>