<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>


<c:choose>

    <c:when test="${empty category.categoryId}">

        <c:set
                var="pageTitle"
                value="Add Category"/>

        <c:set
                var="formTitle"
                value="Add Category"/>

        <c:set
                var="formAction"
                value="/category/add"/>

    </c:when>


    <c:otherwise>

        <c:set
                var="pageTitle"
                value="Edit Category"/>

        <c:set
                var="formTitle"
                value="Edit Category"/>

        <c:set
                var="formAction"
                value="/category/edit"/>

    </c:otherwise>

</c:choose>


<%@ include file="../common/header.jsp" %>


<div class="form-container">

    <h1>
        ${formTitle}
    </h1>


    <form
            action="${pageContext.request.contextPath}${formAction}"
            method="post">

<c:if test="${not empty category.categoryId}">

    <div class="form-group">

        <label>
            Category ID
        </label>

        <input type="text"
               value="${category.categoryId}"
               readonly>

    </div>

</c:if>

        <!-- ================================================= -->
        <!-- Category Name -->
        <!-- ================================================= -->

        <div class="form-group">

            <label for="categoryName">
                Category Name
            </label>


            <input
                    type="text"
                    id="categoryName"
                    name="categoryName"
                    value="${category.categoryName}"
                    required
                    minlength = "3"
                    maxlength="100"
            />

        </div>


        <!-- ================================================= -->
        <!-- Category Description -->
        <!-- ================================================= -->

        <div class="form-group">

            <label for="categoryDescription">
                Category Description
            </label>


            <textarea
                    id="categoryDescription"
                    name="categoryDescription"
                    rows="5"
                    minlength="7"
                    maxlength="500"
                    required>${category.categoryDescription}</textarea>

        </div>


        <!-- ================================================= -->
        <!-- Buttons -->
        <!-- ================================================= -->

        <button
                type="submit"
                class="btn add-btn">

            Save Category

        </button>


        <a
                href="${pageContext.request.contextPath}/category/list"
                class="btn back-btn">

            Cancel

        </a>

    </form>

</div>


<%@ include file="../common/footer.jsp" %>