<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <title>Products</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">
    <style>
        <%@include file="/css/home-style.css"%>
    </style>

</head>

<body>
<%@ include file="adminHeader.jspf" %>
<div class="header-name">All Products & Edit Products</div>

<table>
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">Category</th>
        <th scope="col">Price</th>
        <th>Type</th>
        <th>Active</th>
        <th scope="col">View</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="product" items="${requestScope.productList}">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.category}</td>
            <td>${product.price}</td>
            <td>${product.type}</td>
            <td>${product.active}</td>
            <td><a href="adminView?id=${product.id}">View</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<br>
<br>
<c:if test="${requestScope.positionList != null}">
    <div class="pagination">
        <c:forEach var="position" items="${requestScope.positionList}">
            <a href="allProductEdit?page=${position}">${position}</a>
        </c:forEach>
    </div>
</c:if>
<jsp:include page="../user/footer.jsp" />

<script>
    if ("${sessionScope.alertFlag}" === "true") {
        swal("${sessionScope.alertHeader}", "${sessionScope.alertBody}", "${sessionScope.alertType}")
    }
</script>
</body>

</html>