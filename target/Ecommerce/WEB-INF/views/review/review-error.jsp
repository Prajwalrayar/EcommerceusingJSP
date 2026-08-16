<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="pageTitle" value="Review Error"/>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../common/header.jsp" %>


<div class="card">

    <h1>Unable to Write Review</h1>


    <div class="error-message">

        ${error}

    </div>


    <br>


    <a
            href="${pageContext.request.contextPath}/customer/dashboard"
            class="btn">

        Back to Dashboard

    </a>

</div>


<%@ include file="../common/footer.jsp" %>