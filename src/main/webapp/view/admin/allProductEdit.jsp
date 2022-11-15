<jsp:include page="adminHeader.jsp" />
<jsp:include page="../user/footer.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Home</title>
    <style>
        h3 {
            color: yellow;
            text-align: center;
        }
    </style>
</head>

<body>
<div style="color: black; text-align: center; font-size: 20px;">All Products & Edit Products</div>

<table>
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">Category</th>
        <th scope="col">Price</th>
        <th>Type</th>
        <th>Active</th>
        <th scope="col">Edit <i class='fas fa-pen-fancy'></i></th>
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
            <td><a href="editProduct?id=${product.id}">Edit <i class='fas fa-pen-fancy'></i></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<br>
<br>
<c:if test="${requestScope.positionList != null}">
    <div class="pagination" style="text-align: center;">
        <c:forEach var="position" items="${requestScope.positionList}">
            <a href="allProductEdit?page=${position}">${position}</a>
        </c:forEach>
    </div>
</c:if>
</body>

</html>