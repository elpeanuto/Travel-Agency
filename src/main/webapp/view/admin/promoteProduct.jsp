<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <title>Promote user</title>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">
    <style>
        <%@include file="/css/home-style.css"%>
    </style>


</head>

<body>
<jsp:include page="adminHeader.jspf" />
<div class="header-name">Promote product</div>

<table>
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">Category</th>
        <th scope="col">Active</th>
        <th scope="col">Price</th>
        <th>Type</th>
        <th scope="col">Edit <i class='fas fa-pen-fancy'></i></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="product" items="${requestScope.productList}">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.category}</td>
            <td>${product.active}</td>
            <td>${product.price}</td>
            <td>${product.type}</td>
            <form action="promoteProduct?id=${product.id}" method="post">
                <td>
                    <select class="input-style" name="status">

                        <option <c:if test="${product.type == 'Ordinary'}">
                            selected
                        </c:if>
                                value="Ordinary">Ordinary
                        </option>
                        <option <c:if test="${product.type == 'Hot'}">
                            selected
                        </c:if>
                                value="Hot">Hot
                        </option>
                    </select>
                    <button class="button">Save<i class='far fa-arrow-alt-circle-right'></i></button>

                </td>
            </form>
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
            <a href="promoteProduct?page=${position}">${position}</a>
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