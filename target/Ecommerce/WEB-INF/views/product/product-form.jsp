<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>


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


<%@ include file="common/header.jsp" %>


<div class="form-container">

    <h1>
        ${formTitle}
    </h1>


    <form
            action="${pageContext.request.contextPath}${formAction}"
            method="post">


        <!-- Product ID -->

        <div class="form-group">

            <label for="productId">
                Product ID
            </label>

            <input
                    type="text"
                    id="productId"
                    name="productId"
                    value="${product.productId}"
                    required
                    <c:if test="${not empty product.productId}">
                        readonly
                    </c:if>
            />

        </div>


        <!-- Product Name -->

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


        <!-- Brand -->

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


        <!-- Description -->

        <div class="form-group">

            <label for="productDescription">
                Description
            </label>

            <textarea
                    id="productDescription"
                    name="productDescription"
                    rows="5"
                    required>${product.productDescription}</textarea>

        </div>


        <!-- Price -->

        <div class="form-group">

            <label for="productPrice">
                Price
            </label>

            <input
                    type="number"
                    id="productPrice"
                    name="productPrice"
                    value="${product.productPrice}"
                    min="0"
                    step="0.01"
                    required
            />

        </div>


        <!-- Category -->

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
                            <c:if test="${category.categoryId == product.category.categoryId}">
                                selected
                            </c:if>
                    >

                        ${category.categoryName}

                    </option>

                </c:forEach>

            </select>

        </div>


        <!-- Seller -->

        <div class="form-group">

            <label for="sellerId">
                Seller
            </label>

            <select
                    id="sellerId"
                    name="seller.userId"
                    required>

                <option value="">
                    -- Select Seller --
                </option>


                <c:forEach
                        var="seller"
                        items="${sellers}">

                    <option
                            value="${seller.userId}"
                            <c:if test="${seller.userId == product.seller.userId}">
                                selected
                            </c:if>
                    >

                        ${seller.shopName}
                        -
                        ${seller.userName}

                    </option>

                </c:forEach>

            </select>

        </div>


        <!-- Status -->

        <div class="form-group">

            <label for="productStatus">
                Product Status
            </label>

            <select
                    id="productStatus"
                    name="productStatus"
                    required>

                <option value="">
                    -- Select Status --
                </option>


                <c:forEach
                        var="status"
                        items="${statuses}">

                    <option
                            value="${status}"
                            <c:if test="${status == product.productStatus}">
                                selected
                            </c:if>
                    >

                        ${status}

                    </option>

                </c:forEach>

            </select>

        </div>


        <!-- Buttons -->

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


<%@ include file="common/footer.jsp" %>