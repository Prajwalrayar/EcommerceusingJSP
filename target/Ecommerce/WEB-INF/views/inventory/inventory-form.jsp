<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>


<c:choose>

    <c:when test="${empty inventory.inventoryId}">

        <c:set var="pageTitle"
               value="Add Inventory"/>

        <c:set var="formTitle"
               value="Add Inventory"/>

        <c:set var="formAction"
               value="/inventory/add"/>

    </c:when>


    <c:otherwise>

        <c:set var="pageTitle"
               value="Edit Inventory"/>

        <c:set var="formTitle"
               value="Edit Inventory"/>

        <c:set var="formAction"
               value="/inventory/edit"/>

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


        <!-- ================================================= -->
        <!-- Inventory ID -->
        <!-- ================================================= -->

        <div class="form-group">

            <label for="inventoryId">
                Inventory ID
            </label>

            <input
                    type="text"
                    id="inventoryId"
                    name="inventoryId"
                    value="${inventory.inventoryId}"
                    required

                    <c:if test="${not empty inventory.inventoryId}">
                        readonly
                    </c:if>
            />

        </div>


        <!-- ================================================= -->
        <!-- Product -->
        <!-- ================================================= -->

        <div class="form-group">

            <label for="productId">
                Product
            </label>

            <select
                    id="productId"
                    name="product.productId"
                    required>

                <option value="">
                    -- Select Product --
                </option>


                <c:forEach
                        var="product"
                        items="${products}">

                    <option
                            value="${product.productId}"

                            <c:if test="${product.productId == inventory.product.productId}">
                                selected
                            </c:if>
                    >

                        ${product.productName}
                        -
                        ${product.brand}

                    </option>

                </c:forEach>

            </select>

        </div>


        <!-- ================================================= -->
        <!-- Quantity -->
        <!-- ================================================= -->

        <div class="form-group">

            <label for="quantity">
                Quantity
            </label>

            <input
                    type="number"
                    id="quantity"
                    name="quantity"
                    value="${inventory.quantity}"
                    min="0"
                    required
            />

        </div>


        <!-- ================================================= -->
        <!-- Buttons -->
        <!-- ================================================= -->

        <button
                type="submit"
                class="btn add-btn">

            Save Inventory

        </button>


        <a
                href="${pageContext.request.contextPath}/inventory/list"
                class="btn back-btn">

            Cancel

        </a>

    </form>

</div>


<%@ include file="common/footer.jsp" %>